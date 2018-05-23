/**
 * Sample Skeleton for 'DizionarioGraph.fxml' Controller Class
 */

package it.polito.tdp.dizionariograph;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.dizionariograph.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioGraphController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtNumberOfCharacters"
    private TextField txtNumberOfCharacters; // Value injected by FXMLLoader

    @FXML // fx:id="txtWords"
    private TextField txtWords; // Value injected by FXMLLoader

    @FXML // fx:id="btnGeneraGrafo"
    private Button btnGeneraGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnTrovaVicini"
    private Button btnTrovaVicini; // Value injected by FXMLLoader

    @FXML // fx:id="btnTrovaGrafo"
    private Button btnTrovaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="btnReset"
    private Button btnReset; // Value injected by FXMLLoader

	private Model m;

    @FXML
    void GeneraGrafo(ActionEvent event) {

    	String numLettereString = this.txtNumberOfCharacters.getText();
    	int numLettere;

    	/**
    	 * controllo del formato int
    	 */
    	try {
			
			numLettere = Integer.parseInt(numLettereString);	
			
		}catch(NumberFormatException e){
			this.txtResult.appendText("il formato inserito non è corretto\n");
			return;
			//break;
		}
    	
    	/**
    	 * controllo dell'esistenza di parole con un determinata
    	 * lunghezza 
    	 */
    	if(!m.exist()) {
			this.txtResult.appendText(String.format("le parole con %d caratteri non sono prresenti nel DataBase\n", numLettere));
			return;
		}	
    	
    	
		this.m.createGraph(numLettere);
    }

    @FXML
    void Reset(ActionEvent event) {

    }

    @FXML
    void TrovaGrafo(ActionEvent event) {

    }

    @FXML
    void TrovaVicini(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtNumberOfCharacters != null : "fx:id=\"txtNumberOfCharacters\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtWords != null : "fx:id=\"txtWords\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnGeneraGrafo != null : "fx:id=\"btnGeneraGrafo\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnTrovaVicini != null : "fx:id=\"btnTrovaVicini\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnTrovaGrafo != null : "fx:id=\"btnTrovaGrafo\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";

    }

	public void setModel(Model model) {
		this.m = model;
	}
}
