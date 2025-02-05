<%--
  Created by IntelliJ IDEA.
  User: zhdlxh48
  Date: 2025-01-23
  Time: 오전 7:06
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
    <title>중고거래 게시판</title>
</head>
<body class="min-h-screen flex flex-col bg-gray-50"> <!-- bg-gray-50 추가 -->
<!-- Header -->
<jsp:include page="../layout/header.jsp">
    <jsp:param name="isLoggedIn" value="${isLoggedIn}"/>
</jsp:include>

<!-- Main Content -->
<main class="flex-grow">
    <div class="container mx-auto px-4 py-6">
        <h1 class="text-2xl font-bold mb-4 text-red-700">중고거래 게시판</h1> <!-- text-red-700 으로 변경 -->

        <!-- 검색 폼 -->
        <div class="mb-4 flex justify-end items-center">
            <form action="${pageContext.request.contextPath}/trade/list" method="get" class="flex items-center">
                <div class="mr-2">
                    <select name="searchType"
                            class="shadow border rounded py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
                        <!-- Tailwind CSS 적용 -->
                        <option value="titleContent" ${searchType == 'titleContent' ? 'selected' : ''}>제목+내용</option>
                        <option value="title" ${searchType == 'title' ? 'selected' : ''}>제목</option>
                        <option value="content" ${searchType == 'content' ? 'selected' : ''}>내용</option>
                    </select>
                </div>
                <input type="text" name="keyword" value="${keyword}" placeholder="검색어를 입력하세요"
                       class="shadow border rounded py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline mr-2">
                <!-- Tailwind CSS 적용 -->
                <button class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                        type="submit">검색
                </button> <!-- Tailwind CSS 적용 -->
            </form>
        </div>

        <!-- 게시글 목록 -->
        <div class="grid grid-cols-1 gap-4">
            <c:forEach var="board" items="${tradeBoardPage.content}">
                <div class="bg-white shadow-md rounded-lg overflow-hidden"> <!-- overflow-hidden 추가 -->
                    <a href="${pageContext.request.contextPath}/trade/item/${board.tradePostId}"
                       class="block hover:bg-gray-50 transition-colors duration-200 p-4"> <!-- hover 효과 및 transition 추가 -->
                        <h2 class="text-xl font-semibold mb-2 text-gray-800 truncate">${board.usedTitle}</h2> <!-- text-gray-800, truncate 추가 -->
                        <p class="text-gray-600 mb-1">작성자: <span class="font-semibold">${board.userName}</span></p> <!-- font-semibold 추가 -->
                        <p class="text-gray-600 mb-1"><fmt:formatDate value="${board.usedCreatedDate}" pattern="yyyy-MM-dd HH:mm"/></p>
                        <p class="text-gray-600">가격: <span class="font-bold text-red-500"><fmt:formatNumber value="${board.tradePrice}"
                                                                                                            pattern="#,###"/>원</span></p>
                        <!-- font-bold, text-red-500 추가 -->
                        <p class="text-gray-600">차종: ${board.productName}</p>
                    </a>
                </div>
            </c:forEach>
        </div>

        <!-- 페이지 네비게이션 -->
        <c:if test="${tradeBoardPage.totalPages > 1}"> <!-- 전체 페이지 수가 1보다 클 때만 표시 -->
            <div class="flex justify-center mt-6">
                <nav class="inline-flex rounded-md shadow-sm" aria-label="Pagination"> <!-- Tailwind CSS 적용 -->
                    <c:if test="${tradeBoardPage.hasPrevious()}">
                        <a href="${pageContext.request.contextPath}/trade/list/${currentPage}?keyword=${keyword}&searchType=${searchType}"
                           class="relative inline-flex items-center px-4 py-2 border border-gray-300 bg-white text-sm font-medium text-gray-700 hover:bg-gray-50">
                            이전
                        </a>
                    </c:if>

                    <c:forEach var="pageNumber" begin="1" end="${tradeBoardPage.totalPages}">
                        <c:choose>
                            <c:when test="${pageNumber - 1 == currentPage}">
                                <span class="bg-red-500 text-white hover:bg-red-700 relative inline-flex items-center px-4 py-2 border text-sm font-medium"
                                      aria-current="page">
                                        ${pageNumber}
                                </span>
                            </c:when>
                            <c:otherwise>
                                <a href="${pageContext.request.contextPath}/trade/list/${pageNumber}?keyword=${keyword}&searchType=${searchType}"
                                   class="bg-white border-gray-300 text-gray-500 hover:bg-gray-50 relative inline-flex items-center px-4 py-2 border text-sm font-medium">
                                        ${pageNumber}
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <c:if test="${tradeBoardPage.hasNext()}">
                        <a href="${pageContext.request.contextPath}/trade/list/${currentPage + 2}?keyword=${keyword}&searchType=${searchType}"
                           class="relative inline-flex items-center px-4 py-2 border border-gray-300 bg-white text-sm font-medium text-gray-700 hover:bg-gray-50 rounded-r-md">
                            다음
                        </a>
                    </c:if>
                </nav>
            </div>
        </c:if>


        <!-- 글쓰기 버튼 -->
        <div class="flex justify-end mt-4">
            <a href="${pageContext.request.contextPath}/trade/write"
               class="bg-red-600 hover:bg-red-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">글쓰기</a>
            <!-- Tailwind CSS 적용 -->
        </div>
    </div>
</main>

<!-- Footer -->
<jsp:include page="../layout/footer.jsp"/>
</body>
</html>