package ModelImpl;

import model.Client;
import model.Sale;

public class SaleImpl implements Sale {

  private double amount;
  private Client client;

  @Override
  public double amount() {
    return amount;
  }

  @Override
  public Client client() {
    return client;
  }
}
