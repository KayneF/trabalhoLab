package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Produto;
import persistence.IProdutoDao;
import persistence.ProdutoDao;

import java.util.List;

public class ProdutoControl {

    // Lists
    private ObservableList<Produto> produtos = FXCollections.observableArrayList();

    // StringProperty
    private StringProperty id = new SimpleStringProperty();
    private StringProperty item = new SimpleStringProperty();
    private StringProperty marca = new SimpleStringProperty();
    private StringProperty modelo = new SimpleStringProperty();
    private StringProperty specs = new SimpleStringProperty();
    private StringProperty cor = new SimpleStringProperty();
    private StringProperty preco = new SimpleStringProperty();

    public StringProperty idProperty(){
        return id;
    }
    public StringProperty itemProperty(){
        return item;
    }
    public StringProperty marcaProperty(){
        return marca;
    }
    public StringProperty modeloProperty(){
        return modelo;
    }
    public StringProperty specsProperty(){
        return specs;
    }
    public StringProperty corProperty(){
        return cor;
    }
    public StringProperty precoProperty(){
        return preco;
    }

    private IProdutoDao<Produto> produtoDao = new ProdutoDao();

    // TableView
    private TableView<Produto> table = new TableView<>();

    // Table Columns
    // --------------------------------------------------------------
    public ProdutoControl(){
        TableColumn<Produto, Integer> col1 = new TableColumn<>("ID: ");
        col1.setCellValueFactory(new PropertyValueFactory<>("id"));
        col1.setMinWidth(20);
        TableColumn<Produto, String> col2 = new TableColumn<>("Item: ");
        col2.setCellValueFactory(new PropertyValueFactory<>("item"));
        col2.setMinWidth(170);
        TableColumn<Produto, String> col3 = new TableColumn<>("Marca: ");
        col3.setCellValueFactory(new PropertyValueFactory<>("marca"));
        col3.setMinWidth(170);
        TableColumn<Produto, String> col4 = new TableColumn<>("Modelo: ");
        col4.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        col4.setMinWidth(140);
        TableColumn<Produto, String> col5 = new TableColumn<>("Cor: ");
        col5.setCellValueFactory(new PropertyValueFactory<>("cor"));
        col5.setMinWidth(140);
        TableColumn<Produto, String> col6 = new TableColumn<>("Especificações: ");
        col6.setCellValueFactory(new PropertyValueFactory<>("specs"));
        col6.setMinWidth(200);
        TableColumn<Produto, Float> col7 = new TableColumn<>("Preço: ");
        col7.setCellValueFactory(new PropertyValueFactory<>("preco"));
        col7.setMinWidth(60);

        table.getColumns().setAll(col1, col2, col3, col4, col5, col6, col7);
        table.setItems(produtos);

        table.setOnMousePressed((MouseEvent event) -> {
            setRowValue();
        });

        table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            for (Produto p : produtos) {
                id.set(String.valueOf(p.getId()));
                item.set(p.getItem());
                marca.set(p.getMarca());
                modelo.set(p.getModelo());
                specs.set(p.getSpecs());
                cor.set(p.getCor());
                preco.set(String.format("%.2f", p.getPreco()));
            }
        });
    }

    public void setRowValue() {
        if (table.getSelectionModel().getSelectedItem() != null) {
            Produto selected = table.getSelectionModel().getSelectedItem();
            id.set(String.valueOf(selected.getId()));
            item.set(selected.getItem());
            marca.set(selected.getMarca());
            modelo.set(selected.getModelo());
            specs.set(selected.getSpecs());
            cor.set(selected.getCor());
            preco.set(String.valueOf(selected.getPreco()));
        }
    }

    public void limparCampos() {
        id.set("");
        item.set("");
        marca.set("");
        modelo.set("");
        specs.set("");
        cor.set("");
        preco.set("");
    }

    public TableView getTable(){
        return table;
    }

    // =====================================================
    // Adicionar Items
    public void adicionar() {
        Produto p = new Produto();
//        p.setId(Integer.parseInt(id.get()));
        p.setItem(item.get().toUpperCase());
        p.setMarca(marca.get().toUpperCase());
        p.setModelo(modelo.get().toUpperCase());
        p.setSpecs(specs.get().toUpperCase());
        p.setCor(cor.get().toUpperCase());
        p.setPreco(Float.parseFloat(preco.get()));
        produtos.add(p);
        produtoDao.inserir(p);
    }

    // =====================================================
    // Pesquisar Itens
    public void pesquisar() {
        for (Produto p : produtos) {
            if (p != null && !item.get().isEmpty() && p.getItem().contains(item.get().toUpperCase())){
                id.set(String.valueOf(p.getId()));
                item.set(p.getItem());
                marca.set(p.getMarca());
                modelo.set(p.getModelo());
                specs.set(p.getSpecs());
                cor.set(p.getCor());
                preco.set(String.valueOf(p.getPreco()));
            }
            List<Produto> lista = produtoDao.pesquisar(item.get());
            produtos.clear();
            produtos.addAll(lista);
        }
    }

    // =====================================================
    // Adicionar items
    public void alterar() {
        Produto p = new Produto();
        p.setId(Integer.parseInt(id.get()));
        p.setItem(item.get().toUpperCase());
        p.setMarca(marca.get().toUpperCase());
        p.setModelo(modelo.get().toUpperCase());
        p.setSpecs(specs.get().toUpperCase());
        p.setCor(cor.get().toUpperCase());
        p.setPreco(Float.parseFloat(preco.get()));
        produtoDao.alterar(p);
        listarItems();
    }

    // =====================================================
    // Excluir items
    public void excluir() {
        Produto p = new Produto();
        p.setId(Integer.parseInt(id.get()));
        p.setItem(item.get().toUpperCase());
        p.setMarca(marca.get().toUpperCase());
        p.setModelo(modelo.get().toUpperCase());
        p.setSpecs(specs.get().toUpperCase());
        p.setCor(cor.get().toUpperCase());
        p.setPreco(Float.parseFloat(preco.get()));
        produtoDao.excluir(p);
        listarItems();
    }

    // =====================================================
    // Exibe Lista Produtos
    public void listarItems() {
        for (Produto p : produtos) {
            if (p != null) {
                id.set(String.valueOf(p.getId()));
                item.set(p.getItem());
                marca.set(p.getMarca());
                modelo.set(p.getModelo());
                specs.set(p.getSpecs());
                cor.set(p.getCor());
                preco.set(String.valueOf(p.getPreco()));
            }
            List<Produto> lista = produtoDao.listarItems();
            produtos.clear();
            produtos.addAll(lista);
            limparCampos();
        }
    }
}
