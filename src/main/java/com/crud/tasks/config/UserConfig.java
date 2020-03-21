package com.crud.tasks.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class UserConfig {
    @Value("${info.app.owner.name}")
    private String name;

    @Value("${info.app.owner.surname}")
    private String surname;
}
