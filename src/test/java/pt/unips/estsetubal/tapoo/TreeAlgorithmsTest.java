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

    @BeforeEach
    void setUp() {
        tree = new TreeImpl<>();
        Position<FileSystemItem> computer = tree.insert(null, folder("Computador"));
        Position<FileSystemItem> documents = tree.insert(computer, folder("Documentos"));
        Position<FileSystemItem> images = tree.insert(computer, folder("Imagens"));
        tree.insert(documents, file("aulas.pdf"));
        tree.insert(documents, file("notas.txt"));
        tree.insert(images, file("ferias.jpg"));
        algorithms = new TreeAlgorithms<>(tree);
    }

    // Percurso A — Estrutura

    @Disabled("Percurso A: retirar depois de implementar countLeaves")
    @Test
    void countsTheThreeFilesAsLeaves() {
        assertEquals(3, algorithms.countLeaves());
    }

    @Disabled("Percurso A: retirar depois de implementar height")
    @Test
    void fileSystemHasHeightTwo() {
        assertEquals(2, algorithms.height());
        assertEquals(-1, new TreeAlgorithms<>(new TreeImpl<>()).height());
    }

    @Disabled("Percurso A: retirar depois de implementar elementsAtLevel")
    @Test
    void levelOneContainsTheTwoFolders() {
        assertEquals(List.of(folder("Documentos"), folder("Imagens")),
                algorithms.elementsAtLevel(1));
        assertTrue(algorithms.elementsAtLevel(-1).isEmpty());
    }

    // Percurso B — Pesquisa

    @Disabled("Percurso B: retirar depois de implementar contains")
    @Test
    void findsExistingElementAndRejectsMissingElement() {
        assertTrue(algorithms.contains(file("aulas.pdf")));
        assertFalse(algorithms.contains(file("inexistente.txt")));
    }

    @Disabled("Percurso B: retirar depois de implementar countMatching")
    @Test
    void countsFoldersUsingAPredicate() {
        assertEquals(3, algorithms.countMatching(FileSystemItem::isFolder));
    }

    @Disabled("Percurso B: retirar depois de implementar pathTo")
    @Test
    void pathContainsRootFolderAndFile() {
        assertEquals(List.of(folder("Computador"), folder("Documentos"), file("aulas.pdf")),
                algorithms.pathTo(file("aulas.pdf")));
        assertTrue(algorithms.pathTo(file("inexistente.txt")).isEmpty());
    }

    // Percurso C — Percursos

    @Disabled("Percurso C: retirar depois de implementar preOrder")
    @Test
    void preOrderVisitsParentBeforeChildren() {
        assertEquals(List.of(folder("Computador"), folder("Documentos"),
                file("aulas.pdf"), file("notas.txt"), folder("Imagens"),
                file("ferias.jpg")), algorithms.preOrder());
    }

    @Disabled("Percurso C: retirar depois de implementar postOrder")
    @Test
    void postOrderVisitsChildrenBeforeParent() {
        assertEquals(List.of(file("aulas.pdf"), file("notas.txt"),
                folder("Documentos"), file("ferias.jpg"), folder("Imagens"),
                folder("Computador")), algorithms.postOrder());
    }

    @Disabled("Percurso C: retirar depois de implementar toIndentedString")
    @Test
    void indentedRepresentationReflectsLevels() {
        String expected = String.join(System.lineSeparator(),
                "Computador", "- Documentos", "  - aulas.pdf", "  - notas.txt",
                "- Imagens", "  - ferias.jpg") + System.lineSeparator();
        assertEquals(expected, algorithms.toIndentedString());
    }

    @Test
    void initialTreeImplementationIsReadyForTheActivity() {
        assertEquals(6, tree.size());
        assertFalse(tree.isEmpty());
    }
}
