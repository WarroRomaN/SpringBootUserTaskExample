package com.example.entity;

import lombok.Getter;

@Getter
public enum Symblos {

    A(2),B(3),C(4),D(5),E(6);

    final int value;

    Symblos(int value) {
        this.value = value;
    }

}
