package ua.com.gup.model.xchangerate.service;

public enum HttpMethods {

	GET("GET"),
	POST("POST"),
	PUT("PUT"),
	DELETE("DELETE");

	private String method;

	private HttpMethods(String method) {
		this.method = method;
	}

    /**
     * Constants only
     */
	public String toString() {
        return method;
    }

}
