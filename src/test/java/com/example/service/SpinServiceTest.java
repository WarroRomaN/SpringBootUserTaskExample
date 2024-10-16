package com.example.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.Random;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class SpinServiceTest {

    @InjectMocks
    private SpinService spinService;

    @Mock
    private Random random;
    @Mock
    private Map<String, Integer> slotRepository;


    @Test
    void testSpinVin() {
        given(random.nextInt(anyInt())).willReturn(3);
        given(slotRepository.get(anyString())).willReturn(1000);

        int result = spinService.spin("admin", 10);
        int expected = 1010;

        Assertions.assertEquals(expected, result);

    }

    @Test
    void testSpinLoos() {
        given(random.nextInt(anyInt())).willReturn(4).willReturn(7);
        given(slotRepository.get(anyString())).willReturn(1000);

        int result = spinService.spin("admin", 10);
        int expected = 990;

        Assertions.assertEquals(expected, result);

    }

}
