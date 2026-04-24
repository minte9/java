# FX Banking System

Spring Boot + PostgreSQL example for withdrawals, deposits, and FX exchanges between accounts.

## Run

```bash
docker compose up -d
mvn spring-boot:run
```

## Seeded accounts

- Account 1: EUR 1000.00
- Account 2: USD 1000.00
- Account 3: RON 5000.00

## Endpoints

```bash
curl http://localhost:8080/accounts

curl -X POST http://localhost:8080/accounts/1/withdraw \
  -H "Content-Type: application/json" \
  -d '{"amountMinor":1000}'

curl -X POST http://localhost:8080/accounts/1/deposit \
  -H "Content-Type: application/json" \
  -d '{"amountMinor":1000}'

curl -X POST http://localhost:8080/transfers/fx \
  -H "Content-Type: application/json" \
  -H "Idempotency-Key: tx-001" \
  -d '{"fromAccountId":1,"toAccountId":2,"sourceAmountMinor":10000,"exchangeRate":"1.08"}'
```

`amountMinor` means cents/bani/etc. Example: EUR 10.00 = 1000.

## Tests

```bash
mvn test
```

Requires Docker for Testcontainers integration tests.

## Stress client

Start server first, then:

```bash
cd stress-client
mvn test
```
