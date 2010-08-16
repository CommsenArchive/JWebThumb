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

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.simpleframework.xml.Element;

/**
 * This class represents the payload of webthumb's 'fetch' API call. See <a
 * href="http://webthumb.bluga.net/apidoc#fetch">http://webthumb.bluga.net/apidoc#fetch</a> for
 * details
 * 
 * @author <a href="mailto:MilenDyankov@gmail.com">Milen Dyankov</a>
 * @see http://webthumb.bluga.net/apidoc#fetch
 * 
 */
public class WebThumbFetchRequest {

	/**
	 * Enumeration contains all sizes acceptable by webthumb's fetch method For more details check
	 * webthumb API at http://webthumb.bluga.net/apidoc#fetch
	 * 
	 * @author <a href="mailto:MilenDyankov@gmail.com">Milen Dyankov</a>
	 * 
	 */
	public static enum Size {
		small, medium, medium2, large, full, excerpt, effect, custom, zip
	};

	@Element
	private String job;

	@Element
	private Size size;


	/**
	 * Constructs new fetch request to webthumb.bluga.net
	 * 
	 * @param job - the job id
	 * @param size - the size of the file
	 */
	public WebThumbFetchRequest(String job, Size size) {
		Validate.notNull(job, "job is null!");
		Validate.notNull(size, "size is null!");
		this.job = job;
		this.size = size;
	}


	@Override
	public String toString() {
		return new ToStringBuilder(this).append("job", job).append("size", size).toString();
	}

}
