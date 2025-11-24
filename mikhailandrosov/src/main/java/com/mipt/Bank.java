package com.mipt;

class Bank {
  public static void sendToAccountDeadlock(BankAccount from, BankAccount to, int amount) {
    validateAccounts(from, to);
    validateAmount(amount);

    synchronized (from) {
      synchronized (to) {
        validateTransfer(from, to, amount);
        from.withdraw(amount);
        to.deposit(amount);
        System.out.println("Перевод: " + amount + " со счета " + from.getId() + " на счет " + to.getId());
      }
    }
  }

  public static void sendToAccount(BankAccount from, BankAccount to, int amount) {
    validateAccounts(from, to);
    validateAmount(amount);

    BankAccount first = from.getId() < to.getId() ? from : to;
    BankAccount second = from.getId() < to.getId() ? to : from;

    synchronized (first) {
      synchronized (second) {
        validateTransfer(from, to, amount);
        from.withdraw(amount);
        to.deposit(amount);
        System.out.println("Перевод: " + amount + " со счета " + from.getId() + " на счет " + to.getId());
      }
    }
  }

  private static void validateAccounts(BankAccount from, BankAccount to) {
    if (from == null) {
      throw new IllegalArgumentException("Счет отправителя не может быть null");
    }
    if (to == null) {
      throw new IllegalArgumentException("Счет получателя не может быть null");
    }
    if (from == to) {
      throw new IllegalArgumentException("Нельзя переводить средства на тот же самый счет");
    }
  }

  private static void validateAmount(int amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("Сумма перевода должна быть положительной: " + amount);
    }
  }

  private static void validateTransfer(BankAccount from, BankAccount to, int amount) {
    if (from.getBalance() < amount) {
      throw new IllegalArgumentException("Недостаточно средств на счете " + from.getId() +
              ". Требуется: " + amount + ", доступно: " + from.getBalance());
    }
  }
}