### Utilização de `Predicate<E>`

Um `Predicate<E>` representa uma condição aplicada a um elemento do tipo `E`. O método:

```java
boolean test(E element)
```

devolve `true` quando o elemento satisfaz a condição e `false` nos restantes casos.

No método:

```java
countMatching(Predicate<E> predicate)
```

o predicado define quais os elementos da árvore que devem ser contados:

```java
predicate.test(position.element())
```

Exemplos de utilização:

```java
// Contar pastas
algorithms.countMatching(item -> item.isFolder());

// Contar ficheiros PDF
algorithms.countMatching(
        item -> item.getName().endsWith(".pdf")
);
```

Também é possível utilizar uma referência a um método:

```java
algorithms.countMatching(FileSystemItem::isFolder);
```

O algoritmo recursivo determina **como percorrer e contar** os elementos; o `Predicate` determina **quais os elementos que devem ser contados**.
