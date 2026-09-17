package pt.unips.estsetubal.tapoo.adt;

public interface Tree<E> {
    int size();
    boolean isEmpty();
    Position<E> root() throws EmptyTreeException;
    Position<E> parent(Position<E> position) throws InvalidPositionException, BoundaryViolationException;
    Iterable<Position<E>> children(Position<E> position) throws InvalidPositionException;
    boolean isInternal(Position<E> position) throws InvalidPositionException;
    boolean isExternal(Position<E> position) throws InvalidPositionException;
    boolean isRoot(Position<E> position) throws InvalidPositionException;
    Iterable<Position<E>> positions();
    Iterable<E> elements();
    Position<E> insert(Position<E> parent, E element) throws InvalidPositionException;
    Position<E> insert(Position<E> parent, E element, int order)
            throws InvalidPositionException, BoundaryViolationException;
    E replace(Position<E> position, E element) throws InvalidPositionException;
    E remove(Position<E> position) throws InvalidPositionException;
}
