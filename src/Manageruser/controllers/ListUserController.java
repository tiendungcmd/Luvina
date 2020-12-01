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
import Manageruser.properties.MessageErrorProperties;
import utils.Common;
import utils.Constant;
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

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// kiem tra login
		if (Common.checkLogin(request.getSession())) {

			// khoi tao 1 list de them group vao list
			List<MstGroupEntities> lstGr = new ArrayList<>();
			MstGroupLogics mstGr = new MstGroupLogicsImpl();
			// khoi tao list User
			List<UserInfor> lstUser = new ArrayList<>();
			TblUserLogics tbUser = new TblUserLogicsImpl();

			// dua danh sách user vào request lay danh sach user
			request.setAttribute("lstUser", lstUser);

			// them nhom vao list
			lstGr.addAll(mstGr.getAllMstGroup());
			// dua vào danh sách vao request
			request.setAttribute("lstGroup", lstGr);

			// gan gia tri
			int grId = Constant.GROUP_ID_DEFAULT;
			String fullName = Constant.FULL_NAME_DEFAULT;
			String sortByFullName = Constant.SORT_FULL_NAME_DEFAULT;
			String sortByCodeLevel = Constant.SORT_NAME_LEVEL_DEFAULT;
			String sortByEndDate = Constant.SORT_END_DATE_DEFAULT;
			int ofset = Constant.OFFSET_DEFAULT;
			String sortType = Constant.SORT_TYPE_DEFAULT;
			int limit = Common.getLimit();
			// gán thông báo lên màn hình
			MessageErrorProperties ms = new MessageErrorProperties();
			request.setAttribute("ERR", ms.getValueByKey("MSG005"));

			// lấy hành động từ request
			String action = request.getParameter("action");
			// kiểm tra xem có rỗng không, nếu rỗng gán giá trị action = default
			if (action == null) {
				action = "default";
			}

			// int grId = Integer.parseInt(request.getParameter("group_id"));
			// String fullName = request.getParameter("name");
			switch (action) {
			case "default":
				lstUser.addAll(tbUser.getListUsers(ofset, limit, grId, fullName, sortType, sortByFullName,sortByCodeLevel, sortByEndDate));
				break;
			case "search":
				fullName = request.getParameter("name");
				String fName = tbUser.replaceWildcard(fullName);
				grId = Integer.parseInt(request.getParameter("group_id"));
				lstUser.addAll(tbUser.getListUsers(ofset, limit, grId, fName, sortType, sortByFullName, sortByCodeLevel,
						sortByEndDate));

				request.setAttribute("fullName", fullName);
				request.setAttribute("group_id", grId);
				// request.setAttribute(name, o);
				break;
			case "sort":
				
				String sortFullName=request.getParameter("sortFullName");
				System.out.println(sortFullName);
				lstUser.addAll(tbUser.getListUsers(ofset, limit, grId, fullName, sortType, sortByFullName,sortByCodeLevel, sortByEndDate));
				break;
			case "paging":
				break;
			default:
				break;
			}
			// hien thi man hinh adm002
			RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.LINK_ADM002_JSP);
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect(Constant.URL_LOGIN);
		}
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
