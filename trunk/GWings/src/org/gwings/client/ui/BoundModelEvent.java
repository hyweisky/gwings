package org.gwings.client.ui;

import org.gwings.client.ui.impl.IntegerBoundModel;

public class BoundModelEvent {

	private IntegerBoundModel source;

	public BoundModelEvent(IntegerBoundModel model) {
		this.source = model;
	}

	/**
	 * @return the source
	 */
	public IntegerBoundModel getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(IntegerBoundModel source) {
		this.source = source;
	}

}
