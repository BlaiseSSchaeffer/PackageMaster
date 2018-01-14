package com.blaise2s.packagemaster.utilities;

import java.util.Date;

import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Response.ResponseBuilder;

public class WebUtilities {
	private static final CacheControl NO_CACHE;
	private static final CacheControl CACHEABLE;
	private static final String PRAGMA_HEADER = "Pragma";
	private static final String PRAGMA_NO_CACHE_STRING = "no-cache";
	private static final Date PAST_DATE;

	static {
		NO_CACHE = new CacheControl();
		NO_CACHE.setNoStore(true);
		NO_CACHE.setNoCache(true);

		CACHEABLE = new CacheControl();
		CACHEABLE.setNoStore(false);
		CACHEABLE.setNoCache(false);

		PAST_DATE = new Date(0L);
	}

	public static void addNoCacheHeaders(ResponseBuilder builder) {
		builder.cacheControl(WebUtilities.NO_CACHE);
		builder.expires(WebUtilities.PAST_DATE);
		builder.header(WebUtilities.PRAGMA_HEADER, WebUtilities.PRAGMA_NO_CACHE_STRING);
	}

	public static void addCacheHeaders(ResponseBuilder builder) {
		builder.cacheControl(WebUtilities.CACHEABLE);
	}
}

