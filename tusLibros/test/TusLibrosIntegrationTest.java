import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import mock.MockFactory;
import model.Cashier;
import model.Client;
import model.Sale;
import modelImpl.CashierImpl;
import modelImpl.ClientImpl;
import org.junit.Before;
import org.junit.Test;
import service.CartService;
import service.ClientService;
import service.CreditCardService;
import service.PurchaseService;
import service.TimeService;
import service.TusLibrosService;
import serviceImpl.CartServiceImpl;
import serviceImpl.ClientServiceImpl;
import serviceImpl.CreditCardServiceImpl;
import serviceImpl.PurchaseServiceImpl;
import serviceImpl.TimeServiceImpl;
import serviceImpl.TusLibrosServiceImpl;

public class TusLibrosIntegrationTest {

  private TusLibrosService tusLibrosService;
  private Set<String> bookCatalogue;
  private Set<Client> clients;
  private Map<String, Integer> prices;
  private Client client1;
  private Client client2;
  private String book1 = "Book 1";
  private String book2 = "Book 2";

  @Before
  public void setup() {
    client1 = new ClientImpl("John doe", "Password", 1L);
    client2 = new ClientImpl("John doe", "Password", 2L);
    clients = new HashSet<>();
    clients.add(client1);
    clients.add(client2);
    ClientService clientService = new ClientServiceImpl(clients);
    bookCatalogue = new HashSet<>();
    bookCatalogue.add(book1);
    bookCatalogue.add(book2);
    CartService cartService = new CartServiceImpl(1L, bookCatalogue);
    CreditCardService creditCardService = new CreditCardServiceImpl();
    prices = new HashMap<>();
    prices.put(book1, 10);
    prices.put(book2, 20);
    Cashier cashier = new CashierImpl(prices, MockFactory.validMerchatProcessor());
    PurchaseService purchaseService = new PurchaseServiceImpl(clients, 1L);
    TimeService timeService = new TimeServiceImpl(new Date());
    tusLibrosService = new TusLibrosServiceImpl(cashier, clientService,
        cartService, timeService, purchaseService, creditCardService);
  }

  @Test
  public void testIntegration() {
    Long cartid = tusLibrosService.createCart(client1.id(), client1.password());
    tusLibrosService.addToCart(cartid, book1, 10);
    Map<String, Integer> booksMap = tusLibrosService.listCart(cartid);
    assertTrue(booksMap.size() == 1);
    assertTrue(booksMap.get(book1) == 10);
    Long saleId = tusLibrosService.checkOut(cartid, "1234123412341234", "022020", "John Doe");
    Set<Sale> sales = tusLibrosService.listPurchases(client1.id(), client1.password());
    assertTrue(sales.size() == 1);
    Sale sale = sales.iterator().next();
    assertThat(sale.client(), is(client1));
    assertTrue(sale.amount() == 10 * prices.get(book1));
    assertThat(sale.id(), is(saleId));
  }

}
