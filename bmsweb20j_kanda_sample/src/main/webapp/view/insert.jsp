<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.*,bean.Book" %>

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
					<h2>書籍登録</h2>
				</div>

				<form class="insert-form" action="<%= request.getContextPath() %>/insert" method="get">
					<table class="insert-table">
						<tr>
							<th><label for="isbn">ISBN</label></th>
							<td><input type="text" name="isbn" id="isbn" value=""></td>
						</tr>
						<tr>
							<th><label for="title" class="uppercase">Title</label></th>
							<td><input type="text" name="title" id="title" value=""></td>
						</tr>
						<tr>
							<th><label for="price">価格</label></th>
							<td><input type="text" name="price" id="price" value=""></td>
						</tr>
					</table>
					<input type="submit" value="登録">
				</form>
			</main>

			<%@include file="../common/footer.jsp" %>
		</div>
	</body>
</html>