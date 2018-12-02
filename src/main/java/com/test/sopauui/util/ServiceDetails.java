package com.test.sopauui.util;

import java.util.List;
import java.util.Map;

public class ServiceDetails {
	private List<String> serviceNames;
	private List<String> operationNames;
	private Map<String, String> serviceMapDetails;

	public List<String> getServiceNames() {
		return serviceNames;
	}

	public void setServiceNames(List<String> serviceNames) {
		this.serviceNames = serviceNames;
	}

	public Map<String, String> getServiceMapDetails() {
		return serviceMapDetails;
	}

	public void setServiceMapDetails(Map<String, String> serviceMapDetails) {
		this.serviceMapDetails = serviceMapDetails;
	}

	public List<String> getOperationNames() {
		return operationNames;
	}

	public void setOperationNames(List<String> operationNames) {
		this.operationNames = operationNames;
	}

}
