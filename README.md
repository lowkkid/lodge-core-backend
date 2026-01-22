[![Codacy Badge](https://app.codacy.com/project/badge/Grade/fdbf41e0d2054c18ae82ada892eb3fa4)](https://app.codacy.com/gh/lowkkid/lodge-core-backend/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=lowkkid_lodge-core-backend&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=lowkkid_lodge-core-backend)

# Lodge Core Backend 🏔️

REST API for a boutique hotel management system. Handles bookings, cabin inventory, guest records, and staff operations for a small lodge with 8 wooden cabins.

**Stack:** Java 17, Spring Boot 3.4.2, PostgreSQL, Spring Security (JWT), MinIO

🔗 [Live Demo](https://lodge-core.lowkkid.dev/login) · [API Docs (Swagger)](https://lodge-core-backend.lowkkid.dev/swagger-ui/index.html#/) · [Frontend Repo](https://github.com/lowkkid/lodge-core)

## 📋 What It Does

Internal hotel management API that enables staff to:
- Manage cabin inventory with image uploads
- Process guest bookings and check-ins/check-outs
- Track sales analytics and daily activity
- Handle user accounts with role-based access (Admin/Employee)

---

## 🔧 Techniques Worth Noting

### Authentication: JWT + Refresh Token Flow

The auth system implements a stateless JWT strategy with secure refresh token rotation.

**Access Tokens (JWT):**
- Signed with [HMAC-SHA256](https://datatracker.ietf.org/doc/html/rfc7518#section-3.2) via [JJWT library](https://github.com/jwtk/jjwt)
- Carries claims: userId (subject), username, role, avatar
- Configurable expiration via environment variables

**Refresh Tokens:**
- Dual expiration model: rolling window (7 days default) + absolute hard limit (30 days)
- 72-byte cryptographically random tokens generated with `SecureRandom`
- Stored as [BCrypt hashes](https://en.wikipedia.org/wiki/Bcrypt) in the database (never plaintext)
- Delivered via HTTP-only, Secure, SameSite=None, [Partitioned](https://developer.mozilla.org/en-US/docs/Web/Privacy/Partitioned_cookies) cookies
- Old tokens deleted on refresh (rotation prevents replay attacks)

The dual expiration prevents indefinite session extension—users must re-authenticate after the absolute deadline regardless of activity.

---

### MinIO Object Storage Integration

Cabin images are stored in [MinIO](https://min.io/) (S3-compatible object storage) with presigned URLs.

**Key patterns:**
- 48-hour presigned download URLs (not permanent links)
- Daily [scheduled job](https://docs.spring.io/spring-framework/reference/integration/scheduling.html) refreshes all URLs at midnight
- Parallel processing with `CompletableFuture` for concurrent URL generation
- [Spring Retry](https://github.com/spring-projects/spring-retry) with `@Retryable` for transient failures
- Content-type detection by file extension (prevents MIME spoofing)

```java
// Parallel URL refresh with CompletableFuture
var tasks = allCabins.stream()
    .map(cabin -> CompletableFuture.runAsync(() -> refreshCabinImage(cabin)))
    .toList();
CompletableFuture.allOf(tasks.toArray(new CompletableFuture[0])).join();
```

---

### JPQL Constructor Projections

Instead of fetching full entities, queries use [constructor expressions](https://docs.jboss.org/hibernate/orm/6.4/userguide/html_single/Hibernate_User_Guide.html#hql-select-clause) to load only needed fields:

```java
@Query("""
    SELECT new com.github.lowkkid.lodgecore.booking.model.BookingSummary(
        b.id, b.createdAt, b.startDate, b.endDate, b.numNights, ...
    )
    FROM Booking b JOIN b.cabin c JOIN b.guest g
    WHERE (:status IS NULL OR b.status = :status)
    """)
Page<BookingSummary> findAllWithCabinsAndGuests(BookingStatus status, Pageable pageable);
```

This reduces memory footprint and bandwidth for list endpoints.

---

### N+1 Prevention with JOIN FETCH

Relationships use `FetchType.LAZY` by default, but common access paths use [JOIN FETCH](https://docs.jboss.org/hibernate/orm/6.4/userguide/html_single/Hibernate_User_Guide.html#hql-explicit-join) to load related entities in a single query:

```java
@Query("SELECT b FROM Booking b JOIN FETCH b.cabin JOIN FETCH b.guest g JOIN FETCH g.country WHERE b.id = :id")
Optional<Booking> findById(Long id);
```

---

## 🛠️ Technologies

| Technology                                                         | Purpose |
|--------------------------------------------------------------------|---------|
| **[Feature-Based Structure](#project-structure)**                  | Code organized by domain (booking, cabin, guest) rather than layer. Makes navigating the codebase more intuitive. |
| **[MapStruct](https://mapstruct.org/)**                            | Compile-time DTO mapping. Generates type-safe mapper implementations at build time, zero runtime reflection. |
| **[Liquibase](https://www.liquibase.com/)**                        | Database schema migrations in YAML format. Version-controlled, repeatable deployments. |
| **[JJWT](https://github.com/jwtk/jjwt)**                           | JWT creation and validation. Fluent API, supports all standard algorithms. |
| **[MinIO](https://min.io/)**                                       | S3-compatible object storage. Self-hosted, works with standard AWS SDK patterns. |
| **[Spring Retry](https://github.com/spring-projects/spring-retry)** | Declarative retry logic with `@Retryable`. Handles transient MinIO failures. |
| **[SpringDoc OpenAPI](https://springdoc.org/)**                    | Swagger UI generation from code. Auto-documents endpoints, supports JWT auth in UI. |

---

## 📁 Project Structure

```
com.github.lowkkid.lodgecore/
├── booking/      # Reservations, check-in/out, sales analytics
├── cabin/        # Cabin CRUD, image management
├── guest/        # Guest profiles, countries
├── user/         # Staff accounts
├── security/     # JWT auth, refresh tokens
├── setting/      # Application settings
├── minio/        # Object storage wrapper
└── common/       # Exceptions, base entities, config
```

Each module follows the pattern:
```
{module}/
├── controller/             # REST endpoints
│   ├── {Controller}.java   # Interface
│   └── impl/               # Implementation
├── domain/
│   ├── entity/             # JPA entities
│   └── repository/         # Spring Data repositories
├── service/                # Buisness logic
│   ├── {Service}.java      # Interface
│   └── impl/               # Implementation
├── mapper/                 # MapStruct mappers
└── model/                  # Request/Response DTOs
```