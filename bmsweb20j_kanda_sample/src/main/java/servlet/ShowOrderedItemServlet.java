package servlet;

import java.io.IOException;
import java.util.ArrayList;

import bean.OrderedItem;
import dao.OrderedItemDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/showOrderedItem")
public class ShowOrderedItemServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String error = "";
		String cmd = "";
		try {
			OrderedItemDAO orderItemdao = new OrderedItemDAO();
			ArrayList<OrderedItem> orderedItemList = orderItemdao.selectAll();
			
			request.setAttribute("orderedItemList", orderedItemList);
		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、購入状況確認はできません。";
			cmd = "logout";
		} finally {
			if (error.equals("")) {
				request.getRequestDispatcher("/view/showOrderedItem.jsp").forward(request, response);
			} else {
				request.setAttribute("cmd", cmd);
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}

	}

}
