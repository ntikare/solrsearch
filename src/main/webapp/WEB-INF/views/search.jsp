<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Sample Search App</title>
<script type="text/javascript">
	function submitform(id) {
		document.searchForm.category.value = id;
		document.searchForm.submit();
	}
	function submitform2(id) {
		document.searchForm.query.value = "";
		document.searchForm.category.value = id;
		document.searchForm.submit();
	}
</script>

</head>
<body>
	<h1>Sample Search App</h1>
	<form name="searchForm" action="" method="post">
		<input type="hidden" name="category" /> <input type="text"
			name="query" value="${searchForm.query}" />&nbsp;<input
			type="submit" value="search" />
			
			 <br> <font color="red"><c:out
				value="${msg}"/></font>
		<h2>Categories</h2>
		<ul>
			<c:forEach items="${categories}" var="cat">
				<li><a href="javascript: submitform('${cat.name}')">${cat.name}(${cat.hits})</a></li>
			</c:forEach>
		</ul>
	</form>
	<hr>
	<hr>
	<h2>Docs</h2>

	<!-- loop from here -->
	<c:forEach items="${docs}" var="doc">
		<hr>

		<h3>${doc.summary}</h3>
		<p>${doc.content}</p>
		<p>${doc.bestAnswer}</p>
		<ul>
			<li><a href="javascript: submitform2('${doc.category}')">${doc.category}</a></li>
			<li>${doc.updatedDate}</li>
		</ul>
	</c:forEach>

	<!-- loop till here -->
</body>
</html>
