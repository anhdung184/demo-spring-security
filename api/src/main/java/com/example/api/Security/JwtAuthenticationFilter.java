package com.example.api.Security;

import com.example.api.Model.JwtTokenProvider;
import com.example.api.Model.MyUserDetails;
import com.example.api.Repository.AccountRepository;
import com.example.api.Service.impl.AccountServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class    JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AccountServiceImpl accountService;
    private static final Logger log = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            // Lấy jwt từ request
            String jwt = getJwtFromRequest(request);

            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                // Lấy id user từ chuỗi jwt
                Long userId = tokenProvider.getUserIdFromJWT(jwt);
                // Lấy thông tin người dùng từ id
                UserDetails userDetails = accountService.loadUserById(userId);
                if(userDetails != null) {
                    // Nếu người dùng hợp lệ, set thông tin cho Seturity Context
                    UsernamePasswordAuthenticationToken
                            authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (Exception ex) {
            log.error("failed on set user authentication", ex);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        // Kiểm tra xem header Authorization có chứa thông tin jwt không
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
