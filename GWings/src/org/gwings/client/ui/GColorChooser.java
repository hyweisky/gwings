/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License. Copyright
 * 2007 Marcelo Emanoel B. Diniz <marceloemanoel AT gmail.com>
 */
package org.gwings.client.ui;


/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License. Copyright
 * 2007 Marcelo Emanoel B. Diniz <marceloemanoel AT gmail.com>
 * 
 * @author Marcelo Emanoel
 * @since 04/02/2007
 */
public interface GColorChooser {

	/**
	 * Adds a color chooser panel to the color chooser.
	 * 
	 * @param panel
	 */
	public void addChooserPanel(GColorChooserPanel panel);

	/**
	 * Gets the current color value from the color chooser.
	 * 
	 * @return
	 */
	public GColor getColor();

	/**
	 * Returns the data model that handles color selections.
	 * 
	 * @return
	 */
	public GColorSelectionModel getSelectionModel();


	/**
	 * Removes the Color Panel specified.
	 * 
	 * @param panel
	 * @return
	 */
	public GColorChooserPanel removeChooserPanel(
			GColorChooserPanel panel);

	/**
	 * Specifies the Color Panels used to choose a color value.
	 * 
	 * @param panels
	 */
	public void setChooserPanels(GColorChooserPanel[] panels);

	/**
	 * Sets the current color of the color chooser to the specified color.
	 * 
	 * @param color
	 */
	public void setColor(GColor color);

	/**
	 * Sets the current color of the color chooser to the specified RGB color.
	 * 
	 * @param r
	 * @param g
	 * @param b
	 */
	public void setColor(int r, int g, int b);

}
