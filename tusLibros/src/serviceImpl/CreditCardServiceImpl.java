package serviceImpl;

import static utils.Utils.checkArgument;

import exceptions.InvalidArgumentException;
import java.util.Date;
import model.CreditCard;
import service.CreditCardService;

public class CreditCardServiceImpl implements CreditCardService {

  public static final String FORMATO_DE_FECHA_INVALIDO = "Formato de fecha invalido";

  @Override
  public CreditCard verifyCrediteCard(String ccn, String cced, String cco, Date current) {
    checkArgument(cced.length() == 6, FORMATO_DE_FECHA_INVALIDO);
    checkArgument(cco.length() <= 30, "Formato de owner invalido");
    String monthString = cced.substring(0, 2);
    String yearString = cced.substring(2, 6);
    int month = 0, year = 0;
    try {
      month = Integer.valueOf(monthString);
      year = Integer.valueOf(yearString);
    } catch (Exception ex) {
      throw new InvalidArgumentException(FORMATO_DE_FECHA_INVALIDO);
    }

    return null;
  }
}
