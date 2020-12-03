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
			int grId;
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
			 grId = Constant.GROUP_ID_DEFAULT;
			String fullName = Constant.FULL_NAME_DEFAULT;
			String sortByFullName = Constant.SORT_FULL_NAME_DEFAULT;
			String sortByCodeLevel = Constant.SORT_NAME_LEVEL_DEFAULT;
			String sortByEndDate = Constant.SORT_END_DATE_DEFAULT;
			int ofset = Constant.OFFSET_DEFAULT;
			String sortType = Constant.SORT_TYPE_DEFAULT;
			int limit = Common.getLimit();
			int currentPage=1; 
			
			// gán thông báo lên màn hình
			MessageErrorProperties ms = new MessageErrorProperties();
			request.setAttribute("ERR", ms.getValueByKey("MSG005"));
			
			// lấy hành động từ request
			String action = request.getParameter("action");
			// kiểm tra xem có rỗng không, nếu rỗng gán giá trị action = default
			if (action == null) {
				action = "default";
			}
			switch (action) {
			case "default":	
				break;
			case "search":
				fullName = request.getParameter("name");
				String fName = tbUser.replaceWildcard(fullName);
				grId = Integer.parseInt(request.getParameter("group_id"));
				
				break;
			case "sort":
				
				sortType = request.getParameter("sortType");
				fullName = request.getParameter("fullName");
				//grId = Integer.parseInt(request.getParameter("group_id"));
			//	grId= Integer.parseInt(request.getParameter("group_id"));
				String sortValue = String.valueOf(request.getParameter("sortValue"));
				if ("full_name".equals(sortType)) {
					sortByFullName = sortValue;
					//lstUser.addAll(tbUser.getListUsers(ofset, limit, grId, fullName, sortType, sortByFullName,sortByCodeLevel, sortByEndDate));
				} else if ("code_level".equals(sortType)) {
					sortByCodeLevel = sortValue;
				//	lstUser.addAll(tbUser.getListUsers(ofset, limit, grId, fullName, sortType, sortByFullName,sortByCodeLevel, sortByEndDate));
				} else if ("end_date".equals(sortType)) {
					sortByEndDate = sortValue;
					//lstUser.addAll(tbUser.getListUsers(ofset, limit, grId, fullName, sortType, sortByFullName,sortByCodeLevel, sortByEndDate));
				}
				break;	
			case "paging":
				currentPage=Integer.parseInt(request.getParameter("currentPage"));
				sortType = request.getParameter("sortType");
				fullName = request.getParameter("fullName");
				grId = Integer.parseInt(request.getParameter("group_id"));
			
				String sortValue1 = String.valueOf(request.getParameter("sortValue"));
				if ("full_name".equals(sortType)) {
					sortByFullName = sortValue1;
				} else if ("code_level".equals(sortType)) {
					sortByCodeLevel = sortValue1;
				} else if ("end_date".equals(sortType)) {
					sortByEndDate = sortValue1;
				}
				break;		
			}
			//lay tong so user
			int totalUser=tbUser.getTotalRecords(grId,fullName);
			//kieem tra neu tong so ban ghi lon hon limit =5 moi phan trang
			if(totalUser>limit) {
				List<Integer> lstPaging = Common.getListPaging(totalUser, limit, currentPage);
				//tong so page
				int totalpage = Common.getTotalPage(totalUser, limit);
				//kiem tra nut >><<
				int beginLstPaging=lstPaging.get(0);
				int endLstPaging=lstPaging.get(lstPaging.size()-1);
				//gan len request
				request.setAttribute("beginLstPaging", beginLstPaging);
				request.setAttribute("endLstPaging", endLstPaging);
				request.setAttribute("totalPage", totalpage);
				request.setAttribute("lstPaging", lstPaging);
				
			}
			
			lstUser.addAll(tbUser.getListUsers(ofset, limit, grId, fullName, sortType, sortByFullName,sortByCodeLevel, sortByEndDate));
			request.setAttribute("fullName", fullName);
			request.setAttribute("group_id", grId);
			request.setAttribute("sortType", sortType);
			request.setAttribute("sortByFullName", sortByFullName);
			request.setAttribute("sortByCodeLevel", sortByCodeLevel);
			request.setAttribute("sortByEndDate", sortByEndDate);
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
