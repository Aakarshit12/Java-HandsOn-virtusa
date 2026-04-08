class ATM {
    private int pin = 2580;
    private int bal = 8500;

    void enter(int p) throws Exception {
        if (p != pin) {
            throw new Exception("Wrong PIN. Attempt " + " of 3");
        }
        System.out.println("Welcome Ji!!");
    }

    void take(int amt) throws Exception {
        if (amt > bal)
            throw new Exception("Low balance. Available: Rs." + bal);
        bal -= amt;
        System.out.println("Rs." + amt + " dispensed. Remaining: Rs." + bal);
    }
}

public class Sixth_handon {
    public static void main(String[] args) {

        ATM atm = new ATM();

        try {
            atm.enter(1111);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            atm.enter(2580);
            System.out.println("Login OK");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            atm.take(9000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            atm.take(2000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}