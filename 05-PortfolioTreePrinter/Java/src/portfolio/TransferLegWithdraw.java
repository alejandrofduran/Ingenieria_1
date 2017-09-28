/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portfolio;

/**
 *
 * @author Cronos
 */
public class TransferLegWithdraw implements TransferLeg {

    private Transfer transferencia;

    public TransferLegWithdraw(Transfer transferencia) {
        this.transferencia = transferencia;
    }

    @Override
    public Transfer transfer() {
        return this.transferencia;
    }

    @Override
    public double value() {
        return this.transferencia.value();
    }

    /*@Override
    public String dameDetalle() {
        return "Transferencia por -" + this.transferencia.value();
    }

    @Override
    public double valueSigned() {
        return this.transferencia.value() * -1;
    }*/

    @Override
    public void metodoObjetoTransaccion(MetodoObjeto metodoObjeto) {
        metodoObjeto.metodoObjetoTransferLegWithdraw(this);
    }

}
