<%--
  Created by IntelliJ IDEA.
  User: zhdlxh48
  Date: 2025-01-23
  Time: 오전 7:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>중고거래 구매 확인</title>
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
<jsp:include page="../layout/header.jsp">
    <jsp:param name="isLoggedIn" value="${isLoggedIn}"/>
</jsp:include>

<!-- Main Content -->
<main class="flex-grow flex items-center justify-center">
    <div class="bg-white shadow-md rounded px-8 py-6 w-full max-w-lg">
        <h1 class="text-2xl font-bold text-center text-red-600 mb-6">중고거래 구매 확인</h1>
        <div class="mb-6 text-center">
            <p class="text-lg text-gray-700 mb-2">
                <strong class="font-semibold text-red-600">${tradeBoardDetail.usedTitle}</strong> 을(를) 구매하시겠습니까?
            </p>
            <p class="text-gray-600 mb-2">
                판매 가격: <strong class="font-semibold"><fmt:formatNumber value="${tradeBoardDetail.tradeDTO.tradePrice}" pattern="#,###"/>원</strong>
            </p>
            <p class="text-gray-600 mb-2">
                차종: <strong class="font-semibold">${tradeBoardDetail.tradeDTO.productName}</strong>
            </p>
            <p class="text-gray-600 mb-2">
                판매자: <strong class="font-semibold">${tradeBoardDetail.userName}</strong>
            </p>
            <p class="text-gray-600">
                거래 희망 일시: <strong class="font-semibold"><fmt:formatDate value="${tradeBoardDetail.tradeDTO.tradeDate}"
                                                                        pattern="yyyy년 MM월 dd일 HH시 mm분"/></strong>
            </p>
            <p class="text-gray-600">
                거래 희망 장소: <strong class="font-semibold">${tradeBoardDetail.tradeDTO.tradeLocation}</strong>
            </p>
        </div>

        <%-- TODO: 차량 이미지 추가 (추후 구현) --%>

        <div class="flex justify-center">
            <form action="${pageContext.request.contextPath}/purchase/trade/${tradeBoardDetail.tradePostId}" method="post">
                <button type="submit"
                        class="bg-red-600 hover:bg-red-700 text-white font-bold py-3 px-6 rounded focus:outline-none focus:shadow-outline">
                    구매 확정 및 결제
                </button>
            </form>
            <a href="${pageContext.request.contextPath}/trade/item/${tradeBoardDetail.tradePostId}"
               class="inline-block ml-4 px-6 py-3 bg-gray-500 text-white rounded-md hover:bg-gray-600 font-semibold">
                취소
            </a>
        </div>
    </div>
</main>

<!-- Footer -->
<jsp:include page="../layout/footer.jsp"/>
</body>
</html>