package domain.my.tgbotcalmaster;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

@Slf4j
public class RunnerTest {
  private Runner runner = new Runner();
  private String info = runner.getInfo();

  private static void toCheckHowStaticAnalyzersWork(String contents, File file) throws IOException {
    FileWriter fileWriter = new FileWriter(file);
    fileWriter.write(contents);
  }

  @Test
  public void toCheckHowTestNGWorksForWrongScenario() {
    log.debug("info: {}", info);
    Assert.assertNull(info);
  }

  @Test
  public void toCheckHowTestNGWorksForCorrectScenario() {
    log.debug("info: {}", info);
    Assert.assertNotNull(info);
  }

}
