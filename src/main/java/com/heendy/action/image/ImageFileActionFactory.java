package com.heendy.action.image;

import com.heendy.action.Action;
import com.heendy.action.ActionFactory;

public class ImageFileActionFactory implements ActionFactory {

private final static ActionFactory factory = new ImageFileActionFactory();
	
	private ImageFileActionFactory() {} 
	
	public static ActionFactory getInstance() {
		return factory;
	}
	
	@Override
	public Action getAction(String command) {
		Action action = null;
		
		
		System.out.println("ImageFileActionFactory : " + command);
		if(command.equals("/upload.do")) {
			action = new UploadImageAction();
		} else if(command.equals("/product")) {
			action = new DownloadImageAction();
		}
		return action;
	}
	
	

}
