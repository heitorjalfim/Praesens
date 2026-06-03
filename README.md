# 🏥 PRAESENS API: Inteligência Preditiva em Saúde

> **Otimizando agendamentos e reduzindo o impacto financeiro do no-show através de ciência de dados e engenharia de software.**

---

### 📑 1. O DESAFIO
O no-show (pacientes que faltam sem aviso) e um dos maiores gargalos financeiros em clinicas. O problema vai alem da simples falta: gera ociosidade, desperdicio de insumos e impacta diretamente a receita. A Praesens API atua como uma camada de inteligencia sobre o ERP da clinica, convertendo dados historicos e variaveis externas em probabilidade de comparecimento.

---

### 🏗️ 2. ARQUITETURA E FLUXO
O sistema foi desenhado para ser resiliente e escalavel:

| Etapa | Acao | Ferramenta |
| :--- | :--- | :--- |
| Ingestao | Recebimento de agendamento | Spring Boot REST |
| Contexto | Busca de clima e trafego | OpenWeather / TomTom |
| Predicao | Calculo de risco | XGBoost Model |
| Resultado | Score de 0 a 1 | API Response |

---

### ⚙️ 3. TECNOLOGIAS E FERRAMENTAS
🚀 Core: Java 17, Spring Boot 3, Spring Cloud OpenFeign.
🧠 Inteligencia: XGBoost (Engine de ML).
☁️ Deploy: Docker, Render Cloud.
📝 Documentacao: OpenAPI 3 / Swagger UI.

---

### 🛠️ 4. GUIA DE RODAGEM (SEM DOCKER)
1. Clone o projeto: git clone https://github.com/heitorjalfim/Praesens.git
2. Configuracao: Defina OPENWEATHER_API_KEY e TOMTOM_API_KEY no seu ambiente.
3. Execucao:
   - Acesse a pasta raiz via terminal.
   - Execute: ./mvnw spring-boot:run
   - Acesse: http://localhost:8080/swagger-ui/index.html

---

### 🌐 5. DOCUMENTACAO E TESTES (SWAGGER)
Acesse a documentacao interativa e teste os endpoints diretamente no navegador:
https://praesens.onrender.com/swagger-ui/index.html#/

---

### 📊 6. EXEMPLO DE REQUISICAO (INPUT)
{
  "tipoProcedimento": "Estetico",
  "horaAgendamento": 14,
  "valorProcedimento": 250,
  "totalAgendamentos": 12,
  "totalFaltas": 1
}

---

### ⚠️ AVISO
Este projeto esta em ambiente de teste no Render. A primeira requisicao pode levar alguns segundos devido a hibernacao do plano gratuito.
