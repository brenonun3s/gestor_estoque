# Escolha o Idioma | Choose your language

[Português](#estoquemei---backend) | [English](#estoquemei---backend-1)

---

# EstoqueMEI - Backend

Bem-vindo ao repositório do **Backend do EstoqueMEI**, responsável pela lógica de negócio, segurança, persistência de dados e geração de relatórios.

---

## 🔍 Objetivo do Repositório

Fornecer uma **API REST segura e escalável** para o sistema EstoqueMEI, permitindo:

✔ Autenticação e autorização com JWT
✔ Gerenciamento de usuários e produtos
✔ Persistência de dados com PostgreSQL
✔ Geração de relatórios com JasperReports

---

## 🛠 Tecnologias e Ferramentas

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge\&logo=openjdk\&logoColor=white)
![Spring Boot](https://img.shields.io/badge/springboot-6DB33F.svg?style=for-the-badge\&logo=springboot\&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/postgresql-%23336791.svg?style=for-the-badge\&logo=postgresql\&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge\&logo=docker\&logoColor=white)
![Linux](https://img.shields.io/badge/linux-FCC624.svg?style=for-the-badge\&logo=linux\&logoColor=black)

---

## 📂 Estrutura do Repositório

```
📦 estoque
 ┣ 📂 src/main/java
 ┃ ┣ 📂 config     
 ┃   ┗ 📂 security     
 ┃ ┣ 📂 controller
 ┃ ┣ 📂 dto     
 ┃    ┣ 📂 mapper
 ┃    ┣ 📂 request
 ┃    ┗ 📂 response   
 ┃ ┣ 📂 exceptions
 ┃    ┣ 📂 custom
 ┃    ┗ 📂 handler      
 ┃ ┣ 📂 model
 ┃    ┣ 📂 entity
 ┃    ┗ 📂 enums
 ┃ ┣ 📂 repository     
 ┃ ┗ 📂 service
 ┣ 📂 src/main/resources
 ┃  ┣ db
 ┃ ┣ reports           # Relatórios Jasper
 ┃ ┗ application.properties
 ┣ Dockerfile
 ┗ README.md
```

---

## 🔐 Segurança

A aplicação utiliza **Spring Security** com **JWT** para autenticação e controle de acesso, garantindo segurança nas requisições.

---

## 🐳 Infraestrutura

O projeto é conteinerizado com **Docker**, facilitando a execução em ambientes Linux e o deploy.

---

## 🌐 Contato

[![LinkedIn](https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge\&logo=linkedin\&logoColor=white)](https://www.linkedin.com/in/brenonun3s/)
[![Email](https://img.shields.io/badge/Outlook-0078D4?style=for-the-badge&logo=microsoft-outlook&logoColor=white)](mailto:morais.brenonunes@hotmail.com) 

---

# EstoqueMEI - Backend

Welcome to the **EstoqueMEI Backend** repository, responsible for business logic, security, data persistence, and report generation.

---

## 🔍 Repository Purpose

Provide a **secure and scalable REST API** for the EstoqueMEI system, featuring:

✔ JWT-based authentication and authorization
✔ Product and user management
✔ PostgreSQL persistence
✔ JasperReports integration

---

## 🛠 Technologies and Tools

Java • Spring Boot (Data JPA, Security, Validation) • JWT • PostgreSQL • Docker • Linux • JasperReports

---

## Check out my portfolio

[https://portfolio-brenonunes.vercel.app/](https://portfolio-brenonunes.vercel.app/)
