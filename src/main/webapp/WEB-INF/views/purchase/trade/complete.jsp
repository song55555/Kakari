<%--
  Created by IntelliJ IDEA.
  User: zhdlxh48
  Date: 2025-01-21
  Time: 오후 8:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>중고거래 완료</title>
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
</head>
<body class="min-h-screen flex flex-col bg-gray-50">
<!-- Header -->
<jsp:include page="../../layout/header.jsp">
    <jsp:param name="isLoggedIn" value="${isLoggedIn}"/>
</jsp:include>

<!-- Main Content -->
<main class="flex-grow flex items-center justify-center">
    <div class="bg-white shadow-md rounded px-8 py-6 w-full max-w-lg">
        <h1 class="text-2xl font-bold text-center text-red-600 mb-6">중고거래 완료</h1>
        <div class="mb-6 text-center">
            <p class="text-lg text-gray-700 mb-2">
                <strong class="font-semibold text-red-600">${tradeBoardDetail.usedTitle}</strong> 중고거래가 완료되었습니다.
            </p>
            <p class="text-gray-600">
                거래 가격: <strong class="font-semibold"><fmt:formatNumber value="${tradeBoardDetail.tradeDTO.tradePrice}" pattern="#,###"/>원</strong>
            </p>
            <p class="text-gray-600">
                차종: <strong class="font-semibold">${tradeBoardDetail.tradeDTO.productName}</strong>
            </p>
            <p class="text-gray-600">
                판매자: <strong class="font-semibold">${tradeBoardDetail.userName}</strong>
            </p>
            <p class="text-gray-600">
                거래 일시: <strong class="font-semibold"><fmt:formatDate value="${tradeBoardDetail.tradeDTO.tradeDate}"
                                                                     pattern="yyyy년 MM월 dd일 HH시 mm분"/></strong>
            </p>
            <p class="text-gray-600">
                거래 장소: <strong class="font-semibold">${tradeBoardDetail.tradeDTO.tradeLocation}</strong>
            </p>
            <p class="text-gray-600 mt-4">
                구매해 주셔서 감사합니다.
            </p>
        </div>

        <%-- TODO: 차량 이미지 추가 (추후 구현) --%>

        <div class="text-center mt-4">
            <a href="${pageContext.request.contextPath}/trade/list"
               class="inline-block px-6 py-3 bg-red-500 text-white rounded-md hover:bg-red-600 font-semibold">
                중고거래 목록으로 돌아가기
            </a>
            <a href="${pageContext.request.contextPath}/"
               class="inline-block px-6 py-3 bg-gray-500 text-white rounded-md hover:bg-gray-600 font-semibold ml-2">
                메인 페이지로 돌아가기
            </a>
        </div>
    </div>
</main>

<!-- Footer -->
<jsp:include page="../../layout/footer.jsp"/>
</body>
</html>