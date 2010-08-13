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

package com.commsen.jwebthumb.test;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.commsen.jwebthumb.WebThumbCredits;
import com.commsen.jwebthumb.WebThumbException;
import com.commsen.jwebthumb.WebThumbFetchRequest;
import com.commsen.jwebthumb.WebThumbJob;
import com.commsen.jwebthumb.WebThumbRequest;
import com.commsen.jwebthumb.WebThumbService;
import com.commsen.jwebthumb.WebThumbFetchRequest.Size;
import com.commsen.jwebthumb.WebThumbRequest.CustomThumbnail;
import com.commsen.jwebthumb.WebThumbRequest.Excerpt;
import com.commsen.jwebthumb.WebThumbRequest.OutputType;

/**
 * @author <a href="mailto:MilenDyankov@gmail.com">Milen Dyankov</a>
 * 
 */
public class JWebThumbRequestsTest {

	private static String apikey = "APIKEY";

	private static WebThumbService webThumbService = null;


	@BeforeClass
	public static void setApikey() throws IOException {
		apikey = IOUtils.toString(ClassLoader.getSystemClassLoader().getResourceAsStream("apikey.txt"));
		webThumbService = new WebThumbService(apikey);
	}


	/**
	 * Test method for
	 * {@link com.commsen.jwebthumb.WebThumbService#sendRequest(com.commsen.jwebthumb.WebThumbRequest)}
	 * .
	 * 
	 * @throws WebThumbException
	 */
	@Test
	public void testSendRequestWebThumbRequest() throws WebThumbException {
		String url = "http://commsen.com";
		WebThumbJob simpleJob = webThumbService.sendRequest(new WebThumbRequest(url));
		Assert.assertNotNull(simpleJob);
		Assert.assertEquals(1, simpleJob.getCost());
		Assert.assertTrue(simpleJob.getEstimate() > 0);
		Assert.assertNotNull(simpleJob.getId());
		Assert.assertTrue(simpleJob.getId().length() == 15);
		Assert.assertNotNull(simpleJob.getTime());
		Assert.assertEquals(url, simpleJob.getUrl());

		url = "http://milen.commsen.com";
		WebThumbRequest webThumbRequest = new WebThumbRequest(url);
		webThumbRequest.setOutputType(OutputType.png);
		webThumbRequest.setCustomThumbnail(new CustomThumbnail(100, 100));
		webThumbRequest.setHeight(800);
		webThumbRequest.setWidth(600);
		webThumbRequest.setDelay(5);
		webThumbRequest.setEffect("dropshadow");
		webThumbRequest.setExcerpt(new Excerpt(0, 0, 800, 100));
		webThumbRequest.setFullthumb(true);
		WebThumbJob extendedJob = webThumbService.sendRequest(webThumbRequest);
		Assert.assertNotNull(extendedJob);
		Assert.assertEquals(2, extendedJob.getCost());
		Assert.assertTrue(extendedJob.getEstimate() > 0);
		Assert.assertNotNull(extendedJob.getId());
		Assert.assertTrue(extendedJob.getId().length() == 15);
		Assert.assertNotNull(extendedJob.getTime());
		Assert.assertEquals(url, extendedJob.getUrl());
	}


	/**
	 * Test method for
	 * {@link com.commsen.jwebthumb.WebThumbService#fetch(com.commsen.jwebthumb.WebThumbFetchRequest)}
	 * .
	 * 
	 * @throws WebThumbException
	 */
	@Test
	public void testFetch() throws WebThumbException {
		byte[] fileContent = webThumbService.fetch(new WebThumbFetchRequest("wt4c59de18347f4", Size.zip));
		Assert.assertNotNull(fileContent);
		Assert.assertTrue(fileContent.length > 0);
	}


	/**
	 * Test method for
	 * {@link com.commsen.jwebthumb.WebThumbService#fetch(com.commsen.jwebthumb.WebThumbFetchRequest)}
	 * .
	 * 
	 * @throws WebThumbException
	 */
	@Test
	public void testFetchWrongUsage() {
		try {
			webThumbService.fetch(null);
			Assert.fail("Fetch with NULL parameter didn't throw an exception!");
		} catch (Exception e) {
			Assert.assertTrue(e instanceof IllegalArgumentException);
		}

		try {
			webThumbService.fetch(new WebThumbFetchRequest("wrong job id", Size.zip));
			Assert.fail("Fetch with wrong job id didn't throw an exception!");
		} catch (WebThumbException e) {
			// This is expected
		}
	}


	/**
	 * Test method for {@link com.commsen.jwebthumb.WebThumbService#getCredits()}.
	 * 
	 * @throws WebThumbException
	 */
	@Test
	public void testGetCredits() throws WebThumbException {
		WebThumbCredits credits = webThumbService.getCredits();
		Assert.assertNotNull(credits);
		Assert.assertNotNull(credits.getCachedThisMonth());
		Assert.assertNotNull(credits.getRequestsThisMonth());
		Assert.assertNotNull(credits.getReserve());
		Assert.assertNotNull(credits.getSubscription());
		Assert.assertNotNull(credits.getSubscriptionLeft());
		Assert.assertNotNull(credits.getUsedThisMonth());
	}

}
