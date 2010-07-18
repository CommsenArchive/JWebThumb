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

/**
 * This class represents the xml payload of all webthumb requests. See
 * http://webthumb.bluga.net/apidoc for details.
 * 
 * @author <a href="mailto:MilenDyankov@gmail.com">Milen Dyankov</a>
 * 
 */
public class WebThumb {

	private String apikey;

	private List<WebThumbRequest> requests;

	private WebThumbFetchRequest fetchRequest;

	private WebThumbCredits credits;


	public static WebThumb creditsRequest(String apikey) {
		WebThumb webThumb = new WebThumb(apikey);
		webThumb.credits = new WebThumbCredits();
		return webThumb;
	}


	private WebThumb(String apikey) {
		this.apikey = apikey;
	}


	public WebThumb(String apikey, List<WebThumbRequest> requests) {
		this.apikey = apikey;
		this.requests = requests;
	}


	public WebThumb(String apikey, WebThumbFetchRequest fetchRequest) {
		this.apikey = apikey;
		this.fetchRequest = fetchRequest;
	}

}
