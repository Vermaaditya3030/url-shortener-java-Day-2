# URL Shortener — Day 2

A backend URL-shortening service built with Java 17, Spring Boot, PostgreSQL, Redis and Docker.

## Features
- REST API
- Random short aliases
- Custom aliases
- Expiration
- Click analytics
- Delete endpoint
- PostgreSQL persistence
- Redis service included
- Docker Compose
- Swagger/OpenAPI
- Validation and global error handling
- JUnit/Mockito tests
- GitHub Actions CI

## Run with Docker

```bash
docker compose up --build
```

API: http://localhost:8080
Swagger: http://localhost:8080/swagger-ui.html

## Create URL

```bash
curl -X POST http://localhost:8080/api/urls -H "Content-Type: application/json" -d '{"url":"https://github.com/","customAlias":"github","expiryHours":24}'
```

Then open `http://localhost:8080/github`.

## Stats

```bash
curl http://localhost:8080/api/urls/github/stats
```

## Delete

```bash
curl -X DELETE http://localhost:8080/api/urls/github
```

## Tests

```bash
mvn test
```

## GitHub upload

Create a public repository named `url-shortener-java`, then run:

```bash
git init
git add .
git commit -m "feat: build production-style URL shortener"
git branch -M main
git remote add origin https://github.com/Vermaaditya3030/url-shortener-java.git
git push -u origin main
```
## Next improvements

- Add Redis read-through caching
- Add JWT authentication
- Add per-IP rate limiting
- Add click-event analytics
- Add integration tests with Testcontainers
