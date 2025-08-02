// Kuo Yu Lu
public class Main {
  public static void main(String[] args) {
    System.out.println("-".repeat(70));
    System.out.println("Assignment 3 \n");

    // Read CSV, return as List<String[]>
    // Create a new records instance
    Records records = new Records(CSVReader.read("./Experiment.csv"));

    // Create a report instance
    DistanceReport report = new DistanceReport(records.getRecords());

    // Calculate all N accuracy
    for (int n : new int[] { 3, 5, 7, 11, 13 }) {

      double acc = report.getTopNAccuracy(n);
      System.out.printf("N = %-3s Accuracy = %.2f%%\n", n, acc);
    }

  }

}