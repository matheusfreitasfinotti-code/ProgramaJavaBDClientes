# Cadastro de Clientes com Java

Este projeto consiste em um sistema de cadastro de clientes desenvolvido em Java utilizando arquitetura em camadas. A persistência dos dados é realizada em um banco de dados PostgreSQL por meio da API JDBC.

## Objetivo do projeto

O sistema tem como objetivo cadastrar clientes em um banco de dados PostgreSQL.

Para cada cliente são armazenadas as seguintes informações:

* Nome;
* CPF;
* Telefone;
* E-mail.

A interface permite:

* Inserir novos clientes;
* Listar todos os clientes cadastrados.

Durante o cadastro, o sistema realiza validações para garantir que:

* Todos os campos sejam preenchidos;
* Não exista outro cliente cadastrado com o mesmo CPF.

## Estrutura do projeto

O projeto foi organizado em quatro camadas para separar as responsabilidades da aplicação.

### Modelo (`modelo`)

Representa os dados do sistema.

A classe `Cliente` contém os atributos:

* Nome;
* CPF;
* Telefone;
* E-mail.

### Persistência (`persistencia`)

Responsável pelo acesso ao banco de dados.

A classe `ConexaoFactory` realiza:

* leitura das configurações do arquivo `.env`;
* carregamento do driver JDBC do PostgreSQL;
* abertura da conexão com o banco de dados.

A classe `ClienteRepositoryPostgres` executa as operações de acesso aos dados:

* inserir cliente;
* listar clientes;
* verificar se um CPF já está cadastrado.

### Aplicação (`aplicacao`)

A classe `ClienteService` contém as regras de negócio da aplicação.

Antes de inserir um cliente, são realizadas as seguintes validações:

* nome obrigatório;
* CPF obrigatório;
* telefone obrigatório;
* e-mail obrigatório;
* CPF não pode estar previamente cadastrado.

Somente após essas validações o cliente é enviado para a camada de persistência.

### Interface Gráfica (`ui`)

A classe `ClienteFrame` foi desenvolvida utilizando Java Swing.

A interface possui:

* campo para Nome;
* campo para CPF;
* campo para Telefone;
* campo para E-mail;
* botão **Salvar**;
* tabela para exibição dos clientes cadastrados.

A interface não acessa diretamente o banco de dados. Todas as operações passam pela camada de aplicação.

---

# Tecnologias utilizadas

* Java
* Java Swing
* JDBC
* PostgreSQL
* Git
* GitHub

---

# Biblioteca necessária

Como o projeto não utiliza Maven nem Gradle, é necessário adicionar manualmente o driver JDBC do PostgreSQL.

Crie uma pasta chamada `lib` na raiz do projeto e coloque nela o arquivo `.jar` do driver PostgreSQL.

Driver utilizado:

https://jdbc.postgresql.org/download/postgresql-42.7.11.jar

---

# Configuração do banco de dados

As informações de conexão ficam armazenadas em um arquivo chamado `.env`, localizado na raiz do projeto.

Crie um arquivo `.env` utilizando o seguinte modelo:

```text
APP_DB_URL=jdbc:postgresql://HOST:PORTA/NOME_DO_BANCO?sslmode=require
APP_DB_USUARIO=USUARIO
APP_DB_SENHA=SENHA
```

Substitua os valores pelos dados do seu banco PostgreSQL.

O arquivo `.env` contém informações sensíveis e, por isso, não deve ser enviado ao GitHub.

---

# Compilação

Após configurar o arquivo `.env` e adicionar o driver JDBC na pasta `lib`, compile o projeto utilizando:

```bash
javac -d target/classes -cp "lib/*" src/main/java/br/com/cadastroempregados/App.java src/main/java/br/com/cadastroempregados/modelo/Cliente.java src/main/java/br/com/cadastroempregados/persistencia/ClienteRepositoryPostgres.java src/main/java/br/com/cadastroempregados/persistencia/ConexaoFactory.java src/main/java/br/com/cadastroempregados/aplicacao/ClienteService.java src/main/java/br/com/cadastroempregados/ui/ClienteFrame.java
```

---

# Execução

Execute o programa com o comando:

```bash
java -cp "target/classes;lib/*" br.com.cadastroempregados.App
```

No VS Code também é possível executar utilizando:

**Terminal → Run Task... → Executar**

---

# Funcionalidades

Atualmente o sistema permite:

* Cadastro de clientes;
* Listagem de clientes cadastrados;
* Validação de campos obrigatórios;
* Verificação de CPF duplicado.

---

# Autor

Matheus Freitas Finotti
