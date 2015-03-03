package help.joker.api0;

public class SilencedThrowable extends RuntimeException {
    SilencedThrowable(Throwable throwable) {
        super(throwable);
    }
}
