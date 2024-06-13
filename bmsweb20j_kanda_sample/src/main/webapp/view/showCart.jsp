<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.*,bean.Book, util.MyFormat" %>

<%
//リクエストスコープに登録した書籍情報を取得
ArrayList<Book> bookList = (ArrayList<Book>)request.getAttribute("bookList");
//MyFormatクラスのオブジェクトを生成
MyFormat objMyFormat = new MyFormat();
// 合計価格用の変数
int total = (int)request.getAttribute("total");
%>

<html>
	<head>
		<title>書籍管理システム</title>
		<link rel ="stylesheet"  href="<%= request.getContextPath() %>/view/css/style.css">
	</head>
	<body>
		<div class="wrapper">
			<%@include file="../common/header.jsp" %>

			<main class="showCart">
				<div class="h2Border">
					<nav>
						<ul class="nav">
							<li>[<a href="<%= request.getContextPath() %>/view/menu.jsp">メニュー</a>]</li>
							<li>[<a href="<%= request.getContextPath() %>/list">書籍一覧</a>]</li>
						</ul>
					</nav>
					<h2>カート内容</h2>
				</div>

				<table class="showCart-table">
					<tr>
						<th>ISBN</th>
						<th class="uppercase">Title</th>
						<th>価格</th>
						<th>削除</th>
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
								<a class="update-link" href="<%= request.getContextPath() %>/showCart?delno=<%= i %>">削除</a>
							</td>
						</tr>
					<%	}
					}
					%>
				</table>
				<p class="total">
					<span>合計</span><span><%= objMyFormat.moneyFormat(total) %></span>
				</p>

				<form class="showCart-form" action="<%= request.getContextPath() %>/buyConfirm" method="get">
					<input type="submit" value="購入">
				</form>
			</main>

			<%@include file="../common/footer.jsp" %>
		</div>
	</body>
</html>