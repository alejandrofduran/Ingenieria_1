/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

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
import org.junit.Test;

/**
 * @author Cronos
 */
public class CartTest {

  private final static Long CLIENT_ID_UNO = 1L;
  private final static String ISBN_EN_CATALGO_UNO = "ISBN 1";
  private final static String ISBN_EN_CATALGO_DOS = "ISBN 2";
  private final static String ISBN_QUE_NO_ESTA_EN_CATALGO = "";

  private Set<String> catalogueIsbn;
  private Client client;

  @Before
  public void setUp() {
    catalogueIsbn = new HashSet<String>();
    catalogueIsbn.add(ISBN_EN_CATALGO_UNO);
    catalogueIsbn.add(ISBN_EN_CATALGO_DOS);
    client = new ClientImpl("John Doe", "pass", 1l);
  }

  /**
   * Test of createCart method, of class CartImpl.
   */
  @Test
  public void testWhenTheCartHasIdAndIsEmpty() {
    Cart carrito = new CartImpl(CLIENT_ID_UNO, catalogueIsbn, new Date(), client);
    Map<String, Integer> libbros = carrito.itemsList();
    assertNotEquals(null, carrito.id());
    assertEquals(0, libbros.size());
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
    try {
      carrito.add(ISBN_QUE_NO_ESTA_EN_CATALGO, 3, new Date());
      fail();
    } catch (InvalidArgumentException invalidBook) {
      assertEquals(CartImpl.MSG_ERROR_LIBRO_INVALIDO, invalidBook.getMessage());
    }
  }

  @Test
  public void testAddInvalidQuantityForBook() {
    Cart carrito = new CartImpl(CLIENT_ID_UNO, catalogueIsbn, new Date(), client);
    try {
      carrito.add(ISBN_EN_CATALGO_UNO, 0, new Date());
      fail();
    } catch (InvalidArgumentException invalidQuantity) {
      assertEquals(CartImpl.MSG_ERROR_CANTIDAD, invalidQuantity.getMessage());
    }
  }

  @Test
  public void testWhenAddingAnInvalidBookDoNotLoseTheRestOfTheBooksInTheCart() {
    Cart carrito = new CartImpl(CLIENT_ID_UNO, catalogueIsbn, new Date(), client);
    try {
      carrito.add(ISBN_EN_CATALGO_UNO, 3, new Date());
      carrito.add(ISBN_EN_CATALGO_UNO, 5, new Date());
      carrito.add(ISBN_EN_CATALGO_DOS, 3, new Date());
      carrito.add(ISBN_QUE_NO_ESTA_EN_CATALGO, 3, new Date());
      fail();
    } catch (InvalidArgumentException invalidBook) {
      Map<String, Integer> libbros = carrito.itemsList();
      assertEquals(2, libbros.size());
      assertEquals(8, libbros.get(ISBN_EN_CATALGO_UNO).longValue());
      assertEquals(3, libbros.get(ISBN_EN_CATALGO_DOS).longValue());
      assertEquals(CartImpl.MSG_ERROR_LIBRO_INVALIDO, invalidBook.getMessage());
    }
  }

  @Test
  public void testWhenAddingAnInvalidQuantityForBookDoNotLoseTheRestOfTheBooksInTheCart() {
    Cart carrito = new CartImpl(CLIENT_ID_UNO, catalogueIsbn, new Date(), client);
    try {
      carrito.add(ISBN_EN_CATALGO_UNO, 3, new Date());
      carrito.add(ISBN_EN_CATALGO_UNO, 5, new Date());
      carrito.add(ISBN_EN_CATALGO_DOS, 3, new Date());
      carrito.add(ISBN_EN_CATALGO_UNO, -3, new Date());
      fail();
    } catch (InvalidArgumentException invalidQuantity) {
      Map<String, Integer> libbros = carrito.itemsList();
      assertEquals(2, libbros.size());
      assertEquals(8, libbros.get(ISBN_EN_CATALGO_UNO).intValue());
      assertEquals(3, libbros.get(ISBN_EN_CATALGO_DOS).intValue());
      assertEquals(CartImpl.MSG_ERROR_CANTIDAD, invalidQuantity.getMessage());
    }

  }

}
