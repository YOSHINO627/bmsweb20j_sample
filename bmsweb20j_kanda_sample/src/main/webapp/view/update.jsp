<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.*,bean.Book, util.MyFormat" %>

<%
//リクエストスコープに登録した書籍情報を取得
Book book = (Book)request.getAttribute("book");
//MyFormatクラスのオブジェクトを生成
MyFormat objMyFormat = new MyFormat();
%>

<html>
	<head>
		<title>書籍管理システム</title>
		<link rel ="stylesheet"  href="<%= request.getContextPath() %>/view/css/style.css">
	</head>
	<body>
		<div class="wrapper">
			<%@include file="../common/header.jsp" %>

			<main>
				<div class="h2Border">
					<nav>
						<ul class="nav">
							<li>[<a href="<%= request.getContextPath() %>/view/menu.jsp">メニュー</a>]</li>
							<li>[<a href="<%= request.getContextPath() %>/view/insert.jsp">書籍登録</a>]</li>
							<li>[<a href="<%= request.getContextPath() %>/list">書籍一覧</a>]</li>
						</ul>
					</nav>
					<h2>書籍変更</h2>
				</div>

				<form class="update-form" action="<%= request.getContextPath() %>/update" method="get">
					<table class="update-table">
						<tr>
							<th class="transparent"></th>
							<th class="center"><変更前情報></th>
							<th class="center"><変更後情報></th>
						</tr>
						<tr>
							<th>ISBN</th>
							<td class="td-highlight"><%= book.getIsbn() %></td>
							<td><%= book.getIsbn() %></td>
						</tr>
						<tr>
							<th class="uppercase">Title</th>
							<td class="td-highlight"><%= book.getTitle() %></td>
							<td><input type="text" name="title" value=""></td>
						</tr>
						<tr>
							<th>価格</th>
							<td class="td-highlight"><%= objMyFormat.moneyFormat(book.getPrice()) %></td>
							<td><input type="text" name="price" value=""></td>
						</tr>
					</table>

					<input type="hidden" name="isbn" value="<%= book.getIsbn() %>">
					<input type="submit" value="変更完了">
				</form>
			</main>

			<%@include file="../common/footer.jsp" %>
		</div>
	</body>
</html>