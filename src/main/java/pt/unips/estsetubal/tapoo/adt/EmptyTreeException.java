package pt.unips.estsetubal.tapoo.adt;

public class EmptyTreeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EmptyTreeException() {
        super("A árvore está vazia.");
    }
}
