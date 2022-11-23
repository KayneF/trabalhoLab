package persistence;

import model.Produto;

import java.util.List;

public interface IProdutoDao<T> {

    void inserir(Produto produto);
    List<Produto> pesquisar(String nome);
    void alterar(Produto produto);
    void excluir(Produto produto);
    List<Produto> listarItems();

}
