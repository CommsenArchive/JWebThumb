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
package com.commsen.jwebthumb.xstream;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.commsen.jwebthumb.WebThumbJob;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * This is XStream converter responsible for marshaling and unmarshaling {@link WebThumbJob}
 * objects.
 * 
 * @author <a href="mailto:MilenDyankov@gmail.com">Milen Dyankov</a>
 * 
 */
public class WebThumbJobConverter implements Converter {

	private static final Logger LOGGER = Logger.getLogger(WebThumbJobConverter.class.getName());

	public static SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


	public void marshal(Object object, HierarchicalStreamWriter writer, MarshallingContext context) {
		WebThumbJob webThumbJob = (WebThumbJob) object;
		writer.addAttribute("estimate", Integer.toString(webThumbJob.getEstimate()));
		writer.addAttribute("cost", Integer.toString(webThumbJob.getCost()));
		writer.addAttribute("url", webThumbJob.getUrl());
		writer.addAttribute("time", timeFormat.format(webThumbJob.getTime()));
		writer.setValue(webThumbJob.getId());
	}


	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		WebThumbJob webThumbJob = new WebThumbJob();
		webThumbJob.setEstimate(Integer.parseInt(reader.getAttribute("estimate")));
		webThumbJob.setCost(Integer.parseInt(reader.getAttribute("cost")));
		webThumbJob.setUrl(reader.getAttribute("url"));
		try {
			webThumbJob.setTime(timeFormat.parse(reader.getAttribute("time")));
		} catch (ParseException e) {
			LOGGER.log(Level.WARNING, "Failed to parse date!", e);
		}
		webThumbJob.setId(reader.getValue());
		return webThumbJob;
	}


	public boolean canConvert(Class clazz) {
		return WebThumbJob.class.equals(clazz);
	}

}
