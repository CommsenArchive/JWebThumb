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
import org.simpleframework.xml.Element;

/**
 * This class represent webthumb's response to "credits" API call. See
 * http://webthumb.bluga.net/apidoc#credits for details
 * 
 * @author <a href="mailto:MilenDyankov@gmail.com">Milen Dyankov</a>
 * @see http://webthumb.bluga.net/apidoc#credits
 * 
 */
public class WebThumbCredits {

	@Element(required = false, name = "requests-this-month")
	Integer requestsThisMonth;

	@Element(required = false, name = "used-this-month")
	Double usedThisMonth;

	@Element(required = false, name = "easythumb-cached-this-month")
	Double cachedThisMonth;

	@Element(required = false)
	Integer subscription;

	@Element(required = false, name = "subscription-left")
	Integer subscriptionLeft;

	@Element(required = false)
	Double reserve;


	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ReflectionToStringBuilder(this).toString();
	}


	/**
	 * @return the requestsThisMonth
	 */
	public Integer getRequestsThisMonth() {
		return this.requestsThisMonth;
	}


	/**
	 * @return the usedThisMonth
	 */
	public Double getUsedThisMonth() {
		return this.usedThisMonth;
	}


	/**
	 * @return the cachedThisMonth
	 */
	public Double getCachedThisMonth() {
		return this.cachedThisMonth;
	}


	/**
	 * @return the subscription
	 */
	public Integer getSubscription() {
		return this.subscription;
	}


	/**
	 * @return the subscriptionLeft
	 */
	public Integer getSubscriptionLeft() {
		return this.subscriptionLeft;
	}


	/**
	 * @return the reserve
	 */
	public Double getReserve() {
		return this.reserve;
	}

}
