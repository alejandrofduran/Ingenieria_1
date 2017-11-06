/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelImpl;

import static utils.Utils.checkArgument;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import model.Cart;
import model.Client;

/**
 * @author Cronos
 */
public class CartImpl implements Cart {

  public static final String MSG_ERROR_LIBRO_INVALIDO = "El libro indicado no es válido.";
  public static final String MSG_ERROR_CANTIDAD = "La cantidad debe de ser mayor o igual a 1.";
  public static final String MSG_ERROR_CARRITO_EXPIRADO = "Carrito expirado";
  public static final String ID_INVALIDO = "Id invalido";
  public static final String CATALOGO_VACIO = "Catalogo vacio";
  public static final String CATALOGO_INVALIDO = "Catalogo invalido";
  public static final String FECHA_ACTUAL_INVALIDA = "Fecha actual invalida";
  public static final String CLIENTE_INVALIDO = "Cliente invalido";
  private static int halfAndHourMillis = 30 * 60 * 1000;
  Set<String> catalogueIsbn;
  private Long cartId = null;
  private Map<String, Integer> listBookIsbn;
  private Date lastOperation;
  private Client client;

  public CartImpl(Long cartId, Set<String> catalogueIsbn, Date current, Client client) {
    checkArgument(cartId != null, ID_INVALIDO);
    checkArgument(catalogueIsbn != null, CATALOGO_INVALIDO);
    checkArgument(!catalogueIsbn.isEmpty(), CATALOGO_VACIO);
    checkArgument(current != null, FECHA_ACTUAL_INVALIDA);
    checkArgument(client != null, CLIENTE_INVALIDO);
    this.cartId = cartId;
    this.catalogueIsbn = catalogueIsbn;
    this.listBookIsbn = new HashMap<String, Integer>();
    lastOperation = current;
    this.client = client;
  }

  public Long id() {
    return this.cartId;
  }

  public void add(String isbn, Integer quantity, Date current) {
    checkArgument(catalogueIsbn.contains(isbn), MSG_ERROR_LIBRO_INVALIDO);
    checkArgument(quantity > 0, MSG_ERROR_CANTIDAD);
    checkArgument(lastOperation().getTime() + halfAndHourMillis >= current.getTime(),
        MSG_ERROR_CARRITO_EXPIRADO);
    listBookIsbn.putIfAbsent(isbn, 0);
    listBookIsbn.replace(isbn, listBookIsbn.get(isbn) + quantity);
    lastOperation = current;
  }

  public Map<String, Integer> itemsList() {
    return listBookIsbn;
  }

  @Override
  public Date lastOperation() {
    return lastOperation;
  }

  @Override
  public Client client() {
    return client;
  }

}

