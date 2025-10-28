# 🐳 Docker Manager API — Java 21 + Spring Boot + OpenAPI

> API backend para gerenciar **containers e imagens Docker** via **Java + Spring Boot**, com arquitetura limpa, documentação via **Swagger/OpenAPI**, e integração direta com o **Docker Engine**.

---

## 📘 Sumário

- [Descrição](#-descrição)
- [Tecnologias](#-tecnologias)
- [Arquitetura](#-arquitetura)
- [Requisitos](#-requisitos)
- [Configuração do Ambiente](#-configuração-do-ambiente)
- [Execução](#-execução)
- [Documentação da API](#-documentação-da-api)
- [Endpoints Principais](#-endpoints-principais)
- [Licença](#-licença)
- [Autor](#-autor)

---

## 💡 Descrição

O **Docker Manager API** permite **listar, criar, iniciar, parar e remover containers**, além de **listar e filtrar imagens Docker** — tudo através de endpoints REST simples e seguros.

Este projeto segue as melhores práticas de desenvolvimento, com foco em:
- Arquitetura **limpa e desacoplada**;
- **Documentação automática** com Swagger/OpenAPI;
- **Integração real com o Docker Engine** via biblioteca `docker-java`;
- Código modular, claro e de fácil manutenção.

---

## 🧰 Tecnologias

| Categoria | Tecnologia |
|------------|-------------|
| Linguagem | **Java 21** |
| Framework | **Spring Boot 3.5.x** |
| Cliente Docker | **docker-java 3.4.0** |
| Documentação | **Springdoc OpenAPI 2.6.0** |
| Build | **Maven** |
| Containerização | **Docker** |

---

## 🧱 Arquitetura

O projeto segue o padrão **Ports and Adapters (Arquitetura Hexagonal)**:

```
camati.docker_manager
├── config            # Configurações (DockerClient, OpenAPI, etc.)
├── controllers       # Camada de entrada (REST API)
├── dto               # Objetos de transferência de dados
├── ports             # Interfaces (contratos de serviço e portas da aplicação)
├── services          # Implementações concretas dos ports
└── DockerManagerApplication.java
```

Essa estrutura facilita manutenção e evolução, permitindo trocar implementações (como o cliente Docker) sem alterar as regras de negócio.

---

## ⚙️ Requisitos

- **Java 21+**
- **Maven 3.9+**
- **Docker Engine ativo**
- (Opcional) **Docker Desktop** ou **WSL 2**

### 🔧 Docker Engine no Windows/WSL

Para que o backend se conecte ao Docker, é necessário expor o daemon no host local:

1. Abra o **Docker Desktop**
2. Vá em **Settings → General**
3. Ative a opção:  
   ✅ *Expose daemon on tcp://localhost:2375 without TLS*

> ⚠️ Importante: essa configuração é apenas para **ambientes de desenvolvimento**.

---

## 🧩 Configuração do Ambiente

Clone o repositório:

```bash
git clone https://github.com/AugustoCamati/docker-manager.git
cd docker-manager
```

Compile o projeto:

```bash
mvn clean install
```

Execute a aplicação:

```bash
mvn spring-boot:run
```

A API estará disponível em:
```
http://localhost:8080
```

---

## 📚 Documentação da API

Após iniciar o servidor, acesse:

| Tipo | URL |
|------|-----|
| Swagger UI | [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) |
| OpenAPI JSON | [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs) |

---

## 🔌 Endpoints Principais

### 🐋 Containers

| Método | Endpoint | Descrição |
|--------|-----------|------------|
| `GET` | `/api/containers` | Lista containers ativos |
| `GET` | `/api/containers?showAll=true` | Lista todos containers (ativos e parados) |
| `POST` | `/api/containers/{imageName}/create` | Cria um novo container a partir de uma imagem |
| `POST` | `/api/containers/{id}/start` | Inicia um container |
| `POST` | `/api/containers/{id}/stop` | Para um container |
| `DELETE` | `/api/containers/{id}/delete` | Remove um container |

### 🧱 Imagens

| Método | Endpoint | Descrição |
|--------|-----------|------------|
| `GET` | `/api/images` | Lista todas as imagens disponíveis |
| `GET` | `/api/images/filter?filterName=nginx` | Filtra imagens pelo nome |
| `DELETE` | `/api/images/{id}` | Remove uma imagem específica |

---

## 🧾 Licença

Este projeto está licenciado sob a **MIT License** — veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

## 💚 Autor

**Augusto Camati**  
Desenvolvedor Fullstack Java / Node.js  
🔗 [GitHub](https://github.com/augustoCamati) • [LinkedIn](https://linkedin.com/in/augustocamati)
