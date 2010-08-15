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

import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 * This class represents the xml payload of all webthumb requests. See <a
 * href="http://webthumb.bluga.net/apidoc">http://webthumb.bluga.net/apidoc</a> for details.
 * 
 * @author <a href="mailto:MilenDyankov@gmail.com">Milen Dyankov</a>
 * @see http://webthumb.bluga.net/apidoc
 */
@Root(name = "webthumb")
public class WebThumb {

	@Element
	private String apikey;

	@Element
	private int version = 3;

	@ElementList(required = false, inline = true, entry = "request")
	private List<WebThumbRequest> requests;

	@Element(required = false, name = "fetch")
	private WebThumbFetchRequest fetchRequest;

	@Element(required = false, name = "status")
	private WebThumbStatusRequest statusRequest;

	@Element(required = false)
	private WebThumbCredits credits;


	/**
	 * Returns new credits request.
	 * 
	 * @param apikey the WebThumb's API key to use
	 * @return WebThumb credits object
	 */
	public static WebThumb creditsRequest(String apikey) {
		WebThumb webThumb = new WebThumb(apikey);
		webThumb.credits = new WebThumbCredits();
		return webThumb;
	}


	/**
	 * Creates new empty WebThumb object
	 * 
	 * @param apikey the WebThumb's API key to use
	 */
	private WebThumb(String apikey) {
		this.apikey = apikey;
	}


	/**
	 * Creates new WebThumb object from provided {@link WebThumbRequest} requests
	 * 
	 * @param apikey the WebThumb's API key to use
	 * @param requests
	 */
	public WebThumb(String apikey, List<WebThumbRequest> requests) {
		this.apikey = apikey;
		this.requests = requests;
	}


	/**
	 * Creates new WebThumb object from provided {@link WebThumbFetchRequest} request
	 * 
	 * @param apikey the WebThumb's API key to use
	 * @param fetchRequest
	 */
	public WebThumb(String apikey, WebThumbFetchRequest fetchRequest) {
		this.apikey = apikey;
		this.fetchRequest = fetchRequest;
	}


	/**
	 * Creates new WebThumb object from provided {@link WebThumbStatusRequest} request
	 * 
	 * @param apikey the WebThumb's API key to use
	 * @param statusRequest
	 */
	public WebThumb(String apikey, WebThumbStatusRequest statusRequest) {
		this.apikey = apikey;
		this.statusRequest = statusRequest;
	}
}
