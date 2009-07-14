package org.gwings.client.ui;

import com.google.gwt.user.client.ui.Widget;

 /**
  * 
  * @author Marcelo Emanoel
  * @since 07/03/2007
  */
public interface ColumnRenderer {
	
	/**
	 * Returns a widget to render properly a determinated object.
	 * @param value The value that should be used with the renderer.
	 * @return A widget to render a value. 
	 */
	public Widget renderType(Object value);

}
