package model;


import ModelImpl.CartImpl;

public interface Cashier {

  public void checkOut(CartImpl cart, CreditCard creditCard);

}
