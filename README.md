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
# Build only
./scripts/jib-build-all.sh

# Push only
./scripts/push-all-images.sh

# Build and push all
./scripts/jib-build-all-and-push-all.sh
These scripts use Jib, so no Dockerfile is required. Images will be pushed to Docker Hub under:
docker.io/sushantpaudel77/<service>:latest

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

bash
Copy
Edit
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

📈 Coming Soon
✅ Monitoring using Prometheus & Grafana

🔐 Security using Keycloak (OAuth2 / OIDC)

☸️ Kubernetes deployment

🎯 Helm for Helm chart packaging & deployment

🙌 Follow My Progress
Stay tuned as I continue building and improving this system!
You can find the config repo here → my-Banking-application-config-server

Built with 💻, 💡, and a lot of #!/bin/bash
— Sushant Paudel


### 💡 Tips for GitHub:

- Name your root GitHub repo something like `my-banking-app-microservices`.
- Create a `/scripts` folder and store your 3 `.sh` scripts there.
- Add `README.md` to the root.
- Push it and pin the repo on your LinkedIn.

Let me know if you'd like me to help with a `README` badge section (e.g., Docker Hub, GitHub Actions) or visuals (architecture diagram or terminal screenshot)!

