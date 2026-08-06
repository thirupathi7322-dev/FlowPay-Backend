# FlowPay 💸

> **A Secure Crypto Payment Gateway API built with Java & Spring Boot**

FlowPay is a production-oriented cryptocurrency payment gateway that enables merchants to securely accept crypto payments, generate invoices, monitor blockchain transactions, manage recurring payments, track budgets, and gain AI-powered financial insights through an analytics dashboard.

---

# 🚀 Project Progress

**Current Status:** **Phase 14 / 16 Completed (88%)**

| Phase                                     | Status          |
| ----------------------------------------- | --------------- |
| Phase 1 – Project Setup                   | ✅ Completed     |
| Phase 2 – Authentication & Security       | ✅ Completed     |
| Phase 3 – User & Merchant Management      | ✅ Completed     |
| Phase 4 – Wallet & Invoice Management     | ✅ Completed     |
| Phase 5 – Payment Processing              | ✅ Completed     |
| Phase 6 – Blockchain Transaction Tracking | ✅ Completed     |
| Phase 7 – Settlement Engine               | ✅ Completed     |
| Phase 8 – Dashboard & Analytics           | ✅ Completed     |
| Phase 9 – Reports & Export                | ✅ Completed     |
| Phase 10 – Notification Service           | ✅ Completed     |
| Phase 11 – Receipt Management             | ✅ Completed     |
| Phase 12 – Recurring Expense Management   | ✅ Completed     |
| Phase 13 – Budget Management              | ✅ Completed     |
| **Phase 14 – Smart Insights & Analytics** | ✅ **Completed** |
| Phase 15 – Admin Panel & Monitoring       | ⏳ Upcoming      |
| Phase 16 – Production Deployment          | ⏳ Upcoming      |

---

# ✨ Features

## 🔐 Authentication

* JWT Authentication
* Spring Security
* Role-Based Authorization
* Secure Password Encryption

## 👤 User Management

* User Registration
* Login
* Merchant Profile
* Account Management

## 💰 Wallet & Payments

* Crypto Invoice Generation
* Payment Tracking
* Payment Status Updates
* Wallet Balance
* Settlement Processing

## ⛓ Blockchain Integration

* Transaction Monitoring
* Confirmation Tracking
* Transaction History

## 📊 Dashboard

* Revenue Dashboard
* Expense Dashboard
* Payment Analytics
* Financial Summary

## 📄 Reports

* Monthly Reports
* Export Reports
* Payment History

## 🔔 Notifications

* Payment Alerts
* Email Notifications
* Transaction Updates

## 🧾 Receipt Management

* Generate Digital Receipts
* Download Receipts
* Payment Records

## 🔁 Recurring Expenses

* Monthly Expense Automation
* Scheduled Payments
* Expense Tracking

## 💼 Budget Management

* Budget Creation
* Spending Limits
* Budget Tracking
* Budget Utilization

## 🧠 Smart Insights (Phase 14)

* Personalized Spending Recommendations
* Monthly Spending Trends
* Category-wise Spending Analysis
* Financial Health Insights
* Intelligent Analytics APIs

---

# 🛠 Tech Stack

## Backend

* Java 21
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate
* Maven

## Database

* PostgreSQL

## Authentication

* JWT
* BCrypt Password Encoder

## API Testing

* Postman

## Version Control

* Git
* GitHub

---

# 📂 Project Structure

```text
FlowPay
│
├── authentication
├── users
├── wallets
├── invoices
├── payments
├── blockchain
├── settlements
├── dashboard
├── reports
├── notifications
├── receipts
├── recurring
├── budgets
├── insights
├── config
├── security
├── repository
├── service
├── controller
└── dto
```

---

# 📈 Analytics APIs

## Smart Insights

```
GET /api/insights/recommendations
```

Returns personalized financial recommendations.

---

## Monthly Spending Trend

```
GET /api/insights/monthly-trend?userId={id}
```

Returns monthly spending statistics.

---

## Category Breakdown

```
GET /api/insights/category-breakdown?userId={id}
```

Returns category-wise expense distribution.

---

# 🏗 Architecture

```text
                 Client Application
                        │
                        ▼
                  Spring Boot API
                        │
        ┌───────────────┼────────────────┐
        ▼               ▼                ▼
 Authentication     Payment Engine    Analytics
        │               │                │
        ▼               ▼                ▼
     PostgreSQL   Blockchain Layer   Smart Insights
```

---

# 🎯 Upcoming (Phase 15)

* Admin Dashboard
* User Management Console
* System Monitoring
* API Health Monitoring
* Logs & Audit Dashboard
* Performance Metrics
* Role-based Admin Controls

---

# 🚀 Final Phase (Phase 16)

* Docker Deployment
* CI/CD Pipeline
* Cloud Deployment
* Production Configuration
* HTTPS & SSL
* Monitoring & Logging
* Performance Optimization
* Final Documentation

---

# 📌 Current Statistics

* ✅ 14 Development Phases Completed
* ✅ Secure Authentication
* ✅ Wallet & Invoice Management
* ✅ Blockchain Payment Tracking
* ✅ Dashboard & Reports
* ✅ Budget Management
* ✅ Smart Financial Insights
* ⏳ 2 Phases Remaining

---

# 👨‍💻 Author

**THIRUPATHI M**

**Project:** FlowPay – Crypto Payment Gateway API

Built using **Java**, **Spring Boot**, **PostgreSQL**, **Spring Security**, **JWT**, **JPA/Hibernate**, and modern backend development practices.
