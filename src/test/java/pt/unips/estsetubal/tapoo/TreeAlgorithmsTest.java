package pt.unips.estsetubal.tapoo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pt.unips.estsetubal.tapoo.adt.Position;
import pt.unips.estsetubal.tapoo.adt.Tree;
import pt.unips.estsetubal.tapoo.adt.TreeImpl;
import pt.unips.estsetubal.tapoo.algorithms.TreeAlgorithms;
import pt.unips.estsetubal.tapoo.model.FileSystemItem;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static pt.unips.estsetubal.tapoo.model.FileSystemItem.file;
import static pt.unips.estsetubal.tapoo.model.FileSystemItem.folder;

class TreeAlgorithmsTest {
    private Tree<FileSystemItem> tree;
    private TreeAlgorithms<FileSystemItem> algorithms;
    private Position<FileSystemItem> computer;

    @BeforeEach
    void setUp() {
        tree = new TreeImpl<>();
        computer = tree.insert(null, folder("Computador"));
        Position<FileSystemItem> documents = tree.insert(computer, folder("Documentos"));
        Position<FileSystemItem> images = tree.insert(computer, folder("Imagens"));
        tree.insert(documents, file("aulas.pdf"));
        tree.insert(documents, file("notas.txt"));
        tree.insert(images, file("ferias.jpg"));
        algorithms = new TreeAlgorithms<>(tree);
    }

    // Percurso A

    @Disabled("Percurso A: retirar depois de implementar countInternals")
    @Test
    void countsTheThreeInternalNodes() {
        assertEquals(3, algorithms.countInternals());
    }

    @Disabled("Percurso A: retirar depois de implementar preOrder")
    @Test
    void preOrderVisitsParentBeforeChildren() {
        assertEquals(List.of(folder("Computador"), folder("Documentos"),
                file("aulas.pdf"), file("notas.txt"), folder("Imagens"),
                file("ferias.jpg")), algorithms.preOrder());
    }

    @Disabled("Percurso A: retirar depois de implementar pathTo")
    @Test
    void pathContainsRootFolderAndFile() {
        assertEquals(List.of(folder("Computador"), folder("Documentos"), file("aulas.pdf")),
                algorithms.pathTo(file("aulas.pdf")));
        assertTrue(algorithms.pathTo(file("inexistente.txt")).isEmpty());
    }

    // Percurso B

    @Disabled("Percurso B: retirar depois de implementar countNodesWithDegree2")
    @Test
    void countsNodesWithExactlyTwoChildren() {
        assertEquals(2, algorithms.countNodesWithDegree2());
    }

    @Disabled("Percurso B: retirar depois de implementar postOrder")
    @Test
    void postOrderVisitsChildrenBeforeParent() {
        assertEquals(List.of(file("aulas.pdf"), file("notas.txt"),
                folder("Documentos"), file("ferias.jpg"), folder("Imagens"),
                folder("Computador")), algorithms.postOrder());
    }

    @Disabled("Percurso B: retirar depois de implementar elementsAtLevel")
    @Test
    void levelOneContainsTheTwoFolders() {
        assertEquals(List.of(folder("Documentos"), folder("Imagens")),
                algorithms.elementsAtLevel(1));
        assertTrue(algorithms.elementsAtLevel(-1).isEmpty());
    }

    // Percurso C

    @Disabled("Percurso C: retirar depois de implementar countNodesWithDegreeGreaterThan2")
    @Test
    void countsNodesWithMoreThanTwoChildren() {
        tree.insert(computer, folder("Transferências"));
        assertEquals(1, algorithms.countNodesWithDegreeGreaterThan2());
    }

    @Disabled("Percurso C: retirar depois de implementar contains")
    @Test
    void findsExistingElementAndRejectsMissingElement() {
        assertTrue(algorithms.contains(file("aulas.pdf")));
        assertFalse(algorithms.contains(file("inexistente.txt")));
    }

    @Disabled("Percurso C: retirar depois de implementar height")
    @Test
    void fileSystemHasHeightTwo() {
        assertEquals(2, algorithms.height());
        assertEquals(-1, new TreeAlgorithms<>(new TreeImpl<>()).height());
    }

    @Test
    void initialTreeImplementationIsReadyForTheActivity() {
        assertEquals(6, tree.size());
        assertFalse(tree.isEmpty());
    }
}
