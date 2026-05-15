# EY AI Advisory Hub

A simple Spring Boot project for placement discussion, inspired by the EY India Gen AI Intern job description. It models advisory engagements where public-sector, infrastructure, and financial-services teams evaluate projects, track AI use cases, and summarize delivery priorities.

## Why this project fits the JD

- Uses Java and Spring Boot, matching the Java coding-test focus.
- Demonstrates Gen AI consulting ideas such as document review assistants, AI copilots, and agentic workflows.
- Includes advisory/business context: capital projects, public infrastructure, regulatory reform, and investment evaluation.
- Shows practical backend skills: REST APIs, validation, database persistence, service layer, error handling, and dashboard aggregation.

## Tech stack

- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA
- Bean Validation
- H2 in-memory database
- Maven Wrapper

## How to run

```powershell
cd C:\Users\KIIT0001\Documents\Codex\2026-05-15\files-mentioned-by-the-user-registration\ey-ai-advisory-hub
.\mvnw.cmd spring-boot:run
```

If Maven says `JAVA_HOME not found`, run this once in the same PowerShell window:

```powershell
$env:JAVA_HOME='C:\Program Files\Java\jdk-23'
```

The API starts at:

```text
http://localhost:8080
```

H2 database console:

```text
http://localhost:8080/h2-console
```

JDBC URL:

```text
jdbc:h2:mem:eyadvisorydb
```

## Main APIs

Get all engagements:

```http
GET /api/engagements
```

Filter by sector or status:

```http
GET /api/engagements?sector=Financial Services
GET /api/engagements?status=DELIVERY
```

Get dashboard summary:

```http
GET /api/engagements/dashboard
```

Create an engagement:

```http
POST /api/engagements
Content-Type: application/json

{
  "clientName": "Urban Transport Mission",
  "sector": "Public Infrastructure",
  "projectTitle": "Bus rapid transit modernization",
  "aiUseCase": "Gen AI assistant for bid document preparation",
  "estimatedInvestmentCrore": 210,
  "strategicImpactScore": 5,
  "implementationComplexityScore": 3,
  "status": "DISCOVERY",
  "targetCompletionDate": "2026-12-30",
  "keyRisks": ["Procurement delays", "Legacy data quality"]
}
```

## Interview explanation

This project can be explained as a mini advisory engagement tracker. EY-style teams often manage multiple client workstreams across infrastructure, government reforms, and AI transformation. The backend stores each engagement, calculates a priority score from investment value, strategic impact, and implementation complexity, and exposes a dashboard API that can support management reporting.

## Useful demo order

1. Run the app.
2. Open `GET /api/engagements` to show seeded projects.
3. Open `GET /api/engagements/dashboard` to show analytics.
4. Create a new project using the sample JSON.
5. Explain how validation and layered architecture make the project maintainable.

## Run tests

```powershell
$env:JAVA_HOME='C:\Program Files\Java\jdk-23'
.\mvnw.cmd test
```
