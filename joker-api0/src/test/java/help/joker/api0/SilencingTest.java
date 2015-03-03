package help.joker.api0;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SilencingTest {
    private static final String HELLO = "hello";
    private static final String WORLD = " world";
    private static final String HELLO_WORLD = HELLO + WORLD;
    private static final RuntimeException RUNTIME_EXCEPTION = new ArithmeticException();
    private static final Throwable THROWABLE = new IOException();

    @Test
    public void testRteWorking() {
        final String[] mutated = {HELLO};
        Joker.rte(() -> {
            mutated[0] = WORLD;
        });
        assertEquals(WORLD, mutated[0]);
        assertEquals(HELLO, Joker.rte(() -> HELLO));
    }

    @Test(expected = SilencedThrowable.class)
    public void testRteThrowingWithSupplier() {
        Joker.<String>rte(() -> {
            throw THROWABLE;
        });
    }

    @Test(expected = SilencedThrowable.class)
    public void testRteThrowingWithRunnable() {
        Joker.rte(() -> {
            throw THROWABLE;
        });
    }

    @Test
    public void testSilencedLambdasWorking() {
        Joker.silencedRunnable(() -> {
        }).run();
        assertEquals(HELLO, Joker.silencedSupplier(() -> HELLO).get());
        assertEquals(HELLO_WORLD.toUpperCase(),
                Joker.silencedFunction((String string) -> string + WORLD)
                        .andThen(String::toUpperCase).apply(HELLO));

    }

    @Test(expected = SilencedThrowable.class)
    public void testSilencedSupplierThrowing() {
        Joker.silencedSupplier(() -> {
            throw THROWABLE;
        }).get();
    }

    @Test(expected = SilencedThrowable.class)
    public void testSilencedFunctionThrowing() {
        Joker.silencedFunction((string) -> {
            throw THROWABLE;
        }).apply(HELLO);
    }

    @Test(expected = SilencedThrowable.class)
    public void testSilencedRunnableThrowing() {
        Joker.silencedRunnable(() -> {
            throw THROWABLE;
        }).run();
    }

    @Test
    public void testPassThroughForRTEs() {
        try {
            Joker.rte(() -> {
                throw RUNTIME_EXCEPTION;
            });
            fail();
        } catch (Throwable throwable) {
            assertEquals(RUNTIME_EXCEPTION, throwable);
        }
        try {
            Joker.<String>rte(() -> {
                throw RUNTIME_EXCEPTION;
            });
            fail();
        } catch (Throwable throwable) {
            assertEquals(RUNTIME_EXCEPTION, throwable);
        }
        try {
            Joker.<String>rte(() -> {
                throw RUNTIME_EXCEPTION;
            });
            fail();
        } catch (Throwable throwable) {
            assertEquals(RUNTIME_EXCEPTION, throwable);
        }
        try {
            Joker.silencedSupplier(() -> {
                throw RUNTIME_EXCEPTION;
            }).get();
            fail();
        } catch (Throwable throwable) {
            assertEquals(RUNTIME_EXCEPTION, throwable);
        }
        try {
            Joker.silencedFunction((obj) -> {
                throw RUNTIME_EXCEPTION;
            }).apply(null);
            fail();
        } catch (Throwable throwable) {
            assertEquals(RUNTIME_EXCEPTION, throwable);
        }
        try {
            Joker.silencedRunnable(() -> {
                throw RUNTIME_EXCEPTION;
            }).run();
            fail();
        } catch (Throwable throwable) {
            assertEquals(RUNTIME_EXCEPTION, throwable);
        }
    }
}
