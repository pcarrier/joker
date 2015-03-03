package help.joker.api0;

import java.util.function.Function;
import java.util.function.Supplier;

public class Joker {
    public static <T> T rte(ThrowingSupplier<T> supplier) {
        try {
            return supplier.get();
        } catch (Throwable throwable) {
            throw new SilencedThrowable(throwable);
        }
    }

    public static <T, R> R rte(T t, ThrowingFunction<T, R> function) {
        try {
            return function.apply(t);
        } catch (Throwable throwable) {
            throw new SilencedThrowable(throwable);
        }
    }

    public static <T> Supplier<T> silencedSupplier(ThrowingSupplier<T> supplier) {
        return () -> {
            try {
                return supplier.get();
            } catch (Throwable throwable) {
                throw new SilencedThrowable(throwable);
            }
        };
    }

    public static <T, R> Function<T, R> silencedFunction(ThrowingFunction<T, R> function) {
        return (t) -> {
            try {
                return function.apply(t);
            } catch (Throwable throwable) {
                throw new SilencedThrowable(throwable);
            }
        };
    }
}
