package help.joker.api0;

import java.util.function.Function;
import java.util.function.Supplier;

public class Joker {
    /**
     * Invoke a {@link ThrowingSupplier}; if it throws a checked {@link Throwable}, wrap it into
     * a {@link SilencedThrowable}.
     */
    public static <T> T rte(ThrowingSupplier<T> supplier) {
        try {
            return supplier.get();
        } catch (RuntimeException rte) {
            throw rte;
        } catch (Throwable throwable) {
            throw new SilencedThrowable(throwable);
        }
    }

    /**
     * Invoke a {@link ThrowingRunnable}; if it throws a checked {@link Throwable}, wrap it into
     * a {@link SilencedThrowable}.
     */
    public static void rte(ThrowingRunnable runnable) {
        try {
            runnable.run();
        } catch (RuntimeException rte) {
            throw rte;
        } catch (Throwable throwable) {
            throw new SilencedThrowable(throwable);
        }
    }

    /**
     * Transforms a {@link ThrowingSupplier} into a {@link Supplier} that invokes it, and turns
     * any thrown checked {@link Throwable} into a {@link SilencedThrowable}.
     */
    public static <T> Supplier<T> silencedSupplier(ThrowingSupplier<T> supplier) {
        return () -> {
            try {
                return supplier.get();
            } catch (RuntimeException rte) {
                throw rte;
            } catch (Throwable throwable) {
                throw new SilencedThrowable(throwable);
            }
        };
    }

    /**
     * Transforms a {@link ThrowingFunction} into a {@link Function} that invokes it, and turns
     * any thrown checked {@link Throwable} into a {@link SilencedThrowable}.
     */
    public static <T, R> Function<T, R> silencedFunction(ThrowingFunction<T, R> function) {
        return (t) -> {
            try {
                return function.apply(t);
            } catch (RuntimeException rte) {
                throw rte;
            } catch (Throwable throwable) {
                throw new SilencedThrowable(throwable);
            }
        };
    }

    /**
     * Transforms a {@link ThrowingRunnable} into a {@link Runnable} that invokes it, and turns
     * any thrown checked {@link Throwable} into a {@link SilencedThrowable}.
     */
    public static Runnable silencedRunnable(ThrowingRunnable runnable) {
        return () -> {
            try {
                runnable.run();
            } catch (RuntimeException rte) {
                throw rte;
            } catch (Throwable throwable) {
                throw new SilencedThrowable(throwable);
            }
        };
    }
}
