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

public class Transfer {

    private double value;
    private ReceptiveAccount fromAccount;
    private ReceptiveAccount toAccount;
    private TransferLegDeposit trasferenciaDelDeposito;
    private TransferLegWithdraw tranferenciaDelRetiro;

    public static Transfer registerFor(double value, ReceptiveAccount fromAccount,
            ReceptiveAccount toAccount) {
        Transfer trasferencia = new Transfer(value, fromAccount, toAccount);
        fromAccount.register(trasferencia.tranferenciaDelRetiro);
        toAccount.register(trasferencia.trasferenciaDelDeposito);
                
        return trasferencia;
    }

    public Transfer(double value, ReceptiveAccount fromAccount, ReceptiveAccount toAccount) {
        this.value = value;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.trasferenciaDelDeposito = new TransferLegDeposit(this);
        this.tranferenciaDelRetiro = new TransferLegWithdraw(this);
    }

    public double value() {
        return this.value;
    }

    public TransferLeg depositLeg() {
        return this.trasferenciaDelDeposito;
    }

    public TransferLeg withdrawLeg() {
        return this.tranferenciaDelRetiro;
    }

}
