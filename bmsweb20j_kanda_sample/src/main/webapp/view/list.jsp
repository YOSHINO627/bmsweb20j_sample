<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.*,bean.Book, util.MyFormat" %>

<%
//リクエストスコープに登録した書籍情報を取得
ArrayList<Book> bookList = (ArrayList<Book>)request.getAttribute("bookList");
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

			<main class="list">
				<div class="h2Border">
					<nav>
						<ul class="nav">
							<li>[<a href="<%= request.getContextPath() %>/view/menu.jsp">メニュー</a>]</li>
							<li>[<a href="<%= request.getContextPath() %>/view/insert.jsp">書籍登録</a>]</li>
						</ul>
					</nav>
					<h2>書籍一覧</h2>
				</div>

				<form class="list-form" action="<%= request.getContextPath() %>/search" method="get">
					<span>
						<label for="isbn">ISBN:</label>
						<input type="text" name="isbn" id="isbn" value="">
					</span>
					<span>
						<label for="title" class="uppercase">Title:</label>
						<input type="text" name="title" id="title" value="">
					</span>
					<span>
						<label for="price">価格:</label>
						<input type="text" name="price" id="price" value="">
					</span>
					<input type="submit" value="検索">
				</form>
				<form class="list-form" action="<%= request.getContextPath() %>/list" method="get">
					<input type="submit" value="全件表示">
				</form>

				<table class="list-table">
					<tr>
						<th>ISBN</th>
						<th class="uppercase">Title</th>
						<th>価格</th>
						<th>変更/削除/カートに入れる</th>
					</tr>
					<%
					if (bookList != null) {
						for (int i = 0; i < bookList.size(); i++) {
					%>
						<tr>
							<td><a href="<%= request.getContextPath() %>/detail?isbn=<%= bookList.get(i).getIsbn() %>&cmd=detail"><%= bookList.get(i).getIsbn() %></a></td>
							<td><%= bookList.get(i).getTitle() %></td>
							<td><%= objMyFormat.moneyFormat(bookList.get(i).getPrice())  %></td>
							<td>
								<a class="update-link" href="<%= request.getContextPath() %>/detail?isbn=<%= bookList.get(i).getIsbn() %>&cmd=update">変更</a>
								<a class="update-link" href="<%= request.getContextPath() %>/delete?isbn=<%= bookList.get(i).getIsbn() %>">削除</a>
								<a class="update-link" href="<%= request.getContextPath() %>/insertIntoCart?isbn=<%= bookList.get(i).getIsbn() %>">カートに入れる</a>
							</td>
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