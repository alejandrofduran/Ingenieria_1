import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import org.junit.Test;
import service.TimeService;
import serviceImpl.TimeServiceImpl;

public class TimeServiceTest {

  private TimeService timeService;

  @Test
  public void testCreateTimeService() {
    Date date = new Date();
    timeService = new TimeServiceImpl(date);
    assertTrue(timeService.getCurrent().getTime() >= date.getTime());
  }

  @Test
  public void testCreateWithOldTime() {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DAY_OF_YEAR, -1);
    Date date = calendar.getTime();
    timeService = new TimeServiceImpl(date);
    assertTrue(timeService.getCurrent().getTime() >= date.getTime());
    assertTrue(timeService.getCurrent().getTime() < new Date().getTime());
  }

  @Test
  public void testCreateWithNewTime() {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DAY_OF_YEAR, 2);
    Date date = calendar.getTime();
    timeService = new TimeServiceImpl(date);
    assertTrue(timeService.getCurrent().getTime() >= date.getTime());
    assertTrue(timeService.getCurrent().getTime() > new Date().getTime());
  }

}
