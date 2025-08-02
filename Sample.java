// Kuo Yu Lu

public class Sample {
  private String id;
  private String diagnosis;
  private double[] features;

  // Store each sample by its type
  public Sample(String id, String diagnosis, double[] features) {
    this.id = id;
    this.diagnosis = diagnosis;
    this.features = features;
  }

  public String getId() {
    return id;
  }

  public String getDiagnosis() {
    return diagnosis;
  }

  public double[] getFeatures() {
    return features;
  }
}
