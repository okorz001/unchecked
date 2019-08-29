package org.korz.unchecked;

public class UncheckedException extends RuntimeException {
    public UncheckedException(Exception cause) {
        // disable our own stack trace, it adds no value
        // Throwable(Throwable) sets message to cause.toString()
        super(cause.toString(), cause, true, false);
    }
}
