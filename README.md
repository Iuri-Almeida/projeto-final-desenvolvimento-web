<div align='center'>
  
  <img width="500" src="https://user-images.githubusercontent.com/60857927/158478144-97443a36-d2e6-4028-9818-f805aafafe52.png" />
  
</div>

<div align = "center">

<p>

  <a href="#introducao">Introdução</a> &#xa0; | &#xa0;
  <a href="#descricao">Descrição</a> &#xa0; | &#xa0;
  <a href="#tecnologias">Tecnologias</a> &#xa0; | &#xa0;
  <a href="#requisitos">Requisitos</a> &#xa0; | &#xa0;
  <a href="#executando">Executando</a> &#xa0; | &#xa0;
  <a href="#endpoints">Endpoints</a> &#xa0; | &#xa0;
  <a href="#referencias">Referências</a>

</p>

</div>

<div id = "introducao">

## :boom: Introdução ##

<p>

  O império continua sua luta incessante de dominar a galáxia, tentando ao máximo expandir seu território e eliminar os rebeldes.
  
  Você, como um soldado da resistência, foi designado para desenvolver um sistema para compartilhar recursos entre os rebeldes.

  Você irá desenvolver uma API REST (sim, nós levamos a arquitetura da aplicação a sério mesmo no meio de uma guerra), ao qual irá armazenar informação sobre os rebeldes, bem como os recursos que eles possuem.

</p>

</div>

<div id = "descricao">

## :pushpin: Descrição ##

<p>

  Esse é o repositório do projeto final do módulo Desenvolvimento Web do curso Let's Code Web Full Stack.

  Esse projeto consiste no desenvolvimento de uma API REST capaz de lidar com fluxos dos Rebeldes em uma guerra.

</p>

</div>

<div id = "tecnologias">

## :rocket: Tecnologias ##

Todas as tecnologias usadas na realização do projeto:

* [Java][java] [Versão 11]
* [IntelliJ][intellij]
* [Spring Boot][spring_boot]
* [H2][h2]
* [Maven][maven]
* [JUnit][junit]
* [Postman][postman]

</div>

<div id = "requisitos">

## :warning: Requisitos ##

<p>

  Antes de executar, você precisar ter o [Git][git] e [Java][java] (Versão 11), bem como o JDK do Java 11 instalados na sua máquina.

</p>

</div>

<div id = "executando">

## :computer: Executando ##

<p>

  Depois de correr tudo certo nas instalações, está na hora de clonar o repositório.

</p>

```bash
# Clone este projeto
$ git clone https://github.com/Iuri-Almeida/projeto-final-desenvolvimento-web.git
# Inicie o projeto pela IDE (IntelliJ, Eclipse, ...)
```

</div>

<div id = "endpoints">

## :bulb: Endpoints ##

<p>

  O arquivo, gerado com o [Postman][postman], está na raiz do projeto. Para utilizá-lo basta baixar o arquivo e importar para o Postman instalado na sua máquina.

  Segue os exemplos dos endpoints que a API está aceitando até o momento.

</p>

* **[GET]** Get rebels - http://localhost:8080/api/rebels


* **[GET]** Get rebel by id - http://localhost:8080/api/rebels/<id>


* **[POST]** Insert rebel - http://localhost:8080/api/rebels
```json
// Rebel object
{
    "name": "Letícia",
    "age": 56,
    "gender": "FEMALE",
    "localization": {
        "lat": 45.76,
        "lon": 81.98,
        "galaxyName": "Via Láctea"
    },
    "inventory": {
        "food": 1,
        "water": 3,
        "ammunition": 6,
        "gun": 3
    }
}
```

* **[PUT]** Update rebel localization - http://localhost:8080/api/localization/<id>
```json
// Localization object
{
  "lat": 67.98,
  "lon": 102.23,
  "galaxyName": "Via Láctea"
}
```

* **[GET]** Report rebel by id - http://localhost:8080/api/rebels/reportTraitorById?fromRebel=<from_id>&toRebel=<to_id>

* **[GET]** Report rebel by name - http://localhost:8080/api/rebels/reportTraitorByName?fromRebel=<from_name>&toRebel=<to_name>

* **[GET]** Trade items by id - http://localhost:8080/api/rebels/tradeItemsById?fromRebel=<from_id>&toRebel=<to_id>
* **[GET]** Trade items by name - http://localhost:8080/api/rebels/tradeItemsByName?fromRebel=<from_name>&toRebel=<to_name>
```json
// List of Inventory object
[
  // Inventory object related to <from_id>
  {
    "food": 1,
    "water": 3,
    "ammunition": 6,
    "gun": 3
  },
  // Inventory object related to <to_id>
  {
    "food": 1,
    "water": 3,
    "ammunition": 6,
    "gun": 3
  }
]
```

* **[GET]** API report - http://localhost:8080/api/report

</div>

<div id = "referencias">

## :key: Referências ##

Alguns locais de onde me baseei para realizar o projeto:

* [Todo List - oliveiraL][todo_list_repo]

:mag: &#xa0; Os ícones usados nesse README foram tirados desse [repositório][icones].

</div>

<!-- Links -->
[java]: https://www.java.com/pt-BR/
[intellij]: https://www.jetbrains.com/idea/
[git]: https://git-scm.com
[icones]: https://gist.github.com/rxaviers/7360908
[spring_boot]: https://spring.io/
[h2]: https://www.h2database.com/html/main.html
[maven]: https://maven.apache.org/
[junit]: https://junit.org/junit5/
[todo_list_repo]: https://github.com/oliveiraL/todo-list
[postman]: https://www.postman.com/