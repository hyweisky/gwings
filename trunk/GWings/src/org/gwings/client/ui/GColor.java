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
public class GColor {

	private static final int MIN_VALUE = 0;

	private static final int MAX_VALUE = 255;

	/**
	 * The color white. In the default sRGB space.
	 */
	public final static GColor WHITE = new GColor(255, 255, 255);

	/**
	 * The color light gray. In the default sRGB space.
	 */
	public final static GColor LIGHT_GRAY = new GColor(192, 192, 192);

	/**
	 * The color gray. In the default sRGB space.
	 */
	public final static GColor GRAY = new GColor(128, 128, 128);

	/**
	 * The color dark gray. In the default sRGB space.
	 */
	public final static GColor DARK_GRAY = new GColor(64, 64, 64);

	/**
	 * The color black. In the default sRGB space.
	 */
	public final static GColor BLACK = new GColor(0, 0, 0);

	/**
	 * The color red. In the default sRGB space.
	 */
	public final static GColor RED = new GColor(255, 0, 0);

	/**
	 * The color pink. In the default sRGB space.
	 */
	public final static GColor PINK = new GColor(255, 175, 175);

	/**
	 * The color orange. In the default sRGB space.
	 */
	public final static GColor ORANGE = new GColor(255, 200, 0);

	/**
	 * The color yellow. In the default sRGB space.
	 */
	public final static GColor YELLOW = new GColor(255, 255, 0);

	/**
	 * The color green. In the default sRGB space.
	 */
	public final static GColor GREEN = new GColor(0, 255, 0);

	/**
	 * The color magenta. In the default sRGB space.
	 */
	public final static GColor MAGENTA = new GColor(255, 0, 255);

	/**
	 * The color cyan. In the default sRGB space.
	 */
	public final static GColor CYAN = new GColor(0, 255, 255);

	/**
	 * The color blue. In the default sRGB space.
	 */
	public final static GColor BLUE = new GColor(0, 0, 255);
	
	private int red;
	private int green;
	private int blue;
	
	public GColor(int r, int g, int b) {
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
	
	public GColor brighter(){
		if(canBeBrighter()){
			return new GColor(getRed()+5, getGreen()+5, getBlue()+5);
		}
		throw new RuntimeException("Can't be brighter than this.");
	}
	private boolean canBeBrighter() {
		return (getRed() < MAX_VALUE && getGreen() < MAX_VALUE && getBlue() < MAX_VALUE);
	}

	public GColor darker(){
		if(canBeDarker()){
			return new GColor(getRed() - 5, getGreen() - 5, getBlue() - 5);
		}
		throw new RuntimeException("Can't be darker than this.");
	}

	private boolean canBeDarker() {
		return (getRed() > MIN_VALUE && getGreen() > MIN_VALUE && getBlue() > MIN_VALUE);
	}
}
