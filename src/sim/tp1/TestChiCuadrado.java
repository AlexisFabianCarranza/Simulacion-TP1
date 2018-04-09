/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.tp1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author nicolastomassi
 */
public class TestChiCuadrado {
    
    private int numAGenerar;
    private int numIntervalos;
    private ArrayList<Double> numerosAleatorios;
    private int gradoDeLibertad;

    public ArrayList<Double> getNumerosAleatorios() {
        return numerosAleatorios;
    }
    private double esperado;
    private int intervalos[];
    
    public TestChiCuadrado(int i, int n)
    {
        this.numIntervalos = i;
        this.numAGenerar = n;
        this.numerosAleatorios = new ArrayList<>();
        this.generarNumeros();
        this.valorEsperado();
        this.intervalos();
        this.contarFrecuencia();
        
    }
    
    public TestChiCuadrado(int i, ArrayList numeros)
    {
        this.numIntervalos = i;
        this.numAGenerar = 0;
        this.numerosAleatorios = numeros;
        this.valorEsperado();
        this.intervalos();
        this.contarFrecuencia();
    }
    
    public boolean esAprobado()
    {
        if (this.generarChiCuadradoTabulado() >= this.generarChiCuadradoCalculado())
        {
            return true;
        }
        return false;
    }
    
    private void generarNumeros()
    {
        Random g = new Random();
        double aux;
        for (int i = 0; i < this.numAGenerar; i++)
        {
            aux = g.nextDouble();
            aux = Math.round(aux*10000.0) / 10000.0;

            numerosAleatorios.add(aux);            
        }
    }
    
    public int getGradosDeLibertad()
    {
        return this.gradoDeLibertad;
    }
    
    private void valorEsperado()
    {
        this.esperado = (float) numerosAleatorios.size() / (float) numIntervalos;
        /*if (esperado < 5)
        {
            this.numIntervalos -= 1;
            this.valorEsperado();
        }*/
        while(esperado < 5)
        {
            this.numIntervalos -= 1;
            this.esperado = (float) numerosAleatorios.size() / (float) numIntervalos;
        }
        
        this.esperado = Math.round(this.esperado*10000.0) / 10000.0;

    }
    
    private void intervalos()
    {
        intervalos = new int[numIntervalos];
    }
    
     public double generarChiCuadradoTabulado()
    {
        ArrayList<Double> aux = new ArrayList<>();
        aux.add(3.84);
        aux.add(5.99);
        aux.add(7.81);
        aux.add(9.49);
        aux.add(11.1);
        aux.add(12.6);
        aux.add(14.1);
        aux.add(15.5);
        aux.add(16.9);
        aux.add(18.3);
        aux.add(19.7);
        aux.add(21.0);
        aux.add(22.4);
        aux.add(23.7);
        aux.add(25.0);
        aux.add(26.3);
        aux.add(27.6);
        aux.add(28.9);
        aux.add(30.1);
        aux.add(31.4);
        aux.add(32.7);
        aux.add(33.9);
        aux.add(35.2);
        aux.add(36.4);
        aux.add(37.7);
        aux.add(38.9);
        aux.add(40.1);
        aux.add(41.3);
        aux.add(42.6);
        aux.add(43.8);
        aux.add(55.8);
        aux.add(67.5);
        aux.add(79.1);
        aux.add(90.5);
        aux.add(101.9);
        aux.add(113.1);
        aux.add(124.3);
        this.gradoDeLibertad = this.numIntervalos - 1;
        if(this.gradoDeLibertad >30)
        {
            this.gradoDeLibertad -= 30;
            this.gradoDeLibertad /= 10;
            this.gradoDeLibertad += 29;
        }
        return aux.get(this.gradoDeLibertad-1);
    }
     
    private void contarFrecuencia()
    {

        double amplitudIntervalo = 1 / (float) numIntervalos;
        amplitudIntervalo = Math.round(amplitudIntervalo*10000.0) / 10000.0;
        for (double aux : numerosAleatorios)
        {
            int count = 0;
            for (double i = 0; i< 1 ; i += amplitudIntervalo)
            {
                if(aux >= 1) 
                    {
                        break;
                    
                    }
                if (i <= aux && aux < i + amplitudIntervalo)
                {
                    
                    intervalos[count] += 1;
                    break;
                }
                else { count += 1;  }
            }
        }

    }
    
    public ArrayList listaIntervalos()
    {
        ArrayList<String> listaIntervalos = new ArrayList<>();
        double amplitudIntervalo = 1 / (float) numIntervalos;
        amplitudIntervalo = Math.round(amplitudIntervalo*10000.0) / 10000.0;
        
        String aux;
        
        for (float i = 0; i < 1; i+=amplitudIntervalo)
        {
                double intM = Math.round((i+amplitudIntervalo) * 10000.0) / 10000.0;
                double intm = Math.round(i * 10000.0) / 10000.0;
                aux = String.valueOf(intm)  + " - " + String.valueOf(intM);
                if (i+amplitudIntervalo > 1) 
                {
                    aux = String.valueOf(intm)  + " - 1";
                }
                listaIntervalos.add(aux);
            
        }
        
        return listaIntervalos;
    }
    
    

    public ArrayList<Double> diferenciaYalCuadrado()
    {
        ArrayList<Double> aux = new ArrayList<>();
        for ( int i = 0 ; i < intervalos.length ; i++)
        {
            double aux1 = Math.pow(intervalos[i] - esperado, 2);
            aux.add(aux1);
        }
        return aux;
    }
    
    public double getEsperada()
    {
        return this.esperado;
    }

    public int[] getIntervalos() {
        return intervalos;
    }
    
    
    
    public ArrayList<Double> generarSumatoriaChi()
    {
        ArrayList<Double> restas = this.diferenciaYalCuadrado();
        ArrayList<Double> lista = new ArrayList<>();
        double aux;
        
        for (double i : restas)
        {
            aux = i;
            aux = aux/this.esperado;
            lista.add(aux);
        }
        
        return lista;
    }
    
    public double generarChiCuadradoCalculado()
    {
        ArrayList<Double> lista = this.generarSumatoriaChi();
        double aux = 0;
        
        for (double i : lista)
        {
            aux += i;
        }
        
        return aux;
    }
}
