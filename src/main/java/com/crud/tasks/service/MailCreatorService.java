package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.UserConfig;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private UserConfig userConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private DbService dbService;

    public String buildTrelloCardEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("regards", "Best regards");
        context.setVariable("company", adminConfig.getCompanyName());
        context.setVariable("userName", userConfig.getName());
        context.setVariable("userSurname", userConfig.getSurname());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildScheduledEmail(String message) {
        Context context = new Context();
        context.setVariable("message", "Currently in database you got: " + message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend");
        context.setVariable("button", "Kodilla CRUD App");
        context.setVariable("tasks", dbService);

        context.setVariable("regards", "Best regards");
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("user_config", userConfig);

        return templateEngine.process("mail/created-scheduled-mail", context);
    }
}
