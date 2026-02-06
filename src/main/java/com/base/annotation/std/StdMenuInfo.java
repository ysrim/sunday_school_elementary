package com.base.annotation.std;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.base.enumm.com.MberGrdEnum;
import com.base.enumm.std.StdNaviEnum;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StdMenuInfo {

	MberGrdEnum role();

	StdNaviEnum navi() default StdNaviEnum.EMPTY;

}