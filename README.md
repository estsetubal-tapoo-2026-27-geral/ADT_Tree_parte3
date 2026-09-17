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

## 2. Percurso A — Estrutura

Implemente os três métodos seguintes:

1. `countLeaves()` — devolve o número de folhas da árvore;
2. `height()` — devolve `-1` para uma árvore vazia, `0` para uma árvore com apenas a raiz e, nos restantes casos, o maior nível existente;
3. `elementsAtLevel(int level)` — devolve, da esquerda para a direita, os elementos que se encontram no nível indicado. Um nível negativo devolve uma lista vazia.

Retire `@Disabled` aos testes identificados com **Percurso A** e acrescente pelo menos um teste relevante por método.

## 3. Percurso B — Pesquisa

Implemente os três métodos seguintes:

1. `contains(E target)` — indica se existe um elemento igual ao alvo;
2. `countMatching(Predicate<E> predicate)` — conta os elementos que satisfazem o predicado;
3. `pathTo(E target)` — devolve o caminho entre a raiz e a primeira ocorrência do alvo em pré-ordem; se não existir, devolve uma lista vazia.

> Para compreender o funcionamento de `Predicate<E>`, consulte
> [Utilização de Predicate em Java](docs/predicate.md).

> No método `pathTo`, a solução deverá desfazer a última escolha quando uma subárvore não contém o alvo.


Retire `@Disabled` aos testes identificados com **Percurso B** e acrescente pelo menos um teste relevante por método.

## 4. Percurso C — Percursos

Implemente os três métodos seguintes:

1. `preOrder()` — devolve os elementos em pré-ordem;
2. `postOrder()` — devolve os elementos em pós-ordem;
3. `toIndentedString()` — devolve uma linha por elemento, com dois espaços por nível antes de `- `. A raiz não tem hífen.

Exemplo de representação indentada:

```text
Computador
- Documentos
  - aulas.pdf
  - notas.txt
- Imagens
  - ferias.jpg
```

Retire `@Disabled` aos testes identificados com **Percurso C** e acrescente pelo menos um teste relevante por método.

## 5. Regras de implementação

- O método público trata a árvore vazia e prepara o resultado inicial.
- A recursividade é implementada através de um ou mais métodos auxiliares privados.
- Os algoritmos usam apenas as operações públicas de `Tree<E>` e `Position<E>`.
- Não altere `TreeImpl` nem tente converter uma `Position<E>` no nó privado da implementação.
- Não substitua a recursividade por uma pilha ou fila explícita.
- Não utilize `tree.elements()` para resolver os exercícios.
- Cada chamada recursiva deve avançar para um filho da posição atual.

## 6. Testar e analisar

Além dos testes fornecidos, considere:

- árvore vazia;
- árvore constituída apenas pela raiz;
- nó com vários filhos;
- árvore com alturas diferentes nas subárvores;
- nível inexistente;
- elemento ou predicado sem correspondências;
- primeira ocorrência quando existem elementos repetidos.

Para cada método, determine:

- o número máximo de posições visitadas;
- a complexidade temporal no pior caso;
- a profundidade máxima da pilha de chamadas.

## 7. Preparar a apresentação

Na última hora da aula, cada grupo apresenta uma das soluções. A apresentação deve incluir:

1. assinatura e resultado esperado;
2. caso base e passo recursivo;
3. forma de combinar ou propagar resultados;
4. um teste normal e um caso-limite;
5. complexidade temporal e espaço da pilha;
6. dificuldade encontrada ou solução alternativa.

## Critérios de conclusão

A atividade fica concluída quando:

- os três métodos do percurso atribuído estão implementados recursivamente;
- todos os testes desse percurso estão ativos e passam;
- foi acrescentado pelo menos um teste relevante por método;
- a árvore vazia e os principais casos-limite são tratados;
- a solução usa apenas o contrato público do ADT;
- o grupo consegue justificar a terminação, a correção e a complexidade dos algoritmos;
- a apresentação está preparada e pode ser executada no tempo definido pela docente.

