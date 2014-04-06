package com.mobilepetroleum;

import java.util.concurrent.CountDownLatch;

public class Closer {
    private final CountDownLatch countDownLatch;

    public Closer(CountDownLatch countDownLatch) { this.countDownLatch = countDownLatch; }

    public void close() { countDownLatch.countDown(); }
}