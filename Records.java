
// Kuo Yu Lu
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

  // Record a Map version of the record for faster lookup
  public Map<String, Sample> getMapRecords() {

    Map<String, Sample> map = new HashMap<>();

    for (String[] row : this.records) {
      String id = row[0];
      String diagnosis = row[1];

      // offsef by 2 (ignore id and diagnosis)
      // create a feature array
      int offset = 2;
      double[] features = new double[row.length - offset];

      for (int i = 0; i < features.length; i++) {
        features[i] = Double.parseDouble(row[i + offset]);
      }

      map.put(id, new Sample(id, diagnosis, features));
    }
    return map;
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
