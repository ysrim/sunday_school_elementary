package net.api.enotary.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResRtnMsgVO {

	public ResRtnMsgVO() {
	}

	public ResRtnMsgVO(String txHash) {
		this.txHash = txHash;
	}

	private String txHash;

}