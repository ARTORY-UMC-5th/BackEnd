package com.example.demo.global.config.jpa;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
//import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {
    private final HttpServletRequest httpServletRequest;

    @Autowired
    public AuditorAwareImpl(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public Optional<String> getCurrentAuditor() {
        String modifiedBy = httpServletRequest.getRequestURI();
        if(!StringUtils.hasText(modifiedBy)){
            modifiedBy = "unknown";
        }
        return Optional.of(modifiedBy);
    }

}
