package UsedByAll;

// * Created for W-xmlm by Fill on 30.03.2015.

import java.lang.reflect.Field;

public class ReflectUtils {
    public static Field getFieldRecursive(Class<?> clazz, String field) {
        try {
            return clazz.getDeclaredField(field);
        } catch (NoSuchFieldException e) {
            if (clazz.getSuperclass() != null) {
                return getFieldRecursive(clazz.getSuperclass(), field);
            }
            return null;
        }
    }
}