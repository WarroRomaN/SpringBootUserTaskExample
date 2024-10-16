package com.example.service;

import com.example.entity.Symblos;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import static com.example.entity.Symblos.*;

@Service
public class SpinService {

    private final Random random;
    private final Map<String, Integer> slotRepository;


    public SpinService(Random random, Map<String, Integer> slotRepository) {
        this.random = random;
        this.slotRepository = slotRepository;
    }

    public int spin(String userId, int bet) {

        Integer amount = slotRepository.get(userId);

        List<Symblos> symblosList = List.of(
                A, A, A, A, A, A,
                B, B, B, B, B,
                C, C, C, C,
                D, D, D,
                E, E);

        Symblos symbol1 = symblosList.get(random.nextInt(symblosList.size()));
        Symblos symbol2 = symblosList.get(random.nextInt(symblosList.size()));
        Symblos symbol3 = symblosList.get(random.nextInt(symblosList.size()));

        int result = amount - bet;

        if (Objects.equals(symbol1, symbol2) && Objects.equals(symbol1, symbol3)) {
            result += bet * symbol1.getValue();
        }
        slotRepository.put(userId, result);
        return result;
    }
}

// 1000-3
