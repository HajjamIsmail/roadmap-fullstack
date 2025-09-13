
---

# 🧪 Documentation — Unit Testing with JUnit 5

---

## 1) 📌 Why We Write Unit Tests

Unit tests are small programs that verify **a single piece of functionality** (usually a method or class) works correctly.

**Benefits:**

* 🛡️ **Prevent regressions:** Catch bugs early when code changes.
* ⚡ **Accelerate development:** Instant feedback on code correctness.
* 📚 **Serve as documentation:** Show how the code is intended to be used.
* 🔁 **Enable refactoring safely:** Refactor with confidence knowing tests verify behavior.

> Think of unit tests like **quality checks** for every part of your code.

---

## 2) ⚙️ What JUnit 5 Does

JUnit 5 is a popular testing framework for Java. It allows you to:

* Write test methods easily
* Run tests automatically
* Report results (passed / failed / skipped)
* Integrate with Maven and IDEs like IntelliJ IDEA

> In short, it provides the **structure and tools** for automated testing.

---

## 3) 📁 Project Structure

```
my-junit5-demo/
├── pom.xml                    ← Maven dependencies & build
├── src/
│   ├── main/java/com/example/
│   │   ├── BankAccount.java   ← Production code
│   │   └── Main.java          ← Main class
│   └── test/java/com/example/
│       └── BankAccountTest.java ← Test class
```

* `src/main/java` → production code
* `src/test/java` → unit test code
* `pom.xml` → declares JUnit 5 dependency

---

## 4) 💰 `BankAccount.java` — Production Code

```java
package com.example;

public class BankAccount {

    private String owner;
    private double balance;

    public BankAccount(String owner, double initialBalance) {
        this.owner = owner;
        this.balance = initialBalance;
    }

    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    // Deposit money into the account
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be > 0");
        }
        balance += amount;
    }

    // Withdraw money if balance is sufficient
    public void withdraw(double amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        balance -= amount;
    }

    // Check if account is overdrawn
    public boolean isOverdrawn() {
        return balance < 0;
    }
}
```

**Key points in the code:**

* `private double balance` → internal state of the account
* `deposit` / `withdraw` → main business logic with validation
* `throw new IllegalArgumentException(...)` → signals invalid input

---

## 5) 🚀 `Main.java` — Running the Application

```java
package com.example;

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("Ismail", 1000);
        account.deposit(500);
        account.withdraw(200);

        System.out.println("Owner: " + account.getOwner());
        System.out.println("Final balance: " + account.getBalance());
    }
}
```

> This class demonstrates a simple **manual execution** of the BankAccount methods.

---

## 6) 🧪 `BankAccountTest.java` — Unit Tests with JUnit 5

```java
package com.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

// Using PER_CLASS lifecycle allows @BeforeAll/@AfterAll to be non-static
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BankAccountTest {

    BankAccount account;

    // Runs once before all tests in the class
    @BeforeAll
    void initAll() {
        System.out.println("==> Global test initialization");
    }

    // Runs before each test method
    @BeforeEach
    void init() {
        account = new BankAccount("Ismail", 1000);
        System.out.println("-> New account created for test");
    }

    // Runs after each test method
    @AfterEach
    void tearDown() {
        System.out.println("<- Test finished");
    }

    // Runs once after all tests in the class
    @AfterAll
    void tearDownAll() {
        System.out.println("==> Global test cleanup");
    }

    // Regular test
    @Test
    void testDeposit() {
        account.deposit(500);
        assertEquals(1500, account.getBalance(), "Balance should be 1500 after deposit");
    }

    // Regular test
    @Test
    void testWithdraw() {
        account.withdraw(200);
        assertEquals(800, account.getBalance(), "Balance should be 800 after withdrawal");
    }

    // Test that expects an exception
    @Test
    void testWithdrawTooMuchThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(2000),
                "Should throw exception if balance is insufficient");
    }

    // Grouped assertions
    @Test
    void testMultipleAssertions() {
        account.deposit(100);
        account.withdraw(50);

        assertAll(
                () -> assertEquals(1050, account.getBalance()),
                () -> assertFalse(account.isOverdrawn()),
                () -> assertEquals("Ismail", account.getOwner())
        );
    }

    // Parameterized test
    @ParameterizedTest
    @ValueSource(doubles = {100, 200, 300})
    void testParameterizedDeposit(double amount) {
        account.deposit(amount);
        assertTrue(account.getBalance() >= 1100, "Balance should be >= 1100");
    }

    // Skipped test
    @Disabled("Work in progress")
    @Test
    void testDisabled() {
        fail("This test should not run");
    }
}
```

---

## 7) 🔑 Explanation of Annotations Used

| Annotation                                        | Purpose / Description                                              |
| ------------------------------------------------- | ------------------------------------------------------------------ |
| `@Test`                                           | Marks a method as a unit test                                      |
| `@BeforeEach`                                     | Runs **before each test method**                                   |
| `@AfterEach`                                      | Runs **after each test method**                                    |
| `@BeforeAll`                                      | Runs **once before all tests** (can be non-static with PER\_CLASS) |
| `@AfterAll`                                       | Runs **once after all tests**                                      |
| `@Disabled`                                       | Skips the test temporarily                                         |
| `@ParameterizedTest`                              | Runs a test multiple times with different inputs                   |
| `@ValueSource`                                    | Provides literal values for parameterized tests                    |
| `@TestInstance(TestInstance.Lifecycle.PER_CLASS)` | Allows `@BeforeAll`/`@AfterAll` methods to be non-static           |

---

## 8) ▶️ Running the Tests

```bash
mvn clean test
```

**Example output:**

```
Tests run: 8, Failures: 0, Errors: 0, Skipped: 1
```

> Skipped test corresponds to the `@Disabled` test. All other tests passed successfully.

---

## 9) 📝 Summary

* Unit tests verify **small units of code** independently.
* JUnit 5 provides **annotations and assertions** to organize, execute, and validate tests.
* Maven automatically detects tests in `src/test/java` and runs them via the `surefire` plugin.
* Key practices:

    * Test one behavior per method
    * Use `assertThrows` to check exceptions
    * Use `assertAll` for multiple related checks
    * Use `@ParameterizedTest` for multiple input scenarios
    * Skip incomplete tests with `@Disabled`

---
