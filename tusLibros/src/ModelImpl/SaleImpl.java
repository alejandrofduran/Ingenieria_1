package modelImpl;

import static utils.Utils.checkArgument;

import model.Cart;
import model.Client;
import model.Sale;

public class SaleImpl implements Sale {

  public static final String CARRITO_INVALIDO = "Carrito invalido";
  public static final String ID_INVALIDO = "Id invalido";
  public static final String AMOUNT_DEBE_SER_MAYOR_QUE_0 = "Amount debe ser mayor que 0";
  public static final String CARRITO_VACIO = "Carrito vacio";
  private double amount;
  private Cart cart;
  private Long id;

  public SaleImpl(Cart cart, double amount, Long id) {
    checkArgument(cart != null, CARRITO_INVALIDO);
    checkArgument(!cart.itemsList().isEmpty(), CARRITO_VACIO);
    checkArgument(amount > 0, AMOUNT_DEBE_SER_MAYOR_QUE_0);
    checkArgument(id != null, ID_INVALIDO);
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
