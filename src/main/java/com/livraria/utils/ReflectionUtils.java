package com.livraria.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public final class ReflectionUtils {
    private ReflectionUtils() {

    }
    public static void updateFields(Object existentObject, Object updateObject) throws IllegalAccessException{
        Class<?> entityClazz = existentObject.getClass();
        Class<?> updateClazz = updateObject.getClass();

        for (Field entityField : entityClazz.getDeclaredFields()) {
            int modifiers = entityClazz.getModifiers();

            if (Modifier.isStatic(modifiers) || Modifier.isFinal(modifiers)) {
                continue;
            }

            try {
                Field updateField = updateClazz.getDeclaredField(entityField.getName());
                updateField.setAccessible(true);
                entityField.setAccessible(true);
                Object value = updateField.get(updateObject);
                if (value != null) {
                    entityField.set(existentObject, value);
                }
                updateField.setAccessible(false);
            } catch (NoSuchFieldException e) {

            }
        }
    }
}