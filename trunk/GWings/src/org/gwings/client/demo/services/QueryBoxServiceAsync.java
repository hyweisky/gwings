package org.gwings.client.demo.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface QueryBoxServiceAsync {
	public void getMatches(String pattern, AsyncCallback callback);
}
