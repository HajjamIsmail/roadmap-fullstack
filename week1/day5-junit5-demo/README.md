
---

# 🧪 Documentation — Introduction to Unit Testing with JUnit 5

---

## 1) 📌 Why We Write Unit Tests

In software development, **unit tests** are small programs that verify that a single unit (usually a method or class) works correctly.

**Their role:**

* 🛡️ **Prevent regressions**: Detect bugs early when you change your code.
* ⚡ **Speed up development**: Instant feedback on what works and what is broken.
* 📚 **Serve as documentation**: Show how your code is supposed to be used.
* 🔁 **Enable refactoring**: You can safely improve code without breaking existing logic.

> **Analogy**: Like quality checks on a production line — every part must be tested before assembling the whole system.

---

## 2) ⚙️ What  Does

**JUnit 5** is the most widely used testing framework in the  ecosystem.
It allows you to:

* Write **test methods** easily
* Execute them automatically
* Show results (✅ passed, ❌ failed, ⚠️ skipped)
* Integrate with build tools like  and IDEs like&#x20;

> In short, JUnit provides the structure and tools for writing and running automated tests.

---

## 3) 📁 Project Structure ()

```
my-bank-project/
├── src/
│   ├── main/
│   │   └── java/com/example/BankAccount.java   ← main business class
│   └── test/
│       └── java/com/example/BankAccountTest.java ← test class
└── pom.xml   ← dependencies + build configuration
```

* `src/main/java` → contains **production code**
* `src/test/java` → contains **unit test code**
* `pom.xml` → declares `junit-jupiter` (JUnit 5) as a dependency

---

## 4) 💰 `BankAccount` Class (Production Code)

```java
package com.example;

public class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive");
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive");
        if (amount > balance) throw new IllegalStateException("Insufficient balance");
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}
```

**Keywords explained:**

* `public class BankAccount` → the class we want to test
* `private double balance` → internal state
* `deposit` / `withdraw` → business logic with validations
* `throw new IllegalArgumentException(...)` → signals invalid input
* `throw new IllegalStateException(...)` → signals incorrect state (e.g. not enough funds)

---

## 5) ✅ `BankAccountTest` Class (Test Code)

```java
package com.example;

import org.junit.jupiter.api.*; // JUnit 5 annotations
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void depositShouldIncreaseBalance() {
        BankAccount account = new BankAccount(100);
        account.deposit(50);
        assertEquals(150, account.getBalance());
    }

    @Test
    void withdrawShouldDecreaseBalance() {
        BankAccount account = new BankAccount(100);
        account.withdraw(40);
        assertEquals(60, account.getBalance());
    }

    @Test
    void withdrawShouldFailIfInsufficientFunds() {
        BankAccount account = new BankAccount(50);
        assertThrows(IllegalStateException.class, () -> account.withdraw(100));
    }

    @Test
    @Disabled("Feature not implemented yet")
    void futureFeatureTest() {
        // this test will be skipped
    }
}
```

**Important keywords and annotations:**

* `@Test` → Marks a method as a unit test
* `assertEquals(expected, actual)` → Checks that values are equal
* `assertThrows(Exception.class, () -> ...)` → Verifies an exception is thrown
* `@Disabled` → Temporarily skip this test
* `org.junit.jupiter.api.*` → JUnit 5’s API package

> Each method tests a **single behavior** of `BankAccount`.

---

## 6) 📝 Summary

| Concept         | Description                              |
| --------------- | ---------------------------------------- |
| Unit Test       | Small, isolated test on a method/class   |
| JUnit 5         | Framework to write and run tests in Java |
| `@Test`         | Marks a method as a test                 |
| `assertEquals`  | Verifies two values are equal            |
| `assertThrows`  | Verifies an exception is thrown          |
| `@Disabled`     | Skips a test temporarily                 |
| `src/test/java` | Folder for all test classes              |

✅ **Goal:** Guarantee that each small part of your program works perfectly and automatically detect bugs when you change code.

---

