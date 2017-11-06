package modelImpl;

import model.Cart;
import model.Client;
import model.Sale;

public class SaleImpl implements Sale {

  private double amount;
  private Cart cart;
  private Long id;

  public SaleImpl(Cart cart, double amount, Long id) {
    this.cart = cart;
    this.amount = amount;
    this.id = id;
  }

  @Override
  public Long id() {
    return id;
  }

  @Override
  public double amount() {
    return amount;
  }

  @Override
  public Client client() {
    return cart.client();
  }
}
