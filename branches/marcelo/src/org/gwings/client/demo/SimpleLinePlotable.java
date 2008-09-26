package org.gwings.client.demo;

import java.util.Date;

import org.gwings.client.table.Plotable;

import com.google.gwt.user.client.ui.Image;
/**
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * Copyright 2007 Marcelo Emanoel B. Diniz <marceloemanoel AT gmail.com>
 *
 * @author Marcelo Emanoel
 * @since 07/03/2007
 */
public class SimpleLinePlotable implements Plotable {
    private static final long serialVersionUID = 8614765613220703585L;

    private Boolean marked;
	private Image image;
	private String name;
	private Date value;

	public SimpleLinePlotable() {
		this(Boolean.FALSE, new Image(), "",new Date());
	}

	public SimpleLinePlotable(Boolean marked, Image img, String stringValue, Date value) {
		this.marked = marked;
		this.image = img;
		this.name = stringValue;
		this.value = value;
	}

	public Object[] plot() {
		return new Object[] { marked, image, name , value};
	}

}