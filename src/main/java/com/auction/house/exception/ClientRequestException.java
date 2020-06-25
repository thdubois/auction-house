package com.auction.house.exception;

public class ClientRequestException extends Exception {
	
	private static final long serialVersionUID = -827878408428447903L;

	public ClientRequestException(String errorMessage) {
		super(errorMessage);
	}
	
}
