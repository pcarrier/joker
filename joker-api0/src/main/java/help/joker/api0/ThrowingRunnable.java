package help.joker.api0;

/**
 * Equivalent to {@link Runnable} that can throw any {@link Throwable}.
 */
@FunctionalInterface
public interface ThrowingRunnable {
    void run() throws Throwable;
}
