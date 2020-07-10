package com.example.calculator.annotations;

import com.example.calculator.domains.Method;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.example.calculator.domains.Method.ADD;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Calculate {
    Method method() default ADD;
}
