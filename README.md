# 🏦 My Banking Microservices Application

This is a full-stack backend microservices project developed using **Spring Boot**, **Docker**, and **Jib**.  
It consists of 6 core services communicating in a microservice architecture:

### 🔧 Microservices Included

- `eurekaserver` – Service registry
- `configserver` – Centralized config (🔗 [view config repo](https://github.com/sushantpaudel77/my-Banking-application-config-server))
- `accounts` – Account management service
- `cards` – Card service
- `loans` – Loan service
- `gatewayserver` – API gateway (Spring Cloud Gateway)

---

## 🚀 Shell Scripts for Build & Push

To simplify Docker image management, the following shell scripts are provided under the `/scripts` directory:

| Script Name                      | Description                                 |
|----------------------------------|---------------------------------------------|
| `jib-build-all.sh`              | Build Docker images using Jib only          |
| `push-all-images.sh`            | Push already built images to Docker Hub     |
| `jib-build-all-and-push-all.sh` | Build and push all images in one go         |

### ✅ Run the Scripts

```bash
Scripts Overview
This folder contains handy scripts to build, push, run, and stop your microservices efficiently using Jib and Maven.

Available Scripts

1. Build all Docker images
./scripts/jib-build-all.sh
Builds Docker images for all microservices using Jib.

No Dockerfile needed.

Images are tagged as docker.io/sushantpaudel77/<service>:latest.

2. Push all Docker images
./scripts/push-all-images.sh
Pushes all locally built images to Docker Hub (docker.io/sushantpaudel77).

3. Build and push all images
./scripts/jib-build-all-and-push-all.sh
Combines build and push into a single step for all services.

4. Run all services
./scripts/run-services.sh
Starts all microservices one by one using Maven.

Each service runs in the background (&) with tests skipped (-DskipTests).

Waits between services to ensure proper startup order.

5. Stop all services
./scripts/stop-services.sh
Stops all Spring Boot services started via spring-boot:run.

Gracefully kills all related Java processes.

Force kills any stubborn processes after a short wait.

Notes
Ensure Maven Wrapper (./mvnw) has execution permission (chmod +x ./mvnw) or use mvn if Maven is installed globally.

You must be logged in to Docker Hub to push images.

Images are tagged with the latest tag and pushed to your Docker Hub repository under your username sushantpaudel77.

Adjust sleep times inside run-services.sh if some services need more time to start properly.

🐳 Docker Compose Setup
Inside the docker-compose directory, you’ll find four environment presets:

docker-compose/
├── default/
├── h2DB/
├── prod/
└── qa/

🧩 You can use whatever suits your need:
default and h2DB → Use in-memory H2 database

prod and qa → Can be configured with MySQL or external DBs

⚠️ NOTE: Using MySQL?
If you're planning to use MySQL, make sure to:

Update your Spring Boot microservices' application.yml files with MySQL configuration

Rebuild your services using Jib so the images include MySQL dependencies:

./scripts/jib-build-all.sh
This ensures that each service is packaged with the correct database drivers and settings for MySQL.

🐳 Docker Compose Commands
Here are the essential Docker Compose commands to manage your environment:

✅ Start All Services
docker compose up --build
This will build the images (if needed) and start all services defined in your Compose file.

🛑 Stop All Services
docker compose down
This stops and removes all containers, but preserves volumes (your DB data stays safe).

💣 Stop and Remove Everything (Clean MySQL Setup)
docker compose down -v
This also removes all volumes, which is useful when you want a fresh MySQL setup.

🛠️ How to Run Monitoring Tools with Docker Compose
📁 Prerequisites
Ensure you are inside your monitoring Docker Compose project folder:

cd ~/Desktop/myFolder/workspace/microservices\ for\ ezypay/docker-compose/h2DB
🚀 1. Start All Monitoring Services
docker compose up --build
This will:

Build and start all containers (Grafana, Loki read/write/backend, Gateway, Alloy, Minio).

Apply all config files.

Watch logs for health checks to pass (especially read, write, grafana, and gateway).

🧹 2. Stop All Services (Keep Volumes)
docker compose down
Stops and removes all running containers.

Keeps data stored in named volumes (e.g., minio-data).

🔥 3. Full Reset (Delete Containers + Volumes)
docker compose down -v
Stops containers and removes all volumes.

Use this if:

You want a clean database/storage.

You suspect old data/config is causing issues.

📊 4. Access Interfaces
Tool	URL
Grafana	http://localhost:3000
Minio UI	http://localhost:9000
Username: loki, Password: supersecret

🧪 5. Testing Queries (in Grafana Explore)
Try querying logs with:

{container="account-ms"} |= "Started"
Make sure datasource is set to Loki.

Choose a short time range (last 5–10 minutes) for quick results.

🚨 Troubleshooting
Check container logs:

docker compose logs -f read write backend gateway grafana
Check if Minio buckets loki-data and loki-ruler exist (create via Minio UI if missing).

Ensure /etc/nginx/nginx.conf is correctly rendered (run inside gateway: cat /etc/nginx/nginx.conf).


Built with 💻, 💡, and a lot of #!/bin/bash
— Sushant Paudel
