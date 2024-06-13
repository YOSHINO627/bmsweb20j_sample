package servlet;

import java.io.IOException;

import bean.User;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String error = "";
		String cmd = "";
		HttpSession session = request.getSession();
		UserDAO uerDao = new UserDAO();
		User user = null;

		try {
			String userid = request.getParameter("userid");
			String password = request.getParameter("password");

			// 入力された情報のユーザーがいるか検索する
			user = uerDao.selectByUser(userid, password);

			// もしユーザー情報がなければログイン画面へ
			if (user.getUserid() == null) {
				error = "入力データが間違っています！";
				cmd = "login";
				return;
			}

			// セッションにログイン情報を保存
			session.setAttribute("user", user);
			// クッキーを作成
			Cookie useridCookie = new Cookie("userid", userid);
			useridCookie.setMaxAge(60 * 60 * 24 * 5);
			response.addCookie(useridCookie);
			Cookie passwordCookie = new Cookie("password", password);
			passwordCookie.setMaxAge(60 * 60 * 24 * 5);
			response.addCookie(passwordCookie);

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、一覧表示を行えませんでした。";
			cmd = "logout";
		} finally {
			if (error.equals("")) {
				request.getRequestDispatcher("/view/menu.jsp").forward(request, response);
			} else if (cmd.equals("login")) {
				request.setAttribute("message", error);
				request.getRequestDispatcher("/view/login.jsp").forward(request, response);
			} else {
				request.setAttribute("cmd", cmd);
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}

	}

}
