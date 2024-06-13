package servlet;

import java.io.IOException;

import bean.Book;
import dao.BookDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/insert")
public class InsertServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String error = "";
		String cmd = "list";
		try {
			BookDAO objDao = new BookDAO();
			
			String isbn = request.getParameter("isbn");
			String title = request.getParameter("title");
			String price = request.getParameter("price");
			
			// 入力チェック
			if (isbn.isEmpty()) {
				error = "ISBNが未入力の為、書籍登録処理は行えませんでした。";
				return;
			}
			if (objDao.selectByIsbn(isbn).getIsbn() != null) {
				error = "入力ISBNは既に登録済の為、書籍登録処理は行えませんでした。";
				return;
			}
			if (title.isEmpty()) {
				error = "タイトルが未入力の為、書籍登録処理は行えませんでした。";
				return;
			}
			if (price.isEmpty()) {
				error = "価格が未入力の為、書籍登録処理は行えませんでした。";
				return;
			}
			
			// 本の情報を格納
			Book book = new Book();
			book.setIsbn(isbn);
			book.setTitle(title);
			book.setPrice(Integer.parseInt(price));
			
			// DBに登録
			objDao.insert(book);
		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、書籍登録処理は行えませんでした。";
			cmd = "logout";
		} catch (NumberFormatException e) {//price:未入力or数値以外チェック
			error = "価格の値が不正の為、書籍登録処理は行えませんでした。";
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