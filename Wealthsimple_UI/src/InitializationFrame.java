/**
 * This class houses the main method, which initializes the PendingTransfers and UI classes.
 */
public class InitializationFrame {

  public static void main(String[] args) {
    // Initialize the PendingTransfers class
    PendingTransfers pt = new PendingTransfers();
    UI ui = new UI(pt);
  }
}
