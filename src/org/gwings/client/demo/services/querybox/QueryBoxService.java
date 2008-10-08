package org.gwings.client.demo.services.querybox;

import java.util.List;

import org.gwings.client.demo.exception.QueryBoxSampleException;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
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
 * @author Marcelo Emanoel, Luciano Broussal
 * @since 11/03/2007
 */
public interface QueryBoxService extends RemoteService {
	public static class Util {
		public static QueryBoxServiceAsync getInstance() {
			QueryBoxServiceAsync instance = (QueryBoxServiceAsync) GWT.create(QueryBoxService.class);
			ServiceDefTarget target = (ServiceDefTarget) instance;
			target.setServiceEntryPoint(GWT.getModuleBaseURL() + "/queryBox");
			return instance;
		}
	}
	
	public List<String> getMatches(String pattern) throws QueryBoxSampleException;
}
