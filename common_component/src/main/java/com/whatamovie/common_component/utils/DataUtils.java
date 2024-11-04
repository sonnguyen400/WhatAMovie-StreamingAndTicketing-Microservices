package com.whatamovie.common_component.utils;

import java.lang.reflect.Field;
import java.util.Arrays;

public class DataUtils {
    public static <T> T mapFieldsModifiedObject(T newObject,T oldObject,Class<T> type) {
        try{
            for(Field field : type.getDeclaredFields()){
                field.setAccessible(true);
                if(field.get(newObject)==null) continue;
                field.set(oldObject,field.get(newObject));
            }
        } catch (SecurityException|IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return oldObject;
    }
}
