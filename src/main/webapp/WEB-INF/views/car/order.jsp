<%--
  Created by IntelliJ IDEA.
  User: zhdlxh48
  Date: 2025-01-15
  Time: 오후 10:07
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

    <title>${product.productName} Details</title>
</head>
<body class="min-h-screen flex flex-col bg-white">
<!-- Header -->
<jsp:include page="../layout/header.jsp">
    <jsp:param name="isLoggedIn" value="${isLoggedIn}"/>
</jsp:include>

<main class="flex-grow">
    <div class="container mx-auto px-4 py-6">
        <h1 class="text-2xl font-bold mb-4 text-red-600">${product.productName} Details</h1>

        <div class="bg-white shadow-md rounded-lg p-6">
            <h2 class="text-xl font-semibold mb-2 text-gray-700">${product.productName}</h2>
            <div class="mb-4">
                <img src="/images/${product.productImage}" alt="${product.productName}" class="w-full h-auto max-h-60 object-contain rounded">
            </div>
            <p class="text-gray-600 mb-2">Price: <span class="font-bold">${product.price}</span></p>

            <form action="${pageContext.request.contextPath}/purchase/order" method="post" class="space-y-4">
                <input type="hidden" name="productId" value="${product.productId}"/>
                <div>
                    <label for="color" class="block text-gray-700">Select Color:</label>
                    <select name="productColorId" id="color"
                            class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-red-400" required>
                        <c:forEach items="${colors}" var="color">
                            <option value="${color.productColorId}">${color.colorName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <label class="block text-gray-700">Select Options:</label>
                    <c:forEach items="${options}" var="option">
                        <div class="flex items-center mb-2">
                            <input type="checkbox" name="selectedOptions" value="${option.productOptionId}" class="mr-2">
                            <c:forEach items="${optionTypes}" var="optionType">
                                <c:if test="${optionType.optionTypeId == option.optionTypeId}">
                                    <label class="text-gray-700">${optionType.optionName}</label>
                                </c:if>
                            </c:forEach>
                        </div>
                    </c:forEach>
                </div>

                <button type="submit" class="px-4 py-2 bg-red-500 text-white rounded hover:bg-red-600">Purchase</button>
            </form>
        </div>
    </div>
</main>

<!-- Footer -->
<jsp:include page="../layout/footer.jsp"/>
</body>
</html>
