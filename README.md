🛒 Blink Mart – Event-Driven Quick Commerce Backend

Blink Mart is a scalable, event-driven microservices backend inspired by instant delivery platforms (Blinkit / Zepto / Instamart).
It demonstrates real-world backend engineering practices using Spring Boot, Kafka, Redis, Docker, and MySQL.


Key Highlights
-------------------

✅ Microservices architecture
✅ Event-driven communication using Apache Kafka
✅ Reliable order processing with retry & DLQ
✅ Distributed cache with Redis
✅ Containerized using Docker Compose
✅ Clean separation of concerns (Order, Inventory, Payment)
✅ Production-style error handling & observability


🏗️ Architecture Overview
--------------------------

Client
  |
  v
Order Service  --->  Kafka  --->  Inventory Service
     |                   |
     |                   v
     |              Payment Service
     |
     v
 Redis / MySQL



 Event Flow:
 ----------------
1.Order is placed
2.Order Service publishes event to Kafka
3.Inventory Service reserves stock
4.Payment Service processes payment
5.Order status updated asynchronously
6.Failures → Retry → Dead Letter Queue (DLQ)


🧩 Microservices -> Service	Responsibility
---------------------------------------------------
Order Service ->	Create orders, update order status
Inventory Service ->	Reserve / release stock
Payment Service ->	Handle payments, publish success/failure events
Kafka ->	Event backbone
Redis ->	Caching
MySQL ->	Persistent storage



🛠️ Tech Stack

1. Language: Java 17 / 21
2. Framework: Spring Boot
3. Messaging: Apache Kafka
4. Database: MySQL
5 .Cache: Redis
6 .Build Tool: Gradle
7. Containerization: Docker & Docker Compose
8 .Version Control: Git & GitHub
