# Open Close Principle

Algumas frases de efeito:

- O nosso código deve estar sempre pronto para evoluir.
- O desenvolvedor não deve sentir a necessidade de modificar muitos arquivos diferentes, ou mesmo procurar (usando CTRL+F, por exempplo) os lugares que devem ser alterados.

### Vamos ver um exemplo de código para depois entendermos mais sobre OCP.

Clique para acessar a classe:
[PriceCalculator.java](bad%2FPriceCalculator.java)

O código é bem simples. Ele pegs um produto da loja e tenta descobrir o seu preço.
O código é bastante coeso. Temos classes com responsabilidades bem definidas. 
A tabela de preços `priceTable` calcula o desconto do produto, a classe `Fedex` calcula o frete,
e a classe `PriceCalculator` coordena o preço e faz a conta final. 
O acoplamento também está razoavelmente controlado. 

#### QUAL O PROBLEMA DELA?

Mas agora imagine que o sistema é mais complicado que isso. Não existe apenas uma única regra de cáculo
de descontro, mas várias; e também não existe apenas uma única regra de frete, existem várias.
Uma maneira comum de vermos códigos por ai é resolvendo isso por meio de `ifs`.
Ou seja, o código decide se é a regra A ou B que deve ser executada.
Veja o exemplo abaixo: 

```java
public double calcWithRules(Shop shop, int rule){
    Fedex fedex = new Fedex();
    double discount = 0;
    if (rule == 1){
        PriceTable priceTable = new PriceTable();
        discount = priceTable.discountTo(shop.getValue());
    }
    if (rule == 2){
        PriceTable priceTable = new PriceTable();
        discount = priceTable.discountToSpecial(shop.getValue());
    }
    double delivery = fedex.to(shop.getCity());
    return shop.getValue() * (1 - discount) + delivery;
}
```

Se esse número de regras for razoavelmente grande, a ideia não é boa: esse
código ficará complicadíssimo, cheio de ifs, e difícil de ser mantido.

Uma segunda implementação comum é colocar `ifs` dentro de classes específicas. Por exemplo, a classe `Fedex` passaria
a ter as diferentes regras de neógio: 

```java
public double toWithRules(String city, int rule){
    if(rule == 1){
        if ("San Francisco".equals(city)){
            return 15;
        } else {
            return 30;
        }
    }
    if (rule == 2){
        if ("San Francisco".equals(city)){
            return 30;
        } else {
            return 15;
        }
    }
    else {
        return 0;
    }
}
```

A solução também tem problemas. A complexidade ainda continua a crescer. Já está melhor, claro, afinal todas as regras
de frete estão na classe certa, mas ainda assim esse código pode ser bastante complexo. 
A ‘Interface’ dessa classe também pode ficar complicada. Afinal, ele precisará saber qual regra aplicar, e isso pode fazer
com que o desenvolvedor comece a receber parâmetros ou mesmo ter uma abundância de métodos sobrecarregados na classe.

**A discussão o tempo inteiro é sobre como balancear entre acoplamento e coesão. Equilibrio é fundamental, já dizia Thanos.**


## OCP na prática:
Precisamos fazer com que a criação de novas regras seja mais simples, e que esa mudança propague automaticamente por todo o sistema.

Outro conceito que nos ajuda a ter classes coesas e que evoluam mais fácil é pensar sempre em escrever classes que são:
"Abertas para extensão", mas "fechadas para modificação". (`Open Close principle`). 

**Abetas para extensão**: Estender o comportamento da classe deve ser fácil.

**Fechadas para modificação**: Ela não deve ser modificada(ter o seu código alterado) o tempo todo. 

#### COMO RESOLVER?

Voltamos a classe `PriceCalculator`: Como possibilitar que a regra de frete seja alterada sem
a necessidade de mexer nesse código?
O primeiro passo é criarmos uma abstração para o problema e fazer com que elas possam ser injetadas na classe que as usa.
Se temos diferentes regras de desconto e de frete, basta criarmos ‘interfaces’ que as representam:

Veja as 'interfaces' em `src/O/good` e as suas implementações em `src/O/good/impl`. 

![img.png](image%2Fimg.png)

Com as abstrações em mãos, agora é fazer com que a classe `PriceCalculator` faça uso delas. 
Observe que temos diferentes tipos de implementações das 'interfaces', é necessário que a troca entre elas seja fácil.
Para isso, a solução é deixar de instanciar as implementações concretas dentro da classe, e passar a recebê-las pelo construtor.

Veja que essa simples mudança altera toda a maneira de se lidar com a classe. **Com ela "ABERTA", ou seja, recebendo as
dependências pelo construtor**, podemos passar a implementação concreta que quisermos para ela. 

Clique para acessar a classe:[PriceCalculatorGood.java](good%2FPriceCalculatorGood.java)

Repare na diferença na imagem abaixo:

![img_1.png](image%2Fimg_1.png)

### Classes Extensíveis:
Perceba que a classe anterior agora está `aberta para extensão`. Afinal, basta passarmos diferentes implementações de tabela e de 
frete para que ela execute de maneira distinta. Simultaneamente, está `fechada para modificações`, afinal não há razões
para mudarmos o código dessa classe. 

*OCP aplicado!!!* 


