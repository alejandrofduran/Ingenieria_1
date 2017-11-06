package model;


import ModelImpl.CartImpl;

public interface Cashier {

  public void checkOut(Cart cart, CreditCard creditCard);

}
