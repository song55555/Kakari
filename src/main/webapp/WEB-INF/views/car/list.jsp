<%--
  Created by IntelliJ IDEA.
  User: zhdlxh48
  Date: 2025-01-15
  Time: 오후 10:03
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

    <title>Car List</title>
</head>
<body class="min-h-screen flex flex-col bg-white">
<!-- Header -->
<jsp:include page="../layout/header.jsp">
    <jsp:param name="isLoggedIn" value="${isLoggedIn}"/>
</jsp:include>

<main class="flex-grow">
    <div class="container mx-auto px-4 py-6">
        <h1 class="text-2xl font-bold mb-4 text-red-600">Car List</h1>
        <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
            <c:forEach items="${products}" var="product">
                <div class="bg-white shadow-md rounded-lg p-4">
                    <a href="/car/list/${product.productId}" class="block">
                        <div class="mb-2">
                            <img src="/images/${product.productImage}" alt="${product.productName}"
                                 class="w-full h-auto max-h-40 object-cover rounded">
                        </div>
                        <h2 class="text-xl font-semibold mb-2 text-gray-700">${product.productName}</h2>
                        <p class="text-gray-600">Price: <span class="font-bold">${product.price}</span></p>
                        <p> View More </p>
                    </a>
                </div>
            </c:forEach>
        </div>
    </div>
</main>

<!-- Footer -->
<jsp:include page="../layout/footer.jsp"/>
</body>
</html>
