package com.heendy.controller;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heendy.action.Action;
import com.heendy.action.ActionFactory;
import com.heendy.action.product.ProductActionFactory;


@WebServlet("/product/*")
public class ProductController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
	    doHandle(request, response);
  }

  protected void doPost(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    doHandle(request, response);
  }
  
  protected void doHandle(HttpServletRequest request,
	  HttpServletResponse response) throws ServletException, IOException {
	   String command = request.getPathInfo();
//	   String uri = request.getRequestURI();
//	   String contextPath = request.getContextPath();
//	   String url = uri.substring(contextPath.length()); // /*.do
	   
	  System.out.println("controller : " + command);
	  	ActionFactory af = ProductActionFactory.getInstance();
	    Action action = af.getAction(command);

	    if (action != null) {
	      action.execute(request, response);
	    }
  }
  
}