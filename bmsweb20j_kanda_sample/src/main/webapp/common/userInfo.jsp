<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="bean.User" %>

<%
// ユーザ情報取得
User user = (User)session.getAttribute("user");

// セッション切れの場合
if (user == null) {
	// 「error.jsp」へフォワード
	request.setAttribute("error", "セッション切れの為、メニュー画面が表示できませんでした。");
	request.setAttribute("cmd", "logout");
	request.getRequestDispatcher("/view/error.jsp").forward(request, response);
	return;
}

// 権限の表示
String authority = "";
if (user.getAuthority().equals("2")) {
	authority = "管理者";
} else {
	authority = "一般ユーザー";
}
%>

<ul class="menu-user">
	<li>名前: <%= user.getUserid() %></li>
	<li>権限: <%= authority %></li>
</ul>