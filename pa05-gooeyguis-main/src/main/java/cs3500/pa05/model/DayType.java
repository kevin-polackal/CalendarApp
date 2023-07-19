package cs3500.pa05.model;


/**
 * Represents different days of the week and associated numerical values
 */
public enum DayType {
  MONDAY("Monday", 1),
  TUESDAY("Tuesday", 2), WEDNESDAY("Wednesday", 3),
  THURSDAY("Thursday", 4), FRIDAY("Friday", 5),
  SATURDAY("Saturday", 6), SUNDAY("Sunday", 0);
  public final String rep;
  public final int numRep;

  DayType(String s, int numRep) {
    this.rep = s;
    this.numRep = numRep;
  }
}
