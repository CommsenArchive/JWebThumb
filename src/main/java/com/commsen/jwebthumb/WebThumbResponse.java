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

import org.apache.commons.lang.builder.ToStringBuilder;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 * This class represent webthumb's general response object. It will contain different values for
 * different requests ("credits", "request", "status").
 * 
 * <ul>
 * <li>
 * For "requests" it will contain list of {@link WebThumbJob} in {@link #jobs} field. In this case
 * {@link #credits} and {@link #jobStatuses} will be null;
 * <li>
 * For "credits" is will have {@link #credits} initialized with {@link WebThumbCredits} object. In
 * this case {@link #jobs} and {@link #jobStatuses} will be null.
 * <li>
 * For "status" is will contain list of {@link WebThumbJobStatus} in {@link #jobStatuses} field. In
 * this case {@link #credits} and {@link #jobs} will be null.
 * </ul>
 * 
 * @author <a href="mailto:MilenDyankov@gmail.com">Milen Dyankov</a>
 * @see http://webthumb.bluga.net/apidoc#credits
 * @see http://webthumb.bluga.net/apidoc#requests
 * @see http://webthumb.bluga.net/apidoc#status
 * 
 */
@Root(name = "webthumb")
public class WebThumbResponse {

	@ElementList(required = false, name = "jobs", entry = "job", type = WebThumbJob.class)
	private List<WebThumbJob> jobs;

	@Element(required = false, name = "jobStatus")
	private WebThumbJobStatus jobStatus;

	@Element(required = false)
	private WebThumbCredits credits;

	@Element(required = false)
	private WebThumbError error;

	@ElementList(required = false, name = "errors", entry = "error", type = WebThumbError.class)
	private List<WebThumbError> errors;


	/**
	 * @return the jobs
	 */
	public List<WebThumbJob> getJobs() {
		return this.jobs;
	}


	/**
	 * @return the credits
	 */
	public WebThumbCredits getCredits() {
		return this.credits;
	}


	/**
	 * @return the error
	 */
	public WebThumbError getError() {
		return this.error;
	}


	/**
	 * @return the error
	 */
	public List<WebThumbError> getErrors() {
		return this.errors;
	}


	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder toStringBuilder = new ToStringBuilder(this);
		if (credits != null) {
			toStringBuilder.append("credits", credits);
		}
		if (jobs != null) {
			toStringBuilder.append("jobs", jobs);
		}
		if (jobStatus != null) {
			toStringBuilder.append("jobStatus", jobStatus);
		}
		if (error != null) {
			toStringBuilder.append("error", error);
		}
		if (errors != null) {
			toStringBuilder.append("errors", errors);
		}
		return toStringBuilder.toString();
	}


	/**
	 * @return the jobStatus
	 */
	public WebThumbJobStatus getJobStatus() {
		return this.jobStatus;
	}
}
