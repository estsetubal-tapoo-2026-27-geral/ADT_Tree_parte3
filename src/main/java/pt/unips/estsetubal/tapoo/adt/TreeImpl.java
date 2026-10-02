package pt.unips.estsetubal.tapoo.adt;

import java.util.ArrayList;
import java.util.List;

public class TreeImpl<E> implements Tree<E> {
    private TreeNode root;

    public TreeImpl() {
        root = null;
    }

    public TreeImpl(E rootElement) {
        root = new TreeNode(rootElement, null);
    }

    @Override
    public int size() {
        return isEmpty() ? 0 : size(root);
    }

    private int size(TreeNode node) {
        int result = 1;
        for (TreeNode child : node.children) {
            result += size(child);
        }
        return result;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public Position<E> root() {
        if (isEmpty()) throw new EmptyTreeException();
        return root;
    }

    @Override
    public Position<E> parent(Position<E> position) {
        TreeNode node = checkPosition(position);
        if (node == root) throw new BoundaryViolationException("A raiz não tem pai.");
        return node.parent;
    }

    @Override
    public Iterable<Position<E>> children(Position<E> position) {
        TreeNode node = checkPosition(position);
        return new ArrayList<>(node.children);
    }

    @Override
    public boolean isInternal(Position<E> position) {
        return !checkPosition(position).children.isEmpty();
    }

    @Override
    public boolean isExternal(Position<E> position) {
        return checkPosition(position).children.isEmpty();
    }

    @Override
    public boolean isRoot(Position<E> position) {
        return checkPosition(position) == root;
    }

    @Override
    public Iterable<Position<E>> positions() {
        List<Position<E>> result = new ArrayList<>();
        if (!isEmpty()) collectPositionsPreOrder(root, result);
        return result;
    }

    private void collectPositionsPreOrder(TreeNode node, List<Position<E>> result) {
        result.add(node);
        for (TreeNode child : node.children) collectPositionsPreOrder(child, result);
    }

    @Override
    public Iterable<E> elements() {
        List<E> result = new ArrayList<>();
        if (!isEmpty()) collectElementsPreOrder(root, result);
        return result;
    }

    private void collectElementsPreOrder(TreeNode node, List<E> result) {
        result.add(node.element);
        for (TreeNode child : node.children) collectElementsPreOrder(child, result);
    }

    @Override
    public Position<E> insert(Position<E> parent, E element) {
        if (isEmpty()) {
            if (parent != null) throw new InvalidPositionException("Numa árvore vazia, o pai deve ser null.");
            root = new TreeNode(element, null);
            return root;
        }
        TreeNode parentNode = checkPosition(parent);
        TreeNode newNode = new TreeNode(element, parentNode);
        parentNode.children.add(newNode);
        return newNode;
    }

    @Override
    public Position<E> insert(Position<E> parent, E element, int order) {
        if (isEmpty()) {
            if (parent != null) throw new InvalidPositionException("Numa árvore vazia, o pai deve ser null.");
            if (order != 0) throw new BoundaryViolationException("A raiz apenas pode ser inserida na ordem 0.");
            root = new TreeNode(element, null);
            return root;
        }
        TreeNode parentNode = checkPosition(parent);
        if (order < 0 || order > parentNode.children.size()) {
            throw new BoundaryViolationException("Índice de inserção inválido.");
        }
        TreeNode newNode = new TreeNode(element, parentNode);
        parentNode.children.add(order, newNode);
        return newNode;
    }

    @Override
    public E replace(Position<E> position, E element) {
        TreeNode node = checkPosition(position);
        E previous = node.element;
        node.element = element;
        return previous;
    }

    @Override
    public E remove(Position<E> position) {
        TreeNode node = checkPosition(position);

        if (!node.children.isEmpty()) {
            throw new IllegalStateException(
                    "Só é possível remover um nó folha."
            );
        }

        E removed = node.element;

        if (node == root) {
            root = null;
        } else {
            node.parent.children.remove(node);
        }

        invalidate(node);
        return removed;
    }

    private void invalidate(TreeNode node) {
        node.parent = null;
        node.valid = false;
    }

    private TreeNode checkPosition(Position<E> position) {
        if (position == null) throw new InvalidPositionException("A posição não pode ser null.");
        final TreeNode node;
        try {
            node = (TreeNode) position;
        } catch (ClassCastException exception) {
            throw new InvalidPositionException("A posição não foi criada por uma TreeImpl compatível.");
        }
        if (node.owner != this || !node.valid) {
            throw new InvalidPositionException("A posição não pertence a esta árvore ou já foi removida.");
        }
        return node;
    }

    private class TreeNode implements Position<E> {
        private E element;
        private TreeNode parent;
        private final List<TreeNode> children = new ArrayList<>();
        private final TreeImpl<E> owner = TreeImpl.this;
        private boolean valid = true;

        TreeNode(E element, TreeNode parent) {
            this.element = element;
            this.parent = parent;
        }

        @Override
        public E element() {
            if (!valid) throw new InvalidPositionException("A posição já foi removida.");
            return element;
        }
    }
}
