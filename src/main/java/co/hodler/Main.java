package co.hodler;

import jxl.Sheet;
import jxl.Workbook;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
  private static final String CREATED_REPORT = "template_output.xls";

  public static void main(String[] args) throws Exception {
    List<Person> persons = new ArrayList<>();
    persons.add(new Person("Pete", 32));
    persons.add(new Person("Maggie", 33));
    persons.add(new Person("Allie", 35));
    try (InputStream is = Files.newInputStream(Paths.get("template.xls"))) {
      try (OutputStream os = new FileOutputStream(CREATED_REPORT)) {
        Context context = new Context();
        context.putVar("persons", persons);
        JxlsHelper.getInstance().processTemplate(is, os, context);
      }
    }

    Workbook workbook = Workbook.getWorkbook(new File(CREATED_REPORT));
    Sheet sheet = workbook.getSheet(0);
    List<Person> parsedPersons = new ArrayList<>();
    int firstRowWithContent = 3;
    for (int i = firstRowWithContent; i < sheet.getRows(); i++) {
      Person person = new Person();
      person.setName(sheet.getCell(0, i).getContents());
      person.setAge(Integer.valueOf(sheet.getCell(1, i).getContents()));
      parsedPersons.add(person);
    }
    System.out.println("Parsed persons equal the stored persons?");
    System.out.println(persons.equals(parsedPersons));
  }
}
