package reflection;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ReflectivObjectOperations {

    public static List<Field> getNonNullFields(Object object) {
        return Arrays.stream(object
                .getClass()
                .getDeclaredFields())
                .filter(f -> {
                    f.setAccessible(true);
                    try {

                        return f.get(object) != null;
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        throw new RuntimeException("Illegal Access");
                    }
                })
                .collect(Collectors.toList());
    }

    public static void fillFields(Object model, Object reciever) {
        List<Field> fields = getNonNullFields(model);

        fields.forEach(f -> {
            f.setAccessible(true);
            try {
                f.set(reciever, f.get(model));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new RuntimeException("Illegal Access");
            }
        });

    }

}
