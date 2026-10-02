## Atividade complementar — Percursos iterativos com Stack e Queue

Nesta atividade, explore como percorrer uma árvore sem chamadas recursivas. Implemente os métodos em `TreeAlgorithms<E>`, usando apenas as operações públicas de `Tree<E>` e posições do tipo `Position<E>`.

Use a mesma árvore de sistema de ficheiros dos exercícios anteriores. Mantenha o método recursivo `preOrder()` para comparar os resultados.

### 1. Pré-ordem iterativa com uma pilha

**Classes e interfaces Java a utilizar neste exercício:**

| Finalidade | Classe ou interface | Operações |
|---|---|---|
| Pilha de posições pendentes | `Stack<Position<E>>` | `push()`, `pop()`, `isEmpty()` |
| Lista de resultados e cópia dos filhos | `List<E>` e `List<Position<E>>`, implementadas por `ArrayList` | `add()`, `get()`, `size()` |

```java
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
```

Declare a pilha no método:

```java
Stack<Position<E>> pending = new Stack<>();
```

Use `push()` para empilhar e `pop()` para retirar o topo. Verifique `isEmpty()` antes de retirar uma posição.

Implemente o método:

```java
public List<E> preOrderIterative()
```

Uma pilha segue a regra **LIFO** (*Last In, First Out*): o último elemento inserido é o primeiro a sair. A pilha explícita guarda as posições que ainda é necessário visitar.

Considere o pseudocódigo:

```text
PRE_ORDER_ITERATIVE(tree)
    result ← empty list
    IF tree is empty
        RETURN result

    pending ← empty stack
    PUSH(pending, root of tree)

    WHILE pending is not empty
        position ← POP(pending)
        APPEND(result, element at position)

        children ← list of children of position, in their order
        FOR i ← number of children − 1 DOWN TO 0
            PUSH(pending, children[i])

    RETURN result
```

**Atenção à ordem:** empilhe os filhos do último para o primeiro. Assim, o primeiro filho será o próximo a sair da pilha, preservando a ordem da pré-ordem recursiva.

Como `tree.children(position)` devolve um `Iterable`, copie os filhos para uma lista antes de os percorrer em ordem inversa.

#### Implementação e comparação

1. Implemente o algoritmo a partir do pseudocódigo.
2. Execute `preOrder()` e `preOrderIterative()` sobre a mesma árvore.
3. Compare as listas devolvidas, verificando os elementos **e a sua ordem**:

   ```java
   assertEquals(algorithms.preOrder(), algorithms.preOrderIterative());
   ```

4. Registe o conteúdo da pilha em cada iteração. Indique claramente qual é o topo.
5. Explique o que aconteceria se empilhasse os filhos do primeiro para o último.
6. Compare a pilha de chamadas da versão recursiva com a pilha explícita da versão iterativa: que informação guarda cada uma?

### 2. Percurso em largura com uma fila

**Classes e interfaces Java a utilizar neste exercício:**

| Finalidade | Classe ou interface | Operações |
|---|---|---|
| Fila de posições pendentes | `Queue<Position<E>>`, implementada por `LinkedList` | `offer()`, `poll()`, `isEmpty()` |
| Lista de resultados | `List<E>`, implementada por `ArrayList` | `add()` |

```java
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
```

Declare a fila no método:

```java
Queue<Position<E>> pending = new LinkedList<>();
```

`Queue` define as operações da fila e `LinkedList` fornece a implementação. Use `offer()` para inserir no fim e `poll()` para retirar da frente. Verifique `isEmpty()` antes de retirar uma posição.

Implemente o método:

```java
public List<E> breadthFirst()
```

O percurso em largura visita primeiro a raiz, depois todas as posições do nível seguinte, e assim sucessivamente. Em cada nível, preserve a ordem dos filhos.

Uma fila segue a regra **FIFO** (*First In, First Out*): o primeiro elemento inserido é o primeiro a sair.

```text
BREADTH_FIRST(tree)
    result ← empty list
    IF tree is empty
        RETURN result

    pending ← empty queue
    ENQUEUE(pending, root of tree)

    WHILE pending is not empty
        position ← DEQUEUE(pending)
        APPEND(result, element at position)

        FOR EACH child OF position, in their order
            ENQUEUE(pending, child)

    RETURN result
```

1. Implemente o algoritmo a partir do pseudocódigo.
2. Registe o conteúdo da fila em cada iteração. Indique a frente da fila.
3. Compare a sequência obtida com a pré-ordem: ambos visitam todas as posições, mas podem fazê-lo por ordens diferentes.
4. Explique por que razão a fila permite visitar as posições por níveis.

### Resultados esperados

Para a árvore usada nas aulas:

- `Computador` tem os filhos `Documentos` e `Imagens`.
- `Documentos` tem os filhos `aulas.pdf` e `notas.txt`.
- `Imagens` tem o filho `ferias.jpg`.

| Percurso | Sequência dos nomes dos elementos |
|---|---|
| Pré-ordem recursiva e iterativa | Computador, Documentos, aulas.pdf, notas.txt, Imagens, ferias.jpg |
| Em largura | Computador, Documentos, Imagens, aulas.pdf, notas.txt, ferias.jpg |

Estes métodos definem uma ordem de percurso. O contrato de `positions()` ou `elements()` não precisa de definir a mesma ordem, pelo que estes métodos não devem servir de referência para validar a sequência.

### Testes e critérios de conclusão

Complete testes que verifiquem:

- **Árvore vazia:** ambos os métodos devolvem uma lista vazia.
- **Apenas a raiz:** ambos devolvem uma lista com o elemento da raiz.
- **Vários filhos e níveis:** a pré-ordem iterativa coincide com a recursiva, e o percurso em largura coincide com a sequência esperada.
- **Árvore em cadeia:** todos os elementos aparecem na ordem da raiz até à última folha.
- **Elementos repetidos:** cada posição contribui com um elemento, mesmo quando há valores iguais.
- **Preservação da árvore:** os percursos não alteram o tamanho nem as relações entre pais e filhos.

A atividade fica concluída quando os testes passam e consegue justificar a escolha da estrutura auxiliar e a ordem de inserção das posições.

