package org.gwings.client.ui.impl;

import java.util.ArrayList;
import java.util.List;

import org.gwings.client.ui.BoundModelEvent;
import org.gwings.client.ui.BoundModelListener;
import org.gwings.client.ui.SourcesBoundModelEvents;

public class BoundModelListenerCollection implements SourcesBoundModelEvents {
	private List listeners;

	public BoundModelListenerCollection() {
		listeners = new ArrayList();
	}

	public void addBoundModelListener(BoundModelListener listener) {
		listeners.add(listener);
	}

	public void removeBoundModelListener(BoundModelListener listener) {
		listeners.remove(listener);
	}

	public void fireValueDecremented(BoundModelEvent event) {
		for (int i = 0; i < listeners.size(); i++) {
			BoundModelListener listener = (BoundModelListener) listeners.get(i);
			listener.valueDecremented();
		}
	}

	public void fireValueIncremented(BoundModelEvent event) {
		for (int i = 0; i < listeners.size(); i++) {
			BoundModelListener listener = (BoundModelListener) listeners.get(i);
			listener.valueIncremented();
		}
	}

}
