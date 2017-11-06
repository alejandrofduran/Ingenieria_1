import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import exceptions.InvalidArgumentException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import model.Cart;
import model.Client;
import modelImpl.ClientImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import service.CartService;
import serviceImpl.CartServiceImpl;

public class CartServiceTest {

  private final static String ISBN_EN_CATALGO_UNO = "ISBN 1";
  private final static String ISBN_EN_CATALGO_DOS = "ISBN 2";
  @Rule
  public ExpectedException expectedException = ExpectedException.none();
  private CartService cartService;
  private Set<String> catalogueIsbn;
  private Client client;

  @Before
  public void setup() {
    catalogueIsbn = new HashSet<>();
    catalogueIsbn.add(ISBN_EN_CATALGO_UNO);
    catalogueIsbn.add(ISBN_EN_CATALGO_DOS);
    client = new ClientImpl("john doe", "123456", 1L);
    cartService = new CartServiceImpl(1L, catalogueIsbn);
  }

  @Test
  public void testCreateWithEmptyCatalogue() {
    expectedException.expect(InvalidArgumentException.class);
    expectedException.expectMessage(CartServiceImpl.CATALOGO_VACIO);
    CartService service = new CartServiceImpl(1L, new HashSet<String>());
  }

  @Test
  public void testCreateWithNullCatalogue() {
    expectedException.expect(InvalidArgumentException.class);
    expectedException.expectMessage(CartServiceImpl.CATALOGO_INVALIDO);
    CartService service = new CartServiceImpl(1L, null);
  }

  @Test
  public void testCreateWithNullFirstID() {
    expectedException.expect(InvalidArgumentException.class);
    expectedException.expectMessage(CartServiceImpl.FIRST_ID_INVALIDO);
    CartService service = new CartServiceImpl(null, catalogueIsbn);
  }

  @Test
  public void testCreateCart() {
    Date date = new Date();
    Cart cart = cartService.createCart(client, date);
    assertThat(cart.client(), is(client));
    assertThat(cart.id(), is(1L));
    assertTrue(cart.itemsList().isEmpty());
    assertThat(cart.lastOperation(), is(date));
  }

  @Test
  public void testCreateCartNextId() {
    Date date = new Date();
    Cart cart = cartService.createCart(client, date);
    Cart cart2 = cartService.createCart(client, date);
    assertThat(cart.id(), is(cart2.id() - 1));
  }

  @Test
  public void testFindCart() {
    Cart cart = cartService.createCart(client, new Date());
    Cart find = cartService.findCart(cart.id());
    assertThat(cart, is(find));
  }

  @Test
  public void testFindCartNotFound() {
    expectedException.expect(InvalidArgumentException.class);
    expectedException.expectMessage(CartServiceImpl.CARRITO_NO_ENCONTRADO);
    cartService.findCart(100L);
  }

  @Test
  public void testAddToCart() {
    Cart cart = cartService.createCart(client, new Date());
    cartService.addToCart(cart.id(), ISBN_EN_CATALGO_UNO, 10, new Date());
    Cart find = cartService.findCart(cart.id());
    assertTrue(find.itemsList().get(ISBN_EN_CATALGO_UNO) == 10);
    cartService.addToCart(cart.id(), ISBN_EN_CATALGO_UNO, 20, new Date());
    find = cartService.findCart(cart.id());
    assertTrue(find.itemsList().get(ISBN_EN_CATALGO_UNO) == 30);
  }

}
