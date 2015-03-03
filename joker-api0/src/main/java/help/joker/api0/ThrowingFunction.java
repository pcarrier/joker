package help.joker.api0;

/**
 * Equivalent to {@link java.util.function.Function} that can throw any {@link Throwable}.
 */
@FunctionalInterface
public interface ThrowingFunction<T, R> {
    R apply(T t) throws Throwable;
}
