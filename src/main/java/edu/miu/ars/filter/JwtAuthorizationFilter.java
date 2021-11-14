package edu.miu.ars.filter;

import edu.miu.ars.constant.AppConstant;
import edu.miu.ars.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private static final Logger log = Logger.getLogger("JwtAuthorizationFilter");

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        String header = JwtUtil.getToken(request);
        if (StringUtils.isEmpty(header) || !header.startsWith(AppConstant.TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = JwtUtil.getToken(request);
        if (StringUtils.isNotEmpty(token)) {
            try {
                byte[] signingKey = AppConstant.JWT_SECRET.getBytes();

                Jws<Claims> parsedToken = Jwts.parser()
                        .setSigningKey(signingKey)
                        .parseClaimsJws(token.replace("Bearer", ""));

                String username = parsedToken
                        .getBody()
                        .getSubject();

                List<SimpleGrantedAuthority> authorities = ((List<?>) parsedToken.getBody()
                        .get("rol")).stream()
                        .map(authority -> new SimpleGrantedAuthority((String) authority))
                        .collect(Collectors.toList());

                if (StringUtils.isNotEmpty(username)) {
                    return new UsernamePasswordAuthenticationToken(username, null, authorities);
                } else {
                    return null;
                }
            } catch (ExpiredJwtException exception) {
                log.warning("Request to parse expired JWT : {} failed : {} " + token + " " + exception.getMessage());
            } catch (UnsupportedJwtException exception) {
                log.warning("Request to parse unsupported JWT : {} failed : {} " + token + " " + exception.getMessage());
            } catch (MalformedJwtException exception) {
                log.warning("Request to parse invalid JWT : {} failed : {} " + token + " " + exception.getMessage());
            } catch (IllegalArgumentException exception) {
                log.warning("Request to parse empty or null JWT : {} failed : {} " + token + " " + exception.getMessage());
            }
        }

        return null;
    }

}
