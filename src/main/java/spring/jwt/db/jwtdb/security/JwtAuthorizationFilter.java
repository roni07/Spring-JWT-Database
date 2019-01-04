package spring.jwt.db.jwtdb.security;

import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import spring.jwt.db.jwtdb.service.CustomUserDetailsService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static spring.jwt.db.jwtdb.security.SecurityConstant.*;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private CustomUserDetailsService customUserDetailsService;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager,
                                  CustomUserDetailsService customUserDetailsService) {
        super(authenticationManager);
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)){
            chain.doFilter(request, response);
            throw new RuntimeException("Token is Missing");
        }

        UsernamePasswordAuthenticationToken upAuth = getAuthenticationToken(request);
        SecurityContextHolder.getContext().setAuthentication(upAuth);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) {

        String token = request.getHeader(HEADER_STRING);

        if (token == null) {
            return null;
        }

        String username = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        return username != null ? new UsernamePasswordAuthenticationToken(userDetails,
                null, userDetails.getAuthorities()) : null;
    }
}
