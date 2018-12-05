/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectia;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author joao
 */
public class ProjectIA extends Application {
    public static ArrayList<String> cidades = new ArrayList<String>();
    public static String matriz[][] = new String[21][21];
    public static ArrayList<Vertice> vertices = new ArrayList<Vertice>();
    public static Grafo g;
    
    public static ArrayList<Vertice> buscaLargura(Grafo g, Vertice vInicial, Vertice vFinal, ArrayList<Vertice> caminho){
       ArrayList<Vertice> fila = new ArrayList();
       vInicial.cor="preto";
       fila.add(vInicial);
       while(!fila.isEmpty()){
            Vertice vI = fila.get(0);
            caminho.add(vI);
            if(vI.nome.equals(vFinal.nome)){
                break; 
            }else{
                for(Vertice v: g.N){
                    if(g.isAresta(v, vI) && v.cor.equals("branco")){
                        v.cor="preto";
                        fila.add(v);
                    }
                }
            }
            fila.remove(vI);
       }
       return caminho;
    }
    public static ArrayList<Vertice> buscaProfundidade(Grafo g, Vertice vInicial,Vertice vFinal,ArrayList<Vertice> caminho){
        vInicial.cor="preto";
        caminho.add(vInicial);
        System.out.println(vInicial.nome);
        for(Vertice v: g.N){
            if(vInicial.nome.equals(vFinal.nome)){
                v.cor="preto";
            }
            else if(g.isAresta(vInicial, v) && v.cor.equals("branco")){
                ProjectIA.buscaProfundidade(g, v,vFinal, caminho);
            }
        }
       return caminho;
    }
    public static ArrayList<Vertice> getBestWay(ArrayList<Vertice> bestWay,ArrayList<Vertice> caminho,Vertice vFinal ,Grafo g,int cont){
        if(!bestWay.contains(vFinal)){
            //System.out.println("add"+vFinal.nome);
            bestWay.add(vFinal);
        }
        //System.out.println(vFinal.nome);
        if(cont>=0){
            if(g.isAresta(caminho.get(cont), vFinal)){
                ProjectIA.getBestWay(bestWay, caminho, caminho.get(cont), g,cont-1);
            }else{
                ProjectIA.getBestWay(bestWay, caminho, vFinal, g,cont-1);
            }
        }
        return bestWay;
    }
    
    public static Float getDistance(Vertice a, Vertice b){
        int posA=1;
        int posB=1;

        for(int i=1;i<21;i++){
            if(matriz[0][i].equals(a.nome)){
                posA = i;
                break;

            }
        }

        for(int blinda=1;blinda<21;blinda++) {
            if (matriz[blinda][0].equals(b.nome)) {
                posB = blinda;
                break;
            }
        }
        if(matriz[posB][posA].equals("")){
            System.out.println("ERRO-> "+matriz[posB][posA]+" "+posB+" "+posA+" "+a.nome+" "+b.nome);
        }else{
            //return Float.parseFloat(matriz[posA][posB]);
        }
        return Float.parseFloat(matriz[posB][posA]);
    }

    public static Vertice prox_no(Grafo g, Vertice atual, Vertice vfinal, ArrayList<Vertice> visitados){
        if(visitados.isEmpty()){
            visitados.add(atual);
        }else{
            if( !visitados.contains(atual) ){
                visitados.add(atual);
            }
        }

        ArrayList<Aresta> vizinhos = new ArrayList<Aresta>();
        float f=10000;
        Vertice vEscolhinho = atual;

        for(Aresta j: g.A) {

            if (j.v1.nome.equals(atual.nome)) {
                if(!visitados.contains(j.v2)) {
                    vizinhos.add(j);
                    if ((j.peso + getDistance(j.v2, vfinal) < f)) {
                        f = j.peso + getDistance(j.v2, vfinal);
                        vEscolhinho = j.v2;
                    }
                }
            }else{
                if( j.v2.nome.equals(atual.nome)){
                    if(!visitados.contains(j.v1)) {
                        vizinhos.add(j);
                        if((j.peso + getDistance(j.v1,vfinal)<f)){
                            f = j.peso + getDistance(j.v1,vfinal);
                            vEscolhinho = j.v1;
                        }
                    }
                }
            }
        }
        return vEscolhinho;
    }

    public static Vertice getVertice(String cidade){
        Vertice retorno = new Vertice();
        for(Vertice v:vertices ){
            if(v.nome.equals(cidade)){
                retorno = v;
                break;
            }
        }
        return retorno;
    }

    public static ArrayList<Vertice> aStart(Grafo g, Vertice vInicial, Vertice vFinal, ArrayList<Vertice> path){
        if(path.isEmpty()){
            path.add(vInicial);
        }
        if(vInicial.nome.equals(vFinal.nome)){
            return path;
        }

        Vertice proximo = prox_no(g, vInicial, vFinal,path);
        path.add(proximo);
        return aStart(g,proximo,vFinal,path);

    }
       
    @Override
    public void start(Stage stage) throws Exception {
        ProjectIA.g = new Grafo();

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("edges.csv")));
        String linha = null;
        ArrayList<Aresta> arestas = new ArrayList<Aresta>();


        while ((linha = reader.readLine()) != null) {
            String[] dadosUsuario = linha.split(",");

            Vertice vgenericoA = new Vertice();
            Vertice vgenericoB = new Vertice();

            Aresta agenerico = new Aresta();
            boolean tem=false;
            boolean tem2=false;

            for(Vertice v:vertices){
                if(v.nome.equals(dadosUsuario[0])){
                    tem = true;
                    vgenericoA = v;
                }
                if(v.nome.equals( dadosUsuario[1])){
                    vgenericoB = v;
                    tem2=true;
                }

            }
            if (!tem){
                vgenericoA.nome= dadosUsuario[0];
                vertices.add(vgenericoA);
                ProjectIA.g.N.add(vgenericoA);
            }else{

            }
            if(!tem2) {
                vgenericoB.nome= dadosUsuario[1];
                vertices.add(vgenericoB);
                ProjectIA.g.N.add(vgenericoB);
            }

            agenerico.v1 = vgenericoA;
            agenerico.v2 = vgenericoB;
            agenerico.peso = Integer.parseInt(dadosUsuario[2]);

            ProjectIA.g.A.add(agenerico);
        }
        reader.close();

        BufferedReader reader2 = new BufferedReader(new InputStreamReader(new FileInputStream("heuristics.csv")));
        String linha2 = null;
        int l=0;
        while ((linha2 = reader2.readLine()) != null) {
            String[] dadosUsuario = linha2.split(",");
            for (int j = 0; j < dadosUsuario.length; j++) {
                matriz[l][j] = dadosUsuario[j];
            }
            l++;
        }

        for(int tam=1;tam<21;tam++){
            cidades.add(matriz[0][tam]);
        }
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {launch(args);}
    
}
