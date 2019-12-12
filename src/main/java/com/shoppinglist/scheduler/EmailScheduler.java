package com.shoppinglist.scheduler;

import com.shoppinglist.domain.Mail;
import com.shoppinglist.domain.Product;
import com.shoppinglist.mapper.ProductMapper;
import com.shoppinglist.repository.ProductRepository;
import com.shoppinglist.service.AdminConfig;
import com.shoppinglist.service.DbService;
import com.shoppinglist.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class EmailScheduler {
    @Autowired
    private SimpleEmailService simpleEmailService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AdminConfig adminConfig;
    @Autowired
    private DbService dbService;
    @Autowired
    private ProductMapper productMapper;
    private static final String SUBJECT = "Products to buy. Reminder :)";

    @Scheduled(cron = "0 0 15 * * *")
    public void sendInformationEmail() {
        long size = productRepository.count();

        simpleEmailService.send(new Mail(
                adminConfig.getAdminMail(),
                null,
                SUBJECT,
                "Currently in database you have "  + size  + (size == 1 ? " product" : " products")
                        + " to buy:"  + "\n" + printProducts())
        );
    }

    private String printProducts() {
        return productRepository.findAll().stream()
                .map(Product::getTitle)
                .collect(Collectors.joining("\n"));
    }
}
