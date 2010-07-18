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

import java.io.InputStream;
import java.io.OutputStream;

import com.commsen.jwebthumb.xstream.WebThumbDoubleConverter;
import com.commsen.jwebthumb.xstream.WebThumbJobConverter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * This class provides static methods for Object <-> XML conversions.
 * 
 * @author <a href="mailto:MilenDyankov@gmail.com">Milen Dyankov</a>
 * 
 */
public class XStreamSerializer {

	private static final XStream requestStream = new XStream(new DomDriver());
	private static final XStream responseStream = new XStream(new DomDriver());
	static {
		requestStream.alias("webthumb", WebThumb.class);
		requestStream.addImplicitCollection(WebThumb.class, "requests");
		requestStream.aliasField("fetch", WebThumb.class, "fetchRequest");

		requestStream.alias("request", WebThumbRequest.class);
		requestStream.useAttributeFor(WebThumbRequest.CustomThumbnail.class, "width");
		requestStream.useAttributeFor(WebThumbRequest.CustomThumbnail.class, "height");

		responseStream.alias("webthumb", WebThumbResponse.class);

		responseStream.alias("job", WebThumbJob.class);
		responseStream.registerConverter(new WebThumbJobConverter());

		responseStream.alias("credits", WebThumbCredits.class);
		responseStream.aliasField("used-this-month", WebThumbCredits.class, "usedThisMonth");
		responseStream.aliasField("easythumb-cached-this-month", WebThumbCredits.class, "cachedThisMonth");
		responseStream.aliasField("requests-this-month", WebThumbCredits.class, "requestsThisMonth");
		responseStream.aliasField("subscription-left", WebThumbCredits.class, "subscriptionLeft");
		responseStream.registerConverter(new WebThumbDoubleConverter());
	}


	/**
	 * Converts known 'request' object to XML
	 * 
	 * 
	 * @param object known 'request' object
	 * @return XML representation of the object
	 */
	public static String generateRequest(Object object) {
		return requestStream.toXML(object);
	}


	/**
	 * Converts known 'request' object to XML and writes it to given output stream
	 * 
	 * @param object known 'request' object
	 * @param out the output stream to write object to
	 */
	public static void generateRequest(Object object, OutputStream out) {
		requestStream.toXML(object, out);
	}


	/**
	 * Converts XML to known 'request' object
	 * 
	 * @param <T>
	 * @param xml
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T parseRequest(String xml, Class<T> clazz) {
		return (T) requestStream.fromXML(xml);
	}


	/**
	 * 
	 * @param object
	 * @return
	 */
	public static String responseAsString(Object object) {
		return responseStream.toXML(object);
	}


	/**
	 * Converts XML to known 'response' object
	 * 
	 * @param <T>
	 * @param xml
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T readResponse(String xml, Class<T> clazz) {
		return (T) responseStream.fromXML(xml);
	}


	/**
	 * 
	 * @param <T>
	 * @param xmlInputStream
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T readResponse(InputStream xmlInputStream, Class<T> clazz) {
		return (T) responseStream.fromXML(xmlInputStream);
	}

}
