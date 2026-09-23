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

    // Percurso A

    public int countInternals() {
        // TODO A2.3 — Percurso A: contar as posições que têm pelo menos um filho.
        throw new UnsupportedOperationException("Método countInternals por implementar");
    }

    public List<E> preOrder() {
        // TODO A2.3 — Percurso A: processar cada posição antes dos seus filhos.
        throw new UnsupportedOperationException("Método preOrder por implementar");
    }

    public List<E> pathTo(E target) {
        // TODO A2.3 — Percurso A: construir o caminho e aplicar backtracking.
        throw new UnsupportedOperationException("Método pathTo por implementar");
    }

    // Percurso B

    public int countNodesWithDegree2() {
        // TODO A2.3 — Percurso B: contar as posições com exatamente dois filhos.
        throw new UnsupportedOperationException(
                "Método countNodesWithDegree2 por implementar");
    }

    public List<E> postOrder() {
        // TODO A2.3 — Percurso B: processar cada posição depois dos seus filhos.
        throw new UnsupportedOperationException("Método postOrder por implementar");
    }

    public List<E> elementsAtLevel(int level) {
        // TODO A2.3 — Percurso B: transportar o nível atual e acumular resultados.
        throw new UnsupportedOperationException("Método elementsAtLevel por implementar");
    }

    // Percurso C

    public int countNodesWithDegreeGreaterThan2() {
        // TODO A2.3 — Percurso C: contar as posições com mais de dois filhos.
        throw new UnsupportedOperationException(
                "Método countNodesWithDegreeGreaterThan2 por implementar");
    }

    public boolean contains(E target) {
        // TODO A2.3 — Percurso C: terminar quando encontrar a primeira ocorrência.
        throw new UnsupportedOperationException("Método contains por implementar");
    }

    public int height() {
        // TODO A2.3 — Percurso C: devolver -1 para a árvore vazia.
        throw new UnsupportedOperationException("Método height por implementar");
    }

    // Generalização — discutir apenas depois da apresentação dos percursos

    public int countMatching(Predicate<Position<E>> predicate) {
        Objects.requireNonNull(predicate, "O predicado não pode ser null.");
        // TODO A2.3 — Conclusões: generalizar o padrão comum aos três counts.
        throw new UnsupportedOperationException("Método countMatching por implementar");
    }

    /* Crie aqui os métodos auxiliares privados. Exemplo de assinatura:
       private int countInternals(Position<E> position) { ... }
       Cada chamada recursiva deve avançar para um filho de position. */
}
