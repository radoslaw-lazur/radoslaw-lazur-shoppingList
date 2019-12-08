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

import java.util.List;

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

    //@Scheduled(cron = "0 0 10 * * *")
    @Scheduled(fixedDelay = 10000)
    public void sendInformationEmail() {
        long size = productRepository.count();

        simpleEmailService.send(new Mail(
                adminConfig.getAdminMail(),
                "",
                SUBJECT,
                "Currently in database you have got: " + size + " " + (size == 1 ? "task:": "tasks:")
                , checkProducts())
        );
    }

    private String checkProducts() {
        List<Product> products = productRepository.findAll();
        String list = "";
        for(int i = 0; i < products.size(); i++) {
            list = list + products.get(i) + "\n";
        }
        return list;
    }
}
