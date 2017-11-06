package serviceImpl;

import java.util.Calendar;
import java.util.Date;
import service.TimeService;

public class TimeServiceImpl implements TimeService {

  private Calendar calendar;

  public TimeServiceImpl(Date current) {
    calendar = Calendar.getInstance();
    calendar.setTime(current);
  }

  @Override
  public Date getCurrent() {
    return calendar.getTime();
  }

  @Override
  public void setTime(Date date) {
    this.calendar.setTime(date);
  }
}
