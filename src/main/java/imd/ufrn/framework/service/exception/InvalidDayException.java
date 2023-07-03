package imd.ufrn.framework.service.exception;

public class InvalidDayException extends RuntimeException {
  public InvalidDayException() {
    super("Invalid DayOfWeek. Valid interval: [1,7]. 1 is Monday and 7 is Sunday.");
  }
}
