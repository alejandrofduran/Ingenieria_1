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

  private static String number_regex = "\\d+";

  @Override
  public CreditCard verifyCrediteCard(String ccn, String cced, String cco, Date current) {
    checkArgument(cced.length() == 6, FORMATO_DE_FECHA_INVALIDO);
    checkArgument(cco.length() <= 30, "Formato de owner invalido");
    checkArgument(cco.length() == 16, FORMATO_DE_FECHA_INVALIDO);
    checkArgument(cco.matches(number_regex), FORMATO_DE_FECHA_INVALIDO);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMyyyy");
    simpleDateFormat.setLenient(false);
    Date expiry = null;
    try {
      expiry = simpleDateFormat.parse(cced);
    } catch (ParseException e) {
      throw new InvalidArgumentException(FORMATO_DE_FECHA_INVALIDO);
    }
    checkArgument(expiry.after(current), "Tarjeta expirada");
    return new CreditCardImpl(Integer.valueOf(cco), cced, ccn);
  }
}
