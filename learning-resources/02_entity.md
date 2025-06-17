
## **1. What is an Entity in Spring?**

An **Entity** represents a table in your database. It’s a **Java class** annotated with `@Entity`, and each instance of this class represents a row.

### Example:

```java
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Getters and setters
}
```

---

## **2. Key JPA Annotations for Entities**

| Annotation        | Description                                                               |
| ----------------- | ------------------------------------------------------------------------- |
| `@Entity`         | Marks the class as a JPA entity.                                          |
| `@Id`             | Marks the primary key.                                                    |
| `@GeneratedValue` | Specifies how the primary key should be generated (e.g., AUTO, IDENTITY). |
| `@Column`         | Optional, used to customize column details.                               |
| `@Table`          | Customize the table name or schema.                                       |
| `@Transient`      | Field will not be persisted to the database.                              |
| `@Lob`            | For large objects like `TEXT`, `BLOB`.                                    |

---

## **3. Relationships Between Entities**

Spring JPA supports standard **relational mappings**:

### **One-to-One**

```java
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Profile profile;
}
```

* Bidirectional: Add `@OneToOne(mappedBy = "user")` on the other side.
* Use `@JoinColumn` to customize the foreign key column.

### **One-to-Many**

```java
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;
}
```

```java
@Entity
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
```

* Always define the `mappedBy` on the **inverse** side (the one that doesn't own the foreign key).

### **Many-to-Many**

```java
@Entity
public class Student {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany
    @JoinTable(
        name = "student_course",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses;
}
```

```java
@Entity
public class Course {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students;
}
```

### **Unidirectional vs Bidirectional**

* **Unidirectional**: Only one side knows about the relationship.
* **Bidirectional**: Both entities reference each other.
* Prefer bidirectional when you need to traverse from either side.

---

## **4. Fetch Types**

* `FetchType.EAGER`: Data is loaded **immediately** with the entity.
* `FetchType.LAZY`: Data is loaded **on access** (recommended for collections to avoid performance issues).

```java
@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
private List<Order> orders;
```

---

## **5. Cascade Types**

Cascade operations from one entity to its related entities:

```java
@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
private List<Order> orders;
```

Types:

* `ALL`, `PERSIST`, `MERGE`, `REMOVE`, `REFRESH`, `DETACH`

---

## **6. Orphan Removal**

If a child is removed from the relationship, delete it from the database:

```java
@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
private List<Order> orders;
```

---

## **7. Inheritance in Entities**

Use inheritance strategies for entity class hierarchies:

```java
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Animal {}

@Entity
public class Dog extends Animal {}
```

Other strategies:

* `SINGLE_TABLE` (all in one table)
* `TABLE_PER_CLASS`

---

## **8. Best Practices**

* Use `@Entity` classes only for persistence. Keep them clean of business logic.
* Prefer `DTOs` for data transfer outside of the DB layer.
* Use `LAZY` fetch type for collections.
* Set `mappedBy` correctly in bidirectional relationships.
* Don't use collections in equals/hashCode (`@OneToMany`, etc.) – it leads to performance issues.
* Always use proper cascading and orphan removal only when it makes sense.

