package co.hodler;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) throws Exception {
    List<Person> persons = new ArrayList<>();
    persons.add(new Person("Pete", 32));
    persons.add(new Person("Maggie", 33));
    persons.add(new Person("Allie", 35));
    try (InputStream is = Files.newInputStream(Paths.get("template.xls"))) {
      try (OutputStream os = new FileOutputStream("object_collection_output.xls")) {
        Context context = new Context();
        context.putVar("persons", persons);
        JxlsHelper.getInstance().processTemplate(is, os, context);
      }
    }
  }
}
