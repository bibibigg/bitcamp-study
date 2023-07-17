package bitcamp.util;

import java.io.InputStream;
import java.util.Scanner;

public class GymPrompt {

  private Scanner scanner;

  public GymPrompt() {
    this.scanner = new Scanner(System.in);
  }

  public GymPrompt(InputStream in) {
    this.scanner = new Scanner(in);
  }

  public String inputString(String title, Object... args) {
    System.out.printf(title, args);
    return this.scanner.nextLine();
  }

  public int inputInt(String title, Object... args) {
    return Integer.parseInt(this.inputString(title, args));
  }

  public void close() {
    this.scanner.close();
  }
}
