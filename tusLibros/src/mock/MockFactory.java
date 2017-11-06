package mock;

import model.CreditCard;

public class MockFactory {

  public CreditCard newValidCreditCard() {
    return new CreditCard() {
      @Override
      public boolean isValid() {
        return true;
      }

      @Override
      public String owner() {
        return null;
      }

      @Override
      public String creditCardExpiration() {
        return null;
      }

      @Override
      public Integer number() {
        return null;
      }
    };
  }

  public CreditCard newInvalidCreditCard() {
    return new CreditCard() {
      @Override
      public boolean isValid() {
        return false;
      }

      @Override
      public String owner() {
        return null;
      }

      @Override
      public String creditCardExpiration() {
        return null;
      }

      @Override
      public Integer number() {
        return null;
      }
    };
  }
}
