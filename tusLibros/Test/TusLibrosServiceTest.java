import static mock.MockFactory.cartWithNoBooks;
import static mock.MockFactory.validCahier;
import static mock.MockFactory.validCartService;
import static mock.MockFactory.validClientService;
import static mock.MockFactory.validCreditCardService;
import static mock.MockFactory.validPurchaseService;
import static mock.MockFactory.validTimeService;
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

public class TusLibrosServiceTest {

  private TusLibrosService tusLibrosService;

  @Before
  public void setup() {
    tusLibrosService = new TusLibrosServiceImpl(validCahier(),
        validClientService(), validCartService(),
        validTimeService(), validPurchaseService(),
        validCreditCardService());
  }

  @Test
  public void testCreateCartWithMocks() {
    Long id = tusLibrosService.createCart(1L, "password");
    assertThat(id, is(cartWithNoBooks().id()));
  }

  @Test
  public void testAddToCart() {
    tusLibrosService.addToCart(1L, "book 1", 10);
  }

  @Test
  public void testListCart() {
    Map<String, Integer> list = tusLibrosService.listCart(1l);
    assertThat(list, is(cartWithNoBooks().itemsList()));
  }

  @Test
  public void testListPurchases() {
    Set<Sale> sales = tusLibrosService.listPurchases(1l, "password");
    assertTrue(sales.isEmpty());
  }

  @Test
  public void testCheckOut() {
    tusLibrosService.checkOut(1L, "12341234122341234", "012050", "John Doe");
  }

  @Test
  public void testIntegration() {
  }

}
