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
 * Copyright 2007 Marcelo Emanoel B. Diniz <marceloemanoel AT gmail.com> , <luciano.broussal AT gmail.com>
 *
 * @author Marcelo Emanoel, Luciano Broussal
 * @since 07/03/2007
 */
package org.gwings.client.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.ListBox;


public class StylableListBox extends ListBox {
	
	public StylableListBox(){
		super();
	}
	
	public void setStyleName(int index, String styleName){
		 Element option = DOM.getChild(getElement(), index);
        DOM.setAttribute(option, "className", styleName);
	}
	public String getStyleName(int index){
		Element option = DOM.getChild(getElement(), index);
		return DOM.getAttribute(option, "className");
	}
}
