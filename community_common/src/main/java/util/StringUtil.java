package util;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;

public class StringUtil {
    public static boolean isNullOrBlank(String str) {
        return str == null || str.isBlank();
    }

    public static void emptyToNull(@NotNull Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getType().isAssignableFrom(String.class)) {
                try {
                    String value = (String) field.get(obj);
                    if (isNullOrBlank(value)) {
                        field.set(obj, null);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
