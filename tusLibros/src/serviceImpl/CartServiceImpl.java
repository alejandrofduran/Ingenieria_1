package serviceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import model.Cart;
import model.Client;
import modelImpl.CartImpl;
import service.CartService;

public class CartServiceImpl implements CartService {

  private Long nextId;

  private Set<String> catalogueIsbn;

  private Map<Long, Cart> carts;

  public CartServiceImpl(Long firstId, Set<String> catalogueIsbn) {
    this.catalogueIsbn = catalogueIsbn;
    this.nextId = firstId;
    this.carts = new HashMap<>();
  }

  @Override
  public Cart createCart(Client client) {
    Cart cart = new CartImpl(nextId, catalogueIsbn);
    nextId++;
    carts.put(client.id(), cart);
    return cart;
  }
}
