import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static utils.Utils.random;
import static utils.Utils.randomAbove;
import static utils.Utils.randomMod;

import exceptions.InvalidArgumentException;
import model.FechaDeVencimientoTarjeta;
import org.junit.Test;

public class FechaDeVencimientoTarjetaTest {

  @Test
  public void testCrearTarjeta() {
    FechaDeVencimientoTarjeta fechaDeVencimientoTarjeta = new FechaDeVencimientoTarjeta(
        randomMod(12), random());
  }

  @Test
  public void testCrearFechaInvalidaMes() {
    try {
      FechaDeVencimientoTarjeta fechaDeVencimientoTarjeta;
      fechaDeVencimientoTarjeta = new FechaDeVencimientoTarjeta(randomAbove(12), 2017);
      fail();
    } catch (InvalidArgumentException ex) {
      assertThat(ex.getMessage(), is("Fecha de vencimiento invalida"));
    }
  }

  @Test
  public void testCrearFechaInvalidaAnio() {
    try {
      FechaDeVencimientoTarjeta fechaDeVencimientoTarjeta;
      fechaDeVencimientoTarjeta = new FechaDeVencimientoTarjeta(randomMod(12), -10);
      fail();
    } catch (InvalidArgumentException ex) {
      assertThat(ex.getMessage(), is("Fecha de vencimiento invalida"));
    }
  }

}
