import model.CreditCard;
import org.junit.Before;
import org.junit.Test;
import processor.MerchantProcessor;

public class MerchantProcessorTest {

  private MerchantProcessor merchantProcessor;

  @Before
  public void construct() {
    merchantProcessor = new MerchantProcessor();
  }

  @Test
  public void testA() {
    merchantProcessor.validarTarjeta(new CreditCard());
  }

}
