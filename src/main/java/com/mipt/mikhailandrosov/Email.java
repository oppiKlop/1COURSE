package com.mipt.mikhailandrosov;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Email {
  String message() default "Invalid email format";
}
