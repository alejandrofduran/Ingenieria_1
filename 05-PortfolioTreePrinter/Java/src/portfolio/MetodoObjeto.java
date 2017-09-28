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
public interface MetodoObjeto {

    public void metodoObjetoDeposit(Deposit deposit);

    public void metodoObjetoWithdraw(Withdraw withdraw);

    public void metodoObjetoCertificateOfDeposit(CertificateOfDeposit certificateOfDeposit);

    public void metodoObjetoTransferLegDeposit(TransferLegDeposit transferLegDeposit);

    public void metodoObjetoTransferLegWithdraw(TransferLegWithdraw transferLegWithdraw);

}
