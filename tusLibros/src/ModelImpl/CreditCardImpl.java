package modelImpl;


import static utils.Utils.checkArgument;

import model.CreditCard;

public class CreditCardImpl implements CreditCard {

  public static final String NUMERO_DE_TARJETA_INVALIDO = "Numero de tarjeta invalido";
  public static final String FECHA_DE_EXPIRACION_INVALIDA = "Fecha de expiracion invalida";
  public static final String OWNER_INVALIDO = "Owner invalido";
  private Long number;

  private String expirationDate;

  private String owner;

  public CreditCardImpl(Long number, String expirationDate, String owner) {
    checkArgument(number != null, NUMERO_DE_TARJETA_INVALIDO);
    checkArgument(expirationDate != null, FECHA_DE_EXPIRACION_INVALIDA);
    checkArgument(owner != null, OWNER_INVALIDO);
    this.number = number;
    this.expirationDate = expirationDate;
    this.owner = owner;
  }

  @Override
  public String owner() {
    return owner;
  }

  @Override
  public String creditCardExpiration() {
    return expirationDate;
  }

  @Override
  public Long number() {
    return number;
  }
}
