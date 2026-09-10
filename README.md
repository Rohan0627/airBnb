# 🏠 AirBnb — Hotel & Room Booking Backend

A Spring Boot REST API for an Airbnb-style hotel and room booking platform.

The application supports user authentication, hotel/room management, inventory management, dynamic pricing, booking
workflows, Stripe Checkout payments, webhook-based payment confirmation, cancellations/refunds, guest management, and
hotel reporting.

## ✨ Features

### Authentication & Authorization

- User registration and login
- JWT-based stateless authentication
- Access and refresh tokens
- BCrypt password hashing
- Role-based access for hotel managers
- Protected user and admin endpoints

### Hotel & Room Management

- Create, update, activate/deactivate and delete hotels
- Create, update and delete rooms
- Browse hotel information
- Search hotels
- Hotel owner-specific booking access

### Inventory Management

- Room inventory by date
- Availability checks
- Reservation locking
- Inventory confirmation after successful payment
- Inventory release during cancellation

### Booking

- Initialize a booking
- Reserve inventory
- Add guests to a booking
- View booking status
- View personal bookings
- Cancel confirmed bookings

### Dynamic Pricing

The project uses a strategy/decorator-style pricing pipeline with:

- Base pricing
- Surge pricing
- Occupancy-based pricing
- Urgency pricing
- Holiday pricing

### Stripe Payments

- Stripe Checkout Session creation
- Booking ID stored as Stripe metadata
- Payment-session ID stored against the booking
- Stripe webhook endpoint
- `checkout.session.completed` handling
- Automatic booking confirmation after successful payment
- Refund handling during cancellation

### Reporting

Hotel managers can generate reports containing:

- Confirmed booking count
- Total confirmed revenue
- Average confirmed booking revenue

## 🛠️ Tech Stack

| Technology                  | Usage                          |
|-----------------------------|--------------------------------|
| Java 25                     | Backend language               |
| Spring Boot 4.0.3           | Application framework          |
| Spring MVC                  | REST APIs                      |
| Spring Security             | Authentication & authorization |
| JWT / JJWT                  | Token-based authentication     |
| Spring Data JPA / Hibernate | Persistence                    |
| PostgreSQL                  | Relational database            |
| Stripe Java SDK             | Payments                       |
| ModelMapper                 | DTO/entity mapping             |
| Lombok                      | Boilerplate reduction          |
| SpringDoc OpenAPI           | API documentation              |
| Maven                       | Build & dependency management  |

## 🏗️ Architecture

The backend follows a layered architecture:

```text
Client
  │
  ▼
Controller Layer
  │
  ▼
Service Layer
  │
  ├── Pricing Strategy
  ├── Booking Workflow
  ├── Authentication
  └── Stripe Checkout / Webhooks
  │
  ▼
Repository Layer
  │
  ▼
PostgreSQL
```

### Main packages

```text
src/main/java/com/rohan/airBnb/
├── Advices/       # Global API response & exception handling
├── Config/        # Application, CORS and Stripe configuration
├── controller/    # REST controllers
├── dto/           # Request/response DTOs
├── Entity/        # JPA entities and enums
├── Exceptions/    # Custom exceptions
├── Repository/    # Spring Data repositories
├── security/      # JWT authentication and Spring Security
├── Service/       # Business logic
├── strategy/      # Dynamic pricing strategies
└── utility/       # Shared application utilities
```

## 🔄 Booking & Payment Flow

```text
1. User searches hotels
        ↓
2. User selects hotel + room + dates
        ↓
3. Booking is initialized
        ↓
4. Available inventory is locked/reserved
        ↓
5. Dynamic price is calculated
        ↓
6. Booking enters RESERVED state
        ↓
7. User starts payment
        ↓
8. Stripe Checkout Session is created
        ↓
9. User completes payment on Stripe
        ↓
10. Stripe sends webhook
        ↓
11. checkout.session.completed is processed
        ↓
12. Booking becomes CONFIRMED
        ↓
13. Reserved inventory becomes confirmed
```

### Payment states

The booking workflow includes states such as:

```text
RESERVED
   ↓
GUESTS_ADDED
   ↓
PAYMENTS_PENDING
   ↓
CONFIRMED
   ↓
CANCELLED
```

## 🔐 Authentication Flow

```text
Sign Up
  ↓
Login
  ↓
JWT Access Token + Refresh Token
  ↓
Authorization Header
  ↓
JwtAuthFilter
  ↓
Spring Security Context
  ↓
Protected Controller
```

Send the access token as:

```http
Authorization: Bearer <access-token>
```

## 📌 API Endpoints

Base URL:

```text
http://localhost:8080/api/v1
```

### Authentication

| Method | Endpoint        | Purpose       |
|--------|-----------------|---------------|
| POST   | `/auth/signUp`  | Register user |
| POST   | `/auth/login`   | Login         |
| POST   | `/auth/refresh` | Refresh token |

### Hotel browsing

| Method | Endpoint                 | Purpose               |
|--------|--------------------------|-----------------------|
| GET    | `/hotels/search`         | Search hotels         |
| GET    | `/hotels/{hotelId}/info` | Get hotel information |

### Booking

| Method | Endpoint                         | Purpose              |
|--------|----------------------------------|----------------------|
| POST   | `/booking/init`                  | Initialize booking   |
| POST   | `/booking/{bookingId}/addGuests` | Add guests           |
| POST   | `/booking/{bookingId}/payments`  | Start Stripe payment |
| POST   | `/booking/{bookingId}/cancel`    | Cancel booking       |
| GET    | `/booking/{bookingId}/status`    | Get booking status   |

### Users

| Method | Endpoint                  | Purpose             |
|--------|---------------------------|---------------------|
| PATCH  | `/users/profile`          | Update profile      |
| GET    | `/users/profile`          | Get profile         |
| GET    | `/users/myBookings`       | Get user's bookings |
| GET    | `/users/guests`           | List guests         |
| POST   | `/users/guests`           | Add guest           |
| PUT    | `/users/guests/{guestId}` | Update guest        |
| DELETE | `/users/guests/{guestId}` | Delete guest        |

### Hotel administration

| Method | Endpoint                           | Purpose             |
|--------|------------------------------------|---------------------|
| POST   | `/admin/hotels`                    | Create hotel        |
| GET    | `/admin/hotels`                    | List managed hotels |
| GET    | `/admin/hotels/{hotelId}`          | Get hotel           |
| PUT    | `/admin/hotels/{hotelId}`          | Update hotel        |
| DELETE | `/admin/hotels/{hotelId}`          | Delete hotel        |
| PATCH  | `/admin/hotels/active/{hotelId}`   | Activate hotel      |
| PATCH  | `/admin/hotels/deactive/{hotelId}` | Deactivate hotel    |
| GET    | `/admin/hotels/{hotelId}/bookings` | Hotel bookings      |
| GET    | `/admin/hotels/{hotelId}/reports`  | Hotel report        |

### Rooms & inventory

| Method | Endpoint                                 | Purpose          |
|--------|------------------------------------------|------------------|
| POST   | `/admin/hotels/{hotelId}/rooms`          | Create room      |
| GET    | `/admin/hotels/{hotelId}/rooms`          | List rooms       |
| GET    | `/admin/hotels/{hotelId}/rooms/{Roomid}` | Get room         |
| PUT    | `/admin/hotels/{hotelId}/rooms/{Roomid}` | Update room      |
| DELETE | `/admin/hotels/{hotelId}/rooms/{roomId}` | Delete room      |
| GET    | `/admin/inventory/rooms/{roomId}`        | Get inventory    |
| PATCH  | `/admin/inventory/rooms/{roomId}`        | Update inventory |

### Stripe webhook

| Method | Endpoint           | Purpose                       |
|--------|--------------------|-------------------------------|
| POST   | `/webhook/payment` | Receive Stripe payment events |

## 💳 Stripe Webhook Setup

For local development, Stripe needs a public/reachable webhook endpoint.

The application endpoint is:

```text
POST http://localhost:8080/api/v1/webhook/payment
```

When using Stripe CLI, forward events to:

```bash
stripe listen --forward-to localhost:8080/api/v1/webhook/payment
```

Use the webhook signing secret printed by Stripe CLI as:

```text
STRIPE_WEBHOOK_SECRET=whsec_...
```

Do **not** commit the signing secret to GitHub.

## 🚀 Getting Started

### 1. Prerequisites

Install:

- Java 25
- Maven (or use the included Maven wrapper)
- PostgreSQL
- Stripe account / Stripe CLI for payment testing

### 2. Create the database

Create a PostgreSQL database:

```sql
CREATE
DATABASE "airBnb";
```

### 3. Configure environment variables

Copy the example configuration:

```bash
cp .env.example .env
```

Then set your actual values.

If your IDE does not automatically load `.env`, configure these variables in your run configuration:

```text
DB_URL
DB_USERNAME
DB_PASSWORD
JWT_SECRET_KEY
FRONTEND_URL
STRIPE_SECRET_KEY
STRIPE_WEBHOOK_SECRET
```

### 4. Run the application

Linux/macOS:

```bash
./mvnw spring-boot:run
```

Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

Or run:

```text
AirBnbApplication.java
```

from IntelliJ IDEA.

The API will be available at:

```text
http://localhost:8080/api/v1
```

## 📚 API Documentation

SpringDoc/OpenAPI is included in the project.

After starting the application, Swagger UI is normally available at:

```text
http://localhost:8080/api/v1/swagger-ui/index.html
```

OpenAPI JSON:

```text
http://localhost:8080/api/v1/v3/api-docs
```

## 🧪 Testing

Run the test suite with:

```bash
./mvnw test
```

Windows:

```powershell
.\mvnw.cmd test
```

## 🗄️ Database

The application uses PostgreSQL with Spring Data JPA.

The main domain entities include:

```text
User
 ├── Guest
 ├── Hotel
 │    ├── Room
 │    │    └── Inventory
 │    └── Bookings
 └── Bookings

Booking
 ├── User
 ├── Hotel
 ├── Room
 ├── Guests
 └── Payment information

```



## 📈 Possible Future Improvements

- Property image upload
- Reviews and ratings
- Wishlist/favorites
- Advanced hotel filtering
- Email/SMS booking notifications
- Redis caching
- Kafka/event-driven booking events
- Docker + Docker Compose
- CI/CD with GitHub Actions
- Flyway/Liquibase migrations
- Testcontainers integration tests
- Production observability with metrics and tracing

```

## 👨‍💻 Author

**Rohan Singh**

Built as a backend-focused Airbnb-style hotel booking project using Spring Boot, PostgreSQL, JWT authentication and
Stripe payments.

