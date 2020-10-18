package com.oxygen.oblog.config;

import com.oxygen.oblog.service.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;


/**
 * 错误页面配置类
 * @author oxygen
 * @since 2020/09/27
 *
 */
@Configuration
public class ErrorPageConfig implements ErrorPageRegistrar {
    @Autowired
    private OptionsService optionsService;
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage[] errorPages = new ErrorPage[2];
        try {
            errorPages[0] = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html?site="
                    + optionsService.getSiteUrl());
            errorPages[1] = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/502.html?site="
                    + optionsService.getSiteUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }
        registry.addErrorPages(errorPages);
    }
}
