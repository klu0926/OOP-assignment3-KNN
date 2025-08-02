// Kuo Yu Lu
// Step 1
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
  public static List<String[]> read(String filePath) {
    List<String[]> records = new ArrayList<>();

    // Read File
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      br.readLine(); // consume header

      // Read each row untill there are no more
      while ((line = br.readLine()) != null) {
        String[] row = line.split(",");
        records.add(row);
      }

    } catch (IOException e) {
      System.err.println("Error reading the file.");
      e.printStackTrace();
    }

    return records;
  }
}
