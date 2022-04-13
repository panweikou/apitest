package O2O.util;

/**
 * @Author: Sean_Pan
 * @ClassName CustomerException
 * @Description TODO
 * @date 12/2/2021 11:27 AM
 * @Version 1.0
 */
public class CustomerException extends RuntimeException{

    public CustomerException () {
        super();
    }

    public CustomerException (String errorMessage) {
        super(errorMessage);
    }
}
