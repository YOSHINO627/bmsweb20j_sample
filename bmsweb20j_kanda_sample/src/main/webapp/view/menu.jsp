<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="bean.User" %>


<html>
	<head>
		<title>書籍管理メニュー画面</title>
		<link rel ="stylesheet"  href="<%= request.getContextPath() %>/view/css/style.css">
	</head>
	<body>
		<div class="wrapper">
			<%@include file="../common/header.jsp" %>

			<main>
				<div class="menu-title">
					<%@include file="../common/userInfo.jsp" %>
					<div class="h2Border"><h2>Menu</h2></div>
				</div>

				<ul class="menu-list">
					<li><a href="<%= request.getContextPath() %>/list">【書籍一覧】</a></li>
					<li><a href="<%= request.getContextPath() %>/view/insert.jsp">【書籍登録】</a></li>
					<li><a href="<%= request.getContextPath() %>/showCart">【カート状況確認】</a></li>
					<li><a href="<%= request.getContextPath() %>/insertIniData">【初期データ登録(データがない場合のみ)】</a></li>
					<li><a href="<%= request.getContextPath() %>/showOrderedItem">【購入状況確認】</a></li>
					<li><a href="<%= request.getContextPath() %>/logout">【ログアウト】</a></li>
				</ul>
			</main>

			<%@include file="../common/footer.jsp" %>
		</div>
	</body>
</html>