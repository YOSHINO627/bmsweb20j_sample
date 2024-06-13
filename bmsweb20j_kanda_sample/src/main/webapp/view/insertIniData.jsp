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

			<main class="insertIniData">
				<div class="h2Border">
					<nav>
						<ul class="nav">
							<li>[<a href="<%= request.getContextPath() %>/view/menu.jsp">メニュー</a>]</li>
						</ul>
					</nav>
					<h2>初期データ登録</h2>
				</div>

				<div class="insertIniData-contents">
					<p class="message">
						初期データとして以下のデータを登録しました。
					</p>
					<table class="insertIniData-table">
						<tr>
							<th>ISBN</th>
							<th class="uppercase">Title</th>
							<th>価格</th>
						</tr>
						<%
						if (bookList != null) {
							for (int i = 0; i < bookList.size(); i++) {
						%>
							<tr>
								<td><a href="<%= request.getContextPath() %>/detail?isbn=<%= bookList.get(i).getIsbn() %>&cmd=detail"><%= bookList.get(i).getIsbn() %></a></td>
								<td><%= bookList.get(i).getTitle() %></td>
								<td><%= objMyFormat.moneyFormat(bookList.get(i).getPrice())  %></td>
							</tr>
						<%
							}
						}
						%>
					</table>
				</div>
			</main>

			<%@include file="../common/footer.jsp" %>
		</div>
	</body>
</html>