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
class MetodoObjetoCalculadroTrasferencias implements MetodoObjeto {
    
    SummarizingAccount summarizingAccount;
    private double saldo;

    public MetodoObjetoCalculadroTrasferencias(SummarizingAccount summarizingAccount) {
        this.summarizingAccount = summarizingAccount;
        this.saldo = 0.0;
    }

    public double saldo() {
        for(AccountTransaction transaccion : summarizingAccount.transactions()){
            transaccion.metodoObjetoTransaccion(this);
        }
        return this.saldo;
    }

    @Override
    public void metodoObjetoDeposit(Deposit deposit) {
    }

    @Override
    public void metodoObjetoWithdraw(Withdraw withdraw) {
    }

    @Override
    public void metodoObjetoCertificateOfDeposit(CertificateOfDeposit certificateOfDeposit) {
    }

    @Override
    public void metodoObjetoTransferLegDeposit(TransferLegDeposit transferLegDeposit) {
        this.saldo = this.saldo + transferLegDeposit.value();
    }

    @Override
    public void metodoObjetoTransferLegWithdraw(TransferLegWithdraw transferLegWithdraw) {
        this.saldo = this.saldo - transferLegWithdraw.value();
    }
    
    
}
