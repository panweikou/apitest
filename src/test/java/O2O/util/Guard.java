package O2O.util;

/**
 * @Author: Sean_Pan
 * @ClassName Guard
 * @Description TODO
 * @date 12/2/2021 7:30 PM
 * @Version 1.0
 */
public class Guard {
    public static void notBlank(Object obj, String message ) {
        if (obj == null || obj == "") {
            throw new CustomerException(message);
        }
    }

    public static void isTrue(boolean condition, String message) {
        if (!condition) {
            throw new CustomerException(message);
        }
    }
}
