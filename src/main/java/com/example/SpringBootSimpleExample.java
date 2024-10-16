package com.example;

import com.example.dto.UserDTO;
import com.example.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@SpringBootApplication
public class SpringBootSimpleExample implements CommandLineRunner {

    private static final String DOT = ".";
    private static final String AT = "@";
    private static final String USER = "user";
    private final Random random = new Random();
    private final UserService userService;

    public SpringBootSimpleExample(UserService userService) {
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSimpleExample.class, args);

    }

    private final static List<String> FIRST_NAMES = List.of("Roman", "Anna", "Victor", "Olha", "Pavel", "Oleg", "Viktor", "Katya", "James", "Mary", "John", "Patricia", "Robert", "Jennifer", "Michael", "Linda", "William", "Elizabeth", "David", "Barbara", "Richard", "Susan", "Joseph", "Jessica", "Thomas", "Sarah", "Charles", "Karen", "Christopher", "Nancy", "Daniel", "Lisa", "Matthew", "Margaret", "Anthony", "Betty", "Mark", "Sandra", "Donald", "Ashley", "Steven", "Dorothy", "Paul", "Kimberly", "Andrew", "Emily", "Joshua", "Donna", "Kenneth", "Michelle", "Kevin", "Carol", "Brian", "Amanda", "George", "Melissa", "Edward", "Deborah");
    private final static List<String> LAST_NAMES = List.of("Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Rodriguez", "Martinez", "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson", "Thomas", "Taylor", "Moore", "Jackson", "Martin", "Lee", "Perez", "Thompson", "White", "Harris", "Sanchez", "Clark", "Ramirez", "Lewis", "Robinson", "Walker", "Young", "Allen", "King", "Wright", "Scott", "Torres", "Nguyen", "Hill", "Flores", "Green", "Adams", "Nelson", "Baker", "Hall", "Rivera", "Campbell", "Mitchell", "Carter", "Roberts");
    private final static List<String> EMAILS = List.of("gmail.com", "outlook.com", "yahoo.com", "icloud.com", "protonmail.com", "zoho.com", "hotmail.com");


    @Override
    public void run(String... args) throws Exception {

        List<UserDTO> users = IntStream.range(0, 9).mapToObj(operand -> {
            String username = USER + operand;
            String firstName = FIRST_NAMES.get(random.nextInt(FIRST_NAMES.size()));
            String lastName = LAST_NAMES.get(random.nextInt(LAST_NAMES.size()));
            String email = firstName + DOT + lastName + AT + EMAILS.get(random.nextInt(EMAILS.size()));
            return UserDTO.builder()
                    .username(username)
                    .firstName(firstName)
                    .lastName(lastName)
                    .email(email)
                    .build();
        }).toList();

        users = userService.save(users);
        users.forEach(System.out::println);
    }
}
