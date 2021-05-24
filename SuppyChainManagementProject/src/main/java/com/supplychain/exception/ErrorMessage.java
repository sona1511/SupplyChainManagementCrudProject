package com.supplychain.exception;

import java.util.List;

public class ErrorMessage
{
public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<String> getDetails() {
		return details;
	}
	public void setDetails(List<String> details) {
		this.details = details;
	}
public ErrorMessage(String message, List<String> details) {
    super();
    this.message = message;
    this.details = details;
}

private String message;
private List<String> details;
}

