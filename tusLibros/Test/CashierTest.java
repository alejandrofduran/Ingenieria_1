import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import ModelImpl.CartImpl;
import ModelImpl.CashierImpl;
import ModelImpl.CreditCardImpl;
import exceptions.InvalidArgumentException;
import java.util.HashSet;
import java.util.Set;
import mock.MockFactory;
import model.CreditCard;
import org.junit.Before;
import org.junit.Test;

public class CashierTest {

  private CashierImpl cashier;
  private CartImpl cart;
  private Set<String> catalog;
  private String laBiblia = "La biblia";
  private String elAnticristo = "El anticristo";
  private CreditCard creditCard;
  private MockFactory mockFactory = new MockFactory();

  @Before
  public void setUp() {
    cashier = new CashierImpl();
    catalog = new HashSet<String>();
    catalog.add(laBiblia);
    catalog.add(elAnticristo);
    creditCard = new CreditCardImpl(123, "012019", "John Doe");
    cart = new CartImpl(1l, catalog);
  }

  @Test
  public void testCreateCashier() {
    CashierImpl cashier = new CashierImpl();
  }

  @Test
  public void testCheckOutWithEmptyCart() {
    CartImpl emtpycart = new CartImpl(1L, new HashSet<>());
    CashierImpl cashier = new CashierImpl();
    try {
      cashier.checkOut(emtpycart, creditCard);
      fail();
    } catch (InvalidArgumentException ex) {
      assertThat(ex.getMessage(), is(CashierImpl.CARRITO_VACIO_ERR));
    }
  }

  @Test
  public void testCheckOutWithNotEmptyCart() {
    cart.add(laBiblia, 666);
    cashier.checkOut(cart, creditCard);
  }

  @Test
  public void testCheckOutWithInvalidCreditCard() {
    cart.add(laBiblia, 666);
    try {
      cashier.checkOut(cart, mockFactory.newInvalidCreditCard());
      fail();
    } catch (InvalidArgumentException ex) {
      assertThat(ex.getMessage(), is(CashierImpl.TARJETA_INVALIDA));
    }
  }

  @Test
  public void testCheckOutWithValidCreditCard() {
    cart.add(laBiblia, 666);
    cashier.checkOut(cart, mockFactory.newValidCreditCard());
  }
}
