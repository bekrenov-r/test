package com.lordsofcookies.moodapp.config;

import com.lordsofcookies.moodapp.model.TelegramUser;
import com.lordsofcookies.moodapp.user.TelegramUserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class MockAuthFilter extends OncePerRequestFilter {
    @Value("${spring.security.request-matchers.permit-all}")
    private String[] permitAllMatchers;

    private final TelegramUserRepository telegramUserRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("+");
        if(request.getHeader(HttpHeaders.AUTHORIZATION) != null){
            String userId = request.getHeader(HttpHeaders.AUTHORIZATION);
            TelegramUser user = telegramUserRepository.findByTelegramId(userId);
            if(user == null)
                throw new EntityNotFoundException("User with id " + userId + "not found");
            Authentication authToken = new MockAuthenticationToken(null, user);
            SecurityContextHolder.getContext().setAuthentication(authToken);
        } else throw new RuntimeException("Authorization is required for this request");
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request){
        return Arrays.stream(permitAllMatchers)
                .map(s -> {
                    String[] arr = s.split(" ");
                    return new AntPathRequestMatcher(arr[1], arr[0]);
                }).anyMatch(m -> m.matches(request));
    }
}
