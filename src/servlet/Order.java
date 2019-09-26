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
import com.google.gson.reflect.TypeToken;

import bean.Cart;
import bean.CartDao;
import bean.CartElement;
import bean.Check;
import bean.CheckDao;


@WebServlet("/Order")
public class Order extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * �ϰ汾��ֱ�ӽ���ȫ�����ﳵ
		 * ����ֵ��ֱ��ʹ��session�еĹ��ﳵ
		 */
		String username=(String)request.getSession().getAttribute("username");
		Cart cart=(Cart)request.getSession().getAttribute("cart");
		
		
		/*�°汾��ʹ�ù��ﳵѡ��������ҳ�����
		 * ����ֵΪjson���飬����cartElement*/
		
//		String username=(String)request.getSession().getAttribute("username");
//		String jsonArray=request.getParameter("����ǰ̨����һ������");
//		System.out.println(jsonArray);
		
		
		/*����json����*/
//		Gson gson =new Gson();
//		ArrayList<CartElement> cartElements=gson.fromJson(jsonArray,  new TypeToken<List<Check>>() {}.getType());
//		Cart cart=new Cart(cartElements);
		
		CheckDao checkDao=new CheckDao();
		
		/*�����¶���*/
		Check check=new Check(username,cart);
		int flag=checkDao.checkToSql(check);
		
		/*������ϸ��Ϣ*/
		CartDao cartDao=new CartDao();
		cartDao.orderCart(cart, username, check.getOrderID());
		
		/*��յ�ǰ���ﳵ*/
		cartDao.clean(username);
		cart.clean();
		
		System.out.println("user "+username+" has ordered something with orderid "+check.getOrderID());
		response.sendRedirect("successfulBuying.html");/*����תҳ��δ����*/
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
