import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import exceptions.InvalidArgumentException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import mock.MockFactory;
import model.Client;
import model.CreditCard;
import modelImpl.CartImpl;
import modelImpl.CashierImpl;
import modelImpl.ClientImpl;
import modelImpl.CreditCardImpl;
import org.junit.Before;
import org.junit.Test;
import processor.MerchantProcessor;

public class CashierTest {

  private CashierImpl cashier;
  private CartImpl cart;
  private Set<String> catalog;
  private String laBiblia = "La biblia";
  private String elAnticristo = "El anticristo";
  private CreditCard creditCard;
  private MockFactory mockFactory = new MockFactory();
  private Client client;
  private MerchantProcessor merchantProcessor = MockFactory.validMerchatProcessor();

  @Before
  public void setUp() {
    cashier = new CashierImpl(new HashMap<>(), merchantProcessor);
    catalog = new HashSet<String>();
    catalog.add(laBiblia);
    catalog.add(elAnticristo);
    creditCard = new CreditCardImpl(123L, "012019", "John Doe");
    client = new ClientImpl("John Doe", "pass", 1l);
    cart = new CartImpl(1l, catalog, new Date(), client);
  }

  @Test
  public void testCreateCashier() {
    CashierImpl cashier = new CashierImpl(new HashMap<>(), merchantProcessor);
  }

  @Test
  public void testCheckOutWithEmptyCart() {
    CartImpl emtpycart = new CartImpl(1L, new HashSet<>(), new Date(), client);
    CashierImpl cashier = new CashierImpl(new HashMap<>(), merchantProcessor);
    try {
      cashier.checkOut(emtpycart, creditCard);
      fail();
    } catch (InvalidArgumentException ex) {
      assertThat(ex.getMessage(), is(CashierImpl.CARRITO_VACIO_ERR));
    }
  }

  @Test
  public void testCheckOutWithNotEmptyCart() {
    cart.add(laBiblia, 666, new Date());
    cashier.checkOut(cart, creditCard);
  }

  @Test
  public void testCheckOutWithInvalidCreditCard() {
//    cart.add(laBiblia, 666, new Date());
//    try {
//      cashier.checkOut(cart, mockFactory.newInvalidCreditCard());
//      fail();
//    } catch (InvalidArgumentException ex) {
//      assertThat(ex.getMessage(), is(CashierImpl.TARJETA_INVALIDA));
//    }
  }

  @Test
  public void testCheckOutWithValidCreditCard() {
//    cart.add(laBiblia, 666, new Date());
//    cashier.checkOut(cart, mockFactory.newValidCreditCard());
  }
}
