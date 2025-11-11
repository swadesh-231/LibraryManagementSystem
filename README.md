# Library Management System

A RESTful API for managing library operations including book management, user authentication, and book issue/return tracking.

## Features

- **User Authentication & Authorization**
  - JWT-based authentication
  - Role-based access control (ADMIN, USER)
  - Secure password encryption using BCrypt

- **Book Management**
  - CRUD operations for books
  - Track book availability and quantity
  - Admin-only book modifications

- **Issue & Return System**
  - Issue books to authenticated users
  - Track issue dates and due dates
  - Return books with automatic availability updates

## Technology Stack

- **Framework**: Spring Boot 3.x
- **Security**: Spring Security with JWT
- **Database**: MySQL
- **ORM**: Spring Data JPA / Hibernate
- **Build Tool**: Maven
- **Libraries**:
  - ModelMapper (DTO mapping)
  - Lombok (boilerplate reduction)
  - JJWT (JWT token handling)

## Prerequisites

- Java 17 or higher
- MySQL 8.0 or higher
- Maven 3.6 or higher

## Database Setup

1. Create a MySQL database:
```sql
CREATE DATABASE library;
```

2. Update database credentials in `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/library
spring.datasource.username=your_username
spring.datasource.password=your_password
```

## Configuration

### JWT Configuration
Update the JWT secret key in `application.properties`:
```properties
jwt.secret=your256bitlongsecretkeyhere1234567890abcd
jwt.expiration=3600000
```
**Important**: Replace with a secure secret key in production.

## Running the Application

1. Clone the repository
2. Navigate to project directory
3. Run the application:
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8082`

## API Endpoints

### Authentication Endpoints (`/api/v1/auth`)

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| POST | `/register-normal-user` | Register a new user | Public |
| POST | `/login` | Login and get JWT token | Public |

**Register Request Body:**
```json
{
  "username": "johndoe",
  "email": "john@example.com",
  "password": "password123"
}
```

**Login Request Body:**
```json
{
  "username": "johndoe",
  "password": "password123"
}
```

**Login Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIs...",
  "username": "johndoe",
  "roles": ["ROLE_USER"]
}
```

### Admin Endpoints (`/api/v1/admin`)

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| POST | `/register-admin-user` | Register admin user | Admin Only |

### Book Endpoints (`/api/v1/books`)

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| GET | `/` | Get all books | Authenticated |
| GET | `/{id}` | Get book by ID | Authenticated |
| POST | `/` | Create new book | Admin Only |
| PUT | `/{id}` | Update book | Admin Only |
| DELETE | `/{id}` | Delete book | Admin Only |

**Book Request/Response Body:**
```json
{
  "title": "Clean Code",
  "author": "Robert C. Martin",
  "isbn": "978-0132350884",
  "quantity": 5
}
```

### Issue Record Endpoints (`/issueRecords`)

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| POST | `/issue/{bookId}` | Issue a book | Authenticated |
| POST | `/return/{issueRecordId}` | Return a book | Authenticated |

## Authentication

All protected endpoints require a JWT token in the Authorization header:
```
Authorization: Bearer <your_jwt_token>
```

## Project Structure

```
src/main/java/com/project/librarymanagement/
├── config/              # Configuration classes
├── controller/          # REST controllers
├── dto/                 # Data Transfer Objects
├── exception/           # Custom exceptions and handlers
├── model/              # JPA entities
├── repository/         # Spring Data repositories
├── security/           # Security configuration & JWT
└── service/            # Business logic layer
    └── impl/           # Service implementations
```

## Security Features

- Passwords are encrypted using BCrypt
- JWT tokens expire after 1 hour (configurable)
- Role-based access control using Spring Security
- Stateless session management

## Error Handling

The application includes global exception handling for:
- Resource not found exceptions
- Validation errors
- Authentication failures
- Book availability issues

## Business Logic

- Books are issued for 10 days by default
- Book quantity is automatically decremented when issued
- Books become unavailable when quantity reaches 0
- Quantity is restored when books are returned
- Users can only return books they haven't already returned
