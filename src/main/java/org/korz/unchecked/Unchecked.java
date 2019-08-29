package org.korz.unchecked;

import java.util.concurrent.Callable;

public class Unchecked {
    public static <T> T unchecked(Callable<T> f) {
        try {
            return f.call();
        }
        catch (Exception e) {
            throw wrap(e);
        }
    }

    private static RuntimeException wrap(Exception e) {
        if (e instanceof RuntimeException) {
            // don't wrap runtime exceptions
            return (RuntimeException) e;
        }
        if (e instanceof InterruptedException) {
            // don't suppress interrupts
            Thread.currentThread().interrupt();
        }
        return new UncheckedException(e);
    }
}
