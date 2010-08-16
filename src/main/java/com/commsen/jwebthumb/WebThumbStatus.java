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

import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;

/**
 * This class represents 'jobStatus/status' part of webthumb's response to "status" API call. See <a
 * href="http://webthumb.bluga.net/apidoc#status">http://webthumb.bluga.net/apidoc#status</a> for
 * details
 * 
 * @author <a href="mailto:MilenDyankov@gmail.com">Milen Dyankov</a>
 * @since 0.3
 * 
 */
public class WebThumbStatus {

	@Attribute(required = false)
	private String id;

	@Attribute(required = false)
	private Date submissionTime;

	@Attribute(required = false)
	private int browserWidth;

	@Attribute(required = false)
	private int browserHeight;

	@Attribute(required = false)
	private int inProcess = 0;

	@Attribute(name = "pickup", required = false)
	private String pickupURL;

	@Attribute(required = false)
	private Date completionTime;

	@Text(required = false)
	private String value = null;


	/**
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}


	/**
	 * @return the submissionTime
	 */
	public Date getSubmissionTime() {
		return this.submissionTime == null ? null : (Date) this.submissionTime.clone();
	}


	/**
	 * @return the browserWidth
	 */
	public int getBrowserWidth() {
		return this.browserWidth;
	}


	/**
	 * @return the browserHeight
	 */
	public int getBrowserHeight() {
		return this.browserHeight;
	}


	/**
	 * @return the inProcess
	 */
	public boolean isInProcess() {
		return inProcess == 1;
	}


	/**
	 * @return the inProcess
	 */
	public boolean isCompleted() {
		return value != null && value.equals("Complete");
	}


	/**
	 * @return the pickupURL
	 */
	public String getPickupURL() {
		return this.pickupURL;
	}


	/**
	 * @return the completionTime
	 */
	public Date getCompletionTime() {
		return this.completionTime == null ? null : (Date) this.completionTime.clone();
	}


	@Override
	public String toString() {
		return new ReflectionToStringBuilder(this).toString();
	}

}
