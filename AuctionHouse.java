import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
class AuctionItem {
    private final String type;
    private final String description;
    private final String year;
    private final String condition;

    public AuctionItem(String type, String description, String year, String condition) {
        this.type = type;
        this.description = description;
        this.year = year;
        this.condition = condition;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getYear() {
        return year;
    }

    public String getCondition() {
        return condition;
    }

    @Override
    public String toString() {
        return getType() + ", " + getDescription() + ", " + getYear() + ", " + getCondition();
    }

}
class Car extends AuctionItem {
    private final String make;
    private final String model;
    private final String miles;

    public Car(String make, String model, String year, String miles) {
        super("Car", make, model, year);
        this.make = make;
        this.model = model;
        this.miles = miles;
    }
    public String getMake() {
        return make;
    }

    public String toString() {
        return getType() + "\nMake: " + make + "\nModel: " + model + "\nYear: " + getYear() + "\nMiles: " + miles;
    }


    public String getModel() {
        return model;
    }

    public String getMiles() {
        return miles;
    }
}

class Coin extends AuctionItem {
    private final String grade;

    public Coin(String description, String year, String grade) {
        super("Coin", description, year, grade);
        this.grade = grade;
    }

    public String toString() {
        return getType() + "\nDescription: " + getDescription() + "\nTear: " + getYear() + "\nGrade: " + grade;
    }

    public String getGrade() {
        return grade;
    }
}

class Collectable extends AuctionItem {

    public Collectable(String description, String condition) {
        super("Collectable", description, "", condition);
    }

    public String toString() {
        return getType() + "\nDescription: " + getDescription() + "\nCondition: " + getCondition();
    }

}

class RareBook extends AuctionItem {
    private final String title;
    private final String author;

    public RareBook(String title, String author, String year, String condition) {
        super("RareBook", title + " by " + author, year, condition);
        this.title = title;
        this.author = author;
    }

    public String toString() {
        return getType() + "\nTitle: " + title + "\nAuthor: " + author + "\nYear: " + getYear() + "\nCondition: " + getCondition();
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}
public class AuctionHouse {

    private static final String CSV_HEADER = "Category,car_make,car_model,car_year,car_miles,coin_description,coin_grade,coin_year,collectable_description,collectable_condition,book_title,book_author,book_year,book_condition";
    private static final String CSV_FILENAME = "auction_items.csv";

    private static List<AuctionItem> items = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadItems();
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("Select an option:");
            System.out.println("1) Add a new item for auction");
            System.out.println("2) View items offered for auction");
            System.out.println("3) Remove an item");
            System.out.println("4) Save & Exit");
            System.out.println("5) Exit without Saving");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addItem();
                    break;
                case 2:
                    showItems();
                    break;
                case 3:
                    removeItem();
                    break;
                case 4:
                    saveItems();
                    isRunning = false;
                    break;
                case 5:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void addItem() {
        System.out.println("Enter the category of the item (car, coin, collectable, or rareBook):");
        String category = scanner.next();
        switch (category) {
            case "car":
                System.out.println("Please enter the following details for your car...");
                System.out.print("Make:");
                String make = scanner.next();
                System.out.print("Model:");
                scanner.nextLine();
                String model = scanner.next();
                System.out.print("Year manufactured:");
                scanner.nextLine();
                String year = scanner.next();
                System.out.print("Miles travelled (odometer):");
                scanner.nextLine();
                String miles = scanner.next();
                items.add(new Car(make, model, year, miles));
                break;
            case "coin":
                System.out.println("Please enter the following details for your coin...");
                System.out.print("Description:");
                scanner.nextLine();
                String coinDescription = scanner.next();
                System.out.print("Grade:");
                scanner.nextLine();
                String coinGrade = scanner.next();
                System.out.print("Year:");
                scanner.nextLine();
                String coinYear = scanner.next();
                items.add(new Coin(coinDescription, coinGrade, coinYear));
                break;
            case "collectable":
                System.out.println("Please enter the following details for your collectable...");
                System.out.print("Description:");
                scanner.nextLine();
                String collectableDescription = scanner.next();
                System.out.print("Condition:");
                scanner.nextLine();
                String collectableCondition = scanner.next();
                items.add(new Collectable(collectableDescription, collectableCondition));
                break;
            case "rareBook":
                System.out.println("Please enter the following details for your book...");
                System.out.print("Title:");
                scanner.nextLine();
                String bookTitle = scanner.next();
                System.out.print("Author:");
                scanner.nextLine();
                String bookAuthor = scanner.next();
                System.out.print("Year:");
                scanner.nextLine();
                String bookYear = scanner.next();
                System.out.print("Condition:");
                scanner.nextLine();
                String bookCondition = scanner.next();
                items.add(new RareBook(bookTitle, bookAuthor, bookYear, bookCondition));
                break;
            default:
                System.out.println("Invalid category. Please try again.");
                break;
        }
    }

    private static void showItems() {
        if (items.isEmpty()) {
            System.out.println("No items to show.");
        } else {
            for (int i = 0; i < items.size(); i++) {
                System.out.println((i + 1) + ") " + items.get(i));
            }
        }
    }
    private static void removeItem() {
        showItems();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Which item would you like to remove? ");
        int number = scanner.nextInt();
        if (number >= 1 && number <= items.size()) {
            items.remove(number - 1);
            System.out.println("Removing item"+number+"...");
        } else {
            System.out.println("Invalid item number.");
        }
    }
    private static void saveItems() {
        try {
            File file = new File(CSV_FILENAME);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            // Write the CSV header
            bw.write(CSV_HEADER);
            bw.newLine();

            // Write the details of each item to the CSV file
            for (AuctionItem item : items) {
                if (item instanceof Car) {
                    Car car = (Car) item;
                    bw.write("car,"+car.getMake() + "," + car.getModel() + "," + car.getYear() + "," + car.getMiles() + ",,,,,,,,,");
                    bw.newLine();
                } else if (item instanceof Coin) {
                    Coin coin = (Coin) item;
                    bw.write("coin,,,,," + coin.getDescription() + "," + coin.getGrade() + "," + coin.getYear() + ",,,,,,");
                    bw.newLine();
                } else if (item instanceof Collectable) {
                    Collectable collectable = (Collectable) item;
                    bw.write("collectable,,,,,,,," + collectable.getDescription() + "," + collectable.getCondition() + ",,,,");
                    bw.newLine();
                } else if (item instanceof RareBook) {
                    RareBook rareBook = (RareBook) item;
                    bw.write("rareBook,,,,,,,,,," + rareBook.getTitle() + "," + rareBook.getAuthor() + "," + rareBook.getYear() + "," + rareBook.getCondition());
                    bw.newLine();
                }
            }
            bw.close();
            System.out.println("Saving to auction_items.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void loadItems() {
        try (Scanner scanner = new Scanner(new File(CSV_FILENAME))) {
            if (scanner.hasNextLine()) {
                String headerLine = scanner.nextLine();
                if (!headerLine.equals(CSV_HEADER)) {
                    System.out.println("Warning: CSV file header does not match expected format.");
                }
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(",");
                switch (fields[0]) {
                    case "car":
                        items.add(new Car(fields[1], fields[2], fields[3], fields[4]));
                        break;
                    case "coin":
                        items.add(new Coin(fields[5], fields[6], fields[7]));
                        break;
                    case "collectable":
                        items.add(new Collectable(fields[8], fields[9]));
                        break;
                    case "rareBook":
                        items.add(new RareBook(fields[10], fields[11], fields[12], fields[13]));
                        break;
                    default:
                        System.out.println("Warning: Unrecognized item category \"" + fields[0] + "\" in CSV file.");
                        break;
                }
            }
            System.out.println("Loaded " + items.size() + " items from CSV file.");
        } catch (FileNotFoundException e) {
            System.out.println("Warning: CSV file \"" + CSV_FILENAME + "\" not found. No items loaded.");
        }
    }


}
