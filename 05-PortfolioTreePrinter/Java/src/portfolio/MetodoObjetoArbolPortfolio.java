/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portfolio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

/**
 *
 * @author Cronos
 */
public class MetodoObjetoArbolPortfolio implements MetodoObjetoSummarizingAccount {

    Portfolio composedPortfolio;
    Hashtable<SummarizingAccount, String> accountNames;
    List<String> arbol;
    int espacios = 0;

    public MetodoObjetoArbolPortfolio(Portfolio composedPortfolio, Hashtable<SummarizingAccount, String> accountNames) {
        this.composedPortfolio = composedPortfolio;
        this.accountNames = accountNames;
        this.arbol = new ArrayList<String>();
    }

    public List<String> arbol() {
        int espacios = 0;
        composedPortfolio.metodoObjetoAccount(this);
        return arbol;
    }
    
    public List<String> arbolInverso() {
        List<String> arbolInverso = arbol() ;
        Collections.reverse(arbolInverso);
        return arbolInverso;
    }
    

    @Override
    public void metodoObjetoPorfolio(Portfolio porfolio) {
        agregarRama(porfolio);
        espacios++;
        for (SummarizingAccount cuenta : porfolio.cuantasManejadas()) {
            cuenta.metodoObjetoAccount(this);
        }
        espacios--;
    }

    private void agregarRama(SummarizingAccount cuenta) {
        String nombreCuenta = "";
        for (int i = 0; i < espacios; i++) {
            nombreCuenta = nombreCuenta + " ";
        }
        nombreCuenta = nombreCuenta + accountNames.get(cuenta);
        arbol.add(nombreCuenta);
        
    }

    @Override
    public void metodoObjetoReceptiveAccount(ReceptiveAccount receptiveAccount) {
        agregarRama(receptiveAccount);
    }

}
