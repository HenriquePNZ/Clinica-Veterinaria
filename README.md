# Sistema de Gestão para Clínica Veterinária

Este projeto implementa um sistema de gestão para uma clínica veterinária, que permite o gerenciamento de tutores e seus respectivos pets, facilitando o acompanhamento do histórico de peso e outros dados relacionados à saúde dos animais.

## Funcionalidades

O sistema possui as seguintes funcionalidades principais:

### Gerenciamento de Tutores
- **Cadastro de Tutor**: Adiciona novos tutores com informações básicas como nome, telefone, email e endereço.
- **Atualização de Tutor**: Permite atualizar os dados de contato e endereço de tutores já cadastrados.
- **Listagem de Tutores**: Exibe uma lista de todos os tutores registrados no sistema.
- **Busca de Tutor por Nome**: Pesquisa tutores pelo nome ou parte dele.
- **Remoção de Tutor**: Remove um tutor e todos os pets associados.

### Gerenciamento de Pets
- **Cadastro de Pet**: Adiciona novos pets associados a um tutor, com dados como nome, raça, idade, sexo, cor da pelagem e estado reprodutivo.
- **Atualização de Pet**: Atualiza as informações de pets existentes.
- **Histórico de Peso do Pet**: Permite adicionar e visualizar o histórico de peso de cada pet.
- **Listagem de Pets por Tutor**: Lista todos os pets de um determinado tutor.
- **Remoção de Pet**: Remove pets do sistema.

## Estrutura do Projeto

O projeto segue uma arquitetura em camadas com base no padrão **DAO (Data Access Object)**, facilitando a interação com o banco de dados e a manutenção da aplicação.

### Pacotes Principais

- `model`: Contém as classes de domínio da aplicação, como `Tutor` e `Pet`, que representam os objetos principais da clínica.
- `dao`: Contém as classes DAO (`PetDAO`, `TutorDAO`) responsáveis pela persistência dos dados no banco de dados.
- `controller`:   Uilizado para a lógica de controle da aplicação, como a manipulação de requisições e respostas no caso de um ambiente web ou desktop.

### Classes Principais

#### `Tutor`
Classe responsável por armazenar as informações dos tutores, como nome, telefone, email, endereço e a lista de pets associados.

#### `Pet`
Classe que representa os animais de estimação, contendo dados como nome, raça, idade, sexo, cor da pelagem, estado reprodutivo e o histórico de peso.

#### `TutorDAO`
Classe responsável por realizar as operações de CRUD (Create, Read, Update, Delete) no banco de dados para os tutores.

#### `PetDAO`
Classe responsável por realizar as operações de CRUD (Create, Read, Update, Delete) no banco de dados para os pets.

## Requisitos

- **Java 8+**
- **Bibliotecas JDBC** para a conexão com o banco de dados.
- **Banco de dados relacional**: H2.
- (Opcional) **IDE** como NetBeans, Eclipse ou IntelliJ para facilitar o desenvolvimento.

## Melhorias Futuras

- Implementar interface gráfica para facilitar a utilização do sistema.
- Adicionar suporte para consultas veterinárias e tratamentos.
- Integrar o sistema com APIs externas para envio de lembretes via email ou SMS.
- Melhorar o gerenciamento do histórico de saúde do pet, incluindo vacinas e consultas.
