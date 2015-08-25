package com.xyz.resources.model;

/**
 * RPC处理结果
 * @author val
 *
 */
public class Result extends BaseModel {

	private boolean success;
	private String errCode;
	private String msg;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
