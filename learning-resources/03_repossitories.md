## **1. What is a Spring Data Repository?**

A **Repository** in Spring Data JPA is an interface that abstracts the data access layer. You define an interface, and Spring Data auto-generates the implementation at runtime.

---

## **2. Core Repository Interfaces**

You usually extend one of the following:

* `CrudRepository<T, ID>` – Basic CRUD operations.
* `JpaRepository<T, ID>` – CRUD + pagination + sorting + batch operations.
* `PagingAndSortingRepository<T, ID>` – Adds pagination and sorting only.

**Best Practice:** Use `JpaRepository` as your base interface.

### Example:

```java
public interface UserRepository extends JpaRepository<User, Long> {
}
```

---

## **3. Custom Query Methods Using Method Naming**

Spring Data JPA allows you to define query methods by simply naming them appropriately.

### Examples:

```java
List<User> findByName(String name);
User findByEmail(String email);
List<User> findByAgeGreaterThan(int age);
List<User> findByNameContaining(String keyword);
List<User> findByCreatedAtBetween(LocalDate start, LocalDate end);
```

**Keywords include:**
`And`, `Or`, `Like`, `Containing`, `Between`, `Before`, `After`, `In`, `Not`, `IsNull`, `IsNotNull`, etc.

---

## **4. Custom Queries with `@Query`**

Use JPQL (Java Persistence Query Language) or native SQL.

### JPQL Example:

```java
@Query("SELECT u FROM User u WHERE u.name = :name")
List<User> searchByName(@Param("name") String name);
```

### Native SQL:

```java
@Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
User findByEmailNative(@Param("email") String email);
```

---

## **5. Modify Data with `@Modifying`**

To perform updates or deletes:

```java
@Modifying
@Query("UPDATE User u SET u.status = :status WHERE u.id = :id")
void updateUserStatus(@Param("status") String status, @Param("id") Long id);
```

* Must be used with `@Transactional`.

---

## **6. Dynamic Queries with `@Query` + SpEL**

```java
@Query("SELECT u FROM User u WHERE u.name LIKE %:#{#name}%")
List<User> findByNameSpEL(@Param("name") String name);
```

---

## **7. Pagination and Sorting**

You can add pagination and sorting via method parameters.

```java
Page<User> findByStatus(String status, Pageable pageable);
```

Use like:

```java
Page<User> page = userRepository.findByStatus("ACTIVE", PageRequest.of(0, 10, Sort.by("name")));
```

---

## **8. Specifications (Advanced Filtering)**

Use `JpaSpecificationExecutor` to build dynamic queries.

```java
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
}
```

Then define a `Specification`:

```java
Specification<User> spec = (root, query, cb) ->
    cb.equal(root.get("status"), "ACTIVE");
```

---

## **9. Projections**

### Interface-Based:

```java
public interface UserSummary {
    String getName();
    String getEmail();
}
```

Then in your repo:

```java
List<UserSummary> findByStatus(String status);
```

---

## **10. Example Full Repository**

```java
public interface UserRepository extends JpaRepository<User, Long> {

    // Derived queries
    List<User> findByNameContaining(String name);
    List<User> findByAgeGreaterThanEqual(int age);

    // JPQL
    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(@Param("email") String email);

    // Native SQL
    @Query(value = "SELECT * FROM users WHERE status = :status", nativeQuery = true)
    List<User> findByStatus(@Param("status") String status);

    // Modifying
    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.inactive = true")
    void deleteInactiveUsers();
}
```

---

## **Best Practices**

* Use **method names** for simple queries.
* Use `@Query` for **custom or optimized logic**.
* Always use `@Modifying` with **updates or deletes**.
* Combine with DTOs or projections to avoid exposing entity internals.
* Use pagination when expecting large result sets.
* Avoid native queries unless necessary – prefer JPQL.
