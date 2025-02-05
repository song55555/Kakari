<%--
  Created by IntelliJ IDEA.
  User: zhdlxh48
  Date: 2025-01-14
  Time: 오후 3:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<footer class="bg-gray-100 border-t border-gray-200 py-6">
    <div class="container mx-auto flex justify-between items-center px-4">
        <!-- Company Name -->
        <div class="text-red-400 font-bold text-lg">
            GlobalIN
        </div>

        <!-- Footer Links -->
        <div class="text-sm text-gray-600 flex space-x-8">
            <a href="${pageContext.request.contextPath}/info/about" class="hover:text-red-600">About Us</a>
            <a href="${pageContext.request.contextPath}/info/services" class="hover:text-red-600">Services</a>
            <a href="${pageContext.request.contextPath}/info/privacy" class="hover:text-red-600">Privacy Policy</a>
            <a href="${pageContext.request.contextPath}/info/contact" class="hover:text-red-600">Contact</a>
        </div>
    </div>

    <!-- Bottom Section -->
    <div class="mt-4 text-center text-xs text-gray-500">
        &copy; 2025 GlobalIN. All rights reserved. Created by Team Kakari.
    </div>
</footer>
