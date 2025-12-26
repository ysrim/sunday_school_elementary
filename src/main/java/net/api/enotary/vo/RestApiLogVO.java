package net.api.enotary.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestApiLogVO {

	public RestApiLogVO(String uri, String input, String output, String resCode, String procTime) {
		this.uri = uri;
		this.input = input;
		this.output = output;
		this.resCode = resCode;
		this.procTime = procTime;
	}

	private String uri = "";

	private String input = "";

	private String output = "";

	private String resCode = "";

	private String procTime = "";

}