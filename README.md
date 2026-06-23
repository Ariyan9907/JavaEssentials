# Java Records in Java

## Overview

Java Records are a special type of class introduced to reduce boilerplate code when creating immutable data objects. They are ideal for classes whose primary purpose is to store and transfer data.

Records were introduced as a preview feature in Java 14 and became a standard feature in Java 16.

---

## Why Use Records?

Before Records, creating a simple data class required:

* Fields
* Constructor
* Getters
* `equals()`
* `hashCode()`
* `toString()`

Records automatically generate these methods, making code shorter, cleaner, and easier to maintain.

### Traditional Class

```java
class Employee {
    private final int id;
    private final String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int id() {
        return id;
    }

    public String name() {
        return name;
    }
}
```

### Record

```java
record Employee(int id, String name) {
}
```

---

# Record Syntax

```java
record Employee(int id, String name, double salary) {
}
```

The parameters inside the record declaration are called **Record Components**.

| Component | Type   |
| --------- | ------ |
| id        | int    |
| name      | String |
| salary    | double |

---

# Features of Records

## 1. Automatically Generated Constructor

```java
record Employee(int id, String name) {
}
```

Java automatically generates:

```java
public Employee(int id, String name) {
    this.id = id;
    this.name = name;
}
```

---

## 2. Automatically Generated Accessor Methods

```java
Employee e = new Employee(101, "Aryan");

System.out.println(e.id());
System.out.println(e.name());
```

Output:

```text
101
Aryan
```

Unlike traditional JavaBeans:

```java
getId()
getName()
```

Records provide:

```java
id()
name()
```

---

## 3. Automatically Generated toString()

```java
Employee e = new Employee(101, "Aryan");

System.out.println(e);
```

Output:

```text
Employee[id=101, name=Aryan]
```

---

## 4. Automatically Generated equals()

```java
Employee e1 = new Employee(1, "Aryan");
Employee e2 = new Employee(1, "Aryan");

System.out.println(e1.equals(e2));
```

Output:

```text
true
```

Records compare values rather than memory references.

---

## 5. Automatically Generated hashCode()

```java
Employee e1 = new Employee(1, "Aryan");
Employee e2 = new Employee(1, "Aryan");

System.out.println(e1.hashCode());
System.out.println(e2.hashCode());
```

Objects with identical values produce the same hash code.

---

# Immutability

All record components are automatically:

```java
private final
```

Example:

```java
record Employee(int id, String name) {
}

Employee e = new Employee(1, "Aryan");

// Compilation Error
e.id = 10;
```

Records are immutable after object creation.

---

# Compact Constructor

Used for validation before object creation.

```java
record Employee(int id, String name) {

    public Employee {

        if (id <= 0) {
            throw new IllegalArgumentException("Invalid ID");
        }

        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
    }
}
```

### Benefits

* Input validation
* Data sanitization
* Business rules enforcement

---

# Custom Constructors

Additional constructors can be added.

```java
record Employee(int id, String name) {

    public Employee(int id) {
        this(id, "Unknown");
    }
}
```

Usage:

```java
Employee e = new Employee(101);
```

Output:

```text
Employee[id=101, name=Unknown]
```

---

# Static Variables

Records can contain static variables.

```java
record Employee(int id, String name) {

    static int companyAge = 22;
}
```

Usage:

```java
System.out.println(Employee.companyAge);
```

---

# Static Methods

```java
record Employee(int id, String name) {

    static void showCompany() {
        System.out.println("ABC Technologies");
    }
}
```

Usage:

```java
Employee.showCompany();
```

---

# Instance Methods

Records can have custom methods.

```java
record Employee(int id, String name) {

    void display() {
        System.out.println(id + " " + name);
    }
}
```

Usage:

```java
Employee e = new Employee(1, "Aryan");
e.display();
```

---

# Static Blocks

```java
record Employee(int id, String name) {

    static {
        System.out.println("Record Loaded");
    }
}
```

Runs once when the class is loaded into memory.

---

# Implementing Interfaces

Records can implement interfaces.

```java
interface Person {
    void work();
}
```

```java
record Employee(int id, String name)
        implements Person {

    @Override
    public void work() {
        System.out.println("Working...");
    }
}
```

---

# Inheritance Rules

## Allowed

```java
record Employee(int id, String name)
        implements Runnable {
}
```

## Not Allowed

### Extending a Class

```java
class Person {
}

record Employee(int id)
        extends Person {
}
```

### Extending Another Record

```java
record A(int x) {
}

record B(int y)
        extends A {
}
```

### Extending a Record

```java
record Employee(int id) {
}

class Manager extends Employee {
}
```

Reason: Records are implicitly final.

---

# Reflection Support

## Checking Whether a Class is a Record

```java
System.out.println(Employee.class.isRecord());
```

Output:

```text
true
```

---

## Retrieving Record Components

```java
RecordComponent[] components =
        Employee.class.getRecordComponents();
```

```java
for (RecordComponent component : components) {

    System.out.println(
            component.getName()
            + " -> "
            + component.getType()
    );
}
```

Output:

```text
id -> int
name -> class java.lang.String
```

---

# Useful Reflection Methods

| Method                | Purpose                       |
| --------------------- | ----------------------------- |
| isRecord()            | Checks if class is a record   |
| getRecordComponents() | Returns all record components |
| getName()             | Returns component name        |
| getType()             | Returns component datatype    |

---

# Nested Records

```java
class Company {

    record Employee(
            int id,
            String name) {
    }
}
```

Usage:

```java
Company.Employee emp =
        new Company.Employee(1, "Aryan");
```

---

# Local Records

Records can be declared inside methods.

```java
public class Demo {

    public static void main(String[] args) {

        record Student(
                int id,
                String name) {
        }

        Student s =
            new Student(1, "Aryan");
    }
}
```

---

# Serialization Support

Records can implement Serializable.

```java
import java.io.Serializable;

record Employee(
        int id,
        String name)
        implements Serializable {
}
```

Useful for:

* File Storage
* Networking
* Distributed Systems

---

# Memory Representation

```java
Employee e =
        new Employee(1, "Aryan");
```

```text
Stack Memory
------------
e

Heap Memory
-----------
Employee Object
id = 1
name = Aryan
```

Records are stored in memory exactly like normal objects.

The only difference is that their fields are immutable and several methods are automatically generated.

---

# Advantages

✅ Less Boilerplate Code

✅ Immutable by Default

✅ Cleaner and Readable Code

✅ Auto-generated Methods

✅ Thread-Safe Data Models

✅ Reflection Support

✅ Perfect for DTOs

✅ Reduced Development Time

---

# Limitations

❌ Cannot Extend Classes

❌ Cannot Be Extended

❌ Components Are Final

❌ Not Suitable for Mutable Objects

❌ Not Intended for Heavy Business Logic

---

# Common Use Cases

Records are best suited for:

* Employee Information
* Student Information
* Product Details
* API Responses
* DTO (Data Transfer Object) Classes
* Configuration Objects
* Database Query Results
* Immutable Domain Models

Example:

```java
record Student(
        int id,
        String name,
        String course) {
}
```

---

# Interview Questions

### What is a Record?

A Record is a special immutable class that automatically generates constructors, accessors, `equals()`, `hashCode()`, and `toString()` methods.

### Are Record Fields Mutable?

No. Record components are implicitly `private final`.

### Can Records Extend Classes?

No.

### Can Records Implement Interfaces?

Yes.

### Can Records Have Methods?

Yes. Both static and instance methods are allowed.

### Can Records Have Constructors?

Yes.

* Canonical Constructor
* Compact Constructor
* Custom Constructor

### Which Class Do Records Extend?

```java
java.lang.Record
```

### Are Records Final?

Yes. Records are implicitly final.

### How Can We Check Whether a Class Is a Record?

```java
Employee.class.isRecord();
```

### How Can We Retrieve Record Components?

```java
Employee.class.getRecordComponents();
```

---

# Summary

Java Records provide a concise and powerful way to create immutable data classes. They eliminate boilerplate code by automatically generating constructors, accessors, `equals()`, `hashCode()`, and `toString()` methods, making applications cleaner, safer, and easier to maintain. They are especially useful for DTOs, API models, configuration objects, and other data-centric classes.
