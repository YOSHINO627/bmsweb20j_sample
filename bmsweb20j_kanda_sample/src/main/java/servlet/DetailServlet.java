package servlet;

import java.io.IOException;

import bean.Book;
import dao.BookDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/detail")
public class DetailServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String error = "";
		String cmd = "";
		try {
			BookDAO daoObj = new BookDAO();
			cmd = (String) request.getParameter("cmd");
			String isbn = (String) request.getParameter("isbn");
			
			Book book = daoObj.selectByIsbn(isbn);
			
			if (book.getIsbn() == null) {
				if (cmd.equals("update")) {
					error = "更新対象の書籍が存在しない為、更新画面は表示できませんでした。";
				} else {
					error = "表示対象の書籍が存在しない為、詳細情報は表示できませんでした。";
				}
				cmd = "list";
				return;
			}
			
			request.setAttribute("book", book);
		} catch (IllegalStateException e) {
			if (cmd.equals("update")) {
				error = "DB接続エラーの為、変更画面は表示できませんでした。";
			} else {
				error = "DB接続エラーの為、削除画面は表示できませんでした。";
			}
			cmd = "logout";
		} finally {
			if (error.equals("")) {
				if (cmd.equals("detail")) {
					request.getRequestDispatcher("/view/detail.jsp").forward(request, response);
				} else {
					request.getRequestDispatcher("/view/update.jsp").forward(request, response);
				}
			} else {//エラーメッセージがあるならば
				request.setAttribute("cmd", cmd);
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}

	}

}