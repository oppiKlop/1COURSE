package com.mipt;

class BankAccount {
  private final int id;
  private int balance;

  public BankAccount(int id, int initialBalance) {
    this.id = id;
    this.balance = initialBalance;
  }

  public int getId() {
    return id;
  }

  public int getBalance() {
    return balance;
  }

  public void withdraw(int amount) {
    if (amount > balance) {
      throw new IllegalArgumentException("Недостаточно средств на счете");
    }
    balance -= amount;
  }

  public void deposit(int amount) {
    balance += amount;
  }
}