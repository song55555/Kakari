<%--
  Created by IntelliJ IDEA.
  User: zhdlxh48
  Date: 2025-01-15
  Time: 오후 10:08
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

    <title>My Car List</title>
</head>
<body class="min-h-screen flex flex-col bg-white">
<!-- Header -->
<jsp:include page="../layout/header.jsp">
    <jsp:param name="isLoggedIn" value="${isLoggedIn}"/>
</jsp:include>

<main class="flex-grow">
    <div class="container mx-auto px-4 py-6">
        <h1 class="text-2xl font-bold mb-4 text-red-600">My Car List</h1>
        <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
            <c:forEach items="${carInfoWithOptions}" var="carInfoWithOption">
                <div class="bg-white shadow-md rounded-lg p-4">
                    <h2 class="text-xl font-semibold mb-2 text-gray-700">${carInfoWithOption.userCar.product.productName}</h2>
                    <p class="text-gray-600">Color: <span class="font-bold">${carInfoWithOption.userCar.productColor.colorName}</span></p>
                    <p class="text-gray-600">Options : <span class="font-bold">
                          <c:forEach items="${carInfoWithOption.optionNames}" var="optionName" varStatus="status">
                              ${optionName}
                              <c:if test="${!status.last}">,</c:if>
                          </c:forEach>
                        </span>
                    </p>
                </div>
            </c:forEach>
        </div>
    </div>
</main>

<!-- Footer -->
<jsp:include page="../layout/footer.jsp"/>
</body>
</html>
