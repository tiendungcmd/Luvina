package Manageruser.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Manageruser.entities.MstGroupEntities;
import Manageruser.logics.MstGroupLogics;
import Manageruser.logics.impl.MstGroupLogicsImpl;
import utils.Constant;
import validates.ValidateUser;

/**
 * @author Bùi Tiến Dũng
 *
 */
@WebServlet("/" + Constant.URL_LOGIN)
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	/**
	 * Xử lý thao tác login
	 * 
	 * @param request
	 * @param response
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// TODO Auto-generated method stub

			// lay loginName
			String name = request.getParameter("loginName");
			// lay password
			String pass = request.getParameter("password");
			// khoi tao doi tuong validate
			ValidateUser validateUser = new ValidateUser();
			List<String> lstErr;

			// lay list danh sach err neu dang nhap co loi
			lstErr = validateUser.validateLogin(name, pass);
			// Kiem tra danh sach loi
			if (lstErr.isEmpty()) {
				// khoi tao session
				HttpSession session = request.getSession();
				// thiet lap gia tri session
				session.setAttribute("loginName", name);
				// set timeout cho session
				session.setMaxInactiveInterval(100);
				// chuyen den MH ADM002
				response.sendRedirect("ListUserController");

			} else {
				// dua loi vao listMessError
				request.setAttribute("listMessError", lstErr);
				// gan loginName vào req
				request.setAttribute("loginName", name);

				RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.LINK_ADM001_JSP);
				// bat dau chuyen huong
				dispatcher.forward(request, response);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
