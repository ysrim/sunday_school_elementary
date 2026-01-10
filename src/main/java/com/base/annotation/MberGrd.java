package com.base.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.base.enumm.MberGrdEnum;

@Target({java.lang.annotation.ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MberGrd {

	public abstract MberGrdEnum value() default MberGrdEnum.STUDENT;

}