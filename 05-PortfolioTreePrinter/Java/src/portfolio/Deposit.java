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

public class Deposit implements AccountTransaction {

    private double value;

    public static Deposit registerForOn(double value, ReceptiveAccount account) {
        Deposit deposit = new Deposit(value);
        account.register(deposit);
        return deposit;
    }

    public Deposit(double value) {
        this.value = value;
    }

    @Override
    public double value() {
        return this.value;
    }

    /*@Override
    public double valueSigned() {
        return this.value;
    }

    @Override
    public String dameDetalle() {
        return "Depósito por " + this.value;
    }*/

    @Override
    public void metodoObjetoTransaccion(MetodoObjeto metodoObjeto) {
        metodoObjeto.metodoObjetoDeposit(this);
    }

}
