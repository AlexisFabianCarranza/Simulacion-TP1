/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.tp1;

import java.text.NumberFormat;
import java.util.ArrayList;
import sim.tp1.*;

/**
 *
 * @author aleex
 */
public class CongruencialLineal implements Metodo{
    //Estructura del metodo X(i+1) = (a .X + c) mod (m)
    private int a;
    private int c;
    private int m;
    private int xo; //Semilla del metodo
    private ArrayList<Double> numeros;
    private int numeroAnterior;
    

    public CongruencialLineal(int a, int c, int m, int x0) {
        this.a = a;
        this.c = c;
        this.m = m;
        this.xo = x0;
        numeros = new ArrayList<>();
        numeroAnterior = x0;
    }
    
    
    @Override
    public double generarNumero()
    {
        if (numeros.isEmpty()) {this.numeroAnterior = xo;}
        double aux = ((a * this.numeroAnterior + c) % m);
        this.numeroAnterior = (int) aux;
        double nuevoNumero = Math.round((aux / (this.m - 1)) * 10000.0) / 10000.0;
        numeros.add(nuevoNumero);
        return nuevoNumero;
    }

    @Override
    public ArrayList numerosGenerados() {
        return numeros;
    }

    @Override
    public int cantidadNumeros() {
        return numeros.size();
    }
    
   @Override
    public String toString()
    {
        String aux = "X(i+1) = ( " + String.valueOf(this.a) + " * Xi + " + String.valueOf(this.c) + " ) mod( " + String.valueOf(this.m) + ")";
        return aux;
    } 

    @Override
    public String getSemilla() {
        return String.valueOf(this.xo);
    }
    
}
