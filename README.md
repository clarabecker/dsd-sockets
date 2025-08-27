# Descrição 

Este trabalho, desenvolvido para a disciplina de Sistemas Paralelos e Distribuídos (65DSD), demonstra a construção de um sistema cliente-servidor utilizando Sockets. A aplicação permite que clientes remotos executem operações básicas de cadastro (CRUD - Criar, Ler, Atualizar e Excluir) em classes gerenciadas por um servidor.

# Diagrama de Classes 
<img width="803" height="533" alt="Diagrama DSD" src="https://github.com/user-attachments/assets/826fdb5c-9fbd-481a-9845-230f9cc26fa3" />

# Apresentação
link: https://www.canva.com/design/DAGxGKM1MMk/y_hH-Ndb38BFT3Teqc2LSg/edit?utm_content=DAGxGKM1MMk&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton

# Exemplos de testes:

- **INSERT Pessoa**: INSERT;PROFESSOR;12345678900;João da Silva;Rua B, 123;Matematica
- **UPDATE Pessoa**: UPDATE;12345678900;Lucas da Silva;Rua B, 123;
- **GET Pessoa**: GET;12345678900
- **DELETE Pessoa**: DELETE;12345678900
- **INSERT Universidade**: INSERT_UNI;123;UDESC;25;500
- **UPDATE Universidade**: UPDATE_UNI;123;IFC;20;400;
- **GET Universidade**: GET_UNI;123
- **DELETE Universidade**: DELETE_UNI;123
- **ADD PESSOA NA UNIVERSIDADE**: ADD_PESSOA_UNI;123;12345678900
- **REMOVE PESSOA NA UNIVERSIDADE**: REMOVE_PESSOA_UNI;123;12345678900
- **GET PESSOA NA UNIVERSIDADE**: GET_PESSOA_UNI;123;12345678900
-**LIST PESSOA UNIVERSIDADE**:LIST_PESSOA_UNI;123
