package org.gwings.client.ui;

import com.google.gwt.user.client.ui.Widget;
/*
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
 */

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
