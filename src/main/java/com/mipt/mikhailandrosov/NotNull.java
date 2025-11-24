package com.mipt.mikhailandrosov;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNull {
  String message() default "Field cannot be null";
}
