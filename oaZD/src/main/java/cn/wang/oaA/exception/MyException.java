package cn.wang.oaA.exception;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class MyException extends ActionSupport{
	private Exception exception;
	public Exception getException() {
		return exception;
	}
	public void setException(Exception exception) {
		this.exception = exception;
	}
	public String execute(){
		//将获取到的异常放到栈顶
		ActionContext.getContext().getValueStack().push(exception.getMessage());
		return "error";
	}
}
