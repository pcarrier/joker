package help.joker.api0;

@FunctionalInterface
public interface ThrowingSupplier<T> {
    T get() throws Throwable;
}
