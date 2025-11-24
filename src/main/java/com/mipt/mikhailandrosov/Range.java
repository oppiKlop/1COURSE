package com.mipt.mikhailandrosov;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Range {
  int min();
  int max();
  String message() default "Number must be in range [min, max]";
}
