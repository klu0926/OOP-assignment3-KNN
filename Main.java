// Kuo Yu Lu
public class Main {
  public static void main(String[] args) {
    System.out.println("-".repeat(70));
    System.out.println("Assignment 3 \n");

    // Read CSV, return as List<String[]>
    // Create a new records instance
    CSVReader reader = new CSVReader("./Experiment.csv");

    // Create a report
    DistanceReport report = new DistanceReport(reader);

    // Calculate all N accuracy
    for (int n : new int[] { 3, 5, 7, 11, 13 }) {
      System.out.print("Calculating Please Wait...");

      double acc = report.getTopNAccuracy(n);

      // \r return to start of line, replace the 'waiting'
      System.out.print("\r");
      System.out.printf("N = %-3s Accuracy = %.2f%%\n", n, acc);
    }
  }
}