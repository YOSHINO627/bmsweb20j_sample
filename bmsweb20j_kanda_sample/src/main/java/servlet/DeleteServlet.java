package servlet;

import java.io.IOException;

import dao.BookDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String error = "";
		String cmd = "list";
		try {
			BookDAO daoObj = new BookDAO();
			String isbn = request.getParameter("isbn");

			if (daoObj.selectByIsbn(isbn).getIsbn() == null) {
				error = "削除対象の書籍が存在しない為、書籍削除処理は行えませんでした。";
				return;
			}
			
			daoObj.delete(isbn);
		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、書籍削除処理を行えませんでした。";
			cmd = "logout";
		} finally {
			if (error.equals("")) {
				request.getRequestDispatcher("/list").forward(request, response);
			} else {//エラーメッセージがあるならば（エラーならば）
				request.setAttribute("cmd", cmd);
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}

	}

}
