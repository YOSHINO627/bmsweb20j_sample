package servlet;

import java.io.IOException;
import java.util.ArrayList;

import bean.Book;
import dao.BookDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.FileIn;

@WebServlet("/insertIniData")
public class InsertIniDataServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String error = "";
		String cmd = "menu";
		FileIn in = new FileIn();
		try {

			BookDAO bookDao = new BookDAO();
			ArrayList<Book> bookList = bookDao.selectAll();
			
			//既に登録されている書籍があったらエラー
			if (bookList.size() != 0) {
				error = "DBにはデータが存在するので、初期データは登録できません";
				return;
			}
			
			//初期データファイル読み込み
			String path = getServletContext().getRealPath("file\\initial_data.csv");
			
			if (in.open(path) == false) {
				error = "初期データファイルが無い為、登録は行えません。 ";
				cmd = "menu";
				return;
			}
			
			// バッファー用変数
			String str;
			String[] strArray = null;
			
			while ((str = in.readLine()) != null) {
				int i = 0;
				// 1行をカンマ区切りで分割 
				strArray = str.split(",");

				Book book = new Book();
				book.setIsbn(strArray[i++]);
				book.setTitle(strArray[i++]);
				book.setPrice(Integer.parseInt(strArray[i++]));
				// listに追加
				bookList.add(book);
				// bookをDBに登録
				bookDao.insert(book);
			}
			
			request.setAttribute("bookList", bookList);
			
		} catch (NumberFormatException e) {
			error = "初期データファイルに不備がある為、登録は行えません";
		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、初期データ登録を行えませんでした。";
			cmd = "logout";
		} finally {
			// ファイルをクローズ
			in.close();
			
			if (error.equals("")) {
				// 「insertIniData.jsp」へフォワード
				request.getRequestDispatcher("/view/insertIniData.jsp").forward(request, response);

			} else {
				// エラー発生時
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				// 「error.jsp」へフォワード
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}

	}

}
