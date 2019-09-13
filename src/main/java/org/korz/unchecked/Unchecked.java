package org.korz.unchecked;

import java.util.concurrent.Callable;

public class Unchecked {
    public static <T> T unchecked(Callable<T> f) {
        try {
            return f.call();
        }
        catch (Exception e) {
            throw UncheckedException.wrap(e);
        }
    }
}
