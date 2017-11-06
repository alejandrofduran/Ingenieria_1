/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import model.Cart;

/**
 * @author Cronos
 */
public class CartImpl implements Cart {

  public static final String MSG_ERROR_LIBRO_INVALIDO = "El libro indicado no es válido.";
  public static final String MSG_ERROR_CANTIDAD = "La cantidad debe de ser mayor o igual a 1.";

  Long cartId = null;
  Set<String> catalogueIsbn;
  private Map<String, Integer> listBookIsbn;
  private Date creation;
  private Date lastPurchase;

  public CartImpl(Long cartId, Set<String> catalogueIsbn) {
    this.cartId = cartId;
    this.catalogueIsbn = catalogueIsbn;
    this.listBookIsbn = new HashMap<String, Integer>();
  }

  public Long id() {
    return this.cartId;
  }

  public void add(String isbn, Integer quantity) {

    if (!catalogueIsbn.contains(isbn)) {
      throw new RuntimeException(MSG_ERROR_LIBRO_INVALIDO);
    }
    if (quantity <= 0) {
      throw new RuntimeException(MSG_ERROR_CANTIDAD);
    }

    listBookIsbn.putIfAbsent(isbn, 0);
    listBookIsbn.replace(isbn, listBookIsbn.get(isbn) + quantity);
  }

  public Map<String, Integer> itemsList() {
    return listBookIsbn;
  }

}

