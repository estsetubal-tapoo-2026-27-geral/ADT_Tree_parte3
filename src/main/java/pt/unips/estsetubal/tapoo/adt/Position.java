package pt.unips.estsetubal.tapoo.adt;

public interface Position<E> {
    E element() throws InvalidPositionException;
}
