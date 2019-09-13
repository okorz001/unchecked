package org.korz.unchecked;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UncheckedExceptionTest {
    @Test
    public void noStackTrace() {
        UncheckedException e = new UncheckedException(new Exception("blah"));
        assertEquals(0, e.getStackTrace().length);
    }

    @Test
    public void wrap() {
        RuntimeException e = UncheckedException.wrap(new Exception("blah"));
        assertEquals(UncheckedException.class, e.getClass());
    }

    @Test
    public void wrapRuntime() {
        RuntimeException e = UncheckedException.wrap(new NullPointerException("blah"));
        assertEquals(NullPointerException.class, e.getClass());
    }

    @Test
    public void wrapInterrupted() {
        UncheckedException.wrap(new InterruptedException("blah"));
        assertTrue(Thread.interrupted()); // this clears interrupt
    }

    @Test
    public void unwrap() {
        Exception e = UncheckedException.unwrap(new UncheckedException(new ClassNotFoundException("blah")));
        assertEquals(ClassNotFoundException.class, e.getClass());
    }

    @Test
    public void unwrapOther() {
        Exception e = UncheckedException.unwrap(new NullPointerException("blah"));
        assertEquals(NullPointerException.class, e.getClass());
    }
}
