package com.skl.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)//申请注解放在类名上面
@Retention(RetentionPolicy.CLASS)//申明注解生命周期
public @interface BindPath {
    String value();
}
