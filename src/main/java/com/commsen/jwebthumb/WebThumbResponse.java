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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 * This class represent webthumb's response both "credits" and "request" API call.
 * <p>
 * For "requests" it will contain list of {@link WebThumbJob} in {@link #jobs} and {@link #credits}
 * will be null;
 * <p>
 * For "credits" is will have {@link #credits} initialized with {@link WebThumbCredits} object and
 * {@link #jobs} will be null.
 * 
 * @author <a href="mailto:MilenDyankov@gmail.com">Milen Dyankov</a>
 * @see http://webthumb.bluga.net/apidoc#credits
 * @see http://webthumb.bluga.net/apidoc#requests
 * 
 */
@Root(name = "webthumb")
public class WebThumbResponse {

	@ElementList(required = false, name = "jobs", entry = "job", type = WebThumbJob.class)
	private ArrayList<WebThumbJob> jobs;

	@Element(required = false)
	private WebThumbCredits credits;


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
		return toStringBuilder.toString();
	}
}
