package com.example.privmall;

import com.example.privmall.dto.request.principal.UserAccountPrincipal;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.Set;

public class WithAuthUserSecurityContextFactory
        implements WithSecurityContextFactory<WithAuthUser> {

    @Override
    public SecurityContext createSecurityContext(WithAuthUser annotation) {
        String email = annotation.email();
        String[] roles = annotation.roles();

        UserAccountPrincipal principal = UserAccountPrincipal.of(
                null, email, "revi1337", null, Set.of(roles)
        );

        UsernamePasswordAuthenticationToken token =
                UsernamePasswordAuthenticationToken.authenticated(principal, null, principal.getAuthorities());
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(token);
        return context;
    }

}
