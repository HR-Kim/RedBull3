package kr.co.redbull.cmn;

public class Message extends DTO {
	private String msgId;
	private String msgMsg;
	
	public Message() {}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getMsgMsg() {
		return msgMsg;
	}

	public void setMsgMsg(String msgMsg) {
		this.msgMsg = msgMsg;
	}

	@Override
	public String toString() {
		return "Message [msgId=" + msgId + ", msgMsg=" + msgMsg + "]";
	}
	
	
}
