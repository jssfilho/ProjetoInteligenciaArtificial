/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectia;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author joao
 */
public class Grafo {
    public HashMap <Vertice, ArrayList<Aresta>> listAdj;
    public ArrayList<Vertice> N = new ArrayList();
    public ArrayList<Aresta> A = new ArrayList();
    public int[][] matrizAdj;
    
    public boolean isAresta(Vertice v1, Vertice v2){
         for(Aresta a: this.A){
  
             if((a.v1.nome.equals(v1.nome) && a.v2.nome.equals(v2.nome)) || (a.v1.nome.equals(v2.nome) && a.v2.nome.equals(v1.nome) )){
                return true;
             }
         }
         return false;
    }
}
