<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" isErrorPage="true"%>
<% response.setStatus(200); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>404</title>
<link rel="stylesheet" type="text/css" href="${contextPath}/static/css/error-page.css">
</head>
<body>

<div>
  <aside><img src="${contextPath}/static/images/common/heendy404.png" alt="404 Image" />
  </aside>
  <main>
    <h1>Sorry!</h1>
    <p>
      <strong>404 Page Not Found</strong><br>해당 페이지가 존재하지 않습니다.<br>메인 페이지로 돌아가 주세요!
    </p>
    <a href="${contextPath}/"><button>Back to Heendy</button></a>
  </main>
</div>
</body>
</html>