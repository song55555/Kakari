package com.gbi.kakari.service;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbi.kakari.dto.AuthDTO;
import com.gbi.kakari.dto.UserDTO;
import com.gbi.kakari.entity.user.UserInfo;
import com.gbi.kakari.exception.auth.RegisterException;
import com.gbi.kakari.repository.user.UserInfoRepository;
import com.gbi.kakari.type.user.Role;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthService {
	private static final String userIdAttribute = "userId";
	private static final String userRoleAttribute = "userRole";
	private static final String userIdRegex = "^[a-z0-9]{8,20}$";
	private static final String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,16}$";
	private static final String emailRegex = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
	private static final String phoneRegex = "^010\\d{8}$";
	private final UserInfoRepository userRepository;

	@Autowired
	public AuthService(UserInfoRepository userInfoRepository) {
		this.userRepository = userInfoRepository;
	}

	public boolean login(AuthDTO authDTO, HttpSession session) {
		UserInfo user = userRepository.findById(authDTO.getUserId()).orElse(null);
		if (user == null) {
			return false;
		}
		if (!user.getUserPassword().equals(authDTO.getUserPassword())) {
			return false;
		}

		log.debug("Set session id '{}' and role '{}'", user.getUserId(), user.getUserRole());
		setSessionUserId(session, user.getUserId());
		setSessionUserRole(session, user.getUserRole());

		return true;
	}

	public void logout(HttpSession session) {
		session.invalidate();
	}

	public void register(UserDTO userDTO, Role role) throws RegisterException {
		validateRegisterRequest(userDTO);

		UserInfo user = UserInfo.builder()
			.userId(userDTO.getUserId())
			.userPassword(userDTO.getUserPassword())
			.userName(userDTO.getUserName())
			.userEmail(userDTO.getUserEmail())
			.userPhoneNum(userDTO.getUserPhone())
			.userRole(role)
			.build();
		userRepository.save(user);
	}

	public boolean isLoggedIn(HttpSession session) {
		String userId = getSessionUserId(session);
		if (userId == null) {
			return false;
		}

		log.debug("Already has session with userId '{}'... Checking logged User is valid", userId);
		if (isValidUserId(userId)) {
			log.debug("User '{}' is not valid... invalidating session", userId);
			session.invalidate();
			return false;
		}

		log.debug("User '{}' is logged in", userId);
		return true;
	}

	public boolean isAdminLoggedIn(HttpSession session) {
		if (!isLoggedIn(session)) {
			return false;
		}

		Role userRole = getSessionUserRole(session);
		return userRole.equals(Role.ADMIN);
	}

	public boolean isValidUserId(String userId) {
		if (!userRepository.existsById(userId)) {
			log.debug("User '{}' is not found in database...", userId);
			return true;
		}
		return false;
	}

	public String getSessionUserId(HttpSession session, boolean invalidate) {
		return (String)getSessionAttribute(session, userIdAttribute, invalidate);
	}

	public String getSessionUserId(HttpSession session) {
		return getSessionUserId(session, true);
	}

	public void setSessionUserId(HttpSession session, String userId) {
		session.setAttribute(userIdAttribute, userId);
	}

	public Role getSessionUserRole(HttpSession session, boolean invalidate) {
		Object userRole = getSessionAttribute(session, userRoleAttribute, invalidate);
		log.debug("User role: {}", userRole);
		if (userRole == null) {
			return Role.INVALID;
		}
		return Role.valueOf((int)userRole);
	}

	public Role getSessionUserRole(HttpSession session) {
		return getSessionUserRole(session, true);
	}

	public void setSessionUserRole(HttpSession session, int rawUserRole) {
		session.setAttribute(userRoleAttribute, rawUserRole);
	}

	private void validateRegisterRequest(UserDTO userDTO) throws RegisterException {
		if (!Pattern.matches(userIdRegex, userDTO.getUserId())) {
			throw new RegisterException("아이디는 8~20자, 영어 소문자와 숫자로만 구성되어야 합니다.");
		}
		if (!Pattern.matches(passwordRegex, userDTO.getUserPassword())) {
			throw new RegisterException("비밀번호는 대문자, 소문자, 숫자, 특수기호를 포함하며 8~16자여야 합니다.");
		}
		if (userDTO.getUserName().length() < 4 || userDTO.getUserName().length() > 20) {
			throw new RegisterException("이름은 4자 이상 20자 이하로 설정해야 합니다.");
		}
		if (!Pattern.matches(emailRegex, userDTO.getUserEmail())) {
			throw new RegisterException("올바른 이메일 형식이 아닙니다.");
		}
		if (!Pattern.matches(phoneRegex, userDTO.getUserPhone())) {
			throw new RegisterException("전화번호는 010으로 시작하는 11자리 숫자여야 합니다.(- 기호 제외)");
		}
	}

	private Object getSessionAttribute(HttpSession session, String key, boolean invalidate) {
		if (session == null) {
			log.debug("Session is null");
			return null;
		}

		Object attribute = session.getAttribute(key);
		if (attribute == null) {
			log.debug("No {} in session", key);
			if (invalidate) {
				log.debug("Invalidating session...");
				session.invalidate();
			}
			return null;
		}

		return attribute;
	}
}
