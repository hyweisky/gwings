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
 */
package org.gwings.client.ui.impl;

import org.gwings.client.ui.GColor;
import org.gwings.client.ui.GColorChooser;
import org.gwings.client.ui.GColorChooserPanel;
import org.gwings.client.ui.GColorSelectionModel;

import com.google.gwt.user.client.ui.Composite;

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
 * @author Marcelo Emanoel
 * @since 04/02/2007
 */
public class DefaultGColorChooser extends Composite implements GColorChooser {
	
	private GColor color;
	
	public DefaultGColorChooser(){
		
	}
	public DefaultGColorChooser(GColor color){
		
	}
	
	public void addChooserPanel(GColorChooserPanel panel) {}

	public GColor getColor() {
		return null;
	}

	public GColorSelectionModel getSelectionModel() {
		return null;
	}

	public GColorChooserPanel removeChooserPanel(GColorChooserPanel panel) {
		return null;
	}

	public void setChooserPanels(GColorChooserPanel[] panels) {}

	public void setColor(GColor color) {}

	public void setColor(int r, int g, int b) {}

}
