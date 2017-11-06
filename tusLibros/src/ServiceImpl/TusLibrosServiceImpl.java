package serviceImpl;

import java.util.Map;
import java.util.Set;
import model.Cart;
import model.Cashier;
import model.Client;
import model.CreditCard;
import model.Sale;
import service.CartService;
import service.ClientService;
import service.CreditCardService;
import service.PurchaseService;
import service.TimeService;
import service.TusLibrosService;

public class TusLibrosServiceImpl implements TusLibrosService {

  private Cashier cashier;

  private ClientService clientService;

  private CartService cartService;

  private TimeService timeService;

  private PurchaseService purchaseService;

  private CreditCardService creditCardService;

  public TusLibrosServiceImpl(Cashier cashier, ClientService clientService,
      CartService cartService, TimeService timeService, PurchaseService purchaseService,
      CreditCardService creditCardService) {
    this.cashier = cashier;
    this.clientService = clientService;
    this.cartService = cartService;
    this.timeService = timeService;
    this.purchaseService = purchaseService;
    this.creditCardService = creditCardService;
  }

  @Override
  public Long createCart(Long clientId, String password) {
    Client client = clientService.findClient(clientId, password);
    Cart cart = cartService.createCart(client, timeService.getCurrent());
    return cart.id();
  }

  @Override
  public void addToCart(Long cartId, String bookIsn, Integer bookQuantity) {
    cartService.addToCart(cartId, bookIsn, bookQuantity, timeService.getCurrent());
  }

  @Override
  public Map<String, Integer> listCart(Long cartId) {
    Cart cart = cartService.findCart(cartId);
    return cart.itemsList();
  }

  @Override
  public Set<Sale> listPurchases(Long clientId, String password) {
    Client client = clientService.findClient(clientId, password);
    return purchaseService.findSales(client);
  }

  @Override
  public Long checkOut(Long cartId, String ccn, String cced, String cco) {
    Cart cart = cartService.findCart(cartId);
    CreditCard creditCard = creditCardService
        .verifyCrediteCard(ccn, cced, cco, timeService.getCurrent());
    double amount = cashier.checkOut(cart, creditCard);
    return purchaseService.addSale(cart, amount);
  }
}
