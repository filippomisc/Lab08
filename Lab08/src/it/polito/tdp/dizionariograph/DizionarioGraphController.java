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
    private TextField txtWord; // Value injected by FXMLLoader

    @FXML // fx:id="btnGeneraGrafo"
    private Button btnGeneraGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnTrovaVicini"
    private Button btnTrovaVicini; // Value injected by FXMLLoader

    @FXML // fx:id="btnTrovaGrado"
    private Button btnTrovaGrado; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="btnReset"
    private Button btnReset; // Value injected by FXMLLoader

	private Model m;

    @FXML
    void GeneraGrafo(ActionEvent event) {

    	this.btnReset.setDisable(false);
    	
    	this.btnTrovaVicini.setDisable(false);
    	this.txtWord.setDisable(false);
    	
    	//PARTE RIGUARDANTE LUNGHEZZA PAROLA
    	
    	
    	String numLettereString = this.txtNumberOfCharacters.getText();
    	int numLettere;

    	/**
    	 * controllo del formato int
    	 */
    	try {
			
			numLettere = Integer.parseInt(numLettereString);	
			
		}catch(NumberFormatException e){
			this.Reset(event);
			this.txtResult.appendText("il formato inserito non è corretto\n");
			return;
			//break;
		}
    	
    	/**
    	 * controllo dell'esistenza di parole con un determinata
    	 * lunghezza (superfluo)
    	 */
//    	if(!m.existLenght()) {
//			this.txtResult.appendText(String.format("le parole con %d caratteri non sono prresenti nel DataBase\n", numLettere));
//			return;
// 		}	
    	
    	

    	
		this.m.createGraph(numLettere);
		this.txtResult.setText(String.format("il grafo è stato creato con %d vertici e %d archi.\n", m.getVertex(), m.getEdges()));
    }

    @FXML
    void Reset(ActionEvent event) {
    	this.txtWord.clear();
    	this.txtNumberOfCharacters.clear();
    	this.txtResult.clear();
    	
    	
    	this.btnReset.setDisable(true);
    	this.btnTrovaGrado.setDisable(true);
    	this.btnTrovaVicini.setDisable(true);
    	this.txtWord.setDisable(true);
//    	m.setGraph(null);
    }

    @FXML
    void TrovaGrado(ActionEvent event) {
    	
    	String risultato = m.findMaxDegree();
    	this.txtResult.appendText(risultato);

    }

    @FXML
    void TrovaVicini(ActionEvent event) {
    	
    	this.btnTrovaGrado.setDisable(false);
    	
    	
    	//PARTE RIGUARDANTE PAROLA
    	
   	String parola = this.txtWord.getText();
	
   	/**
   	 * controlla che il numero di parole sia uguale alla
   	 * lunghezza della parola inserita
   	 */
	if(parola.length()!=m.getNumLettere()) {
		this.btnTrovaGrado.setDisable(true);
		this.txtResult.appendText("la parola inserita non corrisponde con la lunghezza prefissata\n");
		return;
	}
	
	/**
	 * controlla che la parola esista nel database
	 */
	if(!m.existWord(parola)) {
		this.btnTrovaGrado.setDisable(true);
		this.txtResult.appendText(String.format("la parola %s non è presente nel DataBase\n", parola));
		return;
	}
	

	String result =m.displayNeighbours(parola).toString();
	this.txtResult.appendText("I i vicini sono:\n" + result + "\n");
	
	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtNumberOfCharacters != null : "fx:id=\"txtNumberOfCharacters\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtWord != null : "fx:id=\"txtWords\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnGeneraGrafo != null : "fx:id=\"btnGeneraGrafo\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnTrovaVicini != null : "fx:id=\"btnTrovaVicini\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnTrovaGrado != null : "fx:id=\"btnTrovaGrado\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";

    }

	public void setModel(Model model) {
		this.m = model;
	}
}
