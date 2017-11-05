package processor;

public interface MerchantProcessor {

  void debit(double price, Integer number, String expirationDate, String owner);
}
