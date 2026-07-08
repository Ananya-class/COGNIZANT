package com.cognizant.springlearn;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringCoreApplication {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Hands-on 2
        SimpleDateFormat format = context.getBean("dateFormat", SimpleDateFormat.class);
        Date date = format.parse("31/12/2018"); // [cite: 60]
        System.out.println("Date parsed successfully: " + date);

        // Hands-on 4 & 5
        Country c1 = context.getBean("country", Country.class);
        Country c2 = context.getBean("country", Country.class);
        System.out.println("Is instance matching? (Singleton Check): " + (c1 == c2));

        // Hands-on 6
        ArrayList<?> list = context.getBean("countryList", ArrayList.class);
        System.out.println("Loaded Countries: " + list);
    }
}
