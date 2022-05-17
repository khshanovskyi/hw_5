package hw;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * A generic comparator that is comparing a random field of the given class. The field is either primitive or
 * {@link Comparable}. It is chosen during comparator instance creation and is used for all comparisons.
 * <p>
 * By default it compares only accessible fields, but this can be configured via a constructor property. If no field is
 * available to compare, the constructor throws {@link IllegalArgumentException}
 *
 * @param <T> the type of the objects that may be compared by this comparator
 */
public class RandomFieldComparator<T> implements Comparator<T> {

    private Class<T> clazz;
    private Field randomField;
    private boolean compareOnlyAccessibleFields;

    public RandomFieldComparator(Class<T> targetType) {
        this(targetType, true);
    }

    /**
     * A constructor that accepts a class and a property indicating which fields can be used for comparison. If property
     * value is true, then only public fields or fields with public getters can be used.
     *
     * @param targetType                  a type of objects that may be compared
     * @param compareOnlyAccessibleFields config property indicating if only publicly accessible fields can be used
     */
    public RandomFieldComparator(Class<T> targetType, boolean compareOnlyAccessibleFields) {
        clazz = targetType;
        this.compareOnlyAccessibleFields = compareOnlyAccessibleFields;
        configureRandomField();
        randomField.setAccessible(true);
    }

    /**
     * Compares two objects of the class T by the value of the field that was randomly chosen. It allows null values
     * for the fields, and it treats null value grater than a non-null value (nulls last).
     */
    @Override
    public int compare(T o1, T o2) {
        try {
            Comparable first = (Comparable) randomField.get(o1);
            Comparable second = (Comparable) randomField.get(o2);

            if (first == null && second == null) {
                return 0;
            } else if (first == null) {
                return 1;
            } else if (second == null) {
                return -1;
            }
            return first.compareTo(second);
        } catch (IllegalAccessException e) {
            throw new RuntimeException();
        }
    }

    /**
     * Returns a statement "Random field comparator of class '%s' is comparing '%s'" where the first param is the name
     * of the type T, and the second parameter is the comparing field name.
     *
     * @return a predefined statement
     */
    @Override
    public String toString() {
        return "Random field comparator of class '" + clazz.getSimpleName() + "' is comparing '" + randomField.getName() + "'";
    }


    private void configureRandomField() {
        Objects.requireNonNull(clazz, "The [clazz] should be not null");
        List<Field> fields;
        if (compareOnlyAccessibleFields) {
            fields = getPublicFieldsOrFieldsWithGetter(getGetters(), List.of(clazz.getFields()));
        } else {
            fields = List.of(clazz.getDeclaredFields());
        }
        randomField = fields.get(new Random().nextInt(fields.size()));
    }

    private List<Method> getGetters() {
        Objects.requireNonNull(clazz, "The [clazz] should be not null");
        return Arrays.stream(clazz.getMethods())
                .filter(method -> isGetter(method) || isGetterForBoolean(method))
                .toList();
    }

    private boolean isGetter(Method method) {
        Objects.requireNonNull(method, "The [method] should be not null");
        return method.getName().startsWith("get");
    }

    private boolean isGetterForBoolean(Method method) {
        Objects.requireNonNull(method, "The [method] should be not null");
        return method.getName().startsWith("is");
    }

    private List<Field> getPublicFieldsOrFieldsWithGetter(List<Method> getters, List<Field> publicFields) {
        Objects.requireNonNull(clazz, "The [clazz] should be not null");
        Objects.requireNonNull(getters, "The [getters] should be not null");

        Set<Field> result = new HashSet<>();
        if (Objects.nonNull(publicFields) && !publicFields.isEmpty()) {
            result.addAll(publicFields);
        }

        List<Field> allFields = List.of(clazz.getDeclaredFields());
        for (Method getter : getters) {
            String name = getSubStringOfNameFromGetter(getter);
            for (Field field : allFields) {
                if (field.getName().equals(name)) {
                    result.add(field);
                }
            }
        }
        return result.stream().toList();
    }

    private String getSubStringOfNameFromGetter(Method getter) {
        String name;
        if (isGetter(getter)) {
            name = getter.getName().substring(3);
        } else {
            name = getter.getName().substring(2);
        }
        return name.substring(0, 1).toLowerCase() + name.substring(1);
    }
}
