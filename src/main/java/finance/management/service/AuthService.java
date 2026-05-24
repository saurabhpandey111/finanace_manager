package finance.management.service;

import jakarta.servlet.http.HttpServletRequest;
import finance.management.dto.request.LoginRequest;
import finance.management.dto.request.RegisterRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

/**
 * Service interface for authentication operations.
 */
public interface AuthService {

    /**
     * Registers a new user.
     * @return map containing message and userId
     */
    Map<String, Object> register(RegisterRequest request);

    /**
     * Authenticates a user and creates a session.
     * @return map containing login message
     */
    Map<String, Object> login(LoginRequest request, HttpServletRequest httpRequest, HttpServletResponse httpResponse);

    /**
     * Invalidates the current user session.
     * @return map containing logout message
     */
    Map<String, Object> logout(HttpServletRequest request, HttpServletResponse response);
}
