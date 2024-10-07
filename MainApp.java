
import java.util.Calendar;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 *
 * @author Brandon Marquart
 * @version 1.o
 * @date 4-2-2024
 */
public class MainApp {

   /**
    * @param args the command line arguments
    */
   public static void main(String[] args) {
      Scanner scnr = new Scanner(System.in);
      boolean exit = false;
      boolean exitOrder = false;
      int orderSize = 0;
      int orderNumber = 1;
      String customer;
      Calendar orderDate = Calendar.getInstance();
      char userIn = 'z';
      
      System.out.println("Welcome to order app");
      
      //Creates hash map where orders will be stored
      HashMap<String, Order> orders = new HashMap(11);
      
      //Creates a few orders to be in hashmap
      Order order1 = new Order(12, "Mary", orderDate);
      order1.addFoodItem(new MainDish("Pizza", 3.00));
      
      Order order2 = new Order(13, "Bill", orderDate);
      order2.addFoodItem(new Drink ("soda", 1.00));
      
      //adds orders to map
      orders.put(order1.getCustomer(), order1);
      orders.put(order2.getCustomer(), order2);
      
      //Program while loop
      while (!exit) {
         System.out.println("Welcome to the order taking application please select an option below");
         System.out.println("A - Add an order");
         System.out.println("B - Lookup an order");
         System.out.println("C - Cancel an order");
         System.out.println("D - Display all orders");
         System.out.println("E - Exit the program");
         
         userIn = scnr.next().charAt(0);
         
         //Switch to check the users input 
         switch (Character.toLowerCase(userIn)) {
            case 'a':
               //Resetting order size
               orderSize = 0;
               //Resetting exitOrder loop.
               exitOrder = false;
               System.out.println("");
               System.out.println("Enter customer name");
               scnr.nextLine();
               customer = scnr.nextLine();
               System.out.println("To add an order please select what food for the order");
               //Creating array to be pushed to order.
               Order orderItems = new Order(orderNumber,customer,orderDate);
               while (!exitOrder){
                  try {
                     orderItems.addFoodItem(addItem(scnr));
                  } catch (InputMismatchException imException) {
                     System.out.println("Invalid input");
                  }
                  
                  System.out.println("Would you like to add another item Y/N");
                  userIn = scnr.next().charAt(0);
                  //checks if the program should exit
                  if (Character.toLowerCase(userIn) == 'n') {
                     exitOrder = true;
                  }
                  if (orderSize == 3){
                     exitOrder = true;
                     System.out.println("Order is full max of three items.");
                  }
               }
               //adds the order to the queue
               orders.put(orderItems.getCustomer(), orderItems);
               orderNumber++;
               System.out.println("Order has been added to the queue");
               System.out.println("");
               break;
               
            //Looksup item in the map
            case 'b':
               System.out.println("");
               System.out.println("Enter the name of the customer"); 
               
               //Takes the key to look up
               scnr.nextLine();
               customer = scnr.nextLine();
               
               if (orders.get(customer) != null) {
                  System.out.println("");
                  System.out.println(orders.get(customer).toString());
                  System.out.println("");
               }
               else {
                  System.out.println("Please try again order could not be found");
               }
               
               break;
               
            //Cancels an order in the hashmap
            case 'c':
               System.out.println("");
               System.out.println("Enter the name of the customer"); 
               
               scnr.nextLine();
               customer = scnr.nextLine();
               
              if (orders.get(customer) != null ) {
                 System.out.println("");
                 System.out.println(orders.remove(customer));
                 System.out.println("");
                 System.out.println("This order has been removed");
                 System.out.println("");
              }
              else {
                 System.out.println("");
                 System.out.println("Please try again order could not be found");
                 System.out.println("");
              }
              break;
            
            //displays all the orders
            case 'd':
               
               for (HashMap.Entry<String, Order> entry : orders.entrySet()) {
                  System.out.println(entry.getValue());
               }
               
            //Exits the program
            case 'e':
               System.out.println("");
               System.out.println("Program stopping");
               exit = true;
               break;
         }
         
      }
      
   }
   /**
    * This method is used to find the information of a new item to add to the map
    * @param scnr A Scanner used for user input
    * @return A item of type FoodItem with the respective category.
    */
   public static FoodItem addItem(Scanner scnr) {
      char userIn = 'z';
      String name;
      double price;
      
      System.out.println("Please enter what kinda of item you would like to add");
      System.out.println("A - Appetizer");
      System.out.println("B - Main Dish");
      System.out.println("C - Drink");
      System.out.println("D - Dessert");
      
      //takes the users input
      userIn = scnr.next().charAt(0);
      System.out.println("Enter a name for the item");
      scnr.nextLine();
      name = scnr.nextLine();
      System.out.println("Enter a price for the item");
      price = scnr.nextDouble();
      
      //switch to see which kind of item to add to the stack
      switch (Character.toLowerCase(userIn)) {
         
         case 'a' -> {
            return new Appetizer(name, price);
         }
         
         case 'b' -> {
            return new MainDish(name, price);
         }
         
         case 'c' -> {
            return new Drink(name, price);
         }
         
         case 'd' -> {
            return new Dessert(name, price);
         }
         
         //Makes sure the input is valid
         default -> {
            throw new InputMismatchException("Invalid input");
         }
      }
      
   }
   
}
