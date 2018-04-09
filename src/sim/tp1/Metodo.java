/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.tp1;

import java.util.ArrayList;

/**
 *
 * @author nicolastomassi
 */
public interface Metodo {
    
    public double generarNumero();
    public ArrayList numerosGenerados();
    public int cantidadNumeros();
    @Override
    public String toString();
    public String getSemilla();
    
    
    
    
    
}
