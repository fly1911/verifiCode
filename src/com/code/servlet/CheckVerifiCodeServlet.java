package com.code.servlet;

import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.code.entity.ClickPoint;
import com.code.utils.GenVerifiCodeUtils;

/**
 * Servlet implementation class CheckVerifiCodeServlet
 */
public class CheckVerifiCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//x,y;x1,y1;
		String points = request.getParameter("points");
		System.out.println("校验points:" + points);
		
		
		String[] pointArr = points.split(";");
		List<ClickPoint> pointList = new ArrayList<>();
		for (String pointStr : pointArr) {
			String[] zArr = pointStr.split(",");
			pointList.add(new ClickPoint(Integer.parseInt(zArr[0]), Integer.parseInt(zArr[1])));
		}
		
		List<Rectangle> rectangles = (List<Rectangle>) request.getSession().getAttribute("rectangles");
		boolean rs = GenVerifiCodeUtils.checkClickPoint(pointList, rectangles);
		String rsStr = "0";
		if (rs) {
			rsStr = "1";
		}
		response.getWriter().print(rsStr);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
