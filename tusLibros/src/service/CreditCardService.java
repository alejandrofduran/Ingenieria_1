package service;

import java.util.Date;
import model.CreditCard;

public interface CreditCardService {

  CreditCard verifyCrediteCard(String ccn, String cced, String cco, Date current);
}
