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

@WebServlet("/showCart")
public class ShowCartServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String error = "";
		String cmd = "";
		User user = null;
		int total = 0;
		try {
			String delno = request.getParameter("delno");
			
			HttpSession session = request.getSession();
			// ログインしていなかったらエラー
			user = (User) session.getAttribute("user");
			if (user == null) {
				error = "セッション切れの為、カート状況は確認できません。";
				cmd = "logout";
				return;
			}
			
			// セッションからカートを取得
			ArrayList<Order> orderList = (ArrayList<Order>) session.getAttribute("orderList");
			// もし削除対象のデータが送られていればカートから削除
			if (delno != null) {
				orderList.remove(Integer.parseInt(delno));
			}
			
			
			BookDAO bookDao = new BookDAO();
			ArrayList<Book> bookList = new ArrayList<>();
			if (orderList != null) {
				// bookinfoからorderList(カートデータ)分だけ書籍情報を呼び出す。
				for (int i = 0; i < orderList.size(); i++) {
					Book book = bookDao.selectByIsbn(orderList.get(i).getIsbn());
					// 取得したデータをbookListに追加
					bookList.add(book);
					total += book.getPrice();
				}
			}
			
			request.setAttribute("bookList", bookList);
			request.setAttribute("total", total);
		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、カート状況は確認できません。";
			cmd = "logout";
		} finally {
			if (error.equals("")) {
				request.getRequestDispatcher("/view/showCart.jsp").forward(request, response);
			} else {//エラーなら
				request.setAttribute("cmd", cmd);
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/login.jsp").forward(request, response);
			}
		}

	}

}
