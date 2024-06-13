<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.*,bean.OrderedItem" %>

<%
//リクエストスコープに登録した購入済み書籍情報を取得
ArrayList<OrderedItem> orderedItemList = (ArrayList<OrderedItem>)request.getAttribute("orderedItemList");
%>

<html>
	<head>
		<title>書籍管理システム</title>
		<link rel ="stylesheet"  href="<%= request.getContextPath() %>/view/css/style.css">
	</head>
	<body>
		<div class="wrapper">
			<%@include file="../common/header.jsp" %>

			<main class="showOrderedItem">
				<div class="h2Border">
					<nav>
						<ul class="nav">
							<li>[<a href="<%= request.getContextPath() %>/view/menu.jsp">メニュー</a>]</li>
						</ul>
					</nav>
					<h2>購入品確認</h2>
				</div>

				<table class="showOrderedItem-table">
					<tr>
						<th>ユーザー</th>
						<th class="uppercase">Title</th>
						<th>注文日</th>
					</tr>
					<%
					if (orderedItemList != null) {
						for (int i = 0; i < orderedItemList.size(); i++) {
					%>
						<tr>
							<td><%= orderedItemList.get(i).getUserid() %></td>
							<td><%= orderedItemList.get(i).getTitle() %></td>
							<td><%= orderedItemList.get(i).getDate().replace("-", "/")  %></td>
						</tr>
					<%
						}
					}
					%>
				</table>
			</main>

			<%@include file="../common/footer.jsp" %>
		</div>
	</body>
</html>