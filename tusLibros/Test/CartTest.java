/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import exceptions.InvalidArgumentException;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import model.Cart;
import model.Client;
import modelImpl.CartImpl;
import modelImpl.ClientImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author Cronos
 */
public class CartTest {

  private final static Long CLIENT_ID_UNO = 1L;
  private final static String ISBN_EN_CATALGO_UNO = "ISBN 1";
  private final static String ISBN_EN_CATALGO_DOS = "ISBN 2";
  private final static String ISBN_QUE_NO_ESTA_EN_CATALGO = "";
  @Rule
  public ExpectedException expectedException = ExpectedException.none();
  private Set<String> catalogueIsbn;
  private Client client;

  @Before
  public void setUp() {
    catalogueIsbn = new HashSet<String>();
    catalogueIsbn.add(ISBN_EN_CATALGO_UNO);
    catalogueIsbn.add(ISBN_EN_CATALGO_DOS);
    client = new ClientImpl("John Doe", "pass", 1l);
  }


  @Test
  public void testCreateCart() {
    Date date = new Date();
    Cart cart = new CartImpl(1L, catalogueIsbn, date, client);
    assertTrue(cart.itemsList().isEmpty());
    assertThat(cart.client(), is(client));
    assertThat(cart.id(), is(1L));
    assertThat(cart.lastOperation(), is(date));
  }

  @Test
  public void testCreateCartWithNullId() {
    expectedException.expect(InvalidArgumentException.class);
    expectedException.expectMessage(CartImpl.ID_INVALIDO);
    Cart cart = new CartImpl(null, catalogueIsbn, new Date(), client);
  }

  @Test
  public void testCreateCartWithNullCatalogue() {
    expectedException.expect(InvalidArgumentException.class);
    expectedException.expectMessage(CartImpl.CATALOGO_INVALIDO);
    Cart cart = new CartImpl(1L, null, new Date(), client);
  }

  @Test
  public void testCreateCartWithEmptyCatalogue() {
    expectedException.expect(InvalidArgumentException.class);
    expectedException.expectMessage(CartImpl.CATALOGO_VACIO);
    Cart cart = new CartImpl(1L, new HashSet<>(), new Date(), client);
  }

  @Test
  public void testCreateCartWithNullDate() {
    expectedException.expect(InvalidArgumentException.class);
    expectedException.expectMessage(CartImpl.FECHA_ACTUAL_INVALIDA);
    Cart cart = new CartImpl(1L, catalogueIsbn, null, client);
  }

  @Test
  public void testCreateCartWithNullClient() {
    expectedException.expect(InvalidArgumentException.class);
    expectedException.expectMessage(CartImpl.CLIENTE_INVALIDO);
    Cart cart = new CartImpl(1L, catalogueIsbn, new Date(), null);
  }

  @Test
  public void testAddCatalogBooks() {
    Cart carrito = new CartImpl(CLIENT_ID_UNO, catalogueIsbn, new Date(), client);
    carrito.add(ISBN_EN_CATALGO_UNO, 3, new Date());
    carrito.add(ISBN_EN_CATALGO_UNO, 5, new Date());
    carrito.add(ISBN_EN_CATALGO_DOS, 3, new Date());
    Map<String, Integer> libbros = carrito.itemsList();

    assertEquals(2, libbros.size());
    assertEquals(8, libbros.get(ISBN_EN_CATALGO_UNO).longValue());
    assertEquals(3, libbros.get(ISBN_EN_CATALGO_DOS).longValue());
  }

  @Test
  public void testAddNotCatalogBook() {
    Cart carrito = new CartImpl(CLIENT_ID_UNO, catalogueIsbn, new Date(), client);
    expectedException.expect(InvalidArgumentException.class);
    expectedException.expectMessage(CartImpl.MSG_ERROR_LIBRO_INVALIDO);
    carrito.add(ISBN_QUE_NO_ESTA_EN_CATALGO, 3, new Date());
  }

  @Test
  public void testAddInvalidQuantityForBook() {
    Date date = new Date();
    Cart carrito = new CartImpl(CLIENT_ID_UNO, catalogueIsbn, date, client);
    expectedException.expect(InvalidArgumentException.class);
    expectedException.expectMessage(CartImpl.MSG_ERROR_CANTIDAD);
    carrito.add(ISBN_EN_CATALGO_UNO, 0, new Date());
    assertEquals(date, carrito.lastOperation());
  }

  @Test
  public void testWhenAddingAnInvalidBookDoNotLoseTheRestOfTheBooksInTheCart() {
    Cart carrito = new CartImpl(CLIENT_ID_UNO, catalogueIsbn, new Date(), client);
    Date date = new Date();
    carrito.add(ISBN_EN_CATALGO_UNO, 3, date);
    carrito.add(ISBN_EN_CATALGO_UNO, 5, date);
    carrito.add(ISBN_EN_CATALGO_DOS, 3, date);
    expectedException.expect(InvalidArgumentException.class);
    expectedException.expectMessage(CartImpl.MSG_ERROR_LIBRO_INVALIDO);
    carrito.add(ISBN_QUE_NO_ESTA_EN_CATALGO, 3, new Date());
    Map<String, Integer> libbros = carrito.itemsList();
    assertEquals(2, libbros.size());
    assertEquals(8, libbros.get(ISBN_EN_CATALGO_UNO).longValue());
    assertEquals(3, libbros.get(ISBN_EN_CATALGO_DOS).longValue());
    assertEquals(date, carrito.lastOperation());
  }

  @Test
  public void testWhenAddingAnInvalidQuantityForBookDoNotLoseTheRestOfTheBooksInTheCart() {
    Cart carrito = new CartImpl(CLIENT_ID_UNO, catalogueIsbn, new Date(), client);
    Date date = new Date();
    carrito.add(ISBN_EN_CATALGO_UNO, 3, date);
    carrito.add(ISBN_EN_CATALGO_UNO, 5, date);
    carrito.add(ISBN_EN_CATALGO_DOS, 3, date);
    expectedException.expect(InvalidArgumentException.class);
    expectedException.expectMessage(CartImpl.MSG_ERROR_CANTIDAD);
    carrito.add(ISBN_EN_CATALGO_UNO, -3, new Date());
    Map<String, Integer> libbros = carrito.itemsList();
    assertEquals(2, libbros.size());
    assertEquals(8, libbros.get(ISBN_EN_CATALGO_UNO).intValue());
    assertEquals(3, libbros.get(ISBN_EN_CATALGO_DOS).intValue());
    assertEquals(date, carrito.lastOperation());
  }

}
