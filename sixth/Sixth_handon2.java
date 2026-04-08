class Item {
    private String nm;
    private int stock;

    Item(String nm, int stock) {
        this.nm = nm;
        this.stock = stock;
    }

    void buy(int qty) throws Exception {
        if (qty <= 0)
            throw new Exception("Invalid quantity: " + qty);
        if (stock == 0)
            throw new Exception(nm + " is out of stock");
        stock -= qty;
        System.out.println("Ordered " + qty + "x " + nm + ". Stock left: " + stock);
    }
}

public class Sixth_handon2 {
    public static void main(String[] args) {

        Item laptop = new Item("Lenovo IdeaPad", 3);
        Item mouse = new Item("Logitech M235", 0);

        try {
            laptop.buy(-1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            laptop.buy(2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            mouse.buy(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}