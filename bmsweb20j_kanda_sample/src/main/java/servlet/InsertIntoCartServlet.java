package servlet;

import java.io.IOException;
import java.util.ArrayList;

import bean.Book;
import bean.Order;
import bean.User;
import dao.BookDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/insertIntoCart")
public class InsertIntoCartServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String error = "";
		String cmd = "logout";
		User user = null;
		try {
			HttpSession session = request.getSession();
			// ログインしていなかったらエラー
			user = (User) session.getAttribute("user");
			if (user == null) {
				error = "セッション切れの為、カートに追加できません。";
				return;
			}
			
			String isbn = request.getParameter("isbn");
			BookDAO bookDao = new BookDAO();
			Book book = bookDao.selectByIsbn(isbn);
			
			// 注文情報を格納
			Order order = new Order();
			order.setIsbn(isbn);
			order.setUserid(user.getUserid());
			order.setQuantity(1);
			
			// セッションからカートを取り出す、初回の場合はインスタンスを作成する
			ArrayList<Order> orderList = (ArrayList<Order>) session.getAttribute("orderList");
			if (orderList == null) {
				orderList = new ArrayList<Order>();
			}
			// カートについて
			orderList.add(order);
			
			request.setAttribute("book", book);
			// カートをセッション保存
			session.setAttribute("orderList", orderList);
		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、カートに追加はできません。";
		} finally {
			if (error.equals("")) {
				request.getRequestDispatcher("/view/insertIntoCart.jsp").forward(request, response);
			} else {
				request.setAttribute("cmd", cmd);
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}

	}

}
