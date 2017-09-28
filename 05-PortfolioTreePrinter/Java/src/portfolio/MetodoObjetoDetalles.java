/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portfolio;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cronos
 */
public class MetodoObjetoDetalles implements MetodoObjeto {

    SummarizingAccount summarizingAccount;
    private List<String> listaDetalles;

    public MetodoObjetoDetalles(SummarizingAccount summarizingAccount) {
        this.summarizingAccount = summarizingAccount;
        this.listaDetalles = new ArrayList<String>();
    }

    public List<String> detalles() {
        for(AccountTransaction transaccion : summarizingAccount.transactions()){
            transaccion.metodoObjetoTransaccion(this);
        }
        return this.listaDetalles;
    }
    
    @Override
    public void metodoObjetoDeposit(Deposit deposit) {
         this.listaDetalles.add("Depósito por " + deposit.value());
    }

    @Override
    public void metodoObjetoWithdraw(Withdraw withdraw) {
        this.listaDetalles.add("Extracción por " + withdraw.value());
    }

    @Override
    public void metodoObjetoCertificateOfDeposit(CertificateOfDeposit certificateOfDeposit) {
        this.listaDetalles.add("Plazo fijo por " + certificateOfDeposit.value() + " durante " + certificateOfDeposit.numberOfDays() + " días a una tna de " + certificateOfDeposit.tna());
    }

    @Override
    public void metodoObjetoTransferLegDeposit(TransferLegDeposit transferLegDeposit) {
        this.listaDetalles.add("Transferencia por " + transferLegDeposit.value());
    }

    @Override
    public void metodoObjetoTransferLegWithdraw(TransferLegWithdraw transferLegWithdraw) {
        this.listaDetalles.add("Transferencia por -" + transferLegWithdraw.value());
    }
    
}
