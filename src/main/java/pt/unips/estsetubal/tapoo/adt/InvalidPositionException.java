package pt.unips.estsetubal.tapoo.adt;

public class InvalidPositionException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InvalidPositionException() {
        super("A posição indicada não é válida para esta árvore.");
    }

    public InvalidPositionException(String message) {
        super(message);
    }
}
