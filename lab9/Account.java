package lab9;

public class Account {
    private final int id;
    private int balance;

    public Account(int id, int initialBalance) {
        this.id = id;
        this.balance = initialBalance;
    }

    public int getId() {
        return id;
    }

    public synchronized int getBalance() {
        return balance;
    }

    public void withdraw(int amount) {
        if (balance < amount) {
            throw new IllegalArgumentException("Not enough funds");
        }
        balance -= amount;
    }

    public void deposit(int amount) {
        balance += amount;
    }
}
