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

import com.thoughtworks.xstream.converters.basic.DoubleConverter;

/**
 * Class extends {@link DoubleConverter} to allow conversion of 'null' and empty strings to 0
 * 
 * @author <a href="mailto:MilenDyankov@gmail.com">Milen Dyankov</a>
 * 
 */
public class WebThumbDoubleConverter extends DoubleConverter {

	/*
	 * (non-Javadoc)
	 * @see com.thoughtworks.xstream.converters.basic.DoubleConverter#fromString(java.lang.String)
	 */
	@Override
	public Object fromString(String str) {
		if (str == null || str.trim().length() == 0) return 0d;
		return super.fromString(str);
	}

}
