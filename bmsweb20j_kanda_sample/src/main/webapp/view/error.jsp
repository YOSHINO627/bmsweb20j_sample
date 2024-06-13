<%@page contentType="text/html; charset=UTF-8" %>

<%
//各Servletでリクエストスコープに登録したエラーメッセージと発生場所情報を取得
String error = (String)request.getAttribute("error");
String cmd = (String)request.getAttribute("cmd");
%>

<html>
	<head>
		<title>書籍管理メニュー画面</title>
		<link rel ="stylesheet"  href="<%= request.getContextPath() %>/view/css/style.css">
	</head>
	<body>
		<div class="wrapper">
			<%@include file="../common/header.jsp" %>

			<main class="error">
				<dl>
					<dt>●●エラー●●</dt>
					<dd><%= error %></dd>
				</dl>
				<% if (cmd.equals("logout")) { %>
					<p>[<a href="<%= request.getContextPath() %>/logout">ログイン画面へ</a>]</p>
				<% } else if (cmd.equals("menu")) { %>
					<p>[<a href="<%= request.getContextPath() %>/view/menu.jsp">メニューに戻る</a>]</p>
				<% } else { %>
					<p>[<a href="<%= request.getContextPath() %>/list">一覧表示に戻る</a>]</p>
				<% } %>
			</main>

			<%@include file="../common/footer.jsp" %>
		</div>
	</body>
</html>