package modelImpl;

import static utils.Utils.checkArgument;

import java.util.Map;
import model.Cart;
import model.Cashier;
import model.CreditCard;
import processor.MerchantProcessor;

public class CashierImpl implements Cashier {

  public static final String CARRITO_VACIO_ERR = "Carrito vacio, no se puede hacer checkout";
  public static final String TARJETA_INVALIDA = "Tarjeta invalida";
  public static final String LA_LISTA_DE_PRECIOS_ESTA_VACIA = "La lista de precios esta vacia";
  public static final String PRECIO_MAYOR_A_0 = "El precio debe ser > 0";
  private Map<String, Integer> prices;
  private MerchantProcessor merchantProcessor;

  public CashierImpl(Map<String, Integer> prices, MerchantProcessor merchantProcessor) {
    checkArgument(!prices.isEmpty(), LA_LISTA_DE_PRECIOS_ESTA_VACIA);
    for (String book : prices.keySet()) {
      checkArgument(prices.get(book) > 0, PRECIO_MAYOR_A_0);
    }
    this.prices = prices;
  }

  public double checkOut(Cart cart, CreditCard creditCard) {
    checkArgument(!cart.itemsList().isEmpty(), CARRITO_VACIO_ERR);
    double amount = 0;
    for (String book : cart.itemsList().keySet()) {
      checkArgument(prices.get(book) != null, "Libro fuera del catalogo de precios");
      amount += cart.itemsList().get(book) * prices.get(book);
    }
    merchantProcessor
        .debit(amount, creditCard.number(), creditCard.creditCardExpiration(), creditCard.owner());
    return amount;
  }
}
