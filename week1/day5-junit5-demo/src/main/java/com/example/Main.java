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