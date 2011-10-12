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

package com.commsen.jwebthumb.simplexml;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.commsen.jwebthumb.WebThumbException;

/**
 * Utility class responsible for converting objects to XML and XMLs to Objects. The class relies on
 * <a href="http://simple.sourceforge.net/">Simple XML serialization project</a>
 * 
 * 
 * @author <a href="mailto:MilenDyankov@gmail.com">Milen Dyankov</a>
 * @since 0.3
 */
public class SimpleXmlSerializer {

	private static final Logger LOGGER = Logger.getLogger(SimpleXmlSerializer.class.getName());


	/**
	 * Converts property annotated object to XML
	 * 
	 * 
	 * @param object property annotated object
	 * @return XML representation of the object
	 */
	public static String generateRequest(Object object) {
		Serializer serializer = new Persister();
		StringWriter writer = new StringWriter();
		try {
			serializer.write(object, writer);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Failed to generate XML request!", e);
			return null;
		}
		return writer.toString();
	}


	/**
	 * Converts property annotated object to XML and writes it to given stream
	 * 
	 * @param object property annotated object
	 * @param outputStream the stream to write the XML to
	 */
	public static void generateRequest(Object object, OutputStream outputStream) {
		Serializer serializer = new Persister();
		try {
			serializer.write(object, outputStream);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Failed to generate XML request!", e);
		}
	}


	/**
	 * Converts XML to appropriate object based on objects' annotations
	 * 
	 * @param <T>
	 * @param xml string containing the XML to be converted to object
	 * @param clazz the type of the return object
	 * @return
	 */
	public static <T> T parseResponse(String xml, Class<T> clazz) {
		Serializer serializer = new Persister(new JWebThumbMatcher());
		try {
			return serializer.read(clazz, xml);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Failed to parse XML response!", e);
			return null;
		}
	}


	/**
	 * Converts XML stream to appropriate object based on objects' annotations
	 * 
	 * @param <T>
	 * @param inputStream the stream to read the XML from
	 * @param clazz the type of the return object
	 * @return
	 * @throws WebThumbException 
	 */
	public static <T> T parseResponse(InputStream inputStream, Class<T> clazz) throws WebThumbException {
		Serializer serializer = new Persister(new JWebThumbMatcher());
		try {
			return serializer.read(clazz, inputStream);
		} catch (Exception e) {
			throw new WebThumbException("Failed to parse XML response!", e);
		}
	}

}
