package com.ht.calltree.util;

/**
 * Title: 移动端返回成功失败通用Json对象 Description: 移动端返回成功失败通用Json对象 Copyright: Copyright
 * (c) 2003 Company:Hengtian Software Ltd.
 * 
 * @author huironglou(Fyeman)
 * @version 1.0
 */
public class CommonHeadJson {
	private boolean success = true;
	private String returnCode = CalltreeConstant.RETURN_CODE_SUCCESS;
	private String message = "成功";

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static CommonHeadJson failedMessage(String message) {
		CommonHeadJson head = new CommonHeadJson();
		head.setSuccess(false);
		head.setReturnCode(CalltreeConstant.RETURN_CODE_FAILED);
		head.setMessage(message);
		return head;
	}

	public static CommonHeadJson successMessage(String message) {
		CommonHeadJson head = new CommonHeadJson();
		head.setMessage(message);
		return head;
	}

	public static CommonHeadJson successMessage() {
		return new CommonHeadJson();
	}
}
