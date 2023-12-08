# Liskov Substitution Principle

## Herança x Composição e o tal do LSP:
Henraça é sempre um assunto delicado. No começo das linguagens orientadas a objetos, a herança era a funcionalidade
usada para vender a ideia.
Afinal, reúso de código de maneira fácil, quem não quer? Mas, na prática, utilizar herança pode não ser tão simples.
É fácil cair em armadilhas criadas por hierarquias de classes longas ou confusas.

Veja o exemplo da classe: 
[CommonAccount.java](bad%2FCommonAccount.java)

Ela representa, de maneira simplificada, uma conta num banco. A classe possui operações simples como:
`deposit()` e `income()`.
Mas, como sempre, o sistema precisa crescer. Imagine agora a classe `StudentAccount`, que é exatamente igual a uma conta, 
com a diferença de que ela não "rende". Usando herança, a implementação seria algo parecido com a que segue, onde o 
método `income()` lança uma exceção: 
[StudentAccount.java](bad%2FStudentAccount.java)

Você já sobrescreveu métodos da maneira como acabamos de fazer? 

#### QUAL O PROBLEMA DELA?
É difícil enxergar o problema dessa simples sobrescrita. Para isso, imagine um código que faz uso de ambas: 
`CommonAccount` e `StudentAccount`: [InvestmentProcessor.java](bad%2FInvestmentProcessor.java)

O método `bankAccount()` retorna uma lista com diferentes contas. Não sabemos exatamente quais estão lá dentro, mas, 
dado o polimorfismo, podemos tratar todas elas pela referência da classe pai. 

**Agora o problema**: Qual o comportamento da aplicação? **Não sabemos!**.
Se houver uma conta de estudante nesse código, a execução do programa irá parar, pois uma exceção será lançada.
Isso acontece porque a classe filha quebrou o contrato definido pela classe pai: o método `income()` na classe pai não
lança exceção. 
MUDAR O CONTRATO PODE SER PERIGOSO!!!

## LSP:
Para usar herança de maneira adequada, devemos pensar em `pré` e `pós-condições` que a classe pai definiu. 

`Precondição`: os dados que chegam no método e as restrições iniciais para que o método funcione corretamente.
Exemplo:
O método `deposit()` deve receber um inteiro maior que zero. 
```java
public void deposit(double value) throws AccountException {
    if (value <= 0){ // Pré-condição.
        throw AccountException.invalidValueException();
    }
    this.balance += value;
}
```

Ou sejam o valor "1" é válido. Se uma classe filha mudar essa `precondição` para somente números maiores que 10, 
poderemos ter problemas. 

A `pós-condições` são o outro lado da moeda. O que aquele comportamento devolve? 
O método `income()` não devolve nada e não lança exeção. No exemplo que demos, esse mesmo método lança uma exeção.
**Problema!**

Podemos, sim, mudar as `pré` e `pós-condições`, mas com regras. 
A classe filha só pode "afrouxar" a `precondição`. Pense no caso em que a classe pai tem um método que recebe inteiros
de 1 a 100. A classe filha pode sobrescrever esse método e permitir que receba entre 1 a 200. Dessa forma, todo o
código que já usava a classe pai continua a funcionar. 

Ao contrário, a `pós-condição` só pode ser "apertada"; ela nunca pode "afrouxar". Pense num método que devolve
um inteiro, de 1 a 100. As classes que a usam entendem isso. A classe filha sobrescreve e devolve só entre 1 e 50.
Os clientes continuarão a funcionar, afinal eles já entendiam saídas entre 1 e 50.

- Não podemos nunca apertar uma `precondição`;
- Não podemos nunca afrouxar uma `pós-condição`.
É sobre esses dois pontos acima que o princípio de substituição de Liskov discute. 

## O exemplo do quadrado e retângulo
Um quadrado é só um tipo especial de retângulo: parece um cenário perfeito para usar herança e reaproveitar código.

Imagine a classe retângulo, que armazena apenas o tamanho dos seus dois lados:
[Rectangle.java](bad%2Fgeometry%2FRectangle.java)

E o quadrado, por ser um retângulo "diferente", poderia ser uma extensão da classe `Rectangle`.
O construtor da classe `Square` passaria ambos os lados iguais para o construtor da classe pai: 
[Square.java](bad%2Fgeometry%2FSquare.java)

```java
public class Square extends Rectangle{
    public Square(int x) {
        super(x, x); // ambos x
    }
}
```
Perceba que a `precondição` da classe `Square` é **mais forte** que a da classe pai. 
Num quadrado, ambos os lados precisam ser iguais. Num retângulo, não. 
Conforme o `LSP`, não poderíamos fazer essa herança. 

## Acoplamento entre a classe pai e a classe filho.
A discussão de acoplamento deixou isso claro. Sempre que uma classe depende da outra para existir, é acoplamento. 
E, dependendo da forma com que esse acoplamento é feito, podemos ter problemas no futuro.

É fácil perceber que a classe filho é totalmente acoplada à classe pai. Afinal, qualquer mudança no pai impacta no filho.
Mas será que esse tipo de acoplamento pode ser problemático? **Sem dúvidas**.

Por isso, ao pensar em modelar hierarquias de classe usando herança,
lembre-se do acoplamento entre classe pai e filho. Modele hierarquias nas
quais as classes filhos precisam conhecer pouco (*ou não conhecer nada*) dos
detalhes da classe pai.

Repare que isso não é fácil. Imagine uma classe `Funcionario`, que guarda o salário dele, 
e possui o cálculo de bonificação de fim do ano:

```java
public class Funcionario {
    // Outros atributos aqui

    protected double salario;

    // Outros comportamentos aqui

    public double bonus() {
        return this.salario * 0.2;
    }
}
```

Agora imagine uma classe filho de `Funcionario`, por exemplo, a classe `Gerente`, que é um funcionário, mas cujo cálculo 
de bônus é de 30% em vez de 20%. Uma solução apenas didática seria invocar o método do pai (que dá 20%) e somar mais 10%:

```java
public class Gerente extends Funcionario {
    private String placaDoCarro;

    public double bonus() {
        return super.bonus() + this.salario * 0.1;
    }
}
```

Apesar de funcionar, qualquer mudança na implementação do pai afetará diretamente o filho.
O ideal seria o método ter a sua própria implementação, sem depender da implementação do pai.

Mas nunca devo usar o método do pai? Claro que sim, se fizer sentido.
Se a regra fosse diferente: o bônus do gerente iguala o bônus do funcionário
mais R$ 500, faria todo o sentido usar a implementação do pai:

```java
public double bonus() {
    return super.bonus() + 500;
}
```

Lembre-se, portanto, de tentar ao máximo reduzir o acoplamento entre a
classe pai e a classe filho.

## Favoreça a composição
Usar herança é, sim, complicado, e o seu mau uso pode trazer problemas. É por isso que muitos desenvolvedores sugerem
o uso da `composição` em vez de herança. 

Ao optar por composição em vez de herança, as vantagens incluem uma relação menos íntima entre a classe principal e a 
classe dependente, tornando mais difícil quebrar o encapsulamento em comparação com a relação entre classes pai e filho.

A composição nos dá flixibilidade. Os próprios padrões de projeto do GoF usam, em sua maioria, composição para trazer
flexibilidade. 

**Testes automatizados:**
Escrever testes automatizados torna-se mais simples ao adotar composição. A prática de criar objetos simulados (mocks) 
e comportamentos e depois passá-los para classes que os utilizam para compor o comportamento é mais natural nesse contexto. 
Em comparação, ao lidar com herança, essa abordagem é mais desafiadora. Isso pode explicar por que códigos desenvolvidos 
com TDD (Desenvolvimento Orientado por Testes) frequentemente evitam extensivamente o uso de herança.

### Como resolver? 
No nosso exemplo das classes `CommonAccount` e `StudentAccount`, onde a manipulação de saldo é basicamente a 
funcionalidade que muda em cada classe filho, é possível extrair uma classe cuja única responsabilidade é manipulá-lo. 
As classes de Conta, por sua vez, fazem uso dela: [AccountManager.java](good%2FAccountManager.java)

```java
public class AccountManager {
    private double balance;

    public void deposit(double value){
        // implementation
    }

    public void income(double tax){
        // implementation
    }

    public double getBalance() {
        return balance;
    }
}
```

Conta comum: [CommonAccount.java](good%2FCommonAccount.java)

```java
public class CommonAccount {
    private final AccountManager accountManager;

    public CommonAccount() {
        accountManager = new AccountManager();
    }

    public void deposit(double value){
        accountManager.deposit(value);
    }

    public void income(double value){
        // Faz uso do método icome()
        accountManager.income(value);
    }
}
```

Conta estudante: [StudentAccount.java](good%2FStudentAccount.java)

```java
public class StudentAccount {
    private final AccountManager accountManager;

    public StudentAccount() {
        accountManager = new AccountManager();
    }

    public void deposit(double value){
        accountManager.deposit(value);
    }

    // Não tem o método income()
}
```

Repare que a classe `AccountManager` encapsula bem todo o comportamento, e ambas as classes apenas a utilizam. 
O problema dessa abordagem é a quantidade de métodos que apenas repassam a invocação de método para a classe utilizada. 
Veja que as implementações de `deposit()` basicamente repassam a chamada para o método `deposit()` da `AccountManager`.
Como sempre, é uma troca. 
Com frequência, a melhor.

## Quando usar herança então? 

Lembre-se da frase do Joshua no começo do capítulo: **ou você modela a classe
para usar herança, ou não.** Herança deve ser usada quando existe realmente a
relação de **X é um Y**. Por exemplo, gerente é um Funcionário, ou ICMS é um Imposto. Não use herança caso a relação seja 
de composição, ou seja, X tem um Y, ou X usa Y.

Classes que efetivamente aplicam herança aderem aos princípios discutidos, minimizando o conhecimento da classe filha 
sobre detalhes da implementação do pai e respeitando as restrições de pré e pós-condições durante a sobrescrição de 
comportamentos. Além disso, é viável utilizar tanto herança quanto composição conforme apropriado: herança para 
reutilização de código significativo e composição para partes que requerem maior flexibilidade.

## Conclusão:
Não descarte herança, apenas favoreça a composição.