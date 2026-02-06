package com.base.xss;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

import com.base.annotation.XssCheck;

public class XssCheckValidator implements ConstraintValidator<XssCheck, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null) {
            return true;
        }

        return Jsoup.isValid(value, Safelist.none());
    }

}