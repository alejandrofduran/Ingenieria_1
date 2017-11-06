package processor;

public interface MerchantProcessor {

  void debit(double price, Long number, String expirationDate, String owner);
}
