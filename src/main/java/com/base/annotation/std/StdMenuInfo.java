package com.base.annotation.std;

import com.base.enumm.com.MberGrdEnum;
import com.base.enumm.std.StdNaviEnum;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StdMenuInfo {

	MberGrdEnum role();

	StdNaviEnum navi() default StdNaviEnum.EMPTY;

}