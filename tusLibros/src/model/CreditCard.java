package model;

public interface CreditCard {

  boolean isValid();

  String owner();

  String creditCardExpiration();

  Integer number();


}
