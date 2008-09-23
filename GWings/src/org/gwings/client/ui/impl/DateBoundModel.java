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

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

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
public class DateBoundModel extends AbstractBoundModel{
	
	private Date value;
	private Date startDate;
	private Date finishDate;
	private Integer increment;
	private DateField field;
	private boolean limited;
	
	public DateBoundModel(){
		setIncrement(new Integer(1));
		setLimited(false);
		setStart(new Date());
		setFinish(new Date());
		setValue(new Date());
		setDateField(DateField.DATE_FIELD);
	}
	
	/* (non-Javadoc)
	 * @see org.gwings.client.ui.BoundModel#decrement()
	 */
	public void decrement() {
		Date cloneDate = (Date) value.clone();
		int incrementValue = increment.intValue();
		int date = cloneDate.getDate();
		int month = cloneDate.getMonth();
		int year = cloneDate.getYear();
		
		if(field.equals(DateField.DATE_FIELD)){
			cloneDate.setDate(date + incrementValue);
			if(cloneDate.after(finishDate) || !isLimited()){
				value.setDate(value.getDate() - incrementValue);
			}
		}
		else{
			if(field.equals(DateField.MONTH_FIELD)){
				cloneDate.setMonth(month + incrementValue);
				if(cloneDate.after(finishDate) || !isLimited()){
					value.setMonth(value.getMonth() - incrementValue);
				}
			}
			else{
				cloneDate.setYear(year + incrementValue);
				if(cloneDate.after(finishDate) || !isLimited()){
					value.setYear(value.getYear() - incrementValue);
				}
			}
		}		
	}

	/* (non-Javadoc)
	 * @see org.gwings.client.ui.BoundModel#getFinish()
	 */
	public Object getFinish() {
		return this.finishDate;
	}

	/* (non-Javadoc)
	 * @see org.gwings.client.ui.BoundModel#getIncrement()
	 */
	public Object getIncrement() {
		return this.increment;
	}

	/* (non-Javadoc)
	 * @see org.gwings.client.ui.BoundModel#getStart()
	 */
	public Object getStart() {
		return this.startDate;
	}

	/* (non-Javadoc)
	 * @see org.gwings.client.ui.BoundModel#getValue()
	 */
	public Object getValue() {
		return this.value;
	}

	/* (non-Javadoc)
	 * @see org.gwings.client.ui.BoundModel#increment()
	 */
	public void increment() {
		Date cloneDate = (Date) value.clone();
		int incrementValue = increment.intValue();
		int date = cloneDate.getDate();
		int month = cloneDate.getMonth();
		int year = cloneDate.getYear();
		
		if(field.equals(DateField.DATE_FIELD)){
			cloneDate.setDate(date + incrementValue);
			if(cloneDate.before(finishDate) || !isLimited()){
				value.setDate(value.getDate() + incrementValue);
			}
		}
		else{
			if(field.equals(DateField.MONTH_FIELD)){
				cloneDate.setMonth(month + incrementValue);
				if(cloneDate.before(finishDate) || !isLimited()){
					value.setMonth(value.getMonth() + incrementValue);
				}
			}
			else{
				cloneDate.setYear(year + incrementValue);
				if(cloneDate.before(finishDate) || !isLimited()){
					value.setYear(value.getYear() + incrementValue);
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.gwings.client.ui.BoundModel#setFinish(java.lang.Object)
	 */
	public void setFinish(Object finish) {
		this.finishDate = (Date) finish;
	}

	/* (non-Javadoc)
	 * @see org.gwings.client.ui.BoundModel#setIncrement(java.lang.Object)
	 */
	public void setIncrement(Object increment) {
		this.increment = (Integer) increment;
	}

	/* (non-Javadoc)
	 * @see org.gwings.client.ui.BoundModel#setInitialValue(java.lang.Object)
	 */
	public void setValue(Object initValue) {
		this.value = (Date) initValue;
	}

	/* (non-Javadoc)
	 * @see org.gwings.client.ui.BoundModel#setStart(java.lang.Object)
	 */
	public void setStart(Object start) {
		this.startDate = (Date) start;
	}

	public boolean isLimited() {
		return this.limited;
	}

	public void setLimited(boolean limited) {
		this.limited = limited;
	}
	
	public void setDateField(DateField field){
		this.field = field;
	}
	
	public DateField getDateField(){
		return this.field;
	}

	public String formatValue() {
		DateTimeFormat fmt = DateTimeFormat.getFormat("dd/MM/yyyy");
		return fmt.format(this.value);
	}
}
