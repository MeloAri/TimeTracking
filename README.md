# TimeTracking
Sistema de Gestão de Ponto


Este é o backend de um sistema completo de controle de jornada de trabalho, desenvolvido com Spring Boot projeto foca em segurança robusta e na implementação de diferentes níveis de acesso (RBAC - Role-Based Access Control) para atender às necessidades de administradores e funcionários.

🚀 Tecnologias Utilizadas
Java 17

Spring Boot 3

Spring Security (Autenticação e Autorização)

JSON Web Token (JWT) (Comunicação Stateless)

Spring Data JPA (Persistência de dados)

MySQL/H2 (Banco de Dados)

Maven (Gerenciador de dependências)

🛡️ Destaques de Segurança (Spring Security)
O grande diferencial deste projeto é a arquitetura de segurança, que implementa:

Autenticação JWT: Proteção de endpoints através de tokens temporários.

Role-Based Access Control (RBAC): * ADMIN: Possui permissão para gerenciar grupos (empresas) e cadastrar novos funcionários.

USER (Funcionário): Possui acesso restrito ao módulo de "Meu Ponto" para registro de jornada.

Criptografia de Senhas: Utilização de BCryptPasswordEncoder para garantir que as credenciais nunca sejam armazenadas em texto puro.

Security Filter Chain: Filtros customizados para interceptar requisições e validar a autoridade de cada perfil antes de acessar os recursos.

🏗️ Estrutura de Rotas (Resumo)
POST /api/auth/login: Realiza a autenticação e retorna o token JWT junto com o cargo (Role).

POST /api/auth/register-admin: Criação de administradores.

POST /api/ponto/registrar: Registro de entrada, saída e intervalos (exclusivo para funcionários).

🔧 Como Rodar o Projeto
Clone o repositório.

Crie um arquivo application.properties na pasta src/main/resources/ baseando-se no application.properties.example.

Certifique-se de ter o Maven instalado.

Execute o comando:

Bash

mvn spring-boot:run
