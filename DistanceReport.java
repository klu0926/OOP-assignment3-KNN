
// Kuo Yu Lu
// Step 3
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DistanceReport extends DistanceCalculator {
  private List<String[]> records;
  private Map<String, Sample> recordsMap;
  private List<String[]> distances;
  private Map<String, List<String[]>> cacheRelated; // Cache

  public DistanceReport(CSVReader reader) {
    this.records = reader.getRecords();
    this.recordsMap = reader.getRecordsMap();
    this.distances = getAllDistances();
    this.cacheRelated = new HashMap<>();
  }

  // Step 3
  public List<String[]> getAllDistances() {
    // {ID of sampleA , ID of sampleB, distance }
    List<String[]> distanceRecords = new ArrayList<>();

    // Get distance for all records
    // !!! Based on the asssignment sample needs to compare to itself
    for (int i = 0; i < records.size(); i++) {
      for (int j = 0; j < records.size(); j++) {
        String sampleAId = records.get(i)[0];
        String sampleBId = records.get(j)[0];
        double distance = getDistance(
            recordsMap.get(sampleAId), recordsMap.get(sampleBId));

        // Create entry
        String[] entry = {
            sampleAId,
            sampleBId,
            String.valueOf(distance)
        };
        distanceRecords.add(entry);
      }
    }

    return distanceRecords;
  }

  // Step 4
  public double getTopNAccuracy(int n) {
    // how many sample in the records is prediction is correct
    int correct = 0;

    // From original record, literate through each id
    for (String[] sample : records) {

      // current sample id and diagnosis (ex: "001", "M")
      String sampleId = sample[0];
      String actualDiagnosis = sample[1];

      List<String[]> related = new ArrayList<>();
      // Check if cache exist
      if (cacheRelated.get(sampleId) != null) {
        related = cacheRelated.get(sampleId);
      } else {
        // Get a list of comparsions(row) of current sample, then sorted to ASC
        // steps:
        // stream : convert to a stream in order to use filter and sorted
        // filter : col 1 is current id, col 2 is not current id
        // sorted : parse col 2 to double, compare to sort to ASC (small -> big)
        // (closest on top)
        // collect : convert back to a list
        related = distances
            .stream()
            .filter(row -> row[0].equals(sampleId) && !row[1].equals(sampleId))
            .sorted(Comparator.comparingDouble(row -> Double.parseDouble(row[2])))
            .collect(Collectors.toList());

        // add to cache
        cacheRelated.put(sampleId, related);
      }

      // Get top N closest
      // run n times
      List<String> neighborDiagnoses = new ArrayList<>();
      for (int i = 0; i < n; i++) {
        String neighborId = related.get(i)[1];

        // (HashMap)Find neighbor diagnosis in records
        String neighborDiagnosis = recordsMap.get(neighborId).getDiagnosis();
        neighborDiagnoses.add(neighborDiagnosis);
      }

      // Predict majority
      int countB = (int) neighborDiagnoses.stream().filter(d -> d.equals("B")).count();
      int countM = (int) neighborDiagnoses.stream().filter(d -> d.equals("M")).count();
      String prediction = (countB > countM) ? "B" : "M";

      // Compare to current sample
      // if correct add to correct
      if (prediction.equals(actualDiagnosis)) {
        correct++;
      }
      // End of a loop, move to next sample
    }

    // return Accuracy(%)
    // (correct samples / total samples) * 100
    double accuracy = ((double) correct / records.size()) * 100;
    return accuracy;
  }

}
