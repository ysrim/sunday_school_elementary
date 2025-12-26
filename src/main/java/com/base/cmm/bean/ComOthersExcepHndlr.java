package com.base.cmm.bean;

import org.egovframe.rte.fdl.cmmn.exception.handler.ExceptionHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ComOthersExcepHndlr implements ExceptionHandler {

	public void occur(Exception exception, String packageName) {
		log.error(packageName, exception);
	}

}