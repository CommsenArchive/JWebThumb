/*
 * Copyright (c) 2010 Commsen International. All rights reserved.
 * 
 * This file is part of JWebThumb library.
 *	
 * JWebThumb library is free software: you can redistribute it and/or modify 
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 * 
 * JWebThumb library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JWebThumb library.  If not, see <http://www.gnu.org/licenses/lgpl.html>.
 */

package com.commsen.jwebthumb;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.Validate;

/**
 * This class provides convenient methods for webthumb's "request", "fetch" and "credits" API calls.
 * It does not support "status" call. Please use "notifications" instead by extending
 * {@link WebThumbNotificationServlet} and implementing
 * {@link WebThumbNotificationServlet#processThumb(String, String)} method.
 * 
 * For more details about webthumb's API please visit http://webthumb.bluga.net/apidoc
 * 
 * @author <a href="mailto:MilenDyankov@gmail.com">Milen Dyankov</a>
 * @see http://webthumb.bluga.net/apidoc
 * 
 */
public class WebThumbService {

	private static final String CONTENT_TYPE_TEXT_PLAIN = "text/plain";

	private static final String CONTENT_TYPE_TEXT_XML = "text/xml";

	private static final Logger LOGGER = Logger.getLogger(WebThumbService.class.getName());

	private static final String WEB_THUMB_URL = "http://webthumb.bluga.net/api.php";
	private String apikey;


	public WebThumbService(String apiKey) {
		if (apiKey == null) throw new IllegalArgumentException("apiKey is null!");
		this.apikey = apiKey;
	}


	/**
	 * Sends single thumbnail request to webthumb site. For more information see webthumb's request
	 * API: http://webthumb.bluga.net/apidoc#request
	 * 
	 * @param webThumbRequest object containing the request parameters
	 * @return job
	 * @throws WebThumbException if the request fails for whatever reason
	 */
	public WebThumbJob sendRequest(WebThumbRequest webThumbRequest) throws WebThumbException {
		List<WebThumbRequest> webThumbRequests = new ArrayList<WebThumbRequest>(1);
		webThumbRequests.add(webThumbRequest);
		List<WebThumbJob> results = sendRequest(webThumbRequests);
		if (results != null && !results.isEmpty()) {
			return results.get(0);
		}
		return null;
	}


	/**
	 * Sends thumbnail requests to webthumb site. For more information see webthumb's request API:
	 * http://webthumb.bluga.net/apidoc#request
	 * 
	 * @param webThumbRequests list of objects containing the request parameters
	 * @return list of jobs
	 * @throws WebThumbException if the request fails for whatever reason
	 */
	public List<WebThumbJob> sendRequest(List<WebThumbRequest> webThumbRequests) throws WebThumbException {
		Validate.notNull(webThumbRequests, "webThumbRequests is null!");
		Validate.notEmpty(webThumbRequests, "webThumbRequests is empty!");
		Validate.noNullElements(webThumbRequests, "webThumbRequests contains null elements!");

		if (LOGGER.isLoggable(Level.FINE)) {
			LOGGER.fine("Attempting to send webThumbRequests: " + webThumbRequests);
		}
		WebThumb webThumb = new WebThumb(apikey, webThumbRequests);

		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(WEB_THUMB_URL).openConnection();
			connection.setInstanceFollowRedirects(false);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");

			WebThumbXML.generateRequest(webThumb, connection.getOutputStream());

			int responseCode = connection.getResponseCode();
			String contentType = getContentType(connection);
			if (LOGGER.isLoggable(Level.FINE)) {
				LOGGER.fine("webThumbRequest sent. Got response: " + responseCode + " " + connection.getResponseMessage());
				LOGGER.fine("Content type: " + contentType);
			}

			if (responseCode == HttpURLConnection.HTTP_OK) {
				if (CONTENT_TYPE_TEXT_PLAIN.equals(contentType)) {
					throw new WebThumbException("Got error message: " + IOUtils.toString(connection.getInputStream()));
				}
				if (!CONTENT_TYPE_TEXT_XML.equals(contentType)) {
					throw new WebThumbException("Unknown content type in response: " + contentType);
				}

				WebThumbResponse webThumbResponse = WebThumbXML.readResponse(connection.getInputStream(), WebThumbResponse.class);
				if (LOGGER.isLoggable(Level.FINE)) {
					LOGGER.fine("Response processed! Returning: " + webThumbResponse.getJobs());
				}
				return webThumbResponse.getJobs();
			} else {
				throw new WebThumbException("GOT error (" + connection.getResponseCode() + ") " + connection.getResponseMessage());
			}
		} catch (MalformedURLException e) {
			throw new WebThumbException("failed to send request", e);
		} catch (IOException e) {
			throw new WebThumbException("failed to send request", e);
		}
	}


	/**
	 * Fetches single file form webthumb site. Depending on what image format was requested
	 * (jpg|png|png8) and what file size is set in {@link WebThumbFetchRequest} returned byte array
	 * will be the content of the jpg, png, png8 or zip file.
	 * 
	 * @param webThumbFetchRequest fetch request containing the job and size to be fetched
	 * @return the content of the jpg, png, png8 or zip file.
	 * @throws WebThumbException if the file can not be fetched for whatever reason
	 */
	public byte[] fetch(WebThumbFetchRequest webThumbFetchRequest) throws WebThumbException {
		if (webThumbFetchRequest == null) throw new IllegalArgumentException("webThumbFetchRequest is null!");
		if (LOGGER.isLoggable(Level.FINE)) {
			LOGGER.fine("Attempting to send webThumbFetchRequest: " + webThumbFetchRequest);
		}
		WebThumb webThumb = new WebThumb(apikey, webThumbFetchRequest);

		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(WEB_THUMB_URL).openConnection();
			connection.setInstanceFollowRedirects(false);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");

			WebThumbXML.generateRequest(webThumb, connection.getOutputStream());

			int responseCode = connection.getResponseCode();
			String contentType = getContentType(connection);
			if (LOGGER.isLoggable(Level.FINE)) {
				LOGGER.fine("webThumbFetchRequest sent. Got response: " + responseCode + " " + connection.getResponseMessage());
				LOGGER.fine("Content type: " + contentType);
			}

			if (responseCode == HttpURLConnection.HTTP_OK) {
				if (CONTENT_TYPE_TEXT_PLAIN.equals(contentType)) {
					throw new WebThumbException("Got error message: " + IOUtils.toString(connection.getInputStream()));
				}

				int contentLength = connection.getContentLength();
				if (contentLength != -1) {

					byte[] data = IOUtils.toByteArray(connection.getInputStream());
					if (data.length != contentLength) {
						throw new WebThumbException("Read " + data.length + " bytes; Expected " + contentLength + " bytes");
					}
					if (LOGGER.isLoggable(Level.FINE)) {
						LOGGER.fine("Response processed! Returning: " + data.length + " bytes of data");
					}
					return data;

				} else {
					throw new WebThumbException("Failed to fetch image! Missing content length!");
				}
			} else {
				throw new WebThumbException("GOT error (" + connection.getResponseCode() + ") " + connection.getResponseMessage());
			}

		} catch (MalformedURLException e) {
			throw new WebThumbException("failed to send request", e);
		} catch (IOException e) {
			throw new WebThumbException("failed to send request", e);
		}

	}


	/**
	 * Lets you see how many credits you've used and how many subscription/reserve credits you have
	 * left.
	 * 
	 * @return
	 * @throws WebThumbException
	 */
	public WebThumbCredits getCredits() throws WebThumbException {
		WebThumb webThumb = WebThumb.creditsRequest(apikey);
		if (LOGGER.isLoggable(Level.FINE)) {
			LOGGER.fine("Attempting to send 'credits' request!");
		}

		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(WEB_THUMB_URL).openConnection();
			connection.setInstanceFollowRedirects(false);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");

			WebThumbXML.generateRequest(webThumb, connection.getOutputStream());

			int responseCode = connection.getResponseCode();
			String contentType = getContentType(connection);
			if (LOGGER.isLoggable(Level.FINE)) {
				LOGGER.fine("'credits' request sent. Got response: " + responseCode + " " + connection.getResponseMessage());
			}

			if (responseCode == HttpURLConnection.HTTP_OK) {
				if (CONTENT_TYPE_TEXT_PLAIN.equals(contentType)) {
					throw new WebThumbException("Got error message: " + IOUtils.toString(connection.getInputStream()));
				}
				if (!CONTENT_TYPE_TEXT_XML.equals(contentType)) {
					throw new WebThumbException("Unknown content type in response: " + contentType);
				}
				WebThumbResponse webThumbResponse = WebThumbXML.readResponse(connection.getInputStream(), WebThumbResponse.class);
				if (LOGGER.isLoggable(Level.FINE)) {
					LOGGER.fine("Response processed! Returning: " + webThumbResponse.getCredits());
					LOGGER.fine("Content type: " + contentType);
				}
				return webThumbResponse.getCredits();
			} else {
				throw new WebThumbException("GOT error (" + connection.getResponseCode() + ") " + connection.getResponseMessage());
			}
		} catch (MalformedURLException e) {
			throw new WebThumbException("failed to send request", e);
		} catch (IOException e) {
			throw new WebThumbException("failed to send request", e);
		}
	}


	private String getContentType(HttpURLConnection connection) {
		String s = connection.getContentType();
		int semicolonIndex = s.indexOf(';');
		if (semicolonIndex < 0) {
			return s;
		} else {
			return s.substring(0, semicolonIndex);
		}
	}

}
