package service;

import java.util.Date;
import model.Cart;
import model.Client;

public interface CartService {

  Cart createCart(Client client, Date current);

  void addToCart(Long cartId, String bookIsn, Integer bookQuantity, Date current);

  Cart findCart(Long cartId);
}
