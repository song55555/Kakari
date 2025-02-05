<%--
  Created by IntelliJ IDEA.
  User: zhdlxh48
  Date: 2025-01-14
  Time: 오전 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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

    <title>${title}</title>
</head>

<body class="min-h-screen flex flex-col bg-white">

<!-- Header -->
<jsp:include page="./layout/header.jsp">
    <jsp:param name="isLoggedIn" value="${isLoggedIn}"/>
</jsp:include>

<!-- Main Content -->
<main class="flex-grow">
    <div class="container mx-auto px-4 py-6">
        Main Page
    </div>
</main>

<!-- Footer -->
<jsp:include page="./layout/footer.jsp"/>

</body>

</html>
