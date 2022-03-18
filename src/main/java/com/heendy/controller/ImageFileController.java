package com.heendy.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heendy.action.Action;
import com.heendy.action.ActionFactory;
import com.heendy.action.image.ImageFileActionFactory;

/**
* @author 이승준
* image 관련 요청 Controller
*/
@WebServlet("/image/*")
public class ImageFileController extends HttpServlet {

	private final ActionFactory actionFactory = ImageFileActionFactory.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doHandle(req,res);
	}
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doHandle(req, res);
	}

	private void doHandle(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		String command = req.getPathInfo();

		Action action = this.actionFactory.getAction(command);
		action.execute(req, res);

	}
}
