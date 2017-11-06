package modelImpl;


import model.CreditCard;

public class CreditCardImpl implements CreditCard {

  private Long number;

  private String expirationDate;

  private String owner;

  public CreditCardImpl(Long number, String expirationDate, String owner) {
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
  public Long number() {
    return number;
  }
}
