/**
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
 * Copyright 2007 Marcelo Emanoel B. Diniz <marceloemanoel AT gmail.com>, Luciano Broussal <luciano.broussal AT gmail.com>
 */
package org.gwings.client.ui;

import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

/**
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
 * @author Marcelo Emanoel, Luciano Broussal
 * @since 11/03/2007
 */
public class ColouredBox extends Widget implements ChangeListener{
	private Color color;
	private int width;
	private int height;
	private HTML div; 
	
	public ColouredBox(){
		this(Color.BLACK);
	}
	
	public ColouredBox(Color color2){
		initialize(color2);
		setupUI();
		setupStyles();
		setupListeners();
	}

	private void initialize(Color color2) {
		div = new HTML();
		setElement(div.getElement());
		color = color2;
		width = 0;
		height = 0;
	}

	private void setupUI() {
		updateColor(this.color);
	}

	/**
	 * 
	 */
	private void updateColor(Color color) {
		div.setHTML("<div style=\"background-color:"+color+";width:100%;height:100%;\"></div>");
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
		div.setHeight(height+"px");
	}
	
	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
		updateColor(color);
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
		div.setWidth(width+"px");		
	}

	private void setupStyles() {}

	private void setupListeners() {
		color.addChangeListener(this);
	}

	public void onChange(Widget sender) {
		if(sender == color){
			updateColor(color);
		}
	}
}
