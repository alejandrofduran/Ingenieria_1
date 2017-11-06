package service;

import java.util.Date;

public interface TimeService {

  public Date getCurrent();

  public void setTime(Date date);
}
