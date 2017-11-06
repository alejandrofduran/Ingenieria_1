package service;

import model.Cart;
import model.Client;

public interface CartService {

  Cart createCart(Client client);
}
