package model;

import model.Clock.CreditCard;

public class CreditCardImpl implements CreditCard {

  private Integer number;

  private Integer expirationDate;

  private String owner;

  public CreditCardImpl(Integer number, Integer expirationDate, String owner) {
    this.number = number;
    this.expirationDate = expirationDate;
    this.owner = owner;
  }

  public boolean isValid() {
    return true;
  }

  @Override
  public String owner() {
    return owner;
  }

  @Override
  public Integer creditCardExpiration() {
    return expirationDate;
  }

  @Override
  public Integer number() {
    return number;
  }
}
