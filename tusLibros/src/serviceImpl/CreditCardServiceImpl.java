package serviceImpl;

import static utils.Utils.checkArgument;

import exceptions.InvalidArgumentException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.CreditCard;
import modelImpl.CreditCardImpl;
import service.CreditCardService;

public class CreditCardServiceImpl implements CreditCardService {

  public static final String FORMATO_DE_FECHA_INVALIDO = "Formato de fecha invalido";
  public static final String NUMERO_DE_TARJETA_INVALIDO = "Numero de tarjeta invalido";
  public static final String FORMATO_DE_OWNER_INVALIDO = "Formato de owner invalido";
  public static final String TARJETA_EXPIRADA = "Tarjeta expirada";

  private static String number_regex = "\\d+";

  @Override
  public CreditCard verifyCrediteCard(String ccn, String cced, String cco, Date current) {
    checkArgument(cco.length() <= 30, FORMATO_DE_OWNER_INVALIDO);
    checkArgument(ccn.length() == 16, NUMERO_DE_TARJETA_INVALIDO);
    checkArgument(ccn.matches(number_regex), NUMERO_DE_TARJETA_INVALIDO);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMyyyy");
    simpleDateFormat.setLenient(false);
    Date expiry = null;
    try {
      expiry = simpleDateFormat.parse(cced);
    } catch (ParseException e) {
      throw new InvalidArgumentException(FORMATO_DE_FECHA_INVALIDO);
    }
    checkArgument(expiry.after(current), TARJETA_EXPIRADA);
    return new CreditCardImpl(Long.valueOf(ccn), cced, cco);
  }
}
