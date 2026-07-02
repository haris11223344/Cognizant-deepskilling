import java.util.Scanner;

class Product {
    int id;
    String name;
    double price;

    Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}

public class EcommerceSearch {

    public static void main(String[] args) {

        Product[] products = {
            new Product(101, "Laptop", 65000),
            new Product(102, "Mouse", 700),
            new Product(103, "Keyboard", 1500),
            new Product(104, "Monitor", 12000),
            new Product(105, "Headphones", 2500)
        };

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter product name to search: ");
        String search = sc.nextLine();

        boolean found = false;

        for (Product product : products) {
            if (product.name.equalsIgnoreCase(search)) {
                System.out.println("\nProduct Found!");
                System.out.println("ID    : " + product.id);
                System.out.println("Name  : " + product.name);
                System.out.println("Price : ₹" + product.price);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Product not found.");
        }

        sc.close();
    }
}