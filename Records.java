
// Kuo Yu Lu
import java.util.Arrays;
import java.util.List;

public class Records {
  private List<String[]> records;

  public Records(List<String[]> records) {
    this.records = records;
  }

  public List<String[]> getRecords() {
    return records;
  }

  public String[] getRecord(int index) {
    if (index > records.size() - 1 || index < 0) {
      throw new IndexOutOfBoundsException("Index out of range " + index);
    }
    return records.get(index);
  }

  // Print out records for testing ------------------
  public void printRecord(int index) {
    System.out.println(Arrays.toString(records.get(index)));
  }

  public void printRecords(int limit) {
    for (int i = 0; i < limit; i++) {
      System.out.println(Arrays.toString(records.get(i)));
    }
    System.out.println("Total Records Count: " + limit);
  }

  public void printRecords() {
    // default no limit
    printRecords(records.size());
  }

}
