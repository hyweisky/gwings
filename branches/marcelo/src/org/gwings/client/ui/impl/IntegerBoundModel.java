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
package org.gwings.client.ui.impl;

import org.gwings.client.ui.BoundModelEvent;

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
 * @since 08/03/2007
 */
public class IntegerBoundModel extends AbstractBoundModel{
	private int start;
	private int finish;
	private int increment;
	private int value;
	private boolean limited;
	

	public IntegerBoundModel() {
		super();
		limited = true;
		start = 0;
		finish = 0;
		increment = 1;
		value = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gwings.client.ui.BoundModel#getFinish()
	 */
	public Object getFinish() {
		return new Integer(finish);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gwings.client.ui.BoundModel#getIncrement()
	 */
	public Object getIncrement() {
		return new Integer(increment);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gwings.client.ui.BoundModel#getStart()
	 */
	public Object getStart() {
		return new Integer(start);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gwings.client.ui.BoundModel#getValue()
	 */
	public Object getValue() {
		return new Integer(value);
	}

	public void decrement() {
		int newValue = value - increment;
		if (newValue >= start || !isLimited()){
			this.value = newValue;
			fireValueDecremented(new BoundModelEvent(this));
		}
	}

	public void increment() {
		int newValue = value + increment;
		if (newValue <= finish || !isLimited()) {
			this.value = newValue;
			fireValueIncremented(new BoundModelEvent(this));
		}
	}

	public void setFinish(Object finish) {
		Integer theFinish = (Integer) finish;
		this.finish = theFinish.intValue();
	}

	public void setIncrement(Object increment) {
		Integer theIncrement = (Integer) increment;
		this.increment = theIncrement.intValue();
	}

	public void setValue(Object initValue) {
		Integer theInitValue = (Integer) initValue;
		this.value = theInitValue.intValue();
	}

	public void setStart(Object start) {
		Integer theStart = (Integer) start;
		this.start = theStart.intValue();
	}

	public boolean isLimited() {
		return this.limited;
	}

	public void setLimited(boolean limited) {
		this.limited = limited;
	}

	public String formatValue() {
		return getValue().toString();
	}
}