# Livraria

Aplicação desenvolvida para atividade da disciplina de Software Concorrente e Distribuído, ministrada pelo prof. Dr. Sérgio Teixeira de Carvalho no semestre letivo de 2022/1 para o Instituto de Informática da Universidade Federal de Goiás.

A aplicação foi desenvolvida em Java, utilizando banco de dados MariaDB para persistência. Ela usa threads para simular vários usuários disputando recursos do sistema simultaneamente, com controle de concorrência por Monitor de Hoare e algorítmo de request/release.

Seu contexto é o de uma rede de livrarias, com diversas unidades, vendedores e livros em estoque. Os usuários são vendedores que devem fazer login para efetuar alguma operação de cadastro de livro, alteração de quantidade em estoque, busca por título, e remoção do item do estoque. Todas os login e operações são registrados em relatório em arquivo .txt, com a região crítica da aplicação sendo composta por estas ações.


# Concurrent CRUD Logger

Implementação de um CRUD Logger, conforme especificado na aula de 20/07.
* Universidade Federal de Goiás
* Engenharia de Software 
* Software Concorrentes e distribuídos 

## Objetivo 
Simular o acesso de vários usuários por meio de threads que competem por uma região crítica (neste caso o registro de Logs)

### Alunos
* Ariel Marte - 201900264
* Marco Feitosa - 201905542

## Domínio
Aplicação para manter as informações de uma livraria. 
Sendo que nesta aplicação possui Livrarias, Vendedores e Livros

## Configuração 
Configuração para a execução da aplicação
**Executar a partir da classe: *Caixa.java***
### Tecnologias Utilizadas
* Linguagem: Java
* IDE: Eclipse
* Banco de Dados: MariaDB

### Configuração Banco de Dados
1. Instalar MariaBD
2. Criar DataBase: livraria
3. Utilizar as seguintes credenciais no MariaDB.
    1. Usuário: root
    2. Senha: root
4. Porta Padrão: 3306
5. Executar na mesma máquina que a aplicação

*Obs: a aplicação já se encarrega de criar as tabelas e da exclusão de dados existes em caso de re-execução.*
