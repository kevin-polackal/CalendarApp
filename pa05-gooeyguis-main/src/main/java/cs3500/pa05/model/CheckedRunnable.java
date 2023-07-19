package cs3500.pa05.model;

/**
 * It provides a run() method that throws an exception specified by the type parameter E.
 */
@FunctionalInterface
public interface CheckedRunnable<E extends Exception> {
  void run() throws E;
}
