package org.gwings.client.ui;

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
public class Color {

	private static final int MIN_VALUE = 0;

	private static final int MAX_VALUE = 255;

	/**
	 * The color white. In the default sRGB space.
	 */
	public final static Color WHITE = new Color(255, 255, 255);

	/**
	 * The color light gray. In the default sRGB space.
	 */
	public final static Color LIGHT_GRAY = new Color(192, 192, 192);

	/**
	 * The color gray. In the default sRGB space.
	 */
	public final static Color GRAY = new Color(128, 128, 128);

	/**
	 * The color dark gray. In the default sRGB space.
	 */
	public final static Color DARK_GRAY = new Color(64, 64, 64);

	/**
	 * The color black. In the default sRGB space.
	 */
	public final static Color BLACK = new Color(0, 0, 0);

	/**
	 * The color red. In the default sRGB space.
	 */
	public final static Color RED = new Color(255, 0, 0);

	/**
	 * The color pink. In the default sRGB space.
	 */
	public final static Color PINK = new Color(255, 175, 175);

	/**
	 * The color orange. In the default sRGB space.
	 */
	public final static Color ORANGE = new Color(255, 200, 0);

	/**
	 * The color yellow. In the default sRGB space.
	 */
	public final static Color YELLOW = new Color(255, 255, 0);

	/**
	 * The color green. In the default sRGB space.
	 */
	public final static Color GREEN = new Color(0, 255, 0);

	/**
	 * The color magenta. In the default sRGB space.
	 */
	public final static Color MAGENTA = new Color(255, 0, 255);

	/**
	 * The color cyan. In the default sRGB space.
	 */
	public final static Color CYAN = new Color(0, 255, 255);

	/**
	 * The color blue. In the default sRGB space.
	 */
	public final static Color BLUE = new Color(0, 0, 255);
	
	private int red;
	private int green;
	private int blue;
	
	public Color(int r, int g, int b) {
		setRed(r);
		setGreen(g);
		setBlue(b);
	}

	/**
	 * @return the blue
	 */
	public int getBlue() {
		return blue;
	}

	/**
	 * @param blue the blue to set
	 */
	public void setBlue(int blue) {
		if(!inTheRange(blue))
			throw new RuntimeException("Not in the range.");
		this.blue = blue;
	}

	/**
	 * @return the green
	 */
	public int getGreen() {
		return green;
	}

	/**
	 * @param green the green to set
	 */
	public void setGreen(int green) {
		if(!inTheRange(green)){
			throw new RuntimeException("Not in the range.");
		}
		this.green = green;
	}

	/**
	 * @return the red
	 */
	public int getRed() {
		return red;
	}

	/**
	 * @param red the red to set
	 */
	public void setRed(int red) {
		if(!inTheRange(red)){
			throw new RuntimeException("Not in the range.");
		}
		this.red = red;
	}
	
	private boolean inTheRange(int value) {
		return (MIN_VALUE <= value && value <= MAX_VALUE);
	}
	
	public Color brighter(){
		if(canBeBrighter()){
			return new Color(getRed()+5, getGreen()+5, getBlue()+5);
		}
		throw new RuntimeException("Can't be brighter than this.");
	}
	private boolean canBeBrighter() {
		return (getRed() < MAX_VALUE && getGreen() < MAX_VALUE && getBlue() < MAX_VALUE);
	}

	public Color darker(){
		if(canBeDarker()){
			return new Color(getRed() - 5, getGreen() - 5, getBlue() - 5);
		}
		throw new RuntimeException("Can't be darker than this.");
	}

	private boolean canBeDarker() {
		return (getRed() > MIN_VALUE && getGreen() > MIN_VALUE && getBlue() > MIN_VALUE);
	}
}
