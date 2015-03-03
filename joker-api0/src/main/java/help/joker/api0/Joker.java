package help.joker.api0;

import java.util.function.Function;
import java.util.function.Supplier;

public class Joker {
    public static <T> T rte(ThrowingSupplier<T> lambda) {
        try {
            return lambda.get();
        } catch (Throwable throwable) {
            throw new SilencedThrowable(throwable);
        }
    }

    public static <T, R> R rte(T t, ThrowingFunction<T, R> lambda) {
        try {
            return lambda.apply(t);
        } catch (Throwable throwable) {
            throw new SilencedThrowable(throwable);
        }
    }

    public static <T> Supplier<T> silencedSupplier(ThrowingSupplier<T> lambda) {
        return () -> {
            try {
                return lambda.get();
            } catch (Throwable throwable) {
                throw new SilencedThrowable(throwable);
            }
        };
    }

    public static <T, R> Function<T, R> silencedFunction(ThrowingFunction<T, R> lambda) {
        return (t) -> {
            try {
                return lambda.apply(t);
            } catch (Throwable throwable) {
                throw new SilencedThrowable(throwable);
            }
        };
    }
}
