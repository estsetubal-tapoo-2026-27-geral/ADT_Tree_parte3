package pt.unips.estsetubal.tapoo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.unips.estsetubal.tapoo.adt.BoundaryViolationException;
import pt.unips.estsetubal.tapoo.adt.InvalidPositionException;
import pt.unips.estsetubal.tapoo.adt.Position;
import pt.unips.estsetubal.tapoo.adt.Tree;
import pt.unips.estsetubal.tapoo.adt.TreeImpl;
import pt.unips.estsetubal.tapoo.model.FileSystemItem;

import java.util.List;
import java.util.ArrayList;
import pt.unips.estsetubal.tapoo.adt.EmptyTreeException;

import static org.junit.jupiter.api.Assertions.*;
import static pt.unips.estsetubal.tapoo.model.FileSystemItem.file;
import static pt.unips.estsetubal.tapoo.model.FileSystemItem.folder;

/**
 * Testes completos da Parte 2, com size() acrescentado para a Parte 3.
 * Requer a implementação dos métodos ainda por fazer em TreeImpl.
 * remove() de um nó interno lança IllegalStateException, conforme Tree da Parte 2.
 * A ordem de children() é significativa. positions() e elements() não impõem percurso.
 */
class TreeImplTest {

    private Tree<FileSystemItem> tree;
    private Position<FileSystemItem> computer;
    private Position<FileSystemItem> documents;
    private Position<FileSystemItem> images;
    private Position<FileSystemItem> classesPdf;
    private Position<FileSystemItem> notesTxt;
    private Position<FileSystemItem> holidaysJpg;

    @BeforeEach
    void setUp() {
        tree = new TreeImpl<>();
        computer = tree.insert(null, folder("Computador"));
        documents = tree.insert(computer, folder("Documentos"));
        images = tree.insert(computer, folder("Imagens"));
        classesPdf = tree.insert(documents, file("aulas.pdf"));
        notesTxt = tree.insert(documents, file("notas.txt"));
        holidaysJpg = tree.insert(images, file("ferias.jpg"));
    }

    @Test
    void rootContainsComputer() {
        assertSame(computer, tree.root());
        assertEquals("Computador", tree.root().element().getName());
    }


    @Test
    void parentOfRootThrowsException() {
        assertThrows(BoundaryViolationException.class,
                () -> tree.parent(computer));
    }

    @Test
    void nullPositionIsRejected() {
        assertThrows(InvalidPositionException.class,
                () -> tree.children(null));
    }

    @Test
    void positionFromAnotherImplementationIsRejected() {
        Position<FileSystemItem> externalPosition =
                () -> folder("Externa");

        assertThrows(InvalidPositionException.class,
                () -> tree.children(externalPosition));
    }

    @Test
    void positionFromAnotherTreeIsRejected() {
        Tree<FileSystemItem> otherTree = new TreeImpl<>();
        Position<FileSystemItem> otherRoot = otherTree.insert(null, folder("Outra"));

        assertThrows(InvalidPositionException.class,
                () -> tree.children(otherRoot));
    }


    @Test
    void computerIsRoot() {
        assertTrue(tree.isRoot(computer));
        assertFalse(tree.isRoot(documents));
    }

    @Test
    void foldersAndFilesAreClassified() {
        assertTrue(tree.isInternal(documents));
        assertTrue(tree.isInternal(computer));
        assertFalse(tree.isExternal(documents));
        assertFalse(tree.isInternal(notesTxt));
        assertTrue(tree.isExternal(notesTxt));
        assertTrue(tree.isExternal(holidaysJpg));
    }

    @Test
    void positionsContainsAllPositions() {
        List<Position<FileSystemItem>> actual = new java.util.ArrayList<>();
        tree.positions().forEach(actual::add);

        List<Position<FileSystemItem>> expected =
                List.of(computer, documents, images,
                        classesPdf, notesTxt, holidaysJpg);

        assertEquals(expected.size(), actual.size());
        assertTrue(actual.containsAll(expected));
    }



    @Test
    void removingLeafInvalidatesItsPosition(){
        assertEquals("aulas.pdf", tree.remove(classesPdf).getName());
        assertThrows(InvalidPositionException.class, classesPdf::element);
        assertIterableEquals(List.of(notesTxt), tree.children(documents));
    }

    @Test
    void removingInternalNodeIsRejectedWithoutChanges() {
        assertThrows(IllegalStateException.class, () -> tree.remove(documents));
        assertSame(computer, tree.parent(documents));
        assertEquals("Documentos", documents.element().getName());
        assertIterableEquals(List.of(classesPdf, notesTxt), tree.children(documents));
        assertIterableEquals(List.of(documents, images), tree.children(computer));
        assertEquals(6, tree.size());
    }

    // Testes completados a partir da Parte 2.

    @Test
    void insertWithOrderAddsChildAtSpecifiedPosition() {
        Position<FileSystemItem> added = tree.insert(computer, folder("Programas"), 1);
        assertIterableEquals(List.of(documents, added, images), tree.children(computer));
        assertSame(computer, tree.parent(added));
        assertEquals(7, tree.size());
    }

    @Test
    void replaceChangesElementAndReturnsPreviousElement() {
        FileSystemItem previous = notesTxt.element();
        FileSystemItem replacement = file("resumo.txt");
        assertSame(previous, tree.replace(notesTxt, replacement));
        assertSame(replacement, notesTxt.element());
        assertSame(documents, tree.parent(notesTxt));
        assertIterableEquals(List.of(classesPdf, notesTxt), tree.children(documents));
        assertEquals(6, tree.size());
    }

    @Test
    void removingSoleRootEmptiesTreeAndInvalidatesPosition() {
        Tree<FileSystemItem> single = new TreeImpl<>();
        FileSystemItem item = folder("Raiz");
        Position<FileSystemItem> position = single.insert(null, item);
        assertSame(item, single.remove(position));
        assertTrue(single.isEmpty());
        assertEquals(0, single.size());
        assertThrows(EmptyTreeException.class, single::root);
        assertThrows(InvalidPositionException.class, position::element);
        assertFalse(single.positions().iterator().hasNext());
        assertFalse(single.elements().iterator().hasNext());
    }

    @Test
    void operationWithRemovedPositionThrowsInvalidPositionException() {
        tree.remove(classesPdf);
        assertThrows(InvalidPositionException.class, () -> tree.children(classesPdf));
        assertThrows(InvalidPositionException.class, () -> tree.parent(classesPdf));
        assertThrows(InvalidPositionException.class, () -> tree.isRoot(classesPdf));
        assertThrows(InvalidPositionException.class, () -> tree.isInternal(classesPdf));
        assertThrows(InvalidPositionException.class, () -> tree.isExternal(classesPdf));
        assertThrows(InvalidPositionException.class, () -> tree.replace(classesPdf, file("novo.txt")));
        assertThrows(InvalidPositionException.class, () -> tree.insert(classesPdf, file("novo.txt")));
        assertThrows(InvalidPositionException.class, () -> tree.remove(classesPdf));
        assertEquals(5, tree.size());
    }

    @Test
    void insertWithInvalidOrderThrowsBoundaryViolationException() {
        assertThrows(BoundaryViolationException.class,
                () -> tree.insert(computer, folder("Inválida"), -1));
        assertThrows(BoundaryViolationException.class,
                () -> tree.insert(computer, folder("Inválida"), 3));
        assertIterableEquals(List.of(documents, images), tree.children(computer));
        assertEquals(6, tree.size());
    }

    // size() é testado pelo comportamento público, sem depender do auxiliar recursivo.
    @Test
    void emptyTreeHasSizeZero() {
        Tree<FileSystemItem> empty = new TreeImpl<>();
        assertTrue(empty.isEmpty());
        assertEquals(0, empty.size());
    }

    @Test
    void treeWithOnlyRootHasSizeOne() {
        Tree<FileSystemItem> single = new TreeImpl<>(folder("Raiz"));
        assertEquals(1, single.size());
        assertFalse(single.isEmpty());
        assertTrue(single.isExternal(single.root()));
    }

    @Test
    void sizeCountsAllLevelsAndBranches() {
        assertEquals(6, tree.size());
        assertEquals(6, tree.size()); // Consultar não altera a árvore.
    }

    @Test
    void insertionIncreasesSize() {
        Position<FileSystemItem> subfolder = tree.insert(documents, folder("Trabalhos"));
        assertEquals(7, tree.size());
        tree.insert(subfolder, file("projeto.java"));
        assertEquals(8, tree.size());
    }

    @Test
    void removingLeafDecreasesSize() {
        tree.remove(classesPdf);
        assertEquals(5, tree.size());
        tree.remove(notesTxt);
        assertEquals(4, tree.size());
        assertTrue(tree.isExternal(documents));
        tree.remove(documents);
        assertEquals(3, tree.size());
    }

    @Test
    void sizeCountsPositionsWithRepeatedElements() {
        FileSystemItem repeated = file("repetido.txt");
        tree.insert(documents, repeated);
        tree.insert(images, repeated);
        assertEquals(8, tree.size());
    }

    @Test
    void parentAndChildrenPreserveStructure() {
        assertSame(computer, tree.parent(documents));
        assertSame(documents, tree.parent(classesPdf));
        assertSame(images, tree.parent(holidaysJpg));
        assertIterableEquals(List.of(documents, images), tree.children(computer));
        assertIterableEquals(List.of(classesPdf, notesTxt), tree.children(documents));
        assertFalse(tree.children(holidaysJpg).iterator().hasNext());
    }

    @Test
    void insertWithoutOrderAppendsChild() {
        Position<FileSystemItem> added = tree.insert(computer, folder("Programas"));
        assertIterableEquals(List.of(documents, images, added), tree.children(computer));
        assertSame(computer, tree.parent(added));
    }

    @Test
    void insertWithOrderAcceptsBothBoundaries() {
        Position<FileSystemItem> first = tree.insert(computer, folder("Primeira"), 0);
        Position<FileSystemItem> last = tree.insert(computer, folder("Última"), 3);
        assertIterableEquals(List.of(first, documents, images, last), tree.children(computer));
    }

    @Test
    void orderedRootInsertionRequiresOrderZero() {
        Tree<FileSystemItem> empty = new TreeImpl<>();
        assertThrows(BoundaryViolationException.class,
                () -> empty.insert(null, folder("Raiz"), -1));
        assertThrows(BoundaryViolationException.class,
                () -> empty.insert(null, folder("Raiz"), 1));
        assertTrue(empty.isEmpty());
        assertSame(empty.insert(null, folder("Raiz"), 0), empty.root());
        assertEquals(1, empty.size());
    }

    @Test
    void removingRootWithChildrenIsRejected() {
        assertThrows(IllegalStateException.class, () -> tree.remove(computer));
        assertSame(computer, tree.root());
        assertEquals(6, tree.size());
        assertIterableEquals(List.of(documents, images), tree.children(computer));
    }

    @Test
    void elementsContainsAllElementsWithoutImposingTraversalOrder() {
        List<FileSystemItem> actual = new ArrayList<>();
        tree.elements().forEach(actual::add);
        List<FileSystemItem> expected = List.of(computer.element(), documents.element(),
                images.element(), classesPdf.element(), notesTxt.element(), holidaysJpg.element());
        assertEquals(expected.size(), actual.size());
        for (FileSystemItem item : expected) {
            assertTrue(actual.remove(item));
        }
        assertTrue(actual.isEmpty());
    }

    @Test
    void emptyTreeHasNoPositionsOrElements() {
        Tree<FileSystemItem> empty = new TreeImpl<>();
        assertFalse(empty.positions().iterator().hasNext());
        assertFalse(empty.elements().iterator().hasNext());
        assertThrows(EmptyTreeException.class, empty::root);
    }
}
