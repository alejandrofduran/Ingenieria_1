package model;

public class Clock {

  public static interface CreditCard {

    boolean isValid();

    String owner();

    Integer creditCardExpiration();

    Integer number();

  }
}
