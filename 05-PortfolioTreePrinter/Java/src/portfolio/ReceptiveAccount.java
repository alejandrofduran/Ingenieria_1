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

import java.util.ArrayList;
import java.util.List;

public class ReceptiveAccount implements SummarizingAccount {

    private ArrayList<AccountTransaction> transactions = new ArrayList<AccountTransaction>();

    @Override
    public double balance() {
        MetodoObjetoCalculadroBalance calculadorBalance = new MetodoObjetoCalculadroBalance(this);
        return calculadorBalance.saldo();
    }

    public void register(AccountTransaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public boolean registers(AccountTransaction transaction) {
        return transactions.contains(transaction);
    }

    @Override
    public boolean manages(SummarizingAccount account) {
        return this == account;
    }

    @Override
    public List<AccountTransaction> transactions() {
        return new ArrayList<AccountTransaction>(transactions);
    }

}
