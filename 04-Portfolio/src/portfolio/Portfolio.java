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
import java.util.HashSet;
import java.util.List;

public class Portfolio implements SummarizingAccount {

	public static final String ACCOUNT_NOT_MANAGED = "No se maneja esta cuenta";
	public static final String ACCOUNT_ALREADY_MANAGED = "La cuenta ya está manejada por otro portfolio";

	private List<SummarizingAccount> accounts = new ArrayList<SummarizingAccount>();

	public static Portfolio createWith(SummarizingAccount anAccount1, SummarizingAccount anAccount2) {
		Portfolio portfolio = new Portfolio();
		portfolio.addAccount(anAccount1);
		portfolio.addAccount(anAccount2);
		return portfolio;
	}

	public Portfolio() {

	}

	public double balance() {
		double balance = 0;
		for (SummarizingAccount summarizingAccount : accounts) {
			balance += summarizingAccount.balance();
		}
		return balance;
	}

	public boolean registers(AccountTransaction transaction) {
		return this.transactions().contains(transaction);
	}

	public boolean manages(SummarizingAccount account) {
		if (accounts.contains(account))
			return true;
		for (SummarizingAccount summarizingAccount : accounts) {
			if (summarizingAccount.manages(account)) {
				return true;
			}
		}
		return false;
	}

	public List<AccountTransaction> transactions() {
		List<AccountTransaction> accountTransactions = new ArrayList<AccountTransaction>();
		for (SummarizingAccount summarizingAccount : accounts) {
			accountTransactions.addAll(summarizingAccount.transactions());
		}
		return accountTransactions;
	}

	public void addAccount(SummarizingAccount anAccount) {
		if (this.manages(anAccount)) {
			throw new RuntimeException(ACCOUNT_ALREADY_MANAGED);
		}
		accounts.add(anAccount);
	}
}
