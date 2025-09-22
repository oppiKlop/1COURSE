package mikhailandrosov;

public class MainClass {
  private int number;
  private String text;

  protected static double staticValue;

  public final long constantValue = 1000L;

  public static void main(String[] args) {
    for (int i = 0; i < 15; i++) {
      System.out.println("Iter: " + i);
    }
  }
}