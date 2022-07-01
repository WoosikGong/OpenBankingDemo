package com.example.openbankpaydemo.Util;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;

public class PropertiesNamingStrategy extends PropertyNamingStrategy {
    @Override
    public String nameForField(MapperConfig<?> config, AnnotatedField field, String defaultName) {
        return convert(field.getName());
    }

    @Override
    public String nameForGetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
        return convert(method.getName());
    }

    @Override
    public String nameForSetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
        return convert(method.getName());
    }

    private String convert(String input) {
        return input.substring(3);
    }
}
