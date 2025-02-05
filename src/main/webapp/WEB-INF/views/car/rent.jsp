<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>차량 렌트 예약</title>
    <link href="/css/pretendard-font.css" rel="stylesheet">

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
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="min-h-screen flex flex-col bg-white">
<!-- Header -->
<jsp:include page="../layout/header.jsp">
    <jsp:param name="isLoggedIn" value="${isLoggedIn}"/>
</jsp:include>

<main class="flex-grow">
    <div class="container mx-auto px-4 py-6">
        <h1 class="text-2xl font-bold mb-4 text-red-600">차량 렌트 예약</h1>
        <div class="bg-white shadow-md rounded-lg p-6">
            <form action="${pageContext.request.contextPath}/purchase/rent" method="post" class="space-y-4">
                <div>
                    <label for="rentLocation" class="block text-gray-700">렌트 지점 선택:</label>
                    <select name="rentLocationId" id="rentLocation"
                            class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-red-400" required>
                        <c:forEach var="location" items="${rentLocations}">
                            <option value="${location.rentLocationId}">
                                <c:out value="${location.rentLocationName}"/> (<c:out value="${location.rentLocationAddress}"/>)
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div>
                    <label for="startDate" class="block text-gray-700">렌트 시작 날짜:</label>
                    <input type="date" name="rentStartDate" id="startDate"
                           class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-red-400" required>
                </div>
                <div>
                    <label for="startTime" class="block text-gray-700">렌트 시작 시간:</label>
                    <input type="time" name="rentStartTime" id="startTime"
                           class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-red-400" required>
                </div>

                <div>
                    <label for="endDate" class="block text-gray-700">렌트 종료 날짜:</label>
                    <input type="date" name="rentEndDate" id="endDate"
                           class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-red-400" required>
                </div>
                <div>
                    <label for="endTime" class="block text-gray-700">렌트 종료 시간:</label>
                    <input type="time" name="rentEndTime" id="endTime"
                           class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-red-400" required>
                </div>

                <div>
                    <label for="productId" class="block text-gray-700">렌트 차량 선택:</label>
                    <select name="productId" id="productId"
                            class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-red-400" required>
                        <option value="">렌트 차량 선택</option>
                        <c:forEach var="product" items="${rentCarProducts}">
                            <option value="${product.productId}">${product.productName} - ${product.price}</option>
                        </c:forEach>
                    </select>
                </div>

                <button type="submit" class="px-4 py-2 bg-red-500 text-white rounded hover:bg-red-600">결제하기</button>
            </form>
        </div>
    </div>
</main>
<jsp:include page="../layout/footer.jsp"/>
</body>
</html>
