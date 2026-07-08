package com.cognizant.springlearn;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringCoreApplication {

    public static void main(String[] args) throws Exception {
        // Hands-on 2: SimpleDateFormat
        ApplicationContext dateContext = new ClassPathXmlApplicationContext("date-format.xml");
        SimpleDateFormat format = dateContext.getBean("dateFormat", SimpleDateFormat.class);
        Date date = format.parse("31/12/2018");
        System.out.println("Parsed Date: " + date);

        System.out.println("--- Country Demonstration ---");
        // Hands-on 4 & 5: Country Bean & Scopes
        ApplicationContext countryContext = new ClassPathXmlApplicationContext("country.xml");
        Country country1 = countryContext.getBean("country", Country.class);
        Country country2 = countryContext.getBean("country", Country.class);
        System.out.println("Are both instances identical? " + (country1 == country2));

        // Hands-on 6: Country List
        ArrayList<?> list = countryContext.getBean("countryList", ArrayList.class);
        System.out.println("Country List: " + list);
    }
}
