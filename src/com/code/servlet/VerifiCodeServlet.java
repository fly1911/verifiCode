package com.code.servlet;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.code.entity.VerifiCodeData;
import com.code.utils.GenVerifiCodeUtils;

/**
 * Servlet implementation class VerifiCodeServlet
 */
public class VerifiCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("VerifiCodeServlet-------------------------------------");
			VerifiCodeData genImgAndData = GenVerifiCodeUtils.genImgAndData();
			BufferedImage bufferedImage = genImgAndData.getImage();
			List<Rectangle> rectangles = genImgAndData.getRectangles();
			String[] words = genImgAndData.getWords();
			
			request.getSession().setAttribute("words", words);
			request.getSession().setAttribute("rectangles", rectangles);
			ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
