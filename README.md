# 📉 Algorithmic Trading & FinTech Code Examples

A curated collection of production-ready code examples, algorithmic trading strategies, and microservice components designed for high-throughput, low-latency financial environments. 

This repository serves as a portfolio demonstrating clean code practices, deterministic financial calculations, and modern architectural patterns within the Java/Spring ecosystem.

---

## 🚀 Key Highlights & Design Principles

* **Financial Precision:** All monetary and price calculations strictly use `BigDecimal` to ensure zero rounding errors, eliminating data corruption common with floating-point types.
* **Event-Driven & Reactive:** Architecture samples focus on asynchronous communication (Apache Kafka) and sliding-window stream processing.
* **Thread Safety:** Core engines are built with concurrency in mind, ensuring safe state management during high-frequency data ingestion.
* **Clean Code & Testability:** Every algorithmic component follows SOLID principles, design patterns (State, Strategy, Adapter), and comes with a deterministic test suite.

---

## 🛠 Project Structure & Implemented Algorithms

### 1. Simple Moving Average (SMA) Crossover Engine
* **Location:** `/sma-crossover-engine`
* **Description:** A thread-safe component that monitors real-time market data streams using custom sliding queues. It automatically calculates short-term (Fast) and long-term (Slow) moving averages.
* **Core Logic:** 
  * **Golden Cross (BUY):** Triggered when the Fast SMA crosses above the Slow SMA.
  * **Death Cross (SELL):** Triggered when the Fast SMA crosses below the Slow SMA.
* **Key Features:** Integrated with **Spring Kafka** listeners, utilizing an internal state lock to prevent redundant signal spamming once a position is already open.

### 2. Market Data Ingestion & Event Inflow (Coming Soon)
* **Location:** `/market-data-provider`
* **Description:** A microservice simulating volatile market ticks using scheduled events and reactive streams to feed trading engines via Kafka topics.

### 3. Cross-Exchange Arbitrage Monitor (In Progress)
* **Location:** `/arbitrage-engine`
* **Description:** Asynchronous comparison engine utilizing `CompletableFuture` to fetch, map, and analyze asset price spreads across multiple mock financial gateways, factoring in dynamic network transaction fees.

---

## 💻 Tech Stack

* **Language:** Java 17 / 21
* **Core Framework:** Spring Boot, Spring Kafka
* **Message Broker:** Apache Kafka
* **Build Tool:** Maven
* **Testing & Verification:** JUnit 5, AssertJ, Mockito

---

## 🧪 Testing Philosophy

In financial software, determinism is critical. The test suites in this repository simulate actual historical price series to verify that:
1. Moving windows correctly purge stale tick data (`FIFO` queue mechanics).
2. Boundary conditions (e.g., precise intersection points) trigger exactly one actionable trading signal.
3. State transitions remain secure under rapid consecutive market shifts.

To execute tests across all modules, run:
```bash
mvn clean test
```

---

## 📧 Contact & Collaboration

If you are a recruiter or technical leader looking to discuss these implementations, feel free to reach out:
* **GitHub Main Profile:** [://github.com](https://://github.com/)
* **LinkedIn / Email:** (Feel free to connect via the contacts provided in my main profile or CV)
