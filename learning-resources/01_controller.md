
## **1. What Is a Spring Controller?**

In Spring (especially **Spring MVC**), a **Controller** is a class responsible for handling incoming HTTP requests and returning responses. Controllers are annotated with `@Controller` or `@RestController`.

* `@Controller`: Used for traditional MVC controllers. Typically returns a **view name**.
* `@RestController`: Specialized version of `@Controller`. It returns data directly (usually JSON or XML), not views.

---

## **2. Basic Anatomy of a Controller**

### Example:

```java
@RestController
@RequestMapping("/api")
public class MyController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from Spring!";
    }
}
```

### Explanation:

* `@RestController`: Combines `@Controller` and `@ResponseBody`. Used for REST APIs.
* `@RequestMapping("/api")`: Base URI for all handler methods in this controller.
* `@GetMapping("/hello")`: Handles GET requests to `/api/hello`.

---

## **3. Types of Mappings**

* `@GetMapping`: for GET requests
* `@PostMapping`: for POST requests
* `@PutMapping`: for PUT requests
* `@DeleteMapping`: for DELETE requests
* `@PatchMapping`: for PATCH requests

All these are shortcuts for:

```java
@RequestMapping(method = RequestMethod.GET)
```

---

## **4. Handling Parameters**

### Query Parameters

```java
@GetMapping("/greet")
public String greet(@RequestParam String name) {
    return "Hello, " + name;
}
```

* URL: `/api/greet?name=John`

### Path Variables

```java
@GetMapping("/user/{id}")
public String getUser(@PathVariable int id) {
    return "User ID: " + id;
}
```

* URL: `/api/user/42`

---

## **5. Handling Request Bodies**

For POST/PUT requests, you often need to read JSON data.

```java
@PostMapping("/users")
public String createUser(@RequestBody User user) {
    return "Created user: " + user.getName();
}
```

Here, Spring automatically deserializes the JSON payload into a `User` object.

---

## **6. Response Handling**

### Return JSON

```java
@GetMapping("/info")
public User getInfo() {
    return new User("Alice", 30);
}
```

With `@RestController`, Spring will serialize the `User` object to JSON.

### Using `ResponseEntity`

You can also return detailed HTTP responses:

```java
@GetMapping("/status")
public ResponseEntity<String> status() {
    return ResponseEntity.status(HttpStatus.CREATED).body("Resource created");
}
```

---

## **7. Validation and Binding**

Spring supports bean validation via `@Valid`:

```java
@PostMapping("/register")
public ResponseEntity<String> register(@Valid @RequestBody User user, BindingResult result) {
    if (result.hasErrors()) {
        return ResponseEntity.badRequest().body("Validation errors occurred.");
    }
    return ResponseEntity.ok("User registered.");
}
```

---

## **8. Exception Handling**

### Local to Controller:

```java
@ExceptionHandler(UserNotFoundException.class)
public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
}
```

### Global (recommended):

Use `@ControllerAdvice` for global exception handling.

---

## **9. Best Practices**

* Use `@RestController` for REST APIs.
* Always validate input using `@Valid` and a validation framework (like Hibernate Validator).
* Avoid returning raw types (like `Map` or `List`) directly unless you wrap them in a model or DTO.
* Use `ResponseEntity` for better control over responses.
* Separate logic into **service** and **repository** layers to keep controllers thin.

---