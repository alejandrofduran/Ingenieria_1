package modelImpl;


import model.CreditCard;

public class CreditCardImpl implements CreditCard {

  private Integer number;

  private String expirationDate;

  private String owner;

  public CreditCardImpl(Integer number, String expirationDate, String owner) {
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
  public String creditCardExpiration() {
    return expirationDate;
  }

  @Override
  public Integer number() {
    return number;
  }
}
