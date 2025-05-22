

class Instrument {
  public void play() {
    System.out.println("Instrument.play()");
  }
}

class Wind extends Instrument {
  public void play() {
    System.out.println("Wind.play()");
  }
}

class Stringed extends Instrument {
  public void play() {
    System.out.println("Stringed.play()");
  }
}

class Brass extends Instrument {
  public void play() {
    System.out.println("Brass.play()");
  }
}

public class Music2 {
  public static void tune(Wind i) {
    i.play();
  }
  public static void tune(Stringed i) {
    i.play();
  }
  public static void tune(Brass i) {
    i.play();
  }
  public static void main(String[] args) {
    Wind flute = new Wind();
    Stringed violin = new Stringed();
    Brass frenchHorn = new Brass();
    tune(flute); // No upcasting
    tune(violin);
    tune(frenchHorn);
  }
} ///:~