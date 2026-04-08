class BankAccount {
    private int balance = 0;

    synchronized void deposit(int amt) throws InterruptedException {
        balance += amt;
        System.out.println("Deposited: " + amt + " | Balance: " + balance);
        notify();
    }

    synchronized void withdraw(int amt) throws InterruptedException {
        while (balance < amt) {
            System.out.println("Low balance.");
            wait();
        }
        balance -= amt;
        System.out.println("Withdrawn amount: " + amt + " | Balance: " + balance);
    }
}

public class ninth_handon {
    public static void main(String[] args) {

        BankAccount acc = new BankAccount();

        Thread depositor = new Thread(() -> {
            try {
                acc.deposit(500);
                acc.deposit(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        });

        Thread withdrawer = new Thread(() -> {
            try {
                acc.withdraw(700);
                acc.withdraw(500);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        });

        withdrawer.start();
        depositor.start();
    }
}