package domain.my.convertoplantgbot;

import lombok.extern.slf4j.Slf4j;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

@Slf4j
public class MockitoCheckerTest {

  @Test
  public void testMockitoChecker() {
    MockitoChecker mockitoChecker = Mockito.spy(MockitoChecker.class);
    Mockito.when(mockitoChecker.getDataById("1")).thenReturn("1");
    Assert.assertEquals(mockitoChecker.getDataById("1"), "1");
    Assert.assertNotNull(mockitoChecker);
  }
}
