package spider.jb51;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * 希望多个线程顺序执行
 * Spider01
 * Spider02
 */
public class Main {
    public static void main(String[] args) {
//        Spider01 s01 = new Spider01();
        /**//*s01.start();*/
        /*ExecutorService executor = ThreadPoolExecutor.
                Executors.newSingleThreadExecutor();*/
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();

        ExecutorService pool = new ThreadPoolExecutor(5,200,0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<Runnable>(1024),namedThreadFactory,new ThreadPoolExecutor.AbortPolicy());
//        pool.su
//        pool.submit(new Spider01());
        pool.submit(new Spider03());
//        pool.submit(new Thread3());
        pool.shutdown();
    }
}
