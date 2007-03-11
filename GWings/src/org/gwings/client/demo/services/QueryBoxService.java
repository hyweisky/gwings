package org.gwings.client.demo.services;

import java.util.List;

import org.gwings.client.demo.exception.QueryBoxSampleException;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public interface QueryBoxService extends RemoteService {
	public static class Util {
		public static QueryBoxServiceAsync getInstance() {
			QueryBoxServiceAsync instance = (QueryBoxServiceAsync) GWT.create(QueryBoxService.class);
			ServiceDefTarget target = (ServiceDefTarget) instance;
			target.setServiceEntryPoint(GWT.getModuleBaseURL() + "/queryBox");
			return instance;
		}
	}
	
	public List getMatches(String pattern) throws QueryBoxSampleException;
}
