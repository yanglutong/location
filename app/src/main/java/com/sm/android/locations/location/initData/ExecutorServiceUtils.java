package com.sm.android.locations.location.initData;

import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceUtils {
   /**一、CashedThreadPool：缓存线程池，能够自动创建，删除线程对象，并且对已生成的线程对象反复利用，减少创建和回收的次数，降低系统开销
    * //缓存线程池，会自动控制线程数量，自动创建和销毁,并且循环利用已创建的线程
    * @description
    * @param
    * @return
    * @author lutong
    * @time 2021/6/23 8:36
    */
   public static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();







   /**
    * 二、FixedThreadPool：定量线程池，在构造时会设定线程数量上限，用法和缓存线程池差不多
    * //在创建线程池的时候会设定最大线程数。
    * @description
    * @param
    * @return
    * @author lutong
    * @time 2021/6/23 8:36
    */
   public static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);//这里的参数“2”就是线程池做能创建线程的上线





   /**三、SingleThreadPool：单一线程池，线程池只能有一个线程
    *  //单一线程的线程池
    构造方法
    * @description
    * @param
    * @return
    * @author lutong
    * @time 2021/6/23 8:37
    */
   public static ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();







   /**四、ScheduleThreadPool：最后一种是一个计划性的线程池，他可以设置延迟多少时间启动线程，也可以设置周期性启动线程
    //计划性线程池
    * @description
    * @param
    * @return
    * @author lutong
    * @time 2021/6/23 8:38
    */
   public static ScheduledExecutorService scheduledThreadPool = Executors.newSingleThreadScheduledExecutor();



   private void TestScheduledThreadPool() {
      ScheduledExecutorService scheduledThreadPool = Executors.newSingleThreadScheduledExecutor();
      Log.d("MainActivity", "TestScheduledThreadPool");
      //Test1:延迟执行
      /**
       * 第一个参数是Runnable，第二个参数是时间，第三个参数是时间的单位
       */
//        scheduledThreadPool.schedule(new Runnable() {
//            @Override
//            public void run() {
//                Log.d("MainActivity", "TestScheduledThreadPool.run");
//            }
//        }, 3, TimeUnit.SECONDS);

      //Test2:延迟执行，并且周期性执行
      /**
       * 第一个参数是Runnable，第二个参数是延迟时间，第三个参数是巡行间隔，第四个参数是时间的单位
       */
      scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
         @Override
         public void run() {
            Log.d("MainActivity", "TestScheduledThreadPool.run");
         }
      },2,3,TimeUnit.SECONDS);

   }


   private void TestSingleThreadPool() {
      ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
      for (int i = 0; i < 10; i++) {
         final int tmp = i;
         singleThreadPool.execute(new Runnable() {
            @Override
            public void run() {
               Log.d("MainActivity", "Thread Name:" + Thread.currentThread().getName() + " Thread ID:" + Thread.currentThread().getId());
               Log.d("MainActivity", "i:" + tmp);
               try {
                  Thread.sleep(2000);
               } catch (InterruptedException e) {
                  e.printStackTrace();
               }
            }
         });
      }
   }

   private void TestFixedThreadPool() {
      ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
      for (int i = 0; i < 10; i++) {
         final int tmp = i;
         fixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
               Log.d("MainActivity", "Thread Name:" + Thread.currentThread().getName() + " Thread ID:" + Thread.currentThread().getId());
               Log.d("MainActivity", "i:" + tmp);
               try {
                  Thread.sleep(2000);
               } catch (InterruptedException e) {
                  e.printStackTrace();
               }
            }
         });
      }
   }

   private void TestCashedThreadPool() {
      ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
      for (int i = 0; i < 10; i++) {
         final int tmp = i;
         cachedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
               Log.d("MainActivity", "Thread Name:" + Thread.currentThread().getName() + " Thread ID:" + Thread.currentThread().getId());
               Log.d("MainActivity", "i:" + tmp);
            }
         });
      }
   }
}