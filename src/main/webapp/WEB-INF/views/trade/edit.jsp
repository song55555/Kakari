<%--
  Created by IntelliJ IDEA.
  User: zhdlxh48
  Date: 2025-01-23
  Time: 오전 7:08
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
    <title>중고거래 글 수정</title>
</head>
<body class="min-h-screen flex flex-col bg-white">
<!-- Header -->
<jsp:include page="../layout/header.jsp">
    <jsp:param name="isLoggedIn" value="${isLoggedIn}"/>
</jsp:include>

<!-- Main Content -->
<main class="flex-grow flex items-center justify-center">
    <div class="bg-white shadow-md rounded px-8 py-6 w-full max-w-lg">
        <h1 class="text-xl font-bold text-center text-red-600 mb-4">중고거래 글 수정</h1>
        <form action="${pageContext.request.contextPath}/trade/edit/${tradeBoardDetail.tradePostId}" method="post" class="space-y-4">
            <div>
                <label for="usedTitle" class="block text-gray-700">제목</label>
                <input type="text" id="usedTitle" name="usedTitle"
                       class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-red-400"
                       value="${tradeBoardDetail.usedTitle}" required>
            </div>
            <div>
                <label for="productId" class="block text-gray-700">판매 차량 선택</label>
                <select id="productId" name="productId"
                        class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-red-400" required>
                    <option value="">차량 선택</option>
                    <c:forEach var="userCar" items="${userCars}"> <%-- UserCar 목록 iteration --%>
                        <option value="${userCar.userCarId}" ${tradeBoardDetail.tradeDTO.productId == userCar.userCarId ? 'selected' : ''}> <%-- 기존 선택 차량 selected 처리 --%>
                                ${userCar.product.productName} - ${userCar.productColor.colorName} <%-- 차량 정보 표시 --%>
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <label for="tradeDate" class="block text-gray-700">거래 희망일 (날짜)</label>
                <input type="date" id="tradeDate" name="tradeDate"
                       class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-red-400"
                       value="${tradeBoardDetail.tradeDTO.tradeDate}" required>
            </div>
            <div>
                <label for="tradeTime" class="block text-gray-700">거래 희망일 (시간)</label>
                <input type="time" id="tradeTime" name="tradeTime"
                       class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-red-400"
                       value="${tradeBoardDetail.tradeDTO.tradeDate}" required>
            </div>
            <div>
                <label for="tradePrice" class="block text-gray-700">희망 가격</label>
                <input type="number" id="tradePrice" name="tradePrice"
                       class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-red-400"
                       value="${tradeBoardDetail.tradeDTO.tradePrice}" required>
            </div>
            <div>
                <label for="tradeLocation" class="block text-gray-700">거래 희망 장소</label>
                <input type="text" id="tradeLocation" name="tradeLocation"
                       class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-red-400"
                       value="${tradeBoardDetail.tradeDTO.tradeLocation}" required>
            </div>
            <div>
                <label for="usedContent" class="block text-gray-700">내용</label>
                <textarea id="usedContent" name="usedContent" rows="5"
                          class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-red-400"
                          required>${tradeBoardDetail.usedContent}</textarea>
            </div>
            <button type="submit" class="w-full bg-gray-500 text-white py-2 rounded hover:bg-gray-400">수정 완료</button>
        </form>
    </div>
</main>

<!-- Footer -->
<jsp:include page="../layout/footer.jsp"/>
</body>
</html>