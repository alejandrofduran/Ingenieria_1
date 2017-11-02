package model;

import static utils.Utils.checkArgument;

public class FechaDeVencimientoTarjeta {

  private int mes;
  private int anio;

  public FechaDeVencimientoTarjeta(int mes, int anio) {
    checkArgument(mes > 1 && mes < 13 && anio > 1, "Fecha de vencimiento invalida");
    this.mes = mes;
    this.anio = anio;
  }
}
