package com.tsystems.bookstore.servlet;

public enum ActionType {
	
	ADD ("Add"),
	DELETE ("Delete"),
	SEARCH ("Search");

	private String type;
	
	private ActionType (String type) {
		this.type = type;
	}
	
	public String getActionType () {
		return type;
	}
	
	public boolean equals (String actionType) {
		if (type.equals(actionType)) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}
}
