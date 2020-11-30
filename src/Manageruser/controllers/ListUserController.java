package Manageruser.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Manageruser.entities.MstGroupEntities;
import Manageruser.entities.UserInfor;
import Manageruser.logics.MstGroupLogics;
import Manageruser.logics.TblUserLogics;
import Manageruser.logics.impl.MstGroupLogicsImpl;
import Manageruser.logics.impl.TblUserLogicsImpl;
import utils.Common;
import validates.ValidateUser;

/**
 * Servlet implementation class ListUserController
 */
@WebServlet("/ListUserController")
public class ListUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListUserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// khoi tao 1 list de them group vao list
		List<MstGroupEntities> lstGr = new ArrayList<>();
		MstGroupLogics mstGr = new MstGroupLogicsImpl();
		// khoi tao list User
		List<UserInfor> lstUser = new ArrayList<>();
		TblUserLogics tbUser = new TblUserLogicsImpl();
		// khoi tao
		Common cm = new Common();
		// them user vao list

		// them user vao list
		// dua danh sách user vào request lay danh sach user
		request.setAttribute("lstUser", lstUser);

		// them nhom vao list
		lstGr.addAll(mstGr.getAllMstGroup());
		// dua vào danh sách vao request
		request.setAttribute("lstGroup", lstGr);

		// lấy hành động từ request
		String action = request.getParameter("action");
		// kiểm tra xem có rỗng không, nếu rỗng gán giá trị action = default
		if (action == null) {
			action = "default";
		}

		// int grId = Integer.parseInt(request.getParameter("group_id"));
		String fullName = request.getParameter("name");
		switch (action) {
		case "default":
			int limit = Common.getLimit();
			int ofset = Common.getOffset(1, limit);
			lstUser.addAll(tbUser.getListUsers(ofset, limit, 1, "", "", "", "", ""));
			// hien thi man hinh adm002

			break;
		case "search":
			// gán giá trị từ request
			int limit1 = Common.getLimit();
			int ofset1 = Common.getOffset(1, limit1);
			// String grId = request.getParameter("group_id");
			lstUser.addAll(tbUser.getListUsers(ofset1, limit1, 1, fullName, "", "", "", ""));
			request.setAttribute("fullName", fullName);
			break;
		case "sort":
			break;
		case "paging":
			break;
		default:
			break;
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/jsp/ADM002.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

	}
}
