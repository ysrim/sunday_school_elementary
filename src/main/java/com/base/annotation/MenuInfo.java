package com.base.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.base.enumm.MberGrdEnum;
import com.base.enumm.NaviEnum;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MenuInfo {

	public abstract MberGrdEnum role();

	public abstract NaviEnum navi() default NaviEnum.EMPT;

}