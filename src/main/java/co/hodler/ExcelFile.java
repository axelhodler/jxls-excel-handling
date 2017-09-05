package co.hodler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ExcelFile {
  public void storeFromString(String contents) {
    try {
      Files.write(Paths.get("sample.xls"), contents.getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
