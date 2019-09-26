package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.Connecter;

/**
 * Servlet implementation class RemoveFromLike
 */
@WebServlet("/RemoveFromLike")
public class RemoveFromFollowing extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connecter connecter=new Connecter();
		int flag=0;
		String username=(String) request.getSession().getAttribute("username");
		/*������Ʒʱ��keyΪcommodity*/
		String commodityName=request.getParameter("commodity");
		String check ="Select from like where username = "+username+" and commodityname = "+commodityName+" ;";
		if( !connecter.query(check).isEmpty()){
			/*���ڿ���*/
			String remove="";
			flag=connecter.delete(remove);
		}
		else {
			/*���ڿ���*/
			flag = -2;
		}
		response.getWriter().append(Integer.toString(flag));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
