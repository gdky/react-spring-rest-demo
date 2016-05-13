package com.gdky.restfull.configuration;

public final class ApiErrors {
	
    private static final String PREFIX = "errors.";

    public static final String INVALID_REQUEST = PREFIX + "INVALID_REQUEST";
    
    public static final String DATA_ACCESS_ERROR = PREFIX + "Catch DataAccessException";
    
    private ApiErrors() {
        throw new InstantiationError( "Must not instantiate this class" );
    }
}