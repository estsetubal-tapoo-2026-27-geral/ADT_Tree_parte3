package pt.unips.estsetubal.tapoo.algorithms;

import pt.unips.estsetubal.tapoo.adt.Position;
import pt.unips.estsetubal.tapoo.adt.Tree;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/** Algoritmos recursivos a desenvolver na aula A2.3. */
public class TreeAlgorithms<E> {
    private final Tree<E> tree;

    public TreeAlgorithms(Tree<E> tree) {
        this.tree = Objects.requireNonNull(tree, "A árvore não pode ser null.");
    }

    // Percurso A — Estrutura

    public int countLeaves() {
        // TODO A2.3: tratar a árvore vazia e iniciar a recursividade na raiz.
        throw new UnsupportedOperationException("Método countLeaves por implementar");
    }

    public int height() {
        // TODO A2.3: devolver -1 para a árvore vazia.
        throw new UnsupportedOperationException("Método height por implementar");
    }

    public List<E> elementsAtLevel(int level) {
        // TODO A2.3: transportar o nível atual e acumular os resultados.
        throw new UnsupportedOperationException("Método elementsAtLevel por implementar");
    }

    // Percurso B — Pesquisa

    public boolean contains(E target) {
        // TODO A2.3: terminar assim que a primeira ocorrência for encontrada.
        throw new UnsupportedOperationException("Método contains por implementar");
    }

    public int countMatching(Predicate<E> predicate) {
        Objects.requireNonNull(predicate, "O predicado não pode ser null.");
        // TODO A2.3: contar a posição atual e combinar os resultados dos filhos.
        throw new UnsupportedOperationException("Método countMatching por implementar");
    }

    public List<E> pathTo(E target) {
        // TODO A2.3: construir o caminho e aplicar backtracking quando necessário.
        throw new UnsupportedOperationException("Método pathTo por implementar");
    }

    // Percurso C — Percursos

    public List<E> preOrder() {
        // TODO A2.3: processar cada posição antes dos seus filhos.
        throw new UnsupportedOperationException("Método preOrder por implementar");
    }

    public List<E> postOrder() {
        // TODO A2.3: processar cada posição depois dos seus filhos.
        throw new UnsupportedOperationException("Método postOrder por implementar");
    }

    public String toIndentedString() {
        // TODO A2.3: transportar o nível e acrescentar uma linha por posição.
        throw new UnsupportedOperationException("Método toIndentedString por implementar");
    }

    /* Crie aqui os métodos auxiliares privados. Exemplo de assinatura:
       private int countLeaves(Position<E> position) { ... }
       Cada chamada recursiva deve avançar para um filho de position. */
}
