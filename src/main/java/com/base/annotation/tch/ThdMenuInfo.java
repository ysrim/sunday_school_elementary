package com.base.annotation.tch;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.base.enumm.com.MberGrdEnum;
import com.base.enumm.std.StdNaviEnum;
import com.base.enumm.tch.TchNaviEnum;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ThdMenuInfo {

	public abstract MberGrdEnum role();

	public abstract TchNaviEnum navi() default TchNaviEnum.EMPTY;

}