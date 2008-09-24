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
 * @since 08/03/2007
 */
public class DateField implements Comparable{
	public static final DateField DATE_FIELD = new DateField(0);
	public static final DateField MONTH_FIELD = new DateField(1);
	public static final DateField YEAR_FIELD = new DateField(2);

	private int value;

	private DateField(int i) {
		this.value = i;
	}

	public int getValue() {
		return this.value;
	}

	public int compareTo(Object obj) {
		DateField other = (DateField) obj;
		Integer thisValue = new Integer(value);
		Integer otherValue = new Integer(other.getValue());
		return thisValue.compareTo(otherValue);
	}
	public boolean equals(Object obj) {
		DateField other = (DateField) obj;
		Integer thisValue = new Integer(value);
		Integer otherValue = new Integer(other.getValue());
		return thisValue.equals(otherValue);
	}
}
