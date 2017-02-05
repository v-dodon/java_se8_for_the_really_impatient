package com.horstman.javase8.chapter1;

interface RunnableEx {
    void run() throws Exception;

    static Runnable uncheck(RunnableEx runnableEx) {
        return () -> {
            try {
                runnableEx.run();
            } catch (Exception ex) {
                throw new RuntimeException();
            }
        };
    }
}
