
# 🏥 Praesens API

> API inteligente de predição de faltas em consultas médicas, utilizando dados clínicos, histórico do paciente e variáveis externas como clima e trânsito.

---

## 📋 Sobre o Projeto

A **Praesens API** é uma solução backend desenvolvida para calcular a probabilidade de um paciente faltar a uma consulta médica. O sistema combina dados históricos do paciente com informações em tempo real de APIs externas para gerar uma predição precisa via modelo de machine learning.

A ideia central é auxiliar clínicas e consultórios a otimizarem sua agenda, reduzindo o impacto de no-shows e melhorando a gestão de horários.

---

## ⚙️ Como Funciona

A API recebe os dados de um paciente e uma consulta agendada, processa as seguintes variáveis e retorna a chance estimada de falta:

- 🏥 **Tipo e valor do agendamento** - influência no risco de prejuízo para a empresa
- 📅 **Histórico de agendamentos** — frequência e padrão de consultas anteriores
- ❌ **Histórico de faltas** — quantidade e recorrência de ausências passadas
- 🌦️ **Condições climáticas** — integração com API de clima para o dia da consulta
- 🚗 **Condições de trânsito** — integração com API de trânsito para a rota do paciente

Todas as variáveis são processadas pelo motor de IA baseado em **XGBoost**, que retorna um score de probabilidade de falta entre 0 e 1.

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Finalidade |
|---|---|
| Java 17 | Linguagem principal |
| Spring Boot | Framework web e injeção de dependências |
| XGBoost | Motor de machine learning para predição |
| Swagger / OpenAPI | Documentação e testes interativos da API |
| Docker | Containerização da aplicação |
| Render | Deploy em nuvem |

## 🚀 Endpoints

A documentação completa e interativa está disponível via Swagger UI após subir a aplicação:

```
GET /swagger-ui.html
```

### Endpoint principal

```
POST /previsao
```

## 🌐 Deploy

A aplicação está disponível via **Render**, rodando em container Docker, sendo possível acessar o Swagger da aplicação.

**URL base:** `https://praesens.onrender.com/swagger-ui/index.html`


## 💻 Rodando Localmente (sem Docker)

**Pré-requisitos:** Java 17+, Maven

```bash
# Clonar o repositório
git clone https://github.com/seu-usuario/praesens-api.git
cd praesens-api

# Build e execução
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

> ⚠️ Por estar em um plano gratuito do Render, a instância pode entrar em modo de hibernação após inatividade. A primeira requisição pode demorar alguns segundos para inicializar.
