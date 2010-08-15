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

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * An abstract sevlet responsible for receiving notifications from webthumb. All extending classes
 * need to implement {@link #processThumb(String, String)} method in order to react on received
 * notification.
 * <p>
 * This servlet will handle notifications via both GET and POST methods. To prevent fake calls to
 * your servlet set <code>key</code> parameter to some hard to guess value:
 * 
 * <pre>
 * &lt;init-param&gt;
 *   &lt;param-name&gt;key&lt;/param-name&gt;
 *   &lt;param-value&gt;my_hard_to_guess_secure_key&lt;/param-value&gt;
 * &lt;/init-param&gt;
 * </pre>
 * 
 * and make sure to provide the parameter in {@link WebThumbRequest#setNotify(String)} method:
 * 
 * <pre>
 * webThumbRequest.setNotify(&quot;http://YOUR.WEB.SITE/WebThumbNotifications?key=my_hard_to_guess_secure_key&quot;);
 * </pre>
 * 
 * 
 * @see http://webthumb.bluga.net/apidoc#notify
 * 
 * @author <a href="mailto:MilenDyankov@gmail.com">Milen Dyankov</a>
 * 
 */
public abstract class WebThumbNotificationServlet extends HttpServlet {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	private static final String PARAM_ID = "id";
	private static final String PARAM_URL = "url";
	private static final String PARAM_KEY = "key";

	private String key = null;


	/*
	 * (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		key = getServletConfig().getInitParameter("key");
	}


	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}


	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}


	protected void processRequest(HttpServletRequest req, HttpServletResponse resp) {
		if (key != null && !key.equals(req.getParameter(PARAM_KEY))) {
			return;
		}
		String id = req.getParameter(PARAM_ID);
		String url = req.getParameter(PARAM_URL);
		processThumb(id, url);
	}


	/**
	 * Method called when notification is received. Extending classes should implement this method
	 * and handle notifications as desired.
	 * 
	 * @param id the job identifier
	 * @param url the url of the requested site
	 */
	public abstract void processThumb(String id, String url);
}
