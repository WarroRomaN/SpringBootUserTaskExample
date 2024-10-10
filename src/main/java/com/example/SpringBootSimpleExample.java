package com.example;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringBootSimpleExample implements CommandLineRunner {

    private final UserRepository userRepository;

    public SpringBootSimpleExample(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSimpleExample.class, args);

    }

    private final static List<String> FIRST_NAMES = Arrays.asList("Roman", "Anna", "Victor", "Olha", "Pavel", "Oleg", "Viktor", "Katya", "James", "Mary", "John", "Patricia", "Robert", "Jennifer", "Michael", "Linda", "William", "Elizabeth", "David", "Barbara", "Richard", "Susan", "Joseph", "Jessica", "Thomas", "Sarah", "Charles", "Karen", "Christopher", "Nancy", "Daniel", "Lisa", "Matthew", "Margaret", "Anthony", "Betty", "Mark", "Sandra", "Donald", "Ashley", "Steven", "Dorothy", "Paul", "Kimberly", "Andrew", "Emily", "Joshua", "Donna", "Kenneth", "Michelle", "Kevin", "Carol", "Brian", "Amanda", "George", "Melissa", "Edward", "Deborah");
    private final static List<String> LAST_NAMES = Arrays.asList("Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Rodriguez", "Martinez", "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson", "Thomas", "Taylor", "Moore", "Jackson", "Martin", "Lee", "Perez", "Thompson", "White", "Harris", "Sanchez", "Clark", "Ramirez", "Lewis", "Robinson", "Walker", "Young", "Allen", "King", "Wright", "Scott", "Torres", "Nguyen", "Hill", "Flores", "Green", "Adams", "Nelson", "Baker", "Hall", "Rivera", "Campbell", "Mitchell", "Carter", "Roberts");

    @Override
    public void run(String... args) throws Exception {

        List<User> users = Stream.generate(o -> (User.builder().username("user0").password("user0").firstName("Roman").lastName("Halimon").email("Roman.Halimon@outlook.com").build())).limit(10).toList();


        List<User> users =


                List < User > users = Arrays.asList(
                        User.builder().username("user0").password("user0").firstName("Roman").lastName("Halimon").email("Roman.Halimon@outlook.com").build(),
                        User.builder().username("user1").password("user1").firstName("Anna").lastName("Halimon").email("Anna.Halimon@outlook.com").build()
                );
        users = userRepository.saveAll(users);
        users.stream().map(UserMapper::toDTO).forEach(System.out::println);
    }
}
