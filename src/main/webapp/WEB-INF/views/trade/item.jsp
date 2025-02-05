<%--
  Created by IntelliJ IDEA.
  User: zhdlxh48
  Date: 2025-01-23
  Time: 오전 7:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <title>${tradeBoardDetail.usedTitle}</title>
</head>
<body class="min-h-screen flex flex-col bg-white">
<!-- Header -->
<jsp:include page="../layout/header.jsp">
    <jsp:param name="isLoggedIn" value="${isLoggedIn}"/>
</jsp:include>

<!-- Main Content -->
<main class="flex-grow">
    <div class="container mx-auto px-4 py-6">
        <div class="bg-white shadow-md rounded-lg p-6">
            <h1 class="text-2xl font-bold mb-4 text-red-600">${tradeBoardDetail.usedTitle}</h1>

            <div class="mb-4">
                <p class="text-gray-700">작성자: ${tradeBoardDetail.userName}</p>
                <p class="text-gray-700">작성일: <fmt:formatDate value="${tradeBoardDetail.usedCreatedDate}" pattern="yyyy-MM-dd HH:mm"/></p>
            </div>

            <div class="mb-6">
                <h2 class="text-xl font-semibold mb-2 text-gray-800">차량 정보</h2>
                <p class="text-gray-700">차종: ${tradeBoardDetail.tradeDTO.productName}</p>
                <p class="text-gray-700">색상: ${tradeBoardDetail.tradeDTO.productColorName}</p>
                <c:if test="${not empty tradeBoardDetail.tradeDTO.productOptionNames}">
                    <p class="text-gray-700">옵션: ${tradeBoardDetail.tradeDTO.productOptionNames}</p>
                </c:if>
                <p class="text-gray-700">희망 가격: <fmt:formatNumber value="${tradeBoardDetail.tradeDTO.tradePrice}" pattern="#,###"/>원</p>
                <p class="text-gray-700">거래 희망일: <fmt:formatDate value="${tradeBoardDetail.tradeDTO.tradeDate}" pattern="yyyy-MM-dd HH:mm"/></p>
                <p class="text-gray-700">거래 희망 장소: ${tradeBoardDetail.tradeDTO.tradeLocation}</p>
            </div>

            <div class="mb-6">
                <h2 class="text-xl font-semibold mb-2 text-gray-800">게시글 내용</h2>
                <div class="text-gray-700 whitespace-pre-line">${tradeBoardDetail.usedContent}</div>
            </div>

            <div class="flex justify-between items-center">
                <div>
                    <c:if test="${isLoggedIn and tradeBoardDetail.userId == userId}">
                        <a href="${pageContext.request.contextPath}/trade/edit/${tradeBoardDetail.tradePostId}"
                           class="bg-gray-500 hover:bg-gray-700 text-white font-bold py-2 px-4 rounded">수정</a>
                        <form action="${pageContext.request.contextPath}/trade/delete/${tradeBoardDetail.tradePostId}" method="post" class="inline">
                            <button type="submit" class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded ml-2">삭제</button>
                        </form>
                    </c:if>
                </div>
                <c:if test="${isLoggedIn and tradeBoardDetail.userId != userId}">
                    <form method="post" action="${pageContext.request.contextPath}/purchase/trade/${tradeBoardDetail.tradePostId}" class="inline">
                        <button type="submit" class="bg-red-600 hover:bg-red-700 text-white font-bold py-2 px-4 rounded">구매</button>
                    </form>
                </c:if>
            </div>
        </div>
    </div>
</main>

<!-- Footer -->
<jsp:include page="../layout/footer.jsp"/>
</body>
</html>