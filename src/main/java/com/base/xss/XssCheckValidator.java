package com.base.xss;

import com.base.annotation.com.XssCheck;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

public class XssCheckValidator implements ConstraintValidator<XssCheck, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		if (value == null)
			return true;

		return Jsoup.isValid(value, Safelist.none());

	}

}