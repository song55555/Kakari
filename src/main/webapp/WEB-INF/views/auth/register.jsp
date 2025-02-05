<%--
  Created by IntelliJ IDEA.
  User: zhdlxh48
  Date: 2025-01-14
  Time: 오전 11:48
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

    <script>
        function validateUserId() {
            const userId = document.getElementById('user_id').value;
            const userIdRegex = /^[a-z0-9]{8,20}$/;
            const userIdError = document.getElementById('user_id_error');

            if (!userIdRegex.test(userId)) {
                userIdError.textContent = '아이디는 8~20자, 영어 소문자와 숫자로만 구성되어야 합니다.';
                userIdError.classList.remove('hidden');
                return false;
            } else {
                userIdError.textContent = '';
                userIdError.classList.add('hidden');
                return true;
            }
        }

        function validatePassword() {
            const password = document.getElementById('user_password').value;
            const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&#])[A-Za-z\d@$!%*?&#]{8,16}$/;
            const passwordError = document.getElementById('password_error');

            if (!passwordRegex.test(password)) {
                passwordError.innerHTML = '비밀번호는 대문자, 소문자, 숫자, <span class="text-red-600 text-sm cursor-pointer relative group">(특수기호)' +
                    '<span class="hidden group-hover:block absolute left-0 bottom-full mb-2 bg-gray-100 border border-gray-300 p-2 rounded shadow-md whitespace-nowrap">' +
                    '사용 가능한 특수기호: @ $ ! % * ? </span></span>를 포함하며 8자 이상 16자 이하로 설정해야 합니다.';
                passwordError.classList.remove('hidden');
                return false;
            } else {
                passwordError.textContent = '';
                passwordError.classList.add('hidden');
                return true;
            }
        }

        function validateUserName() {
            const userName = document.getElementById('user_name').value;
            const userNameError = document.getElementById('user_name_error');

            if (userName.length < 4 || userName.length > 20) {
                userNameError.textContent = '이름은 4자 이상 20자 이하로 설정해야 합니다.';
                userNameError.classList.remove('hidden');
                return false
            } else {
                userNameError.textContent = '';
                userNameError.classList.add('hidden');
                return true;
            }
        }

        function validateEmail() {
            const userEmail = document.getElementById('user_email').value;
            const userEmailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
            const userEmailError = document.getElementById('user_email_error');

            if (!userEmailRegex.test(userEmail)) {
                userEmailError.textContent = "올바른 이메일 형식이 아닙니다.";
                userEmailError.classList.remove('hidden');
                return false;
            } else {
                userEmailError.textContent = "";
                userEmailError.classList.add('hidden');
                return true;
            }
        }

        function validatePhone() {
            const userPhone = document.getElementById('user_phone').value;
            const userPhoneRegex = /^010\d{8}$/;
            const userPhoneError = document.getElementById('user_phone_error');

            if (!userPhoneRegex.test(userPhone)) {
                userPhoneError.textContent = '전화번호는 010으로 시작하는 11자리 숫자여야 합니다.(- 기호 제외)';
                userPhoneError.classList.remove('hidden');
                return false;
            } else {
                userPhoneError.textContent = "";
                userPhoneError.classList.add('hidden');
                return true;
            }
        }


        function validateForm(event) {
            const userId = document.getElementById('user_id').value;
            const password = document.getElementById('user_password').value;
            const userName = document.getElementById('user_name').value;
            const userEmail = document.getElementById('user_email').value;
            const userPhone = document.getElementById('user_phone').value;

            const userNameError = document.getElementById('user_name_error');
            const userEmailError = document.getElementById('user_email_error');
            const userPhoneError = document.getElementById('user_phone_error');

            let isUserIdValid = validateUserId();
            let isPasswordValid = validatePassword();
            let isUserNameValid = validateUserName();
            let isUserEmailValid = validateEmail();
            let isUserPhoneValid = validatePhone();
            let isValid = true;


            if (!userId) {
                isUserIdValid = false;
                document.getElementById('user_id_error').textContent = "아이디를 입력해주세요.";
                document.getElementById('user_id_error').classList.remove('hidden');
            }

            if (!password) {
                isPasswordValid = false;
                document.getElementById('password_error').textContent = "비밀번호를 입력해주세요.";
                document.getElementById('password_error').classList.remove('hidden');
            }


            if (!userName) {
                isUserNameValid = false;
                userNameError.textContent = "이름을 입력해주세요.";
                userNameError.classList.remove('hidden');
                isValid = false;
            }

            if (!userEmail) {
                isUserEmailValid = false;
                userEmailError.textContent = "이메일을 입력해주세요.";
                userEmailError.classList.remove('hidden');
                isValid = false;
            }

            if (!userPhone) {
                isUserPhoneValid = false;
                userPhoneError.textContent = "전화번호를 입력해주세요.";
                userPhoneError.classList.remove('hidden');
                isValid = false;
            }

            if (!isUserIdValid || !isPasswordValid || !isUserNameValid || !isUserEmailValid || !isUserPhoneValid || !isValid) {
                event.preventDefault();
                return false;
            }
            return true;
        }

        function clearError(elementId) {
            const errorElement = document.getElementById(elementId);
            errorElement.textContent = "";
            errorElement.classList.add('hidden');
        }
    </script>

    <title>회원가입</title>
</head>

<body class="min-h-screen flex flex-col bg-gray-50">
<!-- Header -->
<jsp:include page="../layout/header.jsp"/>

<!-- Main Content -->
<main class="flex-grow flex items-center justify-center">
    <div class="bg-white shadow-md rounded px-8 py-6 w-full max-w-md">
        <h1 class="text-xl font-bold text-center text-red-600 mb-4">회원가입</h1>
        <form action="${pageContext.request.contextPath}/auth/register" method="post" onsubmit="return validateForm(event)" class="space-y-4"
              novalidate>
            <div>
                <label for="user_id" class="block text-gray-700">아이디</label>
                <input type="text" id="user_id" name="user_id" placeholder="8~20자, 영어 소문자와 숫자"
                       class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-red-400"
                       onblur="validateUserId()">
                <p id="user_id_error" class="text-red-500 text-sm mt-1 hidden"></p>
            </div>
            <div class="relative">
                <label for="user_password" class="block text-gray-700">비밀번호</label>
                <input type="password" id="user_password" name="user_password" placeholder="대문자, 소문자, 숫자, 특수기호 포함 8~16자"
                       class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-red-400"
                       onblur="validatePassword()">
                <p id="password_error" class="text-red-500 text-sm mt-1 hidden"></p>
            </div>
            <div>
                <label for="user_name" class="block text-gray-700">이름</label>
                <input type="text" id="user_name" name="user_name" placeholder="4~20자"
                       class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-red-400"
                       oninput="clearError('user_name_error')" onblur="validateUserName()">
                <p id="user_name_error" class="text-red-500 text-sm mt-1 hidden"></p>
            </div>
            <div>
                <label for="user_email" class="block text-gray-700">이메일</label>
                <input type="email" id="user_email" name="user_email"
                       class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-red-400"
                       oninput="clearError('user_email_error')" onblur="validateEmail()">
                <p id="user_email_error" class="text-red-500 text-sm mt-1 hidden"></p>
            </div>
            <div>
                <label for="user_phone" class="block text-gray-700">전화번호</label>
                <input type="tel" id="user_phone" name="user_phone" placeholder="010xxxxxxxx"
                       class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-red-400"
                       oninput="clearError('user_phone_error')" onblur="validatePhone()">
                <p id="user_phone_error" class="text-red-500 text-sm mt-1 hidden"></p>
            </div>
            <button type="submit" class="w-full bg-red-600 text-white py-2 rounded hover:bg-red-500">가입하기</button>
        </form>
    </div>
</main>

<!-- Footer -->
<jsp:include page="../layout/footer.jsp"/>
</body>

</html>
