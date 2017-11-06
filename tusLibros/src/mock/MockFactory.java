package mock;

import model.Client;

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
}
