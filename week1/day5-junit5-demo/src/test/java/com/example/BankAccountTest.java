package com.example;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // So @BeforeAll/@AfterAll can be non-static
class BankAccountTest {

    BankAccount account;

    @BeforeAll
    void initAll() {
        // ⚡ Runs once before all tests
        System.out.println("==> Global test initialization");
    }

    @BeforeEach
    void init() {
        // ⚡ Runs before each test: create a fresh account
        account = new BankAccount("Ismail", 1000);
        System.out.println("-> New account created for test");
    }

    @AfterEach
    void tearDown() {
        // ⚡ Runs after each test
        System.out.println("<- Test finished");
    }

    @AfterAll
    void tearDownAll() {
        // ⚡ Runs once after all tests
        System.out.println("==> Global test cleanup");
    }

    @Test
    void testDeposit() {
        account.deposit(500);
        assertEquals(1500, account.getBalance(), "Balance should be 1500 after deposit");
    }

    @Test
    void testWithdraw() {
        account.withdraw(200);
        assertEquals(800, account.getBalance(), "Balance should be 800 after withdrawal");
    }

    @Test
    void testWithdrawTooMuchThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(2000),
                "Should throw exception if balance is insufficient");
    }

    @Test
    void testMultipleAssertions() {
        account.deposit(100);
        account.withdraw(50);
        // ⚡ assertAll checks multiple assertions without stopping at the first failure
        assertAll(
                () -> assertEquals(1050, account.getBalance()),
                () -> assertFalse(account.isOverdrawn()),
                () -> assertEquals("Ismail", account.getOwner())
        );
    }

    @ParameterizedTest
    @ValueSource(doubles = {100, 200, 300})
    void testParameterizedDeposit(double amount) {
        // ⚡ Runs the same test with different input values
        account.deposit(amount);
        assertTrue(account.getBalance() >= 1100, "Balance should be >= 1100");
    }

    @Disabled("Work in progress")
    @Test
    void testDisabled() {
        // ⚡ This test is ignored
        fail("This test should not run");
    }
}
