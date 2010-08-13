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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.commsen.jwebthumb.WebThumb;
import com.commsen.jwebthumb.WebThumbFetchRequest;
import com.commsen.jwebthumb.WebThumbRequest;
import com.commsen.jwebthumb.WebThumbFetchRequest.Size;
import com.commsen.jwebthumb.WebThumbRequest.CustomThumbnail;
import com.commsen.jwebthumb.WebThumbRequest.Excerpt;
import com.commsen.jwebthumb.WebThumbRequest.OutputType;
import com.commsen.jwebthumb.simplexml.SimpleXmlSerializer;

/**
 * @author <a href="mailto:MilenDyankov@gmail.com">Milen Dyankov</a>
 * 
 */
public class XMLSerializationTest {

	@Test
	public void serializeWebThumbRequest() throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {
		WebThumbRequest webThumbRequest = new WebThumbRequest("http://web.site.address");
		webThumbRequest.setOutputType(OutputType.jpg);
		webThumbRequest.setCustomThumbnail(new CustomThumbnail(10, 20));
		webThumbRequest.setHeight(100);
		webThumbRequest.setWidth(200);
		webThumbRequest.setDelay(5);
		webThumbRequest.setEffect("shadow");
		webThumbRequest.setExcerpt(new Excerpt(50, 60, 70, 80));
		webThumbRequest.setFullthumb(false);
		webThumbRequest.setNotify("http://listaner.site.address");

		List<WebThumbRequest> webThumbRequests = new ArrayList<WebThumbRequest>();
		webThumbRequests.add(webThumbRequest);
		WebThumb webThumb = new WebThumb("API KEY", webThumbRequests);
		String xml = SimpleXmlSerializer.generateRequest(webThumb);

		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(xml)));
		Element webthumb = document.getDocumentElement();
		Assert.assertEquals("webthumb", webthumb.getNodeName());
		Assert.assertTrue(webthumb.hasChildNodes());

		XPath xPath = XPathFactory.newInstance().newXPath();
		Assert.assertEquals("API KEY", xPath.evaluate("/webthumb/apikey", document));
		Assert.assertEquals("http://web.site.address", xPath.evaluate("/webthumb/request/url", document));
		Assert.assertEquals("jpg", xPath.evaluate("/webthumb/request/outputType", document));
		Assert.assertEquals("200", xPath.evaluate("/webthumb/request/width", document));
		Assert.assertEquals("100", xPath.evaluate("/webthumb/request/height", document));
		Assert.assertEquals("0", xPath.evaluate("/webthumb/request/fullthumb", document));
		Assert.assertEquals("10", xPath.evaluate("/webthumb/request/customThumbnail/@width", document));
		Assert.assertEquals("20", xPath.evaluate("/webthumb/request/customThumbnail/@height", document));
		Assert.assertEquals("shadow", xPath.evaluate("/webthumb/request/effect", document));
		Assert.assertEquals("5", xPath.evaluate("/webthumb/request/delay", document));
		Assert.assertEquals("http://listaner.site.address", xPath.evaluate("/webthumb/request/notify", document));
		Assert.assertEquals("50", xPath.evaluate("/webthumb/request/excerpt/x", document));
		Assert.assertEquals("60", xPath.evaluate("/webthumb/request/excerpt/y", document));
		Assert.assertEquals("70", xPath.evaluate("/webthumb/request/excerpt/width", document));
		Assert.assertEquals("80", xPath.evaluate("/webthumb/request/excerpt/height", document));
	}


	@Test
	public void serializeWebThumbFetch() throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {
		WebThumbFetchRequest webThumbFetchRequest = new WebThumbFetchRequest("JOB ID", Size.medium2);
		WebThumb webThumb = new WebThumb("API KEY", webThumbFetchRequest);
		String xml = SimpleXmlSerializer.generateRequest(webThumb);

		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(xml)));
		Element webthumb = document.getDocumentElement();
		Assert.assertEquals("webthumb", webthumb.getNodeName());
		Assert.assertTrue(webthumb.hasChildNodes());

		XPath xPath = XPathFactory.newInstance().newXPath();
		Assert.assertEquals("API KEY", xPath.evaluate("/webthumb/apikey", document));
		Assert.assertEquals("JOB ID", xPath.evaluate("/webthumb/fetch/job", document));
		Assert.assertEquals(Size.medium2.toString(), xPath.evaluate("/webthumb/fetch/size", document));
	}


	@Test
	public void serializeWebThumbCredits() throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {
		WebThumb webThumb = WebThumb.creditsRequest("API KEY");
		String xml = SimpleXmlSerializer.generateRequest(webThumb);

		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(xml)));
		Element webthumb = document.getDocumentElement();
		Assert.assertEquals("webthumb", webthumb.getNodeName());
		Assert.assertTrue(webthumb.hasChildNodes());

		XPath xPath = XPathFactory.newInstance().newXPath();
		Assert.assertEquals("API KEY", xPath.evaluate("/webthumb/apikey", document));
		Assert.assertEquals("", xPath.evaluate("/webthumb/credits", document));
	}


	@Test
	public void serializeToOutputStream() throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {
		WebThumb webThumb = WebThumb.creditsRequest("API KEY");
		String xml = SimpleXmlSerializer.generateRequest(webThumb);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		SimpleXmlSerializer.generateRequest(webThumb, baos);
		Assert.assertEquals(baos.toString(), xml);
	}

}
