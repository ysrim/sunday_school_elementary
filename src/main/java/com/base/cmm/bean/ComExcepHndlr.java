package com.base.cmm.bean;

import org.egovframe.rte.fdl.cmmn.exception.handler.ExceptionHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ComExcepHndlr implements ExceptionHandler {

	public void occur(Exception ex, String packageName) {
		log.error(packageName, ex);
	}

}