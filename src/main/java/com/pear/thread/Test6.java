package com.pear.thread;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * callable的应用
 */
public class Test6 {

    /**
     * 使用ExecutorService
     */
    @Test
    public void test1() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> submit = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 99;
            }
        });

        try {
            System.out.println(submit.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用FutureTask
     */
    @Test
    public void test2() throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 99;
            }
        });
        futureTask.run();
        System.out.println(futureTask.get());
    }

}
