# ADT Tree — Parte 3

Nesta atividade serão desenvolvidos algoritmos recursivos sobre a implementação do **ADT Tree** concluída na Parte 2. Cada grupo de dois estudantes realiza um percurso de três exercícios e apresenta uma das soluções à turma.

## Objetivos

No final da atividade deverá ser capaz de:

- identificar o caso base e o passo recursivo de um algoritmo sobre árvores;
- distinguir a responsabilidade do método público e do auxiliar privado;
- percorrer uma árvore genérica a partir de uma `Position<E>`;
- combinar, acumular ou propagar resultados entre chamadas recursivas;
- testar casos normais, casos-limite e pesquisas sem resultado;
- analisar a complexidade temporal e o espaço ocupado pela pilha de chamadas.

## Ponto de partida

O projeto contém a implementação funcional da Parte 2 e a classe `TreeAlgorithms<E>`, onde serão realizados os exercícios.

```text
Computador
├── Documentos
│   ├── aulas.pdf
│   └── notas.txt
└── Imagens
    └── ferias.jpg
```

## Estrutura do projeto

```text
src
├── main/java/pt/unips/estsetubal/tapoo
│   ├── adt
│   │   ├── Tree.java
│   │   ├── Position.java
│   │   └── TreeImpl.java
│   ├── algorithms
│   │   └── TreeAlgorithms.java
│   ├── model
│   │   └── FileSystemItem.java
│   └── Main.java
└── test/java/pt/unips/estsetubal/tapoo
    └── TreeAlgorithmsTest.java
```

## 1. Preparar o trabalho

1. Execute `mvn test` e confirme que o projeto inicial compila.
2. Abra `TreeAlgorithms.java` e identifique os métodos marcados com `TODO A2.3`.
3. Consulte `TreeAlgorithmsTest.java` e localize os testes do percurso atribuído ao grupo.
4. Para cada exercício, registe antes de programar:

| Questão | Resposta do grupo |
|---|---|
| Qual é a raiz da subárvore processada? | |
| Qual é o caso base? | |
| Como se faz a chamada sobre um problema menor? | |
| Como se combinam ou propagam os resultados? | |

## 2. Percurso A

Implemente os três métodos seguintes:

1. `countInternals()` — devolve o número de nós internos, isto é, posições que têm pelo menos um filho;
2. `preOrder()` — devolve os elementos em pré-ordem;
3. `pathTo(E target)` — devolve o caminho entre a raiz e a primeira ocorrência do alvo em pré-ordem; se não existir, devolve uma lista vazia.

No método `pathTo`, a solução deverá desfazer a última escolha quando uma subárvore não contém o alvo.

Retire `@Disabled` aos testes identificados com **Percurso A** e acrescente pelo menos um teste relevante por método.

## 3. Percurso B

Implemente os três métodos seguintes:

1. `countNodesWithDegree2()` — devolve o número de posições que têm exatamente dois filhos;
2. `postOrder()` — devolve os elementos em pós-ordem;
3. `elementsAtLevel(int level)` — devolve, da esquerda para a direita, os elementos que se encontram no nível indicado. Um nível negativo devolve uma lista vazia.

Retire `@Disabled` aos testes identificados com **Percurso B** e acrescente pelo menos um teste relevante por método.

## 4. Percurso C

Implemente os três métodos seguintes:

1. `countNodesWithDegreeGreaterThan2()` — devolve o número de posições que têm mais de dois filhos;
2. `contains(E target)` — indica se existe um elemento igual ao alvo;
3. `height()` — devolve `-1` para uma árvore vazia, `0` para uma árvore com apenas a raiz e, nos restantes casos, o maior nível existente.

Na árvore inicial, nenhum nó tem grau superior a dois. Acrescente temporariamente um terceiro filho a `Computador` para testar o primeiro método deste percurso.

Retire `@Disabled` aos testes identificados com **Percurso C** e acrescente pelo menos um teste relevante por método.

## 5. Regras de implementação

- O método público trata a árvore vazia e prepara o resultado inicial.
- A recursividade é implementada através de um ou mais métodos auxiliares privados.
- Os algoritmos usam apenas as operações públicas de `Tree<E>` e `Position<E>`.
- Não altere `TreeImpl` nem tente converter uma `Position<E>` no nó privado da implementação.
- Não substitua a recursividade por uma pilha ou fila explícita.
- Não utilize `tree.elements()` para resolver os exercícios.
- Cada chamada recursiva deve avançar para um filho da posição atual.

## 6. Preparar a apresentação

Na última hora da aula, cada grupo apresenta uma das soluções. A apresentação deve incluir:

1. assinatura e resultado esperado;
2. caso base e passo recursivo;
3. forma de combinar ou propagar resultados;
4. um teste normal e um caso-limite;
5. complexidade temporal e espaço da pilha;
6. dificuldade encontrada ou solução alternativa.

## 7. Generalização após as apresentações

Os três percursos incluem um método de contagem:

```java
countInternals()
countNodesWithDegree2()
countNodesWithDegreeGreaterThan2()
```

Depois das apresentações, compare as implementações e identifique o que se mantém e o que varia. O percurso recursivo é semelhante; apenas muda a condição que determina se a posição atual deve ser contada.

Esse padrão pode ser generalizado através de:

```java
int countMatching(Predicate<Position<E>> predicate)
```

O `Predicate` recebe uma posição e devolve `true` quando essa posição satisfaz a condição de contagem. Esta generalização será discutida nas conclusões da aula e não faz parte dos três exercícios iniciais.

## Critérios de conclusão

A atividade fica concluída quando:

- os três métodos do percurso atribuído estão implementados recursivamente;
- todos os testes desse percurso estão ativos e passam;
- foi acrescentado pelo menos um teste relevante por método;
- a árvore vazia e os principais casos-limite são tratados;
- a solução usa apenas o contrato público do ADT;
- o grupo consegue justificar a terminação, a correção e a complexidade dos algoritmos;
- a apresentação está preparada e pode ser executada no tempo definido pela docente.
