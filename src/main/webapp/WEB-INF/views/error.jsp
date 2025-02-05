<%--
  Created by IntelliJ IDEA.
  User: zhdlxh48
  Date: 2024-05-16
  Time: 오후 7:08
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
                font-family: "Dongle", sans-serif;
                font-weight: 400;
                font-style: normal;
            }
        }
    </style>

    <title>Error</title>
</head>

<body class="min-h-screen flex flex-col bg-gray-50">

<!-- Header -->
<jsp:include page="./layout/header.jsp">
    <jsp:param name="isLoggedIn" value="${isLoggedIn}"/>
</jsp:include>

<!-- Main Content -->
<main class="flex-grow flex items-center justify-center">
    <div class="bg-white shadow-md rounded px-8 py-6 w-full max-w-md">
        <h1 class="text-xl font-bold text-center text-red-600 mb-4">${httpStatus}</h1>
        <div class="text-center">
            <c:if test="${not empty message}">
                <p class="text-red-500 text-lg mb-2">${message}</p>
            </c:if>
            <c:if test="${not empty requestUrl}">
                <p class="text-red-500 text-lg mb-2">${requestUrl} - 존재하지 않는 접근 주소입니다</p>
            </c:if>
            <c:if test="${not empty reason}">
                <p class="text-gray-700 text-sm">${reason}</p> <%-- reason 출력 추가 --%>
            </c:if>
            <a href="${pageContext.request.contextPath}/"
               class="inline-block mt-6 px-4 py-2 bg-red-500 text-white rounded hover:bg-red-600">
                메인으로 돌아가기
            </a>
        </div>
    </div>
</main>

<!-- Footer -->
<jsp:include page="./layout/footer.jsp"/>

</body>

</html>
