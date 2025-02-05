<%--
  Created by IntelliJ IDEA.
  User: zhdlxh48
  Date: 2025-01-14
  Time: 오전 11:44
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

    <title>로그인</title>
</head>

<body class="min-h-screen flex flex-col bg-gray-50">

<!-- Header -->
<jsp:include page="../layout/header.jsp"/>

<!-- Main Content -->
<main class="flex-grow flex items-center justify-center">
    <div class="bg-white shadow-md rounded px-8 py-6 w-full max-w-md">
        <h1 class="text-xl font-bold text-center text-red-600 mb-4">로그인</h1>
        <form action="${pageContext.request.contextPath}/auth/login" method="post" class="space-y-4">
            <div>
                <label class="block text-gray-700">아이디</label>
                <input type="text" name="user_id"
                       class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-red-400" required>
            </div>
            <div>
                <label class="block text-gray-700">비밀번호</label>
                <input type="password" name="user_password"
                       class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-red-400" required>
            </div>
            <button type="submit" class="w-full bg-red-600 text-white py-2 rounded hover:bg-red-500">로그인</button>
        </form>
        <c:if test="${not empty error}">
            <p class="text-red-500 text-sm mt-2">${error}</p>
        </c:if>
        <div class="text-center mt-4">
            <p class="text-gray-600">회원이 아니신가요? <a href="${pageContext.request.contextPath}/auth/register" class="text-red-600 hover:underline">이 곳을
                눌러 가입</a></p>
        </div>
    </div>
</main>

<!-- Footer -->
<jsp:include page="../layout/footer.jsp"/>

</body>

</html>
