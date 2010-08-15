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

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;

/**
 * This class represents 'error' part of webthumb's response. See <a
 * href="http://webthumb.bluga.net/apidoc#errorhandling"
 * >http://webthumb.bluga.net/apidoc#errorhandling</a> for details
 * 
 * @author <a href="mailto:MilenDyankov@gmail.com">Milen Dyankov</a>
 * @see http://webthumb.bluga.net/apidoc#errorhandling
 * @since 0.3
 */
public class WebThumbError {

	@Text(required = false)
	private String value;

	@Attribute(required = false)
	private String type;

	@Attribute(required = false)
	private String code;

	@Attribute(required = false)
	private String url;


	/**
	 * @return the value
	 */
	public String getValue() {
		return this.value;
	}


	/**
	 * @return the type
	 */
	public String getType() {
		return this.type;
	}


	/**
	 * @return the code
	 */
	public String getCode() {
		return this.code;
	}


	/**
	 * @return the url
	 */
	public String getUrl() {
		return this.url;
	}


	@Override
	public String toString() {
		return new ReflectionToStringBuilder(this).toString();
	}

}
