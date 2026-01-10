# System Logs Anomaly Detection Platform

A **Dockerized, microservices-based anomaly detection system** that analyzes system logs using a **Machine Learning model (Isolation Forest)** and exposes predictions through a **Spring Boot REST API**.

This project demonstrates real-world integration of **backend engineering, machine learning, Docker networking, and cloud database usage**.

---

## 📌 Project Overview

The system ingests system-level metrics (CPU, memory, disk, network, etc.), detects anomalies using a pretrained ML model, assigns severity levels, identifies likely causes, and stores results in a database.

The entire platform runs as **two independent services**:
1. **Spring Boot API** – orchestration, persistence, and external access
2. **ML Anomaly Service** – real-time inference using a trained model

Both services are containerized and orchestrated using **Docker Compose**.

---

## 🧠 What the System Does

- Accepts system log metrics via REST API
- Sends metrics to a Machine Learning model
- Detects anomalies in real time
- Classifies severity (LOW / MEDIUM / CRITICAL)
- Identifies top contributing metrics
- Stores logs and predictions in MongoDB Atlas
- Returns a combined response to the client

---

## 🏗️ Architecture
<img width="1536" height="1024" alt="systemlogs-anomaly-detection" src="https://github.com/user-attachments/assets/cdee5e7f-064c-4b39-80cf-fb0143a0e65e" />


## 🔄 Request Flow
1. Client sends system metrics JSON
2. Spring Boot validates request
3. Spring Boot calls ML service
4. ML model predicts anomaly & severity
5. Spring Boot stores result in MongoDB
6. Combined response returned to client


## 🤖 Machine Learning Details

- Algorithm: Isolation Forest
- Preprocessing: StandardScaler
- Model format: `.joblib`
- Output includes:
  - Anomaly flag (true / false)
  - Anomaly score
  - Severity level
  - Top contributing metrics

---

## 🛠️ Tech Stack

### Backend
- Java 21
- Spring Boot 3
- Spring Web
- Spring WebClient
- Spring Data MongoDB
- Spring Actuator

### Machine Learning
- Python 3.11
- FastAPI
- scikit-learn
- NumPy
- joblib

### DevOps
- Docker
- Docker Compose
- MongoDB Atlas
- Postman

## 🚀 How to Run the Application
### 🔹 Prerequisites
- Docker
- Docker Compose
- Maven (only if rebuilding JAR)


### 🔹 Step 1: Build Spring Boot JAR

```bash
cd spring-anomaly-api
mvn clean package
```

### 🔹 Step 2: Start the Entire System
From the project root:
``` docker-compose up --build ```


###  Test Full End-to-End Pipeline in PostMan

POST
http://localhost:8080/api/logs

Body JSON
{
  "cpu_utilization": 92.5,
  "memory_usage": 81.2,
  "disk_io": 420,
  "network_latency": 380,
  "process_count": 520,
  "thread_count": 2100,
  "context_switches": 95000,
  "cache_miss_rate": 0.38,
  "temperature": 88,
  "power_consumption": 310,
  "uptime": 140,
  "status": "OK"
}

### OUTPUT
<img width="1366" height="768" alt="Screenshot (30)" src="https://github.com/user-attachments/assets/9272624a-1f32-4781-9cbe-9635a23a4094" />

### Changes to be made
In the `spring-anomaly-api`, in the `application.properties` file, the MongoDB Atlas Username, Password, Cluster Name and Collection Name should be changed as per your configuration.
