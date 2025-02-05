<%--
  Created by IntelliJ IDEA.
  User: zhdlxh48
  Date: 2025-01-14
  Time: 오전 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<header>
    <nav class="bg-white border-b border-gray-200">
        <div class="container mx-auto flex justify-between items-center px-4 py-2">
            <!-- Logo -->
            <div class="text-lg font-bold text-red-600">
                <a href="${pageContext.request.contextPath}/">KAKARI</a>
            </div>

            <!-- Menu -->
            <ul class="flex space-x-8 text-black">
                <li><a href="${pageContext.request.contextPath}/car/list" class="hover:text-red-600">Buy</a></li>
                <li><a href="${pageContext.request.contextPath}/rent" class="hover:text-red-600">Rent</a></li>
                <li><a href="${pageContext.request.contextPath}/trade/list" class="hover:text-red-600">Trade</a></li>
            </ul>

            <!-- Auth Buttons -->
            <div id="auth-buttons" class="space-x-4">
                <c:choose>
                    <c:when test="${isLoggedIn}">
                        <a href="${pageContext.request.contextPath}/auth/logout">
                            <button class="px-4 py-2 bg-red-500 text-white rounded hover:bg-red-600">Logout</button>
                        </a>
                        <a href="${pageContext.request.contextPath}/user/info">
                            <button class="px-4 py-2 bg-gray-400 text-white rounded hover:bg-gray-500">My Info</button>
                        </a>
                    </c:when>
                    <c:otherwise>
                        <button class="px-4 py-2 bg-red-500 text-white rounded hover:bg-red-600">
                            <a href="${pageContext.request.contextPath}/auth/login">Login</a>
                        </button>
                        <button class="px-4 py-2 bg-gray-400 text-white rounded hover:bg-gray-500">
                            <a href="${pageContext.request.contextPath}/auth/register">Register</a>
                        </button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </nav>
</header>
