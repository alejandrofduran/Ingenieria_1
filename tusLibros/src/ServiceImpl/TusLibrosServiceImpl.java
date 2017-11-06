package serviceImpl;

import java.util.Map;
import java.util.Set;
import model.Cart;
import model.Cashier;
import model.Client;
import model.Sale;
import modelImpl.CashierImpl;
import service.CartService;
import service.ClientService;
import service.TusLibrosService;

public class TusLibrosServiceImpl implements TusLibrosService {

  private Cashier cashier = new CashierImpl();

  private ClientService clientService = new ClientServiceImpl();

  private CartService cartService = new CartServiceImpl();

  @Override
  public Long createCart(Long clientId, String password) {
    Client client = clientService.findClient(clientId, password);
    Cart cart = cartService.createCart(client);
    return cart.id();
  }

  @Override
  public void addToCart(Long cartId, String bookIsn, Integer bookQuantity) {

  }

  @Override
  public Map<String, Integer> listCart(Long id) {
    return null;
  }

  @Override
  public Set<Sale> listPurchases(Long id, String password) {
    return null;
  }
}
