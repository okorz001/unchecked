package org.korz.unchecked;

public class UncheckedException extends RuntimeException {
    public static RuntimeException wrap(Exception e) {
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

    public static Exception unwrap(Exception e) {
        if (e instanceof UncheckedException) {
            // safe because our constructor requires this to be Exception
            return (Exception) e.getCause();
        }
        return e;
    }

    UncheckedException(Exception cause) {
        // disable our own stack trace, it adds no value
        // Throwable(Throwable) sets message to cause.toString()
        super(cause.toString(), cause, true, false);
    }
}
