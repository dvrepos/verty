package domain.my.doSports_Bot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Runner {

  private static void toCheckHowStaticAnalyzersWork(String contents, File file) throws IOException {
    FileWriter fileWriter = new FileWriter(file);
    fileWriter.write(contents);
  }

  public String getInfo() {
    return "some info";
  }

  public static void main(String[] args) {
    log.debug("the main runner");
  }
}
