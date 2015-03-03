package help.joker.api0;

/**
 * Equivalent to {@link java.util.function.Supplier} that can throw any {@link Throwable}.
 */
@FunctionalInterface
public interface ThrowingSupplier<T> {
    T get() throws Throwable;
}
