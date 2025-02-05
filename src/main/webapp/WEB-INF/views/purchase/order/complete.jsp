<%--
  Created by IntelliJ IDEA.
  User: zhdlxh48
  Date: 2025-01-21
  Time: 오후 5:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <!-- Scripts -->
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="/css/pretendard-font.css" rel="stylesheet">
    <!-- Font Style Setting -->
    <style>
        @tailwind base;
        @tailwind components;
        @tailwind utilities;
        @layer base {
            body {
                font-family: "Pretendard", sans-serif;
                font-weight: 400;
                font-style: normal;
            }
        }
    </style>
    <title>주문 완료</title>
</head>
<body class="min-h-screen flex flex-col bg-gray-50">
<!-- Header -->
<jsp:include page="../../layout/header.jsp">
    <jsp:param name="isLoggedIn" value="${isLoggedIn}"/>
</jsp:include>

<!-- Main Content -->
<main class="flex-grow flex items-center justify-center">
    <div class="bg-white shadow-md rounded px-8 py-6 w-full max-w-lg">
        <h1 class="text-2xl font-bold text-center text-red-600 mb-6">주문 완료</h1>

        <div class="mb-6 text-center">
            <p class="text-lg text-gray-700 mb-2">
                <strong class="font-semibold text-red-600"><c:out value="${productName}"/></strong> 차량 주문이 완료되었습니다.
            </p>
            <p class="text-gray-600">
                총 결제 금액: <strong class="font-semibold"><c:out value="${productPrice}"/>원</strong>
            </p>
        </div>

        <div class="mb-6 flex justify-center">
            <img src="/images/<c:out value="${productImage}"/>" alt="<c:out value="${productName}"/>" class="max-h-48 rounded-md shadow-md">
        </div>

        <div class="text-center mt-4">
            <a href="${pageContext.request.contextPath}/"
               class="inline-block px-6 py-3 bg-red-500 text-white rounded-md hover:bg-red-600 font-semibold">
                메인 페이지로 돌아가기
            </a>
        </div>
    </div>
</main>

<!-- Footer -->
<jsp:include page="../../layout/footer.jsp"/>
</body>
</html>