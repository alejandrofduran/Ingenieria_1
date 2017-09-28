/*
 * Developed by 10Pines SRL
 * License: 
 * This work is licensed under the 
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ 
 * or send a letter to Creative Commons, 444 Castro Street, Suite 900, Mountain View, 
 * California, 94041, USA.
 *  
 */
package portfolio;

public class CertificateOfDeposit implements AccountTransaction {

    private double value;
    private int numberOfDays;
    private double tna;

    public static CertificateOfDeposit registerFor(double value, int numberOfDays, double tna,
            ReceptiveAccount account) {
        CertificateOfDeposit certificadoDeDeposito = new CertificateOfDeposit(value, numberOfDays, tna);
        account.register(certificadoDeDeposito);
        return certificadoDeDeposito;
    }

    public CertificateOfDeposit(double value, int numberOfDays, double tna) {
        this.value = value;
        this.numberOfDays = numberOfDays;
        this.tna = tna;
    }

    public double value() {
        return this.value;
    }

   /* @Override
    public String dameDetalle() {
        return "Plazo fijo por " + this.value + " durante " + this.numberOfDays + " días a una tna de " + this.tna;
    }

    @Override
    public double valueSigned() {
        return this.value * (-1);
    }
    */

    @Override
    public void metodoObjetoTransaccion(MetodoObjeto metodoObjeto) {
        metodoObjeto.metodoObjetoCertificateOfDeposit(this);
    }
    
    public int numberOfDays() {
        return this.numberOfDays;
    }
    
    public double tna() {
        return this.tna;
    }
}
