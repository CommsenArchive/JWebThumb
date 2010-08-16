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

import java.util.LinkedList;
import java.util.List;

import org.simpleframework.xml.ElementList;

/**
 * This class represents the payload of webthumb's 'status' API call. See <a
 * href="http://webthumb.bluga.net/apidoc#status">http://webthumb.bluga.net/apidoc#status</a> for
 * details
 * 
 * @author <a href="mailto:MilenDyankov@gmail.com">Milen Dyankov</a>
 * @since 0.3
 */
public class WebThumbStatusRequest {

	@ElementList(required = false, inline = true, entry = "url")
	private List<String> urls = null;

	@ElementList(required = false, inline = true, entry = "job")
	private List<String> jobs = null;


	/**
	 * Adds new URL to the request list
	 * 
	 * @param url the URL to add
	 */
	public void addUrl(String url) {
		if (url == null) throw new IllegalArgumentException("url can not be null");
		if (urls == null) urls = new LinkedList<String>();
		urls.add(url);
	}


	/**
	 * Adds new job id to the request list
	 * 
	 * @param url the job id to add
	 */
	public void addJob(String job) {
		if (job == null) throw new IllegalArgumentException("job can not be null");
		if (jobs == null) jobs = new LinkedList<String>();
		jobs.add(job);
	}
}
