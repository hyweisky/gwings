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
 * Copyright 2007 Marcelo Emanoel B. Diniz <marceloemanoel AT gmail.com>, Luciano Broussal <luciano.broussal AT gmail.com>
 */
package org.gwings.server.demo;

import java.util.ArrayList;
import java.util.List;

import org.gwings.client.demo.exception.QueryBoxSampleException;
import org.gwings.client.demo.services.QueryBoxService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

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
 * @author Marcelo Emanoel, Luciano Broussal
 * @since 11/03/2007
 */
public class QueryBoxServiceImpl extends RemoteServiceServlet implements
		QueryBoxService {

	private static final long serialVersionUID = 3478749403417691496L;
	private static final String[] possibleMatches = {"Java","Javascript","Virtual Machine",
										   "GWT","Garbage Collector","GWings",
										   "GWT Window Manager","GWT Widgets"};

	/* (non-Javadoc)
	 * @see org.gwings.client.demo.services.QueryBoxService#getMatches(java.lang.String)
	 */
	public List getMatches(String pattern) throws QueryBoxSampleException {
		ArrayList matches = new ArrayList();
		for(int i = 0; i < possibleMatches.length; i++){
			String value = possibleMatches[i];
			
			if(value.toLowerCase().startsWith(pattern.toLowerCase())){
				matches.add(value);
			}
		}
		if(matches.isEmpty()){
			throw new QueryBoxSampleException();
		}
		return matches;
	}

}
