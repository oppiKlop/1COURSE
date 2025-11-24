package com.mipt;

public class BankTest {
  public static void main(String[] args) throws InterruptedException {
    testBasicTransfers();
    testInsufficientFunds();
    testValidation();
    testDeadlockScenario();
    testCorrectTransfer();
  }

  private static void testBasicTransfers() {
    System.out.println("=== Тест базовых переводов ===");
    BankAccount acc1 = new BankAccount(1, 1000);
    BankAccount acc2 = new BankAccount(2, 1000);

    Bank.sendToAccount(acc1, acc2, 100);
    System.out.println("После sendToAccount - Баланс счета 1: " + acc1.getBalance() + ", Счет 2: " + acc2.getBalance());

    Bank.sendToAccountDeadlock(acc2, acc1, 50);
    System.out.println("После sendToAccountDeadlock - Баланс счета 1: " + acc1.getBalance() + ", Счет 2: " + acc2.getBalance());
    System.out.println();
  }

  private static void testInsufficientFunds() {
    System.out.println("=== Тест недостаточности средств ===");
    BankAccount acc1 = new BankAccount(1, 100);
    BankAccount acc2 = new BankAccount(2, 100);

    try {
      Bank.sendToAccount(acc1, acc2, 200);
    } catch (IllegalArgumentException e) {
      System.out.println("Ожидаемая ошибка sendToAccount: " + e.getMessage());
    }

    try {
      Bank.sendToAccountDeadlock(acc1, acc2, 200);
    } catch (IllegalArgumentException e) {
      System.out.println("Ожидаемая ошибка sendToAccountDeadlock: " + e.getMessage());
    }
    System.out.println();
  }

  private static void testValidation() {
    System.out.println("=== Тесты валидации ===");
    BankAccount acc1 = new BankAccount(1, 1000);
    BankAccount acc2 = new BankAccount(2, 1000);

    try {
      Bank.sendToAccount(acc1, acc2, 0);
    } catch (IllegalArgumentException e) {
      System.out.println("Ошибка нулевой суммы: " + e.getMessage());
    }

    try {
      Bank.sendToAccount(acc1, acc2, -100);
    } catch (IllegalArgumentException e) {
      System.out.println("Ошибка отрицательной суммы: " + e.getMessage());
    }

    try {
      Bank.sendToAccount(acc1, acc1, 100);
    } catch (IllegalArgumentException e) {
      System.out.println("Ошибка перевода на тот же счет: " + e.getMessage());
    }

    try {
      Bank.sendToAccount(null, acc2, 100);
    } catch (IllegalArgumentException e) {
      System.out.println("Ошибка null счета отправителя: " + e.getMessage());
    }

    try {
      Bank.sendToAccount(acc1, null, 100);
    } catch (IllegalArgumentException e) {
      System.out.println("Ошибка null счета получателя: " + e.getMessage());
    }
    System.out.println();
  }

  private static void testDeadlockScenario() throws InterruptedException {
    System.out.println("=== Тест дедлока (с таймаутом) ===");
    BankAccount acc1 = new BankAccount(1, 1000);
    BankAccount acc2 = new BankAccount(2, 1000);

    Thread t1 = new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        Bank.sendToAccountDeadlock(acc1, acc2, 10);
      }
    });

    Thread t2 = new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        Bank.sendToAccountDeadlock(acc2, acc1, 10);
      }
    });

    t1.start();
    t2.start();

    t1.join(3000);
    t2.join(3000);

    if (t1.isAlive() || t2.isAlive()) {
      System.out.println("ОБНАРУЖЕН ДЕДЛОК! Потоки все еще выполняются после таймаута.");
      t1.interrupt();
      t2.interrupt();
    } else {
      System.out.println("Дедлок не произошел");
    }
    System.out.println("Финальные балансы - Счет 1: " + acc1.getBalance() + ", Счет 2: " + acc2.getBalance());
    System.out.println();
  }

  private static void testCorrectTransfer() throws InterruptedException {
    System.out.println("=== Тест корректных переводов ===");
    BankAccount acc1 = new BankAccount(1, 1000);
    BankAccount acc2 = new BankAccount(2, 1000);

    Thread t1 = new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        Bank.sendToAccount(acc1, acc2, 10);
      }
    });

    Thread t2 = new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        Bank.sendToAccount(acc2, acc1, 10);
      }
    });

    t1.start();
    t2.start();

    t1.join();
    t2.join();

    System.out.println("Финальные балансы - Счет 1: " + acc1.getBalance() + ", Счет 2: " + acc2.getBalance());
    System.out.println("Тест завершен успешно без дедлока!");
  }
}
