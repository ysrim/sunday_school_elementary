package com.base.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.base.enumm.MberGrdEnum;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MenuInfo {

	public abstract MberGrdEnum role() default MberGrdEnum.STD;

	public abstract int seq() default 0;

	public abstract String name() default "";

}