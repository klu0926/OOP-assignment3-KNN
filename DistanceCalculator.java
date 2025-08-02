// Kuo Yu Lu
// Step 2
public class DistanceCalculator {
  public double getDistance(String[] sample1, String[] sample2) {

    double total = 0;
    // ignore id and diagnosis col (0, 1)
    for (int i = 2; i < sample1.length; i++) {

      double value1 = Double.parseDouble(sample1[i]);
      double value2 = Double.parseDouble(sample2[i]);

      double diff = value1 - value2;
      total += (diff * diff);
    }

    return Math.sqrt(total);
  }
}
