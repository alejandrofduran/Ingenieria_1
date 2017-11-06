package serviceImpl;

import static utils.Utils.checkArgument;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import model.Cart;
import model.Client;
import modelImpl.CartImpl;
import service.CartService;

public class CartServiceImpl implements CartService {

  public static final String CATALOGO_VACIO = "Catalogo vacio";
  public static final String CATALOGO_INVALIDO = "Catalogo invalido";
  public static final String FIRST_ID_INVALIDO = "First id Invalido";
  public static final String CARRITO_NO_ENCONTRADO = "Carrito no encontrado";
  public static final String LIBRO_FUERA_DEL_CATALOGO = "Libro fuera del catalogo";
  private Long nextId;
  private Set<String> catalogueIsbn;
  private Map<Long, Cart> clientsCarts;

  public CartServiceImpl(Long firstId, Set<String> catalogueIsbn) {
    checkArgument(catalogueIsbn != null, CATALOGO_INVALIDO);
    checkArgument(!catalogueIsbn.isEmpty(), CATALOGO_VACIO);
    checkArgument(firstId != null, FIRST_ID_INVALIDO);
    this.catalogueIsbn = catalogueIsbn;
    this.nextId = firstId;
    this.clientsCarts = new HashMap<>();
  }

  @Override
  public Cart createCart(Client client, Date current) {
    Cart cart = new CartImpl(nextId, catalogueIsbn, current, client);
    clientsCarts.put(nextId, cart);
    nextId++;
    return cart;
  }

  @Override
  public void addToCart(Long cartId, String bookIsn, Integer bookQuantity, Date current) {
    Cart cart = findCart(cartId);
    cart.add(bookIsn, bookQuantity, current);
  }

  @Override
  public Cart findCart(Long cartId) {
    Cart cart = clientsCarts.get(cartId);
    checkArgument(cart != null, CARRITO_NO_ENCONTRADO);
    return cart;
  }

}
