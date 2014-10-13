package com.bg.phrobe.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by bg on 2014.10.13..
 */
public class UtilMethods {

    public static void setFinalStatic(Field field, Object newValue) throws Exception {
        field.setAccessible(true);

        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        field.set(null, newValue);
    }
}
