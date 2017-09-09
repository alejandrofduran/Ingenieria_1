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
package numero;

public class Entero extends Numero {

    protected int value;

    public Entero(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    @Override
    public Numero mas(Numero sumando) {

        /*if (sumando instanceof Entero) {
            return new Entero(value + ((Entero) sumando).value());
        }

        if (sumando instanceof Fraccion) {
            Fraccion fraccionAsFraccion = (Fraccion) sumando;
            Entero numerador = (Entero) this.por(fraccionAsFraccion.denominador()).mas(fraccionAsFraccion.numerador());
            return Fraccion.dividir(numerador, fraccionAsFraccion.denominador());
        }

        throw new RuntimeException();*/
        return sumando.sumameUnEnter(this);
    }

    @Override
    protected Numero sumameUnaFraccion(Numero sumando) {
        Fraccion fraccionEsFraccion = (Fraccion) sumando;
        Entero nuevoNumerador = (Entero) this.por(fraccionEsFraccion.denominador()).mas(fraccionEsFraccion.numerador());
        return Fraccion.dividir(nuevoNumerador, fraccionEsFraccion.denominador());
    }

    @Override
    protected Numero sumameUnEnter(Numero sumando) {
        return new Entero(value + ((Entero) sumando).value());
    }

    @Override
    public Numero por(Numero multiplicador) {

        /*if (multiplicador instanceof Entero) {
            return new Entero(value * ((Entero) multiplicador).value());
        }

        if (multiplicador instanceof Fraccion) {
            Fraccion fraccionAsFraccion = (Fraccion) multiplicador;
            Entero newNumerador = (Entero) this.por(fraccionAsFraccion.numerador());
            return Fraccion.dividir(newNumerador, fraccionAsFraccion.denominador());
        }

        throw new RuntimeException();*/
        return multiplicador.multiplicamePorUnEntero(this);
    }

    @Override
    protected Numero multiplicamePorUnaFraccion(Numero multiplicador) {
        Fraccion multiplicadorEsFraccion = (Fraccion) multiplicador;
        Entero nuevoNumerador = (Entero) this.por(multiplicadorEsFraccion.numerador());
        return Fraccion.dividir(nuevoNumerador, multiplicadorEsFraccion.denominador());
    }

    @Override
    protected Numero multiplicamePorUnEntero(Numero multiplicador) {
        return new Entero(value * ((Entero) multiplicador).value());
    }

    @Override
    public Numero dividido(Numero divisor) {

        /*if (divisor instanceof Entero) {
            return Fraccion.dividir(this, (Entero) divisor);
        }

        if (divisor instanceof Fraccion) {
            Fraccion fraccionAsFraccion = (Fraccion) divisor;
            Entero numerador = (Entero) this.por(fraccionAsFraccion.denominador());
            return Fraccion.dividir(numerador, fraccionAsFraccion.numerador());
        }

        throw new RuntimeException();*/
        return divisor.dividiElEntero(this);
    }

    @Override
    public Numero dividiLaFraccion(Numero dividendo) {
        Fraccion dividendeoEsFraccion = (Fraccion) dividendo;
        Entero nuevoDenominador = (Entero) this.por(dividendeoEsFraccion.denominador());
        return Fraccion.dividir(dividendeoEsFraccion.numerador(), nuevoDenominador);
    }

    @Override
    public Numero dividiElEntero(Numero dividendo) {
        return Fraccion.dividir((Entero) dividendo, this);
    }

    @Override
    public boolean esCero() {
        return value == 0;
    }

    @Override
    public boolean esUno() {
        return value == 1;
    }

    @Override
    public boolean equals(Object anObject) {
        if (anObject instanceof Entero) {
            return value == ((Entero) anObject).value();
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return value;
    }

    public Entero maximoComunDivisorCon(Entero otroEntero) {
        if (otroEntero.esCero()) {
            return this;
        } else {
            return otroEntero.maximoComunDivisorCon(this.restoCon(otroEntero));
        }
    }

    public Entero restoCon(Entero divisor) {
        return new Entero(value % divisor.value());
    }

    public Entero divisionEntera(Entero divisor) {
        return new Entero(value / divisor.value());
    }

}
