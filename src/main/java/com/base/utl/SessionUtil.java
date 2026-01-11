package com.base.utl;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SessionUtil {

	/**
	 * 현재 스레드의 HttpServletRequest 객체를 가져옵니다.
	 */
	public static HttpServletRequest getRequest() {
		ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		return (attributes != null) ? attributes.getRequest() : null;
	}

	/**
	 * 현재 세션을 가져옵니다. 존재하지 않으면 null을 반환합니다.
	 */
	public static HttpSession getSession() {
		HttpServletRequest request = getRequest();
		return (request != null) ? request.getSession(false) : null;
	}

	/**
	 * 세션에 객체를 저장합니다.
	 */
	public static void setAttribute(String name, Object value) {
		HttpSession session = getRequest().getSession();
		session.setAttribute(name, value);
	}

	/**
	 * 세션에서 특정 이름의 객체를 가져옵니다.
	 */
	public static Object getAttribute(String name) {
		HttpSession session = getSession();
		return (session != null) ? session.getAttribute(name) : null;
	}

	/**
	 * 세션에서 특정 이름의 객체를 삭제합니다.
	 */
	public static void removeAttribute(String name) {
		HttpSession session = getSession();
		if (session != null) {
			session.removeAttribute(name);
		}
	}

}