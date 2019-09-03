/**
 * Sample Skeleton for 'NewUfoSightings.fxml' Controller Class
 */

package it.polito.tdp.newufosightings;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.newufosightings.model.Model;
import it.polito.tdp.newufosightings.model.StatePese;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class NewUfoSightingsController {

	private Model model;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="txtResult"
	private TextArea txtResult; // Value injected by FXMLLoader

	@FXML // fx:id="txtAnno"
	private TextField txtAnno; // Value injected by FXMLLoader

	@FXML // fx:id="txtxG"
	private TextField txtxG; // Value injected by FXMLLoader

	@FXML // fx:id="btnCreaGrafo"
	private Button btnCreaGrafo; // Value injected by FXMLLoader

	@FXML // fx:id="txtT1"
	private TextField txtT1; // Value injected by FXMLLoader

	@FXML // fx:id="txtT2"
	private TextField txtT2; // Value injected by FXMLLoader

	@FXML // fx:id="btnSimula"
	private Button btnSimula; // Value injected by FXMLLoader

	@FXML
	void doCreaGrafo(ActionEvent event) {

		int anno;
    	
    	try {
    		anno =Integer.parseInt(txtAnno.getText());
    	}catch(NumberFormatException e) {
    		txtResult.appendText("Scegli un numero");
    		return;
    	}
    	
    	if ( anno < 1906 || anno > 2014 ) {
    		txtResult.appendText("Scegli un numero tra 1906 e 2014");
    		return;
    	}
   
    	int giorni;
        	
        	try {
        		giorni =Integer.parseInt(txtxG.getText());
        	}catch(NumberFormatException e) {
        		txtResult.appendText("Scegli un numero");
        		return;
        	}
        	if ( giorni < 1 || giorni > 180 ) {
        		txtResult.appendText("Scegli un numero tra 1906 e 2014");
        		return;	
        	}
    		this.model.creaGrafo(anno,giorni);
    		txtResult.appendText("Grafo creato!"+"\n");
    		txtResult.appendText("Con #vertici: "+model.getGrafo().vertexSet().size()+"\n");
    		txtResult.appendText("E #archi: "+model.getGrafo().edgeSet().size());
    		
    		for(StatePese s : model.getVicini()){
    			txtResult.appendText("\n"+s.getS().getName()+" "+s.getPesoTot());
    	}
	}

	@FXML
	void doSimula(ActionEvent event) {

	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'NewUfoSightings.fxml'.";
		assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'NewUfoSightings.fxml'.";
		assert txtxG != null : "fx:id=\"txtxG\" was not injected: check your FXML file 'NewUfoSightings.fxml'.";
		assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'NewUfoSightings.fxml'.";
		assert txtT1 != null : "fx:id=\"txtT1\" was not injected: check your FXML file 'NewUfoSightings.fxml'.";
		assert txtT2 != null : "fx:id=\"txtT2\" was not injected: check your FXML file 'NewUfoSightings.fxml'.";
		assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'NewUfoSightings.fxml'.";

	}

	public void setModel(Model model) {
		this.model = model;

	}
}
