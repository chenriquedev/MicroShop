# MicroShop

## Apresentação do Projeto

o **MicroShop** é uma aplicação de microsserviços que oferece um conjunto robusto de funcionalidades para gerenciar usuários, produtos e registros de compras. Desenvolvida usando tecnologias modernas como Docker, Spring Boot e PostgreSQL, a **MicroShop** é altamente escalável e resiliente, adequada para atender às demandas de um ambiente empresarial dinâmico.

## Dependências

O projeto utiliza tecnologias como Spring Boot, Spring Data JPA e PostgreSQL para fornecer uma experiência confiável e escalável.
Outras dependências utilizadas foram: **modelmapper** , **flyway-core** , **lombok**.

# Iniciando a Aplicação MicroShop com Docker Compose

Este guia descreve os passos para iniciar a aplicação "MicroShop" usando Docker Compose. Certifique-se de ter o Docker e o Docker Compose instalados em sua máquina antes de prosseguir.

## Iniciando a Aplicação

### 1. Clonar o Repositório

Clone o repositório da aplicação "MicroShop" usando o Git:

```bash
git clone https://github.com/carlloshen/MicroShop.git
```

## 2. Navegar até o Diretório do Projeto

Navegue até o diretório do projeto "MicroShop" que você clonou:

```bash
cd microshop
```

## 3. Iniciar os Microsserviços com Docker Compose

Use o seguinte comando para iniciar os microsserviços com o Docker Compose:

```bash
docker-compose up -d
```

## 4. Acessar a API

Após a inicialização bem-sucedida, você poderá acessar as APIs dos microsserviços:

- User API: [http://localhost:8080/user-api/](http://localhost:8080/user-api/)
- Product API: [http://localhost:8081/product-api/](http://localhost:8081/product-api/)
- Shopping API: [http://localhost:8082/shopping-api/](http://localhost:8082/shopping-api/)

As URLs podem variar dependendo da configuração do seu ambiente.

## 5. Testar as Funcionalidades

Utilize ferramentas como o Postman, o cURL ou outros clientes HTTP para testar as funcionalidades oferecidas pelos microsserviços, como criar usuários, listar produtos, registrar compras, etc.

Lembre-se de ajustar os comandos e as URLs de acordo com a configuração específica do seu ambiente e do projeto.

Isso conclui o processo de inicialização da aplicação "MicroShop" usando Docker Compose. Certifique-se de consultar a documentação e os arquivos de configuração específicos do seu projeto para obter detalhes precisos.


## Principais Componentes
A aplicação é dividida em três componentes principais, cada um com suas funcionalidades específicas:


# User API

## Apresentação

A **User API** é um dos componentes da aplicação de microsserviços "MicroShop". Este serviço é responsável por gerenciar informações de usuários e oferece um conjunto abrangente de funcionalidades relacionadas a usuários.

## Funcionalidades Principais

Aqui estão as principais funcionalidades oferecidas pela **User API**:

1. **Listar Usuários**
    - Rota: `/users`
    - Descrição: Esta funcionalidade permite listar todos os usuários registrados no sistema.

2. **Encontrar Usuário por ID**
    - Rota: `/users/{userId}`
    - Descrição: Permite encontrar um usuário com base no seu ID único.

3. **Criar Novo Usuário**
    - Rota: `/users`
    - Descrição: Possibilita a criação de um novo usuário no sistema, com informações como nome, CPF, endereço, email e telefone.

4. **Excluir Usuário**
    - Rota: `/users/{userId}`
    - Descrição: Permite a exclusão de um usuário com base no seu ID.

5. **Encontrar Usuário por CPF e Chave**
    - Rota: `/users/find-by-cpf`
    - Descrição: Permite encontrar um usuário por meio do seu CPF e chave exclusiva.

6. **Consultar Usuários por Nome**
    - Rota: `/users/query-by-name/{name}`
    - Descrição: Oferece a capacidade de consultar usuários com base em uma correspondência parcial do nome.

## Configuração e Execução

Para utilizar a **User API**, siga os seguintes passos:

1. Certifique-se de que a aplicação "MicroShop" esteja em execução, incluindo o serviço de usuário.

2. Utilize as rotas mencionadas acima para acessar as funcionalidades da **User API**.


# Product API

## Apresentação

A **Product API** é um dos componentes da aplicação de microsserviços "MicroShop". Este serviço é responsável por gerenciar informações de produtos e oferece funcionalidades relacionadas a produtos.

## Funcionalidades Principais

Aqui estão as principais funcionalidades oferecidas pela **Product API**:

1. **Listar Produtos**
    - Rota: `/products`
    - Descrição: Esta funcionalidade permite listar todos os produtos disponíveis no sistema.

2. **Encontrar Produto por Identificador**
    - Rota: `/products/{productIdentifier}`
    - Descrição: Permite encontrar um produto com base em seu identificador único.

3. **Criar Novo Produto**
    - Rota: `/products`
    - Descrição: Possibilita a criação de um novo produto no sistema, com informações como nome, descrição, preço e categoria.

4. **Excluir Produto**
    - Rota: `/products/{productId}`
    - Descrição: Permite a exclusão de um produto com base em seu ID.

## Configuração e Execução

Para utilizar a **Product API**, siga os seguintes passos:

1. Certifique-se de que a aplicação "MicroShop" esteja em execução, incluindo o serviço de produto.

2. Utilize as rotas mencionadas acima para acessar as funcionalidades da **Product API**.


# Shopping API

## Apresentação

A **Shopping API** é um dos componentes da aplicação de microsserviços "MicroShop". Este serviço é responsável por registrar compras feitas por usuários. Além disso, ele se comunica com os serviços "User" e "Product" para enriquecer as informações das compras.

## Funcionalidades Principais

Aqui estão as principais funcionalidades oferecidas pela **Shopping API**:

1. **Listar Compras**
    - Rota: `/shopping`
    - Descrição: Esta funcionalidade permite listar todas as compras registradas no sistema.

2. **Encontrar Compras por Usuário**
    - Rota: `/shopping/user/{userIdentifier}`
    - Descrição: Permite encontrar todas as compras feitas por um usuário específico com base em seu identificador.

3. **Encontrar Compras por Data**
    - Rota: `/shopping/date`
    - Descrição: Possibilita encontrar todas as compras realizadas em uma data específica ou posterior.

4. **Criar Nova Compra**
    - Rota: `/shopping`
    - Descrição: Permite a criação de um novo registro de compra no sistema. É possível incluir informações como o identificador do usuário e os itens comprados.

## Comunicação com "User" e "Product"

A **Shopping API** se comunica com os serviços "User" e "Product" para enriquecer as informações das compras. Duas classes de serviço são usadas para essa comunicação:

### 1. UserService

O serviço "UserService" lida com a comunicação com o serviço "User". Ele permite encontrar um usuário com base em seu CPF e chave exclusiva. Esta comunicação é essencial para associar as compras aos usuários corretos.

### 2. ProductService

O serviço "ProductService" comunica-se com o serviço "Product". Ele permite encontrar informações detalhadas de um produto com base em seu identificador. Isso é útil para incluir informações precisas dos produtos nas compras registradas.

## Configuração e Execução

Para utilizar a **Shopping API**, siga os seguintes passos:

1. Certifique-se de que os três serviços estejam em execução.

2. Utilize as rotas mencionadas acima para acessar as funcionalidades da **Shopping API**.


## Contribuição

O **MicroShop** é um projeto em constante evolução e a sua contribuição é fundamental para o seu sucesso contínuo. Se você deseja participar e melhorar este projeto, aqui estão algumas maneiras pelas quais você pode contribuir:

- Relate Problemas: Se encontrar algum problema, erro ou comportamento inesperado, sinta-se à vontade para abrir um problema (issue) no repositório do projeto. Certifique-se de fornecer informações detalhadas sobre o problema para que possa ser reproduzido e corrigido de maneira eficaz.

- Proponha Melhorias: Se tiver ideias para melhorias, novos recursos ou funcionalidades adicionais que acredita que seriam valiosos para a aplicação, compartilhe essas ideias abrindo uma issue. Discutiremos suas sugestões em detalhes.

- Envie Pull Requests: Se deseja contribuir diretamente com código, você pode criar pull requests (PRs) com suas implementações. Certifique-se de seguir as diretrizes de contribuição e incluir testes apropriados para sua funcionalidade.

- Compartilhe Experiências: Se você usou a **MicroShop** em seu projeto e deseja compartilhar suas experiências, histórias de uso ou casos de sucesso, sinta-se à vontade para fazê-lo. Isso pode inspirar e ajudar outros desenvolvedores.

Sua contribuição é valiosa e apreciada. Trabalhando juntos, podemos tornar a **MicroShop** uma ferramenta ainda mais poderosa e eficaz para o desenvolvimento de aplicações de microsserviços.

Agradecemos por considerar a contribuição para o projeto e estamos ansiosos para trabalhar com você!

## Agradecimentos

Gostaríamos de agradecer ao autor do livro "Back-end Java Microsserviços, Spring Boot e Kubernete" por fornecer informações valiosas e inspiração para o desenvolvimento deste projeto. O livro foi uma fonte fundamental de conhecimento que nos ajudou a criar a aplicação "MicroShop".

- Autor: [Eduardo Felipe Zambom Santana](...)
- Título do Livro: [Back-end Java](https://www.casadocodigo.com.br/products/livro-backend-java)

[Carlos Henrique]
