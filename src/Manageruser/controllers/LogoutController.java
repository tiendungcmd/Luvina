package Manageruser.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Manageruser.properties.MessageErrorProperties;
import utils.Constant;

/**
 * @author Bùi Tiến Dũng
 *
 */
@WebServlet("/LogoutController")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub+
		//Bắt lỗi
		try {
			//Lấy ra session
			HttpSession session = request.getSession();
			//Xóa session
			session.invalidate();
			//Quay về màn hình Login
			response.sendRedirect(Constant.URL_LOGIN);
		} catch (Exception e) {
			// Lấy ra câu thông báo lỗi 
			String err = MessageErrorProperties.getValueByKey("ER015");
			//Set cây thông báo lỗi lên req
			request.setAttribute("Err", err);
			//Lấy đường dẫn forward đến
			RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.SYSTEM_ERROR_JSP);
			//Di chuyển đến màn hình system error
			dispatcher.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
