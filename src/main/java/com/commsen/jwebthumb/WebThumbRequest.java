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

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * This class represents the payload of webthumb's 'request' API call. See
 * http://webthumb.bluga.net/apidoc#request for details
 * 
 * @author <a href="mailto:MilenDyankov@gmail.com">Milen Dyankov</a>
 * @see http://webthumb.bluga.net/apidoc#request
 * 
 */
public class WebThumbRequest implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	public static enum OutputType {
		jpg, png, png8
	};

	/**
	 * The url to snapshot
	 */
	private String url;

	/**
	 * The image output type (jpg|png|png8)
	 */
	private OutputType outputType;

	/**
	 * Width of the browser, 15 to 1280;
	 */
	private Integer width;

	/**
	 * Height of the browser, 15 to 2048
	 */
	private Integer height;

	/**
	 * Output a full sized snapshot
	 */
	private Integer fullthumb;

	/**
	 * 2 attributes width, height (1 to browser height), width (1 to browser width)
	 */
	private CustomThumbnail customThumbnail;

	/**
	 * Visual effect thumbnail to produce (mirror|dropshadow|border)
	 */
	private String effect;

	/**
	 * Wait before taking the snapshot (1 to 15 seconds, 3 second default)
	 */
	private Integer delay;

	/**
	 * Url to call when the thumbnail is complete
	 */
	private String notify;

	/**
	 * Size and offset of the excerpt thumnbnail
	 */
	private Excerpt excerpt;


	public WebThumbRequest(String url) {
		this.url = url;
	}


	public WebThumbRequest(String url, OutputType outputType) {
		this.url = url;
		this.outputType = outputType;
	}

	public static class Excerpt {
		private int x, y, width, height;


		Excerpt(int x, int y, int width, int height) {
			super();
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
		}


		@Override
		public String toString() {
			return new ToStringBuilder(this).append("x", x).append("y", y).append("width", width).append("height", height).toString();
		}

	}

	public static class CustomThumbnail {
		private int width, height;


		CustomThumbnail(int width, int height) {
			super();
			this.width = width;
			this.height = height;
		}


		@Override
		public String toString() {
			return new ToStringBuilder(this).append("width", width).append("height", height).toString();
		}
	}


	/**
	 * @return the url
	 */
	public String getUrl() {
		return this.url;
	}


	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}


	/**
	 * @return the outputType
	 */
	public OutputType getOutputType() {
		return this.outputType;
	}


	/**
	 * @param outputType the outputType to set
	 */
	public void setOutputType(OutputType outputType) {
		this.outputType = outputType;
	}


	/**
	 * @return the width
	 */
	public Integer getWidth() {
		return this.width;
	}


	/**
	 * @param width the width to set
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}


	/**
	 * @return the height
	 */
	public Integer getHeight() {
		return this.height;
	}


	/**
	 * @param height the height to set
	 */
	public void setHeight(Integer height) {
		this.height = height;
	}


	/**
	 * @return the fullthumb
	 */
	public boolean getFullthumb() {
		return this.fullthumb == 1 ? true : false;
	}


	/**
	 * @param fullthumb the fullthumb to set
	 */
	public void setFullthumb(boolean fullthumb) {
		this.fullthumb = fullthumb ? 1 : 0;
	}


	/**
	 * @return the customThumbnail
	 */
	public CustomThumbnail getCustomThumbnail() {
		return this.customThumbnail;
	}


	/**
	 * @param customThumbnail the customThumbnail to set
	 */
	public void setCustomThumbnail(CustomThumbnail customThumbnail) {
		this.customThumbnail = customThumbnail;
	}


	/**
	 * @return the effect
	 */
	public String getEffect() {
		return this.effect;
	}


	/**
	 * @param effect the effect to set
	 */
	public void setEffect(String effect) {
		this.effect = effect;
	}


	/**
	 * @return the delay
	 */
	public Integer getDelay() {
		return this.delay;
	}


	/**
	 * @param delay the delay to set
	 */
	public void setDelay(Integer delay) {
		this.delay = delay;
	}


	/**
	 * @return the notify
	 */
	public String getNotify() {
		return this.notify;
	}


	/**
	 * @param notify the notify to set
	 */
	public void setNotify(String notify) {
		this.notify = notify;
	}


	/**
	 * @return the excerpt
	 */
	public Excerpt getExcerpt() {
		return this.excerpt;
	}


	/**
	 * @param excerpt the excerpt to set
	 */
	public void setExcerpt(Excerpt excerpt) {
		this.excerpt = excerpt;
	}


	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ReflectionToStringBuilder(this).toString();
	}
}
