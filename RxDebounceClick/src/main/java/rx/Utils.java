package rx;

/**
 * Created by SmartDengg on 2016/5/1.
 */
public class Utils {

    static <T> T checkNotNull(T object, String message) {
        if (object == null) throw new NullPointerException(message);
        return object;
    }

}
