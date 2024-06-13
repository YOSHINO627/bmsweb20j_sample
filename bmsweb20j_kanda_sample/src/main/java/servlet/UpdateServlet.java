package servlet;

import java.io.IOException;

import bean.Book;
import dao.BookDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 
 */
@WebServlet("/update")
public class UpdateServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String error = "";
		String cmd = "list";
		try {
			BookDAO daoObj = new BookDAO();
			
			String isbn = request.getParameter("isbn");
			String title = request.getParameter("title");
			String price = request.getParameter("price");
			
			// 入力チェック
			if (daoObj.selectByIsbn(isbn).getIsbn() == null) {
				error = "更新対象の書籍が存在しない為、書籍更新処理は表示できませんでした。";
				return;
			}
			if (title.isEmpty()) {
				error = "タイトルが未入力の為、書籍更新処理は行えませんでした。";
				return;
			}
			if (price.isEmpty()) {
				error = "価格が未入力の為、書籍更新処理は行えませんでした。";
				return;
			}
			
			// 新しい情報をbookに格納
			Book book = new Book();
			book.setIsbn(isbn);
			book.setTitle(title);
			book.setPrice(Integer.parseInt(price));

			// DBの更新処理
			daoObj.update(book);
		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、書籍更新処理は行えませんでした。";
			cmd = "logout";
		} catch (NumberFormatException e) {
			error = "価格の値が不正の為、書籍更新処理は行えませんでした。";
		} finally {
			if (error.equals("")) {
				request.getRequestDispatcher("/list").forward(request, response);
			} else {
				request.setAttribute("cmd", cmd);
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}

	}

}
