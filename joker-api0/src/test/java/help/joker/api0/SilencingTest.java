package help.joker.api0;

import org.junit.Test;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;

public class SilencingTest {
    public static final String HELLO = "hello";
    public static final String WORLD = " world";
    public static final String HELLO_WORLD = HELLO + WORLD;

    @Test
    public void testRteWorking() {
        final String hello = Joker.rte(() -> HELLO);
        assertEquals(HELLO, hello);
        assertEquals(HELLO_WORLD, Joker.rte(HELLO, (string) -> string + WORLD));
    }

    @Test(expected = SilencedThrowable.class)
    public void testRteThrowingWithSupplier() {
        Joker.rte(() -> {
            throw new IOException();
        });
    }

    @Test(expected = SilencedThrowable.class)
    public void testRteThrowingWithFunction() {
        Joker.rte(HELLO, (string) -> {
            throw new IOException();
        });
    }

    @Test
    public void testSilencedLambdasWorking() {
        assertEquals(HELLO, Joker.silencedSupplier(() -> HELLO).get());
        assertEquals(HELLO_WORLD.toUpperCase(),
                Joker.silencedFunction((String string) -> string + WORLD)
                        .andThen(String::toUpperCase).apply(HELLO));

    }

    @Test(expected = SilencedThrowable.class)
    public void testSilencedSupplierThrowing() {
        Joker.silencedSupplier(() -> {
            throw new IOException();
        }).get();
    }

    @Test(expected = SilencedThrowable.class)
    public void testSilencedFunctionThrowing() {
        Joker.silencedFunction((string) -> {
            throw new IOException();
        }).apply(HELLO);
    }
}
