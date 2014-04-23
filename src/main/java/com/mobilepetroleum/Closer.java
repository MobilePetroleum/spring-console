package com.mobilepetroleum;

import java.util.concurrent.CountDownLatch;

class Closer {
    private final CountDownLatch countDownLatch;

    public Closer(CountDownLatch countDownLatch) { this.countDownLatch = countDownLatch; }

    @SuppressWarnings("UnusedDeclaration")
    public void close() { countDownLatch.countDown(); }
}