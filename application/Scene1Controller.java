package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.net.URL;
import java.sql.*;
import model.util.CriadorHTML;
import model.util.LixoOrganico;
import model.util.LixoSeco;

import java.util.List;
import model.database.*;
import java.time.format.DateTimeFormatter;


public class Scene1Controller implements Initializable{
	
	@FXML
	TabPane tabPane;
	
	//nodes da primeira aba
	@FXML
	Label labelNomeUsuario;
	@FXML
	Label labelUsername;
	@FXML
	Label labelQualificacao;
	@FXML
	Button logoutButton;
	
	//nodes da segunda aba
	@FXML
	TextField textFieldLote;
	@FXML
	TextField textFieldLixoGerado;
	@FXML
	TextField textFieldVolumeDeAgua;
	@FXML 
	TextField textFieldAreaDeVazao;
	@FXML
	TextArea textFieldComentario;
	@FXML
	Button buttonGerar;
	@FXML
	Button buttonAtualizar;
	@FXML
	Button buttonDeletar;
	@FXML
	Button buttonVisualizar;
	
	//nodes da terceira aba
		@FXML
		TextField textFieldLote1;
		@FXML
		TextField textFieldMadeira;
		@FXML
		TextField textFieldPapeis;
		@FXML
		TextField textFieldTecidos;
		@FXML
		TextField textFieldVolumeDeAgua1;
		@FXML 
		TextField textFieldAreaDeVazao1;
		@FXML
		TextArea textFieldComentario1;
		@FXML
		Button buttonGerar1;
		@FXML
		Button buttonAtualizar1;
		@FXML
		Button buttonDeletar1;
		@FXML
		Button buttonVisualizar1;
	
	
	//nodes da tableView
	@FXML
	TableView<Dados> dados;
	@FXML
	TableColumn<Dados, Integer> idColumn;
	@FXML
	TableColumn<Dados, String> dia_relatorioColumn;
	@FXML
	TableColumn<Dados, String> loteColumn;
	@FXML
	TableColumn<Dados, String> tipoColumn;
	@FXML
	TableColumn<Dados, Double> toneladas_lixo_organicoColumn;
	@FXML
	TableColumn<Dados, Double> area_de_vazaoColumn;
	@FXML
	TableColumn<Dados, Double> volumeColumn;
	
	//Nodes da outra table
	@FXML
	TableView<Dados> dados1;
	@FXML
	TableColumn<Dados, Integer> idColumn1;
	@FXML
	TableColumn<Dados, String> dia_relatorioColumn1;
	@FXML
	TableColumn<Dados, String> loteColumn1;
	@FXML
	TableColumn<Dados, String> tipoColumn1;
	@FXML
	TableColumn<Dados, Double> madeiraColumn1;
	@FXML
	TableColumn<Dados, Double> papeisColumn1;
	@FXML
	TableColumn<Dados, Double> tecidosColumn1;
	@FXML
	TableColumn<Dados, Double> area_de_vazaoColumn1;
	@FXML
	TableColumn<Dados, Double> volumeColumn1;
	
	
	Database database;
	DadosRepository repositorio;
	
	//declaracao do stage
	Stage stage;
	
	private void habilitadorBotoes(boolean adicionar, boolean atualizar, boolean delear, boolean visualizar)
    {
    	buttonGerar.setDisable(adicionar);
    	buttonAtualizar.setDisable(atualizar);
    	buttonDeletar.setDisable(delear);
    	buttonVisualizar.setDisable(visualizar);
    }
    
    private void habilitadorCampos(boolean desabilidado)
    {
    	textFieldLote.setDisable(desabilidado);
    	textFieldLixoGerado.setDisable(desabilidado);
    	textFieldVolumeDeAgua.setDisable(desabilidado);
    	textFieldAreaDeVazao.setDisable(desabilidado);
    	textFieldComentario.setDisable(desabilidado);
    }
    
    private void limparCampos() {
    	textFieldLote.setText("");
    	textFieldLixoGerado.setText("");
    	textFieldVolumeDeAgua.setText("");
    	textFieldAreaDeVazao.setText("");
    	textFieldComentario.setText("");
    }
	//metodo de dados do usuario
	public void displayData(String nome, String username, String qualificacao) {
		labelNomeUsuario.setText("Bem Vindo, "+nome+".");
		labelUsername.setText("Username: "+username+".");
		labelQualificacao.setText("Qualificação: "+qualificacao+".");
	}
	
	//tratamento para quando o usuario fechar o programa 
    public void logout(ActionEvent event) {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("Você está preste a SAIR!");
		alert.setContentText("Você quer salvar antes de FECHAR o programa?");
		
		if (alert.showAndWait().get() == ButtonType.OK) {
			stage = (Stage) tabPane.getScene().getWindow();
			System.out.println("Você DESCONECTOU com SUCESSO!");
			stage.close();
		}
	}
    
    
    
    private Dados modelToView(Dados d) {
    	Dados dado = new Dados();
    	d.setArea(dado.getArea());
    	d.setData(dado.getData());
    	d.setVolume(dado.getVolume());
    	d.setLote(dado.getLote());
        return d;
    }
    private ObservableList<Dados> loadAllDados() {
    	//Database database = new Database("db");
    	//DadosRepository repositorio = new DadosRepository(database);
    	ObservableList<Dados> lista = FXCollections.observableArrayList();
        List<Dados> listaFromDatabase = repositorio().loadAll();
        for(Dados dado: listaFromDatabase) {
            lista.add(modelToView(dado));
        }
        return lista;
    }
    
    private void handleDadoSelected(Dados newSelection, Dados.TipoLixo tipo) {
        if (newSelection != null && tipo == Dados.TipoLixo.ORGANICO)
        {
            textFieldLote.setText(newSelection.getLote());
            textFieldAreaDeVazao.setText(Double.toString(newSelection.getArea()));
            textFieldVolumeDeAgua.setText(Double.toString(newSelection.getVolume()));
            textFieldLixoGerado.setText(Double.toString(newSelection.getToneladas()));
    		}
        else if (newSelection != null && tipo == Dados.TipoLixo.SECO)
    	{
            textFieldLote1.setText(newSelection.getLote());
            textFieldAreaDeVazao1.setText(Double.toString(newSelection.getArea()));
            textFieldVolumeDeAgua1.setText(Double.toString(newSelection.getVolume()));
            textFieldMadeira.setText(Double.toString(newSelection.getToneladasMadeira()));
            textFieldPapeis.setText(Double.toString(newSelection.getToneladasPapel()));
            textFieldTecidos.setText(Double.toString(newSelection.getToneladasTecido()));
            //textFieldLixoGerado.setText(Double.toString(newSelection.getToneladas()));
    	}
    }
    
    private void recarregarTable()
    {
    	dados.getItems().clear();
    	dados1.getItems().clear();
    	
    	
    	idColumn.setCellValueFactory(new PropertyValueFactory<Dados, Integer>("id"));
		dia_relatorioColumn.setCellValueFactory(new PropertyValueFactory<Dados, String>("dataFormatted"));
		loteColumn.setCellValueFactory(new PropertyValueFactory<Dados, String>("lote"));
		tipoColumn.setCellValueFactory(new PropertyValueFactory<Dados, String>("tipoLixo"));
		toneladas_lixo_organicoColumn.setCellValueFactory(new PropertyValueFactory<Dados, Double>("toneladas"));
		area_de_vazaoColumn.setCellValueFactory(new PropertyValueFactory<Dados, Double>("area"));
		volumeColumn.setCellValueFactory(new PropertyValueFactory<Dados, Double>("volume"));
		
		dados.setItems(loadAllDados());
        dados.getSelectionModel().selectedItemProperty().addListener((observableValue, oldSelection, newSelection) -> handleDadoSelected(newSelection, Dados.TipoLixo.ORGANICO));

        System.out.println("TAMANHO " + dados.getItems().size());
        
        for(int i = 0; i < dados.getItems().size(); i++)
		{
			if (dados.getItems().get(i).getTipo() == Dados.TipoLixo.SECO)
			{		
				System.out.println(dados.getItems().get(i).getTipo() + "AAAAAAAAAAA");
				dados.getItems().remove(i);
				i = 0;
			}
		}
        
        
        
        
        
        
        idColumn1.setCellValueFactory(new PropertyValueFactory<Dados, Integer>("id"));
		dia_relatorioColumn1.setCellValueFactory(new PropertyValueFactory<Dados, String>("dataFormatted"));
		loteColumn1.setCellValueFactory(new PropertyValueFactory<Dados, String>("lote"));
		tipoColumn1.setCellValueFactory(new PropertyValueFactory<Dados, String>("tipoLixo"));
		madeiraColumn1.setCellValueFactory(new PropertyValueFactory<Dados, Double>("toneladasMadeira"));
		tecidosColumn1.setCellValueFactory(new PropertyValueFactory<Dados, Double>("toneladasTecido"));
		papeisColumn1.setCellValueFactory(new PropertyValueFactory<Dados, Double>("toneladasPapel"));
		area_de_vazaoColumn1.setCellValueFactory(new PropertyValueFactory<Dados, Double>("area"));
		volumeColumn1.setCellValueFactory(new PropertyValueFactory<Dados, Double>("volume"));
		
		dados1.setItems(loadAllDados());
        dados1.getSelectionModel().selectedItemProperty().addListener((observableValue, oldSelection, newSelection) -> handleDadoSelected(newSelection, Dados.TipoLixo.SECO));
    
        for(int i = 0; i < dados1.getItems().size(); i++)
		{
			if (dados1.getItems().get(i).getTipo() == Dados.TipoLixo.ORGANICO)
			{
				dados1.getItems().remove(i);
				i = 0;
			}
		}
    }
    
    private DadosRepository repositorio()
    {
    	if(database == null)
    	{
    	database = new Database("db");
    	repositorio = new DadosRepository(database);
    	}
    	return repositorio;
    }
    
    
    
    //inicializacao da tableview
    @FXML
	public void initialize(URL url, ResourceBundle resourceBundle) {
		recarregarTable();
    }
    
    // COLOQUE METODOS PARA DESABILITAR BOTOES E CAMPOS AQUI
	
    //metodo para criar relatórios
    public void gerarOrganico() {
    	
    	//os textos nos TextFields sao salvos dentro de variaveis
    	String lote = textFieldLote.getText();
    	double lixoGerado = Double.parseDouble(textFieldLixoGerado.getText());
    	double areaDeVazao = Double.parseDouble(textFieldAreaDeVazao.getText());
    	double volumeDeAgua = Double.parseDouble(textFieldVolumeDeAgua.getText());
    	String comentario = textFieldComentario.getText();
    	
    	//inicializacao de um novo dado a ser salvo
    	Dados dado = new Dados();
    	
    	//sets do novo dado
    	dado.setLote(lote);
    	dado.setToneladas(lixoGerado);
    	dado.setArea(areaDeVazao);
    	dado.setVolume(volumeDeAgua);
    	
    	dado.setTipo(Dados.TipoLixo.ORGANICO);
    	
    	//criacao do novo dado na tableView
    	ObservableList<Dados> list = dados.getItems();
    	list.add(dado);
    	dados.setItems(list);
    	
    	
    	dado.setComentario(comentario);
    	
    	//sets de calculos complexos do novo dado
    	LixoOrganico lixoOrg = new LixoOrganico();
    	lixoOrg.Processo(lote, lixoGerado, areaDeVazao, volumeDeAgua);
    	dado.setEnergiaLixo(lixoOrg.energia_lixo);
    	dado.setEnergiaGerada(lixoOrg.energia_produzida_gerador);
    	dado.setEnergiaPerdida(Math.abs(dado.getEnergiaLixo()) - dado.getEnergiaGerada());
    	
    	//sets complexos de data de geracao do novo dado
    	LocalDate ld = LocalDate.now();
    	java.util.Date dia_util = Date.from(ld.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    	java.sql.Date dia_sql = new java.sql.Date(dia_util.getTime());
    	dado.setData(dia_sql);
    	
    	//instanciacao do banco de dados
    	//Database database = new Database("db");
    	//DadosRepository repositorio = new DadosRepository(database);
    	
    	//salvamento do novo dado em um banco de dados
    	repositorio().create(dado);
    	
    	System.out.println("Relatório Gerado!");	
    }
    
public void gerarSeco() {
    	
    	//os textos nos TextFields sao salvos dentro de variaveis
    	String lote = textFieldLote1.getText();
    	double madeiraGerado = Double.parseDouble(textFieldMadeira.getText());
    	double papelGerado = Double.parseDouble(textFieldPapeis.getText());
    	double tecidoGerado = Double.parseDouble(textFieldTecidos.getText());
    	double areaDeVazao = Double.parseDouble(textFieldAreaDeVazao1.getText());
    	double volumeDeAgua = Double.parseDouble(textFieldVolumeDeAgua1.getText());
    	String comentario = textFieldComentario1.getText();
    	
    	//inicializacao de um novo dado a ser salvo
    	Dados dado = new Dados();
    	
    	//sets do novo dado
    	dado.setLote(lote);
    	dado.setToneladasMadeira(madeiraGerado);
    	dado.setToneladasPapel(papelGerado);
    	dado.setToneladasTecido(tecidoGerado);
    	dado.setArea(areaDeVazao);
    	dado.setVolume(volumeDeAgua);
    	
    	dado.setTipo(Dados.TipoLixo.SECO);
    	
    	//criacao do novo dado na tableView
    	ObservableList<Dados> list = dados1.getItems();
    	list.add(dado);
    	dados1.setItems(list);
    	
    	
    	dado.setComentario(comentario);
    	
    	//sets de calculos complexos do novo dado
    	LixoSeco lixoSec = new LixoSeco(madeiraGerado, tecidoGerado, papelGerado);
    	lixoSec.Processo(lote, areaDeVazao, volumeDeAgua);
    	dado.setEnergiaLixo(lixoSec.energia_total);
    	dado.setEnergiaGerada(lixoSec.energia_produzida_gerador);
    	dado.setEnergiaPerdida(Math.abs(dado.getEnergiaLixo()) - dado.getEnergiaGerada());
    	
    	//sets complexos de data de geracao do novo dado
    	LocalDate ld = LocalDate.now();
    	java.util.Date dia_util = Date.from(ld.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    	java.sql.Date dia_sql = new java.sql.Date(dia_util.getTime());
    	dado.setData(dia_sql);
    	
    	//instanciacao do banco de dados
    	//Database database = new Database("db");
    	//DadosRepository repositorio = new DadosRepository(database);
    	
    	//salvamento do novo dado em um banco de dados
    	repositorio().create(dado);
    	
    	System.out.println("Relatório Gerado!");	
    }
    
    //metodo para alterar os valores de um dado ja existente
    public void atualizar() {

    	if(dados.getSelectionModel().getSelectedItem() != null)
    	{
    		
    	//selecao do objeto na tableView
    	int idSelecionado = dados.getSelectionModel().getSelectedIndex();
    	
    	// COLOQUE TRATAMENTO DE ACESSIBILIDADE A BOTOES AQUI DENTRO
    	
    	String lote = textFieldLote.getText();
    	double lixoGerado = Double.parseDouble(textFieldLixoGerado.getText());
    	double areaDeVazao = Double.parseDouble(textFieldAreaDeVazao.getText());
    	double volumeDeAgua = Double.parseDouble(textFieldVolumeDeAgua.getText());
    	String comentario = textFieldComentario.getText();
    	
    	//Database database = new Database("db");
    	//DadosRepository repositorio = new DadosRepository(database);
    	
    	//geracao de uma copia do dado a partir do seu id no banco de dados
    	//Dados dado = repositorio.loadFromId(idSelecionado);
    	
    	Dados dado = dados.getSelectionModel().getSelectedItem();
    	
    	//sets do dado copiado com os novos parametros
    	dado.setLote(lote);
    	dado.setToneladas(lixoGerado);
    	dado.setArea(areaDeVazao);
    	dado.setVolume(volumeDeAgua);
    	
    	// ATUALIZAR O DADO NA TABLEVIEW
    	
    	dado.setComentario(comentario);
    	
    	//chamamento do metodo update() para atualizar o dado verdadeiro no banco de dados
    	repositorio().update(dado);
    	
    	System.out.println("Relatório Atualizado!");
    	
    	
    	
    	
    	ObservableList<Dados> list = dados.getItems();
    	//list.add(dado);
    	list.set(idSelecionado, dado);
    	dados.setItems(list);
    	//dados1.setItems(list);    
    	}
    	
    	
    	if(dados1.getSelectionModel().getSelectedItem() != null)
    	{
    		
    	//selecao do objeto na tableView
    	int idSelecionado = dados1.getSelectionModel().getSelectedIndex();
    	
    	// COLOQUE TRATAMENTO DE ACESSIBILIDADE A BOTOES AQUI DENTRO
    	
    	String lote = textFieldLote1.getText();
    	double madeiraGerado = Double.parseDouble(textFieldMadeira.getText());
    	double papelGerado = Double.parseDouble(textFieldPapeis.getText());
    	double tecidoGerado = Double.parseDouble(textFieldTecidos.getText());
    	double areaDeVazao = Double.parseDouble(textFieldAreaDeVazao1.getText());
    	double volumeDeAgua = Double.parseDouble(textFieldVolumeDeAgua1.getText());
    	String comentario = textFieldComentario.getText();
    	
    	//Database database = new Database("db");
    	//DadosRepository repositorio = new DadosRepository(database);
    	
    	//geracao de uma copia do dado a partir do seu id no banco de dados
    	//Dados dado = repositorio.loadFromId(idSelecionado);
    	
    	Dados dado = dados1.getSelectionModel().getSelectedItem();
    	
    	//sets do dado copiado com os novos parametros
    	dado.setLote(lote);
    	dado.setToneladasMadeira(madeiraGerado);
    	dado.setToneladasPapel(papelGerado);
    	dado.setToneladasTecido(tecidoGerado);
    	dado.setArea(areaDeVazao);
    	dado.setVolume(volumeDeAgua);
    	
    	// ATUALIZAR O DADO NA TABLEVIEW
    	
    	dado.setComentario(comentario);
    	
    	//chamamento do metodo update() para atualizar o dado verdadeiro no banco de dados
    	repositorio().update(dado);
    	
    	System.out.println("Relatório Atualizado!");
    	
    	
    	
    	
    	ObservableList<Dados> list = dados1.getItems();
    	//list.add(dado);
    	list.set(idSelecionado, dado);
    	//dados.setItems(list);
    	dados1.setItems(list);    
    	}
    }
    
    public void deletar() {
    	
    	//selecao do objeto na tableView
    	//int idSelecionado = dados.getSelectionModel().getSelectedIndex()+1;
    	if(dados.getSelectionModel().getSelectedItem() != null)
    	{
    		Dados dado = dados.getSelectionModel().getSelectedItem();
    	
    	
    		//System.out.println(idSelecionado);
    	
    		// TIRAR DE DENTRO DO METODO?
    		//Database database = new Database("db");
    		//DadosRepository repositorio = new DadosRepository(database);
    	
    		//exclui o dado real do banco de dados
    		repositorio().delete(dado);
    	
    		//remocao da tableView
    		//dados.getItems().remove(idSelecionado-1);
    		//dados.getItems().remove(dado);
    		//dados1.getItems().remove(dado);
    	
    		recarregarTable();
    	
    		System.out.println("Relatório Deletado!");
    	}
    	else if(dados1.getSelectionModel().getSelectedItem() != null)
    	{
    		Dados dado = dados1.getSelectionModel().getSelectedItem();
    	
    	
    		//System.out.println(idSelecionado);
    	
    		// TIRAR DE DENTRO DO METODO?
    		//Database database = new Database("db");
    		//DadosRepository repositorio = new DadosRepository(database);
    	
    		//exclui o dado real do banco de dados
    		repositorio().delete(dado);
    	
    		//remocao da tableView
    		//dados.getItems().remove(idSelecionado-1);
    		//dados.getItems().remove(dado);
    		//dados1.getItems().remove(dado);
    	
    		recarregarTable();
    	
    		System.out.println("Relatório Deletado!");
    	}
    }
    
    //metodo para mostrar html
    public void visualizar() {
    	//selecao do objeto na tableView
    	//int idSelecionado = dados.getSelectionModel().getSelectedIndex();
    	if(dados.getSelectionModel().getSelectedItem() != null)
    	{
    		Dados dado = dados.getSelectionModel().getSelectedItem();
    		
    		// ESCREVER METODOS CORRETOS
        	CriadorHTML.CriarHTML(dado);
        	CriadorHTML.AbrirHTML();
        	System.out.println("Relatório HTML Gerado!!");
    	}
    	else if (dados1.getSelectionModel().getSelectedItem() != null)
    	{
    		Dados dado = dados1.getSelectionModel().getSelectedItem();
    		
    		// ESCREVER METODOS CORRETOS
        	CriadorHTML.CriarHTML(dado);
        	CriadorHTML.AbrirHTML();
        	System.out.println("Relatório HTML Gerado!!");
    	}
    }
   
   
}



