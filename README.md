# FlowPay Backend

A Splitwise-inspired expense sharing backend built with Spring Boot.

## Tech Stack

- Java 21
- Spring Boot 3
- Spring Security
- Spring Data JPA (Hibernate)
- MySQL
- JWT Authentication
- Maven

---

## Features

### ✅ Phase 1 - User Management
- User Registration
- Get All Users
- BCrypt Password Encryption

### ✅ Phase 2 - Authentication
- Login API
- BCrypt Password Verification
- Custom Authentication Service

### ✅ Phase 3 - JWT Security
- JWT Token Generation
- JWT Token Validation
- JWT Authentication Filter
- Stateless Security
- Protected APIs

### ✅ Phase 4 - Expense Group Management
- Create Expense Group
- Get All Expense Groups
- Add Member to Group
- View Group Members
- Remove Member from Group

---

## Project Structure

```
src
├── config
├── controller
├── dto
├── entity
├── exception
├── repository
├── service
```

---

## REST APIs

### Authentication

```
POST /api/users/register
POST /api/auth/login
```

### Users

```
GET /api/users
```

### Expense Groups

```
POST   /api/groups
GET    /api/groups
POST   /api/groups/{groupId}/members
GET    /api/groups/{groupId}/members
DELETE /api/groups/{groupId}/members/{userId}
```

---

## Security

- BCrypt Password Hashing
- JWT Authentication
- Stateless Session Management
- Spring Security Filter Chain
- Protected REST APIs

---

## Database

- MySQL
- JPA/Hibernate
- Entity Relationships

```
User
   ▲
   │
GroupMember
   │
   ▼
ExpenseGroup
```

---

## Current Progress

| Phase | Status |
|--------|--------|
| Phase 1 | ✅ Completed |
| Phase 2 | ✅ Completed |
| Phase 3 | ✅ Completed |
| Phase 4 | ✅ Completed |
| Phase 5 | 🚧 In Progress |

---

## Upcoming Features

- Expense Management
- Expense Splitting
- Balance Calculation
- Settlement APIs
- Dashboard APIs
- Docker Support
- Deployment