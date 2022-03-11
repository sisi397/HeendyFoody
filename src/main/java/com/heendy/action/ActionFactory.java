package com.heendy.action;



public interface ActionFactory {

	public abstract Action getAction(String command);
}
