<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="java.net.*, bean.User" %>

<%
// ログイン情報用の変数
String userid = "";
String password = "";

// クッキー取得
Cookie[] userCookies = request.getCookies();
if (userCookies != null) {
	for (int i = 0; i < userCookies.length; i++) {
		if (userCookies[i].getName().equals("userid")) {
			userid = URLDecoder.decode(userCookies[i].getValue(), "UTF-8");
		}
		if (userCookies[i].getName().equals("password")) {
			password = URLDecoder.decode(userCookies[i].getValue(), "UTF-8");
		}
	}
} else { // クッキーが無かったらセッションから取得
	User user = (User)session.getAttribute("user");
	userid = user.getUserid();
	if (userid == null) {
		userid = "";
	}
	password = user.getPassword();
	if (password == null) {
		password = "";
	}
}

// エラーメッセージ取得
String message = (String)request.getAttribute("message");
if (message == null) {
	message = "";
}
%>

<html>
	<head>
		<title>書籍管理ログイン画面</title>
		<link rel ="stylesheet"  href="<%= request.getContextPath() %>/view/css/style.css">
	</head>
	<body>
		<div class="wrapper">
			<%@include file="../common/header.jsp" %>

			<main class="login">
				<div class="h2Border"></div>
				<form action="<%= request.getContextPath() %>/login" method="post">
					<table class="login-table">
						<tr>
							<th><label for="userid">ユーザー</label></th>
							<td><input type="text" name="userid" id="userid" value="<%= userid %>"></td>
						</tr>
						<tr>
							<th><label for="password">パスワード</label></th>
							<td><input type="password" name="password" id="password" value="<%= password %>"></td>
						</tr>
					</table>
					<input type="submit" value="ログイン">
				</form>
				<p><%= message %></p>
			</main>

			<%@include file="../common/footer.jsp" %>
		</div>
	</body>
</html>