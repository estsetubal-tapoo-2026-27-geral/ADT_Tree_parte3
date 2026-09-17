package pt.unips.estsetubal.tapoo;

import pt.unips.estsetubal.tapoo.adt.Position;
import pt.unips.estsetubal.tapoo.adt.Tree;
import pt.unips.estsetubal.tapoo.adt.TreeImpl;
import pt.unips.estsetubal.tapoo.algorithms.TreeAlgorithms;
import pt.unips.estsetubal.tapoo.model.FileSystemItem;

import static pt.unips.estsetubal.tapoo.model.FileSystemItem.file;
import static pt.unips.estsetubal.tapoo.model.FileSystemItem.folder;

public class Main {
    public static void main(String[] args) {
        Tree<FileSystemItem> tree = new TreeImpl<>();
        Position<FileSystemItem> computer = tree.insert(null, folder("Computador"));
        Position<FileSystemItem> documents = tree.insert(computer, folder("Documentos"));
        Position<FileSystemItem> images = tree.insert(computer, folder("Imagens"));
        tree.insert(documents, file("aulas.pdf"));
        tree.insert(documents, file("notas.txt"));
        tree.insert(images, file("ferias.jpg"));

        TreeAlgorithms<FileSystemItem> algorithms = new TreeAlgorithms<>(tree);
        System.out.println("Árvore criada com " + tree.size() + " elementos.");
        System.out.println("Implemente o percurso atribuído em TreeAlgorithms.java.");

        // Depois de implementar, experimente aqui os três métodos do seu percurso.
    }
}
