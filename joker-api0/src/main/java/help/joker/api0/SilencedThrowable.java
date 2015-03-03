package help.joker.api0;

/**
 * Unchecked ("silenced") wrapper for a checked {@link Throwable}.
 *
 * The original throwable is accessible through {@link #getCause()}.
 *
 * Please note that Joker will never use this class
 * unless a "silencing" API is explicitly called by its consumers.
 */
public class SilencedThrowable extends RuntimeException {
    SilencedThrowable(Throwable throwable) {
        super(throwable);
    }
}
