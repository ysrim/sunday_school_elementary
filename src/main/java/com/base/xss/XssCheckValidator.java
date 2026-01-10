package com.base.xss;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

import com.base.annotation.XssCheck;

public class XssCheckValidator implements ConstraintValidator<XssCheck, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// null 값은 @NotNull 등 다른 어노테이션으로 처리한다고 가정하고 통과시킴
		if (value == null) {
			return true;
		}

		// Jsoup.isValid: 문자열에 허용되지 않은 태그가 있는지 검사
		// Safelist.none(): 모든 HTML 태그를 허용하지 않음 (Strict 모드)
		return Jsoup.isValid(value, Safelist.none());
	}
}