package com.pstu.acdps.server;

import com.pstu.acdps.shared.exception.AnyServiceException;

public class AcdpsException extends AnyServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4154859623358999070L;
	
	public AcdpsException(String message) {
		super(message);
	}

}
