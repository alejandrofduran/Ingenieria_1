package model;

import static utils.Utils.checkArgument;

import model.Clock.CreditCard;

public class Cashier {

  public static final String CARRITO_VACIO_ERR = "Carrito vacio, no se puede hacer checkout";
  public static final String TARJETA_INVALIDA = "Tarjeta invalida";

  public void checkOut(Cart cart, CreditCard creditCard) {
    checkArgument(!cart.listBookIsbn.isEmpty(), CARRITO_VACIO_ERR);
    checkArgument(creditCard.isValid(), TARJETA_INVALIDA);
  }

}
