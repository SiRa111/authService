# 🔐 AuthService

A standalone, production-minded **Authentication and Authorization Service** built with Spring Boot and PostgreSQL. Designed to be reusable across multiple applications — any app can plug into this service for identity and access management.

---

## 🛠 Tech Stack

- **Java** + **Spring Boot**
- **Spring Security**
- **Spring Data JPA** + **Hibernate**
- **PostgreSQL**
- **Lombok**
- **BCrypt** (password hashing)

---

## ✅ What's Been Implemented

- **User Registration** — new users can sign up with email, username and password
- **User Login** — authenticate with email and password
- **Password Hashing** — all passwords are hashed with BCrypt before being stored, plain text passwords never touch the database
- **Duplicate Email Check** — prevents multiple accounts with the same email
- **User Model** — persisted to PostgreSQL with fields for security features like failed attempts and account locking

---

## 🚧 Coming Soon

- **JWT Access & Refresh Tokens** — stateless authentication with short-lived access tokens and rotating refresh tokens
- **Role-Based Access Control (RBAC)** — fine-grained permissions with roles like `USER`, `ADMIN`, `SUPER_ADMIN`
- **Account Lockout** — automatic account locking after 5 failed login attempts
- **Email Verification** — users must verify their email before accessing the system
- **Rate Limiting** — protect endpoints from brute force attacks
- **Two-Factor Authentication (2FA)** — TOTP-based 2FA compatible with Google Authenticator
- **OAuth2 / Social Login** — login with Google and GitHub
- **Audit Logging** — track all auth events (login, logout, password changes)
- **Docker** — containerized deployment
- **AWS Deployment** — hosted on the cloud
- **CI/CD Pipeline** — automated testing and deployment with GitHub Actions

---

## 🏗 Architecture

This service follows a clean layered architecture:

```
Client (Postman / Frontend / Mobile)
           ↓
    Controller Layer        ← handles HTTP requests
           ↓
    Spring Security         ← authentication & authorization
           ↓
    Service Layer           ← business logic
           ↓
    Repository Layer        ← database abstraction (JPA)
           ↓
      PostgreSQL
```

---

## 🚀 Running Locally

### Prerequisites
- Java 17+
- PostgreSQL running on `localhost:5432`
- Maven

### Setup

1. Clone the repo
```bash
git clone https://github.com/SiRa111/authService.git
cd authService
```

2. Create a PostgreSQL database
```bash
psql -U postgres
CREATE DATABASE authservice;
```

3. Create `src/main/resources/application.properties` (not included for security reasons)
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/authservice
spring.datasource.username=postgres
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

4. Run the app
```bash
./mvnw spring-boot:run
```

---

## 📡 API Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| POST | `/api/auth/register` | Register a new user | No |
| POST | `/api/auth/login` | Login and get tokens | No |
| POST | `/api/auth/refresh` | Refresh access token | No |
| POST | `/api/auth/logout` | Logout user | Yes |
| GET | `/api/users/me` | Get current user profile | Yes |
| PUT | `/api/users/me` | Update profile | Yes |
| POST | `/api/users/change-password` | Change password | Yes |

> More endpoints coming as features are implemented

---

## 🔒 Security Notes

- Passwords are never stored in plain text — BCrypt only
- JWT tokens are stateless and short-lived (15 mins)
- Refresh tokens are stored in the database and rotated on every use
- `application.properties` is excluded from version control

---

*Built by [@SiRa111](https://github.com/SiRa111)*
