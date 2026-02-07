package com.base.annotation.mng;

import com.base.enumm.com.MberGrdEnum;
import com.base.enumm.mng.MngNaviEnum;
import com.base.enumm.tch.TchNaviEnum;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MngMenuInfo {

    MberGrdEnum role();

    MngNaviEnum navi() default MngNaviEnum.EMPTY;

}