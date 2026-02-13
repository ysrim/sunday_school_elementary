package com.base.annotation.tch;

import com.base.enumm.com.MberGrdEnum;
import com.base.enumm.tch.TchNaviEnum;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TchMenuInfo {

	MberGrdEnum role();

	TchNaviEnum navi() default TchNaviEnum.EMPTY;

}