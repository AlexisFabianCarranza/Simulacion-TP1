/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.tp1;

import java.util.ArrayList;
import sim.tp1.*;

/**
 *
 * @author nicolastomassi
 */
public class CongruencialMultiplicativo implements Metodo
{
    private int a;
    private int xo;
    private int m;
    private ArrayList<Double> numeros;
    private int numeroAnterior;
    
    
    public CongruencialMultiplicativo (int a, int m, int xo)
    {
        this.a = a;
        this.xo = xo;
        this.m = m;
        this.numeros = new ArrayList<>();
    }

    @Override
    public double generarNumero()
    {
        double numAleatorio;
        if (numeros.isEmpty())
        {
            numeroAnterior = this.xo;
        }
        int aux = ((this.a*numeroAnterior) % this.m);
        this.numeroAnterior = (int) aux;
        numAleatorio = Math.round((aux/(float)(m-1)) * 10000.0) / 10000.0;
        
        numeros.add(numAleatorio);
        
        return numAleatorio;
    }
    
    @Override
    public String getSemilla() {
        return String.valueOf(this.xo);
    }
    
    @Override
    public int cantidadNumeros()
    {
        return numeros.size();
    }

    @Override
    public ArrayList numerosGenerados() {
        return numeros;
    }
    
    @Override
    public String toString()
    {
        String aux = "X(i+1) = ( " + String.valueOf(this.a) + " * Xi ) mod( " + String.valueOf(this.m) + ")";
        return aux;
    }
}
