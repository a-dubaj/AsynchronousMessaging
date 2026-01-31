## RABBITMQ DEMO APP

[![Java CI with Maven](https://github.com/a-dubaj/AsynchronousMessaging/actions/workflows/maven.yml/badge.svg?event=push)](https://github.com/a-dubaj/AsynchronousMessaging/actions/workflows/maven.yml)


# Asynchronous Messaging with Java, Spring Boot, and RabbitMQ

This repository is a hands-on example of **asynchronous messaging** using **Java**, **Spring Boot (Spring AMQP)**, and **RabbitMQ**. It demonstrates a typical event-driven pipeline:

**Producer → Exchange → Queue → Consumer**

The project includes a **Docker Compose** setup that starts RabbitMQ and runs the Spring Boot application using the Maven Wrapper.

---

## Tech Stack

- **Java**
- **Spring Boot** (Spring AMQP / RabbitMQ)
- **RabbitMQ**
- **Maven** (Maven Wrapper included)

---

## What This Project Covers

- Publishing messages to RabbitMQ (producer)
- Consuming messages from RabbitMQ (consumer)
- Basic AMQP topology configuration (exchange / queue / binding)
- Running everything locally with Docker Compose

---

## Requirements

- **Docker Engine** + **Docker Compose v2**
- Linux is recommended for the provided Compose setup (see note below)

---

## Run Locally with Docker Compose (Recommended)

### Important note about networking

The included `docker-compose.yml` uses **host networking** as a workaround for some Docker/iptables configurations (where creating a Docker bridge network fails).

With host networking:
- containers share the host network stack
- ports are exposed directly on your machine
- `ports:` mappings are not required (and are ignored by Docker)

Make sure these ports are **free** on your host:
- RabbitMQ AMQP: **5672**
- RabbitMQ Management UI: **15672**
- Spring Boot app: **8080** (configurable)

### Start

From the repository root:

```bash
docker compose up
```

## Stop

```bash
docker compose stop
```

## Login

```bash
docker compose down
RabbitMQ UI
URL: http://localhost:15672

username: guest

password: guest
```

## Configuration

### RabbitMQ Connection
The application connects to RabbitMQ using environment variables set in `docker-compose.yml`:

```properties
SPRING_RABBITMQ_HOST=localhost
SPRING_RABBITMQ_PORT=5672
SPRING_RABBITMQ_USERNAME=guest
SPRING_RABBITMQ_PASSWORD=guest
```

### Application Port
The app runs on:

```properties
SERVER_PORT=8080
```

To change it, update `SERVER_PORT` in `docker-compose.yml`.

## Messaging Topology (Exchange / Queue / Routing Key)

The exact topology is defined in the application configuration and/or Spring AMQP config classes.

To find it, check:
- `src/main/resources/application.yml` or `application.properties`
- Configuration classes under `src/main/java/...` (often named `*Rabbit*Config` / `*Amqp*Config`)

Once verified, document the values here:
- **Exchange**: YOUR_EXCHANGE_NAME
- **Queue**: YOUR_QUEUE_NAME
- **Routing key**: YOUR_ROUTING_KEY

## How to Publish a Message

How you publish depends on how the producer is implemented:
- REST endpoint (`@RestController`)
- Startup runner (`CommandLineRunner`)
- Service method invoked internally

### If you have a REST endpoint

Locate your controller in `src/main/java/...` and adjust the URL accordingly.

Example request template:

```bash
curl -X POST "http://localhost:8080/api/messages" \
  -H "Content-Type: application/json" \
  -d '{"message":"hello rabbit","timestamp":"2026-01-31T12:00:00Z"}'
```

## View Logs (Verify Producer/Consumer)

### App logs
```bash
docker logs -f asyncmsg-app
```

### RabbitMQ logs
```bash
docker logs -f asyncmsg-rabbitmq
```

## Troubleshooting

### Ports already in use

Because host networking exposes ports on the host, you can get conflicts.

Check port usage:
```bash
sudo ss -lntp | egrep ':(5672|15672|8080)\b'
```

Fix:
- Stop the service using the port, or
- Change `SERVER_PORT` in `docker-compose.yml`

### RabbitMQ UI not reachable

```bash
docker ps
docker logs -f asyncmsg-rabbitmq
```

### App cannot connect to RabbitMQ

- Ensure RabbitMQ is healthy (Compose defines a healthcheck)
- Verify RabbitMQ is listening on 5672
- Check app logs:
  ```bash
  docker logs -f asyncmsg-app
  ```

## Run Without Docker (Optional)

If you run RabbitMQ separately (local install or your own container), you can run the app directly.

### Linux/macOS
```bash
./mvnw spring-boot:run
```

### Windows
```cmd
mvnw.cmd spring-boot:run
```

## Project Structure

- `src/main/java` – Source code (producer/consumer/config)
- `src/main/resources` – Spring configuration
- `pom.xml` – Maven dependencies
