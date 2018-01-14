package com.blaise2s.packagemaster.utilities;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Format {

	private Format() {
		// Intentionally left empty.
	}

	public static Response responseNoCache(Object o) {
		ResponseBuilder builder;
		try {
			builder = Response.ok(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(o));
			WebUtilities.addNoCacheHeaders(builder);
		} catch (JsonProcessingException e) {
			System.out.println("JSON processing failed.");
			e.printStackTrace();
			builder = Response.serverError();
		}
		return builder.build();
	}
}
