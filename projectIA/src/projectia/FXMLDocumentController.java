/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectia;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class FXMLDocumentController {

    @FXML
    private Label label;

    @FXML
    private Label saida;

    @FXML
    private Button calcular;

    @FXML
    private Button oradea;

    @FXML
    private Button arad;

    @FXML
    private Button zerind;

    @FXML
    private Button timisoara;

    @FXML
    private Button sibiu;

    @FXML
    private Button lugoj;

    @FXML
    private Button mehadia;

    @FXML
    private Button dobreta;

    @FXML
    private Button fagaras;

    @FXML
    private Button rimnicuVilcea;

    @FXML
    private Button craiova;

    @FXML
    private Button pitesti;

    @FXML
    private Button bucharest;

    @FXML
    private Button giurgiu;

    @FXML
    private Button urziceni;

    @FXML
    private Button neamt;

    @FXML
    private Button lasi;

    @FXML
    private Button vaslui;

    @FXML
    private Button hirsova;

    @FXML
    private Button eforie;

    @FXML
    private RadioButton bLargura;

    @FXML
    private ToggleGroup tipoBusca;

    @FXML
    private RadioButton bProfundidade;

    @FXML
    private RadioButton bMelhor;
    
    private int cityClick=0;
    private int tBusca=0;
    
    private String cityOne;
    private String cityTwo;
    
    
    public void count(){
        this.cityClick+=1;
        if(this.cityClick>=2){
            if(this.tBusca!=0){
                this.calcular.setDisable(false);
            }
            this.oradea.setDisable(true);
            this.arad.setDisable(true);
            this.zerind.setDisable(true);
            this.timisoara.setDisable(true);
            this.sibiu.setDisable(true);
            this.lugoj.setDisable(true);
            this.mehadia.setDisable(true);
            this.dobreta.setDisable(true);
            this.fagaras.setDisable(true);
            this.rimnicuVilcea.setDisable(true);
            this.craiova.setDisable(true);
            this.pitesti.setDisable(true);
            this.bucharest.setDisable(true);
            this.giurgiu.setDisable(true);
            this.urziceni.setDisable(true);
            this.neamt.setDisable(true);
            this.lasi.setDisable(true);
            this.vaslui.setDisable(true);
            this.hirsova.setDisable(true);
            this.eforie.setDisable(true);
        }
    }

  
    public void bProfundidadeVoid(ActionEvent event){
        
        this.tBusca=1;
        
        count();
    }
    public void bLarguraVoid(ActionEvent event){
        this.tBusca=2;
        
        count();
        
    }
    public void bMelhorVoid(ActionEvent event){
        this.tBusca=3;
       
        count();
    }
    
    
    
    @FXML
    void aradVoid(ActionEvent event) {
       this.count();
    }

    @FXML
    void bucharestVoid(ActionEvent event) {
        this.count();
    }

    @FXML
    void craiovaVoid(ActionEvent event) {
         this.count();
    }

    @FXML
    void dobretaVoid(ActionEvent event) {
        this.count();
    }

    @FXML
    void eforieVoid(ActionEvent event) {
        this.count();
    }

    @FXML
    void fagarasVoid(ActionEvent event) {
        this.count();
    }

    @FXML
    void giurgiuVoid(ActionEvent event) {
        this.count();
    }

    @FXML
    void handleButtonAction(ActionEvent event) {
        this.cityClick=0;
        this.tBusca=0;
        this.calcular.setDisable(false);
        this.oradea.setDisable(false);
        this.arad.setDisable(false);
        this.zerind.setDisable(false);
        this.timisoara.setDisable(false);
        this.sibiu.setDisable(false);
        this.lugoj.setDisable(false);
        this.mehadia.setDisable(false);
        this.dobreta.setDisable(false);
        this.fagaras.setDisable(false);
        this.rimnicuVilcea.setDisable(false);
        this.craiova.setDisable(false);
        this.pitesti.setDisable(false);
        this.bucharest.setDisable(false);
        this.giurgiu.setDisable(false);
        this.urziceni.setDisable(false);
        this.neamt.setDisable(false);
        this.lasi.setDisable(false);
        this.vaslui.setDisable(false);
        this.hirsova.setDisable(false);
        this.eforie.setDisable(false);
        this.calcular.setDisable(true);
    }

    @FXML
    void hirsovaVoid(ActionEvent event) {
        this.count();
    }

    @FXML
    void lasiVoid(ActionEvent event) {
        this.count();
    }

    @FXML
    void lugojVoid(ActionEvent event) {
        this.count();
    }

    @FXML
    void mehadiaVoid(ActionEvent event) {
        this.count();
    }

    @FXML
    void neamtVoid(ActionEvent event) {
        this.count();
    }

    @FXML
    void oradeaVoid(ActionEvent event) {
        this.count();
    }

    @FXML
    void pitestiVoid(ActionEvent event) {
        this.count();
    }

    @FXML
    void rimnicuVilceaVoid(ActionEvent event) {
        this.count();
    }

    @FXML
    void sibiuVoid(ActionEvent event) {
        this.count();
    }

    @FXML
    void timisoaraVoid(ActionEvent event) {
        this.count();
    }

    @FXML
    void urziceniVoid(ActionEvent event) {
        this.count();
    }

    @FXML
    void vasluiVoid(ActionEvent event) {
        this.count();
    }

    @FXML
    void zerindVoid(ActionEvent event) {
        this.count();
    }

}