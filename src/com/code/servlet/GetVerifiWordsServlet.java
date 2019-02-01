package com.code.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetVerifiWordsServlet
 */
public class GetVerifiWordsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String[] words = (String[]) request.getSession().getAttribute("words");
		String verifiStr = "";
		for (int i = 0; i < words.length; i++) {
			if (i == 0) {
				verifiStr += words[i];
			} else {
				verifiStr += "," + words[i];
			}
		}
		System.out.println("verifiStr:" + verifiStr);
		response.getWriter().print(verifiStr);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
