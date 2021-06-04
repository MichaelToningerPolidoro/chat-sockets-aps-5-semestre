<h1 align="center">APS 5º Semestre</h1>

***

## <a name="indice">Índice :bookmark:</a>
  1. [Objetivo](#objetivo)
  2. [Integrantes](#integrantes)
  3. [Tecnologias utilizadas](#tecnologias-utilizadas)
  4. [Conceitos aprendidos](#conceitos-aprendidos)
  5. [Como utilizar?](#como-utilizar)

***

## <a name="objetivo">1. Objetivo :dart:</a>
  O projeto consiste em um chat, utilizando sockets de rede. Ele possui
  um sistema de login para usuários pré-cadastrados que estarão aptos a  se conectar, 
  enviar e receber mensangens dos clientes. A aplicação também realiza uma tratativa
  em mensagens indesejadas.
  <br/><br/>[Voltar ao índice](#indice)

## <a name="integrantes">2. Integrantes :sunglasses:</a>
  - [Lucas Silva Santos](https://www.linkedin.com/in/lucas-santos-2639b5163/)
  - [Marcelo Maeda](https://www.linkedin.com/in/marcelo-yoshio-maeda-junior-43237b190/)
  - [Michael Toninger](https://www.linkedin.com/in/michael-toninger-polidoro-925977193/)
  - [Raquel Ferrari](https://www.linkedin.com/in/raquel-f-ferrari/)

  <br/><br/>[Voltar ao índice](#indice)

## <a name="tecnologias-utilizadas">3. Tecnologias utilizadas :computer:</a>
  Nessa APS foram utilizadas 3 tecnologias principais, que são:
  - Java
  - MySQL
  - Sockets
    
  <br/><br/>[Voltar ao índice](#indice)

## <a name="conceitos-aprendidos">4. Conceitos aprendidos :mortar_board:</a>
  Um dos maiores aprendizados adquiridos nessa APS, foi realizar a troca de mensagens 
  em tempo real entre diversos computadores conectados em um servidor <br/>
  Mas, separando em tópicos:
  - Conexão via Sockets
  - Objetos de I/O do Java para envio e recebimento das mensagens
  - Tratativa de mensagens indesejadas
  - Externalização de dados de conexão com o banco de dados, através de um arquivo properties
  <br/><br/>[Voltar ao índice](#indice)
  
## <a name="como-utilizar">5. Como utilizar? :memo:</a>
  Algumas configurações são necessárias para a utilização do projeto, sendo:  
  - Criar aquivo database.properties no caminho `servidor-aps-5-semestre/src/database.properties`
  - Adicionar propriedades no arquivo seguindo o exemplo abaixo
  
  ```properties
    db.url=jdbc:mysql://localhost/nome-do-banco-de-dados
    db.user=usuario
    db.password=senha
  ```
  
  <br></br>[Voltar ao índice](#indice)
