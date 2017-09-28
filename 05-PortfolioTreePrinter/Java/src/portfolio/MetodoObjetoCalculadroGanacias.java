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
class MetodoObjetoCalculadroGanacias implements MetodoObjeto {
    
    SummarizingAccount summarizingAccount;
    private double ganacia;

    public MetodoObjetoCalculadroGanacias(SummarizingAccount summarizingAccount) {
        this.summarizingAccount = summarizingAccount;
        this.ganacia = 0.0;
    }

    public double ganacia() {
        for(AccountTransaction transaccion : summarizingAccount.transactions()){
            transaccion.metodoObjetoTransaccion(this);
        }
        return this.ganacia;
    }

    @Override
    public void metodoObjetoDeposit(Deposit deposit) {
    }

    @Override
    public void metodoObjetoWithdraw(Withdraw withdraw) {
    }

    @Override
    public void metodoObjetoCertificateOfDeposit(CertificateOfDeposit certificateOfDeposit) {
        this.ganacia = this.ganacia + (certificateOfDeposit.value() * (certificateOfDeposit.tna() / 360) * certificateOfDeposit.numberOfDays());
    }
    
    @Override
    public void metodoObjetoTransferLegDeposit(TransferLegDeposit transferLegDeposit) {
    }

    @Override
    public void metodoObjetoTransferLegWithdraw(TransferLegWithdraw transferLegWithdraw) {
    }
    
    
}
