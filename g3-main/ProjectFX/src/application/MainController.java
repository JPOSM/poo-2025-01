package application;

import javafx.event.ActionEvent;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Node;

public class MainController {
	
	@FXML
	TextField nameTextField;
	@FXML
	PasswordField namePasswordField;
	@FXML
	Button loginButton;
	@FXML
	Label nameLabel;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	//listas de dados sobre os usuarios organizados por indice i
	private String usernames[]= {"i", "arthur_jardim","joao_moynier","pablo_henrique"};
	private String passwords[]= {"i", "arthurjardim","joaomoynier","pablohenrique"};
	private String engineers[]= {"Ítalo Pires", "Arthur Jardim", "João Moynier", "Pablo Henrique"};
	private String qualifications[]= {"Engenheiro Eletricista","Engenheiro Eletricista","Engenheiro Eletricista","Engenheiro Eletricista"};
	
	@FXML //tratamento de acesso ao servico
	public void login(ActionEvent event) throws IOException{
		
		String username = nameTextField.getText();
		String password = namePasswordField.getText();
		
		//o 'for' em conjunto com o 'if' serve para autenticar o usuario com sua senha particular
		for (int i = 0; i<=3; i++) {
			if (usernames[i].equals(username)) {
				if (passwords[i].equals(password)) {
					
					//caso o acesso seja autorizado, a segunda cena é carregada
					FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene1.fxml"));
					root = loader.load();
					Scene1Controller scene1controller = loader.getController();
					scene1controller.displayData(engineers[i], usernames[i], qualifications[i]);
					
				    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
					scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
					
					System.out.println("login feito com SUCESSO!");
					return;
				}
			}
			//tratamento de falha na verificacao de login
			if (i==3) {
				nameLabel.setText("login FALHOU, por favor tente novamente!");
			}
		}
	}
}