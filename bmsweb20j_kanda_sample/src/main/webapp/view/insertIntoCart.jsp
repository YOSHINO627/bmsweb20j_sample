<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.*,bean.Book, util.MyFormat" %>

<%
//リクエストスコープに登録した書籍情報を取得
Book book = (Book)request.getAttribute("book");

//取得した情報を各変数に格納
String isbn = "";
String title = "";
int price = 0;

if (book != null) {
	isbn = book.getIsbn();
	title = book.getTitle();
	price = book.getPrice();
}

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
							<li>[<a href="<%= request.getContextPath() %>/list">書籍一覧</a>]</li>
						</ul>
					</nav>
					<h2>カート追加</h2>
				</div>

				<p class="message">下記の書籍をカートに追加しました。</p>
				<table class="insertIntoCart-table">
					<tr>
						<th>ISBN</th>
						<td><%= isbn %></td>
					</tr>
					<tr>
						<th class="uppercase">Title</th>
						<td><%= title %></td>
					</tr>
					<tr>
						<th>価格</th>
						<td><%= objMyFormat.moneyFormat(price) %></td>
					</tr>
				</table>
				<form class="insertIntoCart-form btn" action="<%= request.getContextPath() %>/showCart" method="get">
					<input type="hidden" name="isbn" value="<%= isbn %>">
					<input type="submit" value="カート確認">
				</form>
			</main>

			<%@include file="../common/footer.jsp" %>
		</div>
	</body>
</html>