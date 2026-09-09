package utilities;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataConfig {
    private Locale locale = new Locale("en");
    private Faker faker = new Faker(locale);

    public static DataConfig getData(){
        return new DataConfig();
    }

    public String getFirstName(){
        return faker.address().firstName();
    }

    public String getLastName(){
        return faker.address().lastName();
    }

    public String getCompanyName(){
        return faker.company().name();
    }

    public String getEmail(){
        return faker.internet().emailAddress();
    }

    public String getPhoneNumber(){
        return faker.phoneNumber().phoneNumber();
    }

    public String getFullName(){
        return faker.name().fullName();
    }


}

