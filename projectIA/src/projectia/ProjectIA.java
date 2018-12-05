/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectia;

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
    	  Grafo g = new Grafo();

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
                g.N.add(vgenericoA);
            }else{

            }
            if(!tem2) {
                vgenericoB.nome= dadosUsuario[1];
                vertices.add(vgenericoB);
                g.N.add(vgenericoB);
            }

            agenerico.v1 = vgenericoA;
            agenerico.v2 = vgenericoB;
            agenerico.peso = Integer.parseInt(dadosUsuario[2]);

            g.A.add(agenerico);
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
        System.out.println("-------------->Busca A*<--------------\n");

        ArrayList<Vertice> caminho = new ArrayList();
      ArrayList<Vertice> path = ProjetoIA.aStart(g,getVertice("Arad"),getVertice("Bucharest"),caminho);

        for(Vertice v:path){

            System.out.println(v.nome);
        }



        System.out.println("-------------->Busca largura<--------------\n");

        ArrayList<Vertice> caminhoLargura = new ArrayList();

        ArrayList<Vertice> vs = ProjetoIA.buscaProfundidade(g, getVertice("Arad"), getVertice("Bucharest"), caminhoLargura);

        for(Vertice ll: vs){
            System.out.println(ll.nome);
        }

        /*Grafo g = new Grafo();
       
       Vertice vC = new Vertice();
       vC.nome="c";
       g.N.add(vC);
       
       Vertice vb = new Vertice();
       vb.nome="b";
       g.N.add(vb);
       
       Vertice vd = new Vertice();
       vd.nome="d";
       g.N.add(vd);
       
       Vertice va = new Vertice();
       va.nome="va";
       g.N.add(va);
       
       Vertice e = new Vertice();
       e.nome="e";
       g.N.add(e);
       
       Vertice f = new Vertice();
       f.nome="f";
       g.N.add(f);
       
       Vertice h = new Vertice();
       h.nome="h";
       g.N.add(h);
       
       Vertice vG = new Vertice();
       vG.nome="g";
       g.N.add(vG);
       
       Vertice i = new Vertice();
       i.nome="i";
       g.N.add(i);
       
       Vertice j = new Vertice();
       j.nome="j";
       g.N.add(j);
       
       Vertice k = new Vertice();
       k.nome="k";
       g.N.add(k);
       
       Vertice l = new Vertice();
       l.nome="l";
       g.N.add(l);
       
       Vertice m = new Vertice();
       m.nome="m";
       g.N.add(m);
       
       //arestas
       
       Aresta cb = new Aresta();
       cb.v1 = vC;
       cb.v2 = vb;
       g.A.add(cb);
       
       Aresta cd = new Aresta();
       cd.v1 = vC;
       cd.v2 = vd;
       g.A.add(cd);
        
        
       Aresta da = new Aresta();
       da.v1 = vd;
       da.v2 = va;
       g.A.add(da);
        
        
       Aresta de = new Aresta();
       de.v1 = vd;
       de.v2 = e;
       g.A.add(de);
       
       Aresta af = new Aresta();
       af.v1 = va;
       af.v2 = f;
       g.A.add(af);
       
       Aresta ag = new Aresta();
       ag.v1 = vG;
       ag.v2 = va;
       g.A.add(ag);
       
       Aresta fj = new Aresta();
       fj.v1 = j;
       fj.v2 = f;
       g.A.add(fj);
       
       Aresta fk = new Aresta();
       fk.v1 = f;
       fk.v2 = k;
       g.A.add(fk);
       
       Aresta eh = new Aresta();
       eh.v1 = e;
       eh.v2 = h;
       g.A.add(eh);
       
       Aresta ei = new Aresta();
       ei.v1 = e;
       ei.v2 = i;
       g.A.add(ei);
       
       Aresta il = new Aresta();
       il.v1 = l;
       il.v2 = i;
       g.A.add(il);
       
       Aresta mi = new Aresta();
       mi.v1 = m;
       mi.v2 = i;
       g.A.add(mi);*/
       
       ArrayList<Vertice> caminho = new ArrayList();
       ArrayList<Vertice> Way = new ArrayList();
       ArrayList<Vertice> vs = ProjectIA.buscaLargura(g, vC, k, caminho);
       int cont = vs.size()-2;
       ArrayList<Vertice> bestWay = ProjectIA.getBestWay(Way,vs,k,g,cont);
       
       for(int ji=0;ji<bestWay.size();ji++){
           System.out.println(bestWay.get(ji).nome);}
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
