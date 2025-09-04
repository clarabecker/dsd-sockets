# Descrição 

Este trabalho, desenvolvido para a disciplina de Sistemas Paralelos e Distribuídos (65DSD), demonstra a construção de um sistema cliente-servidor utilizando Sockets. A aplicação permite que clientes remotos executem operações básicas de cadastro (CRUD - Criar, Ler, Atualizar e Excluir) em classes gerenciadas por um servidor.

# Diagrama de Classes 
<img width="803" height="533" alt="Diagrama DSD" src="https://github.com/user-attachments/assets/826fdb5c-9fbd-481a-9845-230f9cc26fa3" />

# Apresentação
link: https://www.canva.com/design/DAGxGKM1MMk/y_hH-Ndb38BFT3Teqc2LSg/edit?utm_content=DAGxGKM1MMk&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton

# Exemplos de testes:

===== MENU =====
1 - Inserir Pessoa
2 - Atualizar Pessoa
3 - Consultar Pessoa
4 - Remover Pessoa
5 - Listar Pessoas
6 - Inserir Universidade
7 - Consultar Universidade
8 - Listar Pessoas em Universidade
9 - Deletar Universidade
10 - Adicionar Pessoa em Universidade
11 - Remover Pessoa de Universidade
12 - Consultar Pessoa em Universidade
13 - Sair

**INSERÇÃO PROFESSOR:** 
1
PROFESSOR;12345678900;João da Silva;Rua B, 123;Matematica

**INSERÇÃO ESTUDANTE:** 
1
ESTUDANTE;12345678911;Renata da Silva;Rua B, 123;44444444

**ATUALIZAÇÃO PESSOA:**
2
12345678900;João da Silva;Rua B, 123;Português

**CONSULTA PESSOA:**
3
12345678900

**INSERÇÃO UNIVERSIDADE:**
6
123;UDESC;25;500

**ADICIONAR PESSOA NA UNIVERSIDADE:**
10
123;12345678900
10
123;12345678911
