package servlet;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bean.Cart;
import bean.CartDao;
import bean.Check;
import bean.CheckDao;


@WebServlet("/CheckHistoryByUsername")
public class CheckHistoryByUsername extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*ҵ�����ݲ�*/
		String username=(String)request.getSession().getAttribute("username");
		if(username==null) response.sendRedirect("stillNotLogin.html");
		else {
		CheckDao checkDao=new CheckDao();
		List<Check> checkList=checkDao.checkHistoryByUsername(username);
		
		CartDao cartDao=new CartDao();
		List<Cart> cartList=new ArrayList<Cart>();
		
		
		for(Check check:checkList) {
			String orderId=check.getOrderID();
			cartList.add(cartDao.getCartFromOrder(orderId));
		}
		
		
		
		/*����json����*/
//		Gson gson=new Gson();
//		String ans=gson.toJson(checkList);
//		gson.fromJson(username,  new TypeToken<List<Check>>() {}.getType());// �˾�Ϊ����֮��
		
		/*���ظ�ʽ��json����
		 * ����check����������*/
		response.setContentType("text/text;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		request.setAttribute("checkList", checkList);
		request.setAttribute("cartList", cartList);
		request.getRequestDispatcher("history.jsp").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
