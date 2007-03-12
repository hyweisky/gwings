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

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.KeyboardListenerAdapter;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;


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
public class ColorChooser extends SimplePanel {
	
	private Slider redSlider;
	private Slider greenSlider;
	private Slider blueSlider;
	private TextBox hexValueBox;
	private ColouredBox colorSample;
	private FlexTable layout;
	
	public ColorChooser(){
		initialize();
		setupUI();
		setupStyles();
		setupListeners();
	}
	
	private void initialize() {
		layout = new FlexTable();
		colorSample = new ColouredBox();
		hexValueBox = new TextBox();
		
		redSlider = new Slider();
		greenSlider = new Slider();
		blueSlider = new Slider();
		
		hexValueBox.setMaxLength(6);
		
		redSlider.setMaxValue(new Integer(255));
		greenSlider.setMaxValue(new Integer(255));
		blueSlider.setMaxValue(new Integer(255));
		
		redSlider.setMinValue(new Integer(0));
		greenSlider.setMinValue(new Integer(0));
		blueSlider.setMinValue(new Integer(0));
		
		updateHexValue();
	}
	
	private void setupUI() {
		add(layout);
		
		layout.setWidget(0, 0, new Label("R"));
		layout.setWidget(0, 1, redSlider);
		layout.setWidget(1, 0, new Label("G"));
		layout.setWidget(1, 1, greenSlider);
		layout.setWidget(2, 0, new Label("B"));
		layout.setWidget(2, 1, blueSlider);
		layout.setWidget(0, 2, colorSample);
		layout.setWidget(2, 2, hexValueBox);
		
		layout.getFlexCellFormatter().setRowSpan(0,2,2);
		
//		layout.setBorderWidth(1);
	}
	
	private void setupStyles() {
		setStyleName("org_gwings_ColorChooser");
		layout.setStyleName("layout");
		redSlider.addStyleName("redSlider");
		greenSlider.addStyleName("greenSlider");
		blueSlider.addStyleName("blueSlider");
	}
	
	private void setupListeners() {
		redSlider.addChangeListener(new ChangeListener() {
			public void onChange(Widget sender) {
				colorSample.getColor().setRed(redSlider.getValue().intValue());
				updateHexValue();
			}
		});
		greenSlider.addChangeListener(new ChangeListener() {
			public void onChange(Widget sender) {
				colorSample.getColor().setGreen(greenSlider.getValue().intValue());
				updateHexValue();
			}
		});
		blueSlider.addChangeListener(new ChangeListener() {
			public void onChange(Widget sender) {
				colorSample.getColor().setBlue(blueSlider.getValue().intValue());
				updateHexValue();
			}
		});
		hexValueBox.addKeyboardListener(new KeyboardListenerAdapter() {
			public void onKeyUp(Widget sender, char keyCode, int modifiers) {
				if(keyCode == KEY_ENTER){
					convertHexToRGB();
				}
			}
		});
		hexValueBox.addChangeListener(new ChangeListener() {
			public void onChange(Widget sender) {
				convertHexToRGB();
			}
		});
	}
	
	public void updateHexValue(){
		String hex = "";
		hex += Integer.toHexString(redSlider.getValue().intValue());
		hex += Integer.toHexString(greenSlider.getValue().intValue());
		hex += Integer.toHexString(blueSlider.getValue().intValue());
		hexValueBox.setText(hex.toUpperCase());
	}
	
	/**
	 * @deprecated 
	 */
	public void setSize(String width, String height) {}
	
	public void setPixelSize(final int width, final int height) {
		super.setPixelSize(width, height);
		layout.setSize("100%", "100%");
		DeferredCommand.add(new Command() {
			public void execute() {
				redSlider.setPixelSize((int) ((width * 0.7) + 0.5), (int)(height / 3));
				greenSlider.setPixelSize((int) ((width * 0.7) + 0.5), (int)(height / 3));
				blueSlider.setPixelSize((int) ((width * 0.7) + 0.5), (int)(height / 3));
				colorSample.setPixelSize((int) ((width * 0.3) + 0.5), height);
				hexValueBox.setPixelSize((int) ((width * 0.3) + 0.5), hexValueBox.getOffsetHeight());
			}
		});
	}
	
	/**
	 * Gets the current color value from the color chooser.
	 * 
	 * @return
	 */
	public Color getColor() {
		return colorSample.getColor();
	}

	/**
	 * Sets the current color of the color chooser to the specified color.
	 * 
	 * @param color
	 */
	public void setColor(Color color) {
		colorSample.setColor(color);
	}

	/**
	 * Sets the current color of the color chooser to the specified RGB color.
	 * 
	 * @param r
	 * @param g
	 * @param b
	 */
	public void setColor(int r, int g, int b) {
		Color color = new Color(r,g,b);
		setColor(color);
	}

	/**
	 * 
	 */
	private void convertHexToRGB() {
		try{
			String hexString = hexValueBox.getText();
			Integer red = null;
			Integer green = null;
			Integer blue = null;
			if(hexString.length() != 3 && hexString.length() != 6){
				throw new Exception("Invalid value");
			}
			if(hexString.length() == 6){
				String r = hexString.substring(0,2);
				String g = hexString.substring(3, 4);
				String b = hexString.substring(5, 6);
				red = new Integer(Integer.parseInt(r, 16));
				green = new Integer(Integer.parseInt(g, 16));
				blue = new Integer(Integer.parseInt(b, 16));
			}
			if(hexString.length() == 3){
				String r = hexString.substring(0,1);
				String g = hexString.substring(1, 2);
				String b = hexString.substring(2, 3);
				
				r += r;
				g += g;
				b += b;
				red = new Integer(Integer.parseInt(r, 16));
				green = new Integer(Integer.parseInt(g, 16));
				blue = new Integer(Integer.parseInt(b, 16));
			}
			redSlider.setValue(red);
			greenSlider.setValue(green);
			blueSlider.setValue(blue);
			
		}
		catch(Exception e){
			String msgError = "Only valid hex values are permitted.\n" +
					"Valid values are in the range from 000000 to FFFFFF" +
					"and have 3 or 6 digits.";
			Window.alert(msgError);
			DeferredCommand.add(new Command() {
				public void execute() {
					hexValueBox.selectAll();
					hexValueBox.setFocus(true);
				}
			});
		}
	}

}
