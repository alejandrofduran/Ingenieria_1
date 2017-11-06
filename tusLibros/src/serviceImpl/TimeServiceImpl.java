package serviceImpl;

import java.util.Date;
import service.TimeService;

public class TimeServiceImpl implements TimeService {

  @Override
  public Date getCurrent() {
    return new Date();
  }
}
