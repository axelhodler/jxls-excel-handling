package co.hodler;

public class Main {
  public static void main(String[] args) {
    // Shows warning and longer dialog during import
    ExcelFile excelFile = new ExcelFile();
    excelFile.storeFromString("topLeft\ttopRight\nbotLeft\tbotRight");
  }
}
