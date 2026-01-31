## RABBITMQ DEMO APP

[![Java CI with Maven](https://github.com/a-dubaj/AsynchronousMessaging/actions/workflows/maven.yml/badge.svg?event=push)](https://github.com/a-dubaj/AsynchronousMessaging/actions/workflows/maven.yml)


# Asynchronous Messaging with Java, Spring Boot, and RabbitMQ

This repository is a practical example of **asynchronous messaging** using **Java**, **Spring Boot (Spring AMQP)**, and **RabbitMQ**. It demonstrates a typical event-driven flow:

**Producer → Exchange → Queue → Consumer**

---

## Tech Stack
- Java
- Spring Boot (AMQP / RabbitMQ)
- RabbitMQ
- Maven (Maven Wrapper included)

---

## What This Project Demonstrates
- Publishing messages to RabbitMQ (producer)
- Consuming messages from RabbitMQ (consumer)
- Basic RabbitMQ topology configuration (exchange / queue / binding)
- Running RabbitMQ locally via Docker Compose

> If your code includes retries, dead-letter queues (DLQ), or manual acknowledgements, add details in the **Reliability** section below.

---

## Prerequisites
- **JDK** (recommended 17+)
- **Docker** + **Docker Compose** (for local RabbitMQ)
- Linux / macOS / Windows

---

## Quick Start (Local)

### 1) Start RabbitMQ (Docker Compose)
If your repository doesn’t already include a `docker-compose.yml`, add the example below.

**docker-compose.yml**
```yaml
version: "3.8"

services:
  rabbitmq:
    image: rabbitmq:3-management
    container_name: rabbitmq
    ports:
      - "5672:5672"    # AMQP
      - "15672:15672"  # Management UI
    environment:
      RABBITMQ_DEFAULT_USER: guest
      RABBITMQ_DEFAULT_PASS: guest
