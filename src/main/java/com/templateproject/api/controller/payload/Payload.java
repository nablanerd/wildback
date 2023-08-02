package com.templateproject.api.controller.payload;
/**
 * *
 * @author smaile
 *
 */
public class Payload {
	private String message;
    private Object data;
 

    public Payload(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public Payload() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
