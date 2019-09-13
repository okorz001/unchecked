package org.korz.unchecked;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.korz.unchecked.Unchecked.unchecked;

public class UncheckedTest {
    @Test
    public void noException() {
        String s = unchecked(() -> "something");
        assertEquals("something", s);
    }

    @Test
    public void checkedException() {
        Exception cause = new Exception("checked");
        try {
            unchecked(() -> { throw cause; });
            fail("no exception thrown");
        }
        catch (UncheckedException e) {
            assertEquals(cause, e.getCause());
        }
    }

    @Test
    public void uncheckedException() {
        Exception cause = new NullPointerException("unchecked");
        try {
            unchecked(() -> { throw cause; });
            fail("no exception thrown");
        }
        catch (UncheckedException e) {
            fail("wrapped a RuntimeException");
        }
        catch (RuntimeException e) {
            assertEquals(cause, e);
        }
    }
}
