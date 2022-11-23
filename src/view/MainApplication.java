package view;

import controller.ProdutoControl;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainApplication extends Application {

    // =====================================================
    // Scene 1 Variables
    // =====================================================

    // Labels
    private Label labelId = new Label("ID: ");
    private Label labelItem = new Label("Item: ");
    private Label labelMarca = new Label("Marca: ");
    private Label labelModelo = new Label("Modelo: ");
    private Label labelCor = new Label("Cor: ");
    private Label labelPreco = new Label("Preço: ");

    // Text Fields
    private TextField fieldId = new TextField();
    private TextField fieldItem = new TextField();
    private TextField fieldMarca = new TextField();
    private TextField fieldModelo = new TextField();
    private TextField fieldCor = new TextField();
    private TextField fieldPreco = new TextField();
    private TextArea fieldSpecs = new TextArea();

    // Buttons
    private Button btnAdicionar = new Button("Adicionar");
    private Button btnAlterar = new Button("Alterar");
    private Button btnPesquisar = new Button("Pesquisar");
    private Button btnListar = new Button("Listar");
    private Button btnExcluir = new Button("Excluir");
    private Button btnLimpar = new Button("Limpar");

    // Controller
    ProdutoControl produtoControl = new ProdutoControl();

    // Main Stage
    @Override
    public void start(Stage primaryStage) throws Exception {

        // Main Panel
        BorderPane mainPanel = new BorderPane();
        Scene scene1 = new Scene(mainPanel,1000,768);
        mainPanel.setStyle("-fx-font: 14 Arial;");
        mainPanel.setBottom(produtoControl.getTable());

        // Top Panel
        GridPane topPanel = new GridPane();
        mainPanel.setTop(topPanel);

        // |=====================================================|
        // |                       Scene                         |
        // |=====================================================|
        // Top Panel Left
        GridPane textFieldPanelLeft = new GridPane();
        topPanel.add(textFieldPanelLeft,0,0);
        textFieldPanelLeft.setPadding(new Insets(30));
        textFieldPanelLeft.setAlignment(Pos.TOP_LEFT);
        textFieldPanelLeft.setHgap(10);
        textFieldPanelLeft.setVgap(10);
        textFieldPanelLeft.setStyle("-fx-font: 18 Arial;");

        // Labels Left
        textFieldPanelLeft.add(labelId,0,1);
        textFieldPanelLeft.add(labelItem,0,2);
        textFieldPanelLeft.add(labelMarca,0,3);
        textFieldPanelLeft.add(labelModelo,0,4);
        textFieldPanelLeft.add(labelCor,0,5);
        textFieldPanelLeft.add(labelPreco,0,6);

        // Text Fields Left
        textFieldPanelLeft.add(fieldId,1,1);
        fieldId.setPrefWidth(300);
        fieldId.setEditable(false);
        textFieldPanelLeft.add(fieldItem,1,2);
        fieldItem.setPrefWidth(300);
        textFieldPanelLeft.add(fieldMarca,1,3);
        fieldMarca.setPrefWidth(300);
        textFieldPanelLeft.add(fieldModelo,1,4);
        fieldModelo.setPrefWidth(300);
        textFieldPanelLeft.add(fieldCor,1,5);
        fieldCor.setPrefWidth(300);
        textFieldPanelLeft.add(fieldPreco,1,6);
        fieldPreco.setPrefWidth(300);

        // =====================================================
        // Top Panel Right
        GridPane textFieldPanelRight = new GridPane();
        topPanel.add(textFieldPanelRight,1,0);
        textFieldPanelRight.setPadding(new Insets(20));
        textFieldPanelRight.setAlignment(Pos.TOP_LEFT);
        textFieldPanelRight.setHgap(10);
        textFieldPanelRight.setVgap(10);
        textFieldPanelRight.setStyle("-fx-font: 18 Arial;");

        // Labels Right
        Label specsLabel = new Label("Especificações: ");
        textFieldPanelRight.add(specsLabel,0,1);

        // Text Fields Right
        textFieldPanelRight.add(fieldSpecs,1,1);
        fieldSpecs.setPrefWidth(300);
        fieldSpecs.setEditable(false);

        // =====================================================
        // Center Panel (Buttons)
        GridPane buttonPanel = new GridPane();
        mainPanel.setCenter(buttonPanel);
        buttonPanel.setPadding(new Insets(20));
        buttonPanel.setAlignment(Pos.CENTER_LEFT);
        buttonPanel.setHgap(10);
        buttonPanel.setVgap(10);
        buttonPanel.setStyle("-fx-font: 18 Arial;");

        // Buttons
        buttonPanel.add(btnAdicionar,0,4);
        btnAdicionar.setMinSize(150,40);
        buttonPanel.add(btnPesquisar,1,4);
        btnPesquisar.setMinSize(150,40);
        buttonPanel.add(btnAlterar,2,4);
        btnAlterar.setMinSize(150,40);
        buttonPanel.add(btnExcluir,3,4);
        btnExcluir.setMinSize(150,40);
        buttonPanel.add(btnListar,4,4);
        btnListar.setMinSize(150,40);
        buttonPanel.add(btnLimpar,5,4);
        btnLimpar.setMinSize(150,40);

        // Bindings
        Bindings.bindBidirectional(produtoControl.idProperty(), fieldId.textProperty());
        Bindings.bindBidirectional(produtoControl.itemProperty(), fieldItem.textProperty());
        Bindings.bindBidirectional(produtoControl.marcaProperty(), fieldMarca.textProperty());
        Bindings.bindBidirectional(produtoControl.modeloProperty(), fieldModelo.textProperty());
        Bindings.bindBidirectional(produtoControl.specsProperty(), fieldSpecs.textProperty());
        Bindings.bindBidirectional(produtoControl.corProperty(), fieldCor.textProperty());
        Bindings.bindBidirectional(produtoControl.precoProperty(), fieldPreco.textProperty());

        // Button Actions
        btnAdicionar.setOnAction(event -> produtoControl.adicionar());
        btnPesquisar.setOnAction(event -> produtoControl.pesquisar());
        btnAlterar.setOnAction(event -> produtoControl.alterar());
        btnListar.setOnAction(event -> produtoControl.listarItems());
        btnExcluir.setOnAction(event -> produtoControl.excluir());
        btnLimpar.setOnAction(event -> produtoControl.limparCampos());

        // Stage Starter
        // =====================================================
        primaryStage.setScene(scene1);
        primaryStage.setTitle("NOME DA LOJA");
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(MainApplication.class, args);
    }
}
