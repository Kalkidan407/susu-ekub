
# Zhuwa Academy - Susu Savings API

A Spring Boot REST API for managing a rotating weekly savings group.

## Features

- Create groups and register members.
- Record full and partial weekly contributions.
- Prevent invalid payments and overpayments.
- Close a week only when every member has paid in full.
- Rotate payouts in member join order.
- View payment status and payout history.

## Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL(I was neon database)
- Swagger / OpenAPI
- JUnit and Mockito

## Run Locally

1. Configure your PostgreSQL database in `application.properties`.
2. Run:

   ```bash
   ./mvnw spring-boot:run
   ```

3. Open Swagger UI:

   `http://localhost:8080/swagger-ui/index.html`

## Run Tests

```bash
./mvnw test
```

## Main Endpoints

- `POST /api/groups`
- `POST /api/groups/{groupId}/members`
- `POST /api/groups/{groupId}/contributions`
- `POST /api/groups/{groupId}/close-week`
- `GET /api/groups/{groupId}/report`
