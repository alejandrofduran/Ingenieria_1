package mock;

import model.Client;
import processor.MerchantProcessor;

public class MockFactory {


  public static Client clientWithNullId() {
    return new Client() {
      @Override
      public String name() {
        return null;
      }

      @Override
      public String password() {
        return null;
      }

      @Override
      public Long id() {
        return null;
      }
    };
  }

  public static MerchantProcessor validMerchatProcessor() {
    return new MerchantProcessor() {
      @Override
      public void debit(double price, Long number, String expirationDate, String owner) {

      }
    };
  }
}
