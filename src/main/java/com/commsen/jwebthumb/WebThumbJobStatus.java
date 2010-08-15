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

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.simpleframework.xml.ElementList;

/**
 * This class represents 'jobStatus' part of webthumb's response to "status" API call. See <a
 * href="http://webthumb.bluga.net/apidoc#status">http://webthumb.bluga.net/apidoc#status</a> for
 * details
 * 
 * @author <a href="mailto:MilenDyankov@gmail.com">Milen Dyankov</a>
 * @since 0.3
 * 
 */
public class WebThumbJobStatus {

	@ElementList(required = false, inline = true, entry = "status")
	private List<WebThumbStatus> statuses;

	@ElementList(required = false, inline = true, entry = "error")
	private List<WebThumbError> errors;


	// @Element(required = false)
	// private WebThumbError error;

	@Override
	public String toString() {
		return new ReflectionToStringBuilder(this).toString();
	}


	/**
	 * @return the statuses
	 */
	public List<WebThumbStatus> getStatuses() {
		return this.statuses;
	}


	/**
	 * @return the errors
	 */
	public List<WebThumbError> getErrors() {
		return this.errors;
	}

	/**
	 * @return the error
	 */
	// public WebThumbError getError() {
	// return this.error;
	// }

}
