package com.example.a1_cesar_gaviriasepulveda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PaymentRepository {
    private static final List<Payment> PAYMENTS = new ArrayList<>();

    public static void add(Payment payment) {
        PAYMENTS.add(payment);
    }

    public static List<Payment> all() {
        return Collections.unmodifiableList(PAYMENTS);
    }
}