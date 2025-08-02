// Kuo Yu Lu
// Step 2
public class DistanceCalculator {
  public double getDistance(Sample sample1, Sample sample2) {

    double total = 0;
    double[] a = sample1.getFeatures();
    double[] b = sample2.getFeatures(); 

    for (int i = 0; i < a.length; i++) {

      double value1 = a[i];
      double value2 = b[i];

      double diff = value1 - value2;
      total += (diff * diff);
    }

    return Math.sqrt(total);
  }
}
