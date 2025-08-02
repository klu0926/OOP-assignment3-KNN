
// Kuo Yu Lu
// Step 1
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Read CSV
// Create a List and a Map
public class CSVReader {
  private List<String[]> records;
  private Map<String, Sample> recordsMap;

  public CSVReader(String filePath) {
    // Initialize
    this.records = new ArrayList<>();
    this.recordsMap = new HashMap<>();

    // Read File
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      br.readLine(); // consume header

      // Read each line untill there are no more
      while ((line = br.readLine()) != null) {
        // Create col in each row
        String[] row = line.split(",");

        // Add to List
        this.records.add(row);

        // Create Sample(id, diagnosis, features) for Map
        String id = row[0];
        String diagnosis = row[1];

        int offset = 2; // (ignore id and diagnosis)
        double[] features = new double[row.length - offset];
        // Create features array
        for (int i = 0; i < features.length; i++) {
          features[i] = Double.parseDouble(row[i + offset]);
        }

        // Create sample and put in Map
        Sample sample = new Sample(id, diagnosis, features);
        this.recordsMap.put(id, sample);
      }

    } catch (IOException e) {
      System.err.println("Error reading the file.");
      e.printStackTrace();
    }
  }

  public List<String[]> getRecords() {
    return records;
  }

  public Map<String, Sample> getRecordsMap() {
    return recordsMap;
  }
}
