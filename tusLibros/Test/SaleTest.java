import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import exceptions.InvalidArgumentException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import model.Cart;
import model.Client;
import model.Sale;
import modelImpl.CartImpl;
import modelImpl.ClientImpl;
import modelImpl.SaleImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class SaleTest {


  private final static String ISBN_EN_CATALGO_UNO = "ISBN 1";
  private final static String ISBN_EN_CATALGO_DOS = "ISBN 2";
  @Rule
  public ExpectedException expectedException = ExpectedException.none();
  private Cart cart;
  private Client client;
  private Set<String> catalogueIsbn;

  @Before
  public void setup() {
    catalogueIsbn = new HashSet<String>();
    catalogueIsbn.add(ISBN_EN_CATALGO_UNO);
    catalogueIsbn.add(ISBN_EN_CATALGO_DOS);
    client = new ClientImpl("John Doe", "pass", 1l);
    cart = new CartImpl(1L, catalogueIsbn, new Date(), client);
    cart.add(ISBN_EN_CATALGO_UNO, 10, new Date());
  }

  @Test
  public void testCreateSale() {
    Long id = 1L;
    Double amount = 10d;
    Sale sale = new SaleImpl(cart, amount, id);
    assertEquals(sale.id(), id);
    assertTrue(sale.amount() == amount);
    assertEquals(sale.client(), cart.client());
  }

  @Test
  public void testCreateSaleWithNullCart() {
    expectedException.expect(InvalidArgumentException.class);
    expectedException.expectMessage(SaleImpl.CARRITO_INVALIDO);
    Sale sale = new SaleImpl(null, 10, 1L);
  }

  @Test
  public void testCreateSaleWithNegativeAmount() {
    expectedException.expect(InvalidArgumentException.class);
    expectedException.expectMessage(SaleImpl.AMOUNT_DEBE_SER_MAYOR_QUE_0);
    Sale sale = new SaleImpl(cart, -10, 1L);
  }

  @Test
  public void testCreateSaleWithNullId() {
    expectedException.expect(InvalidArgumentException.class);
    expectedException.expectMessage(SaleImpl.ID_INVALIDO);
    Sale sale = new SaleImpl(cart, 10, null);
  }

  @Test
  public void testCreateSaleWithEmptyCart() {
    expectedException.expect(InvalidArgumentException.class);
    expectedException.expectMessage(SaleImpl.CARRITO_VACIO);
    Cart emtpycart = new CartImpl(1L, catalogueIsbn, new Date(), client);
    Sale sale = new SaleImpl(emtpycart, 10, null);
  }

}
