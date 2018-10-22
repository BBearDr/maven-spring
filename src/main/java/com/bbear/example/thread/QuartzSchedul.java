package com.bbear.example.thread;

import org.quartz.*;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

/**
 * @author junxiongchen
 * @date 2018/10/19
 */
public class QuartzSchedul {
    public static void main(String[] args) {
        test("myjob",3);
        test("myjob1",2);
    }
    private static void test(String name,int waitTime) {
        try {
            JobKey jobKey = new JobKey("test");
            JobDetail jobDetail = JobBuilder.newJob(QuartzBean.class).withIdentity(jobKey).build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("test")
                    .startAt(new Date(System.currentTimeMillis() + 2000))
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(waitTime).repeatForever()).build();
            StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
            Properties props = new Properties();
            System.out.println(QuartzSchedul.class.getResource("/quartz.properties").getFile());
            FileInputStream in = new FileInputStream(QuartzSchedul.class.getResource("/quartz.properties").getFile());
            props.load(in);
            in.close();
            props.put("org.quartz.scheduler.instanceName", name);
            props.put("org.quartz.threadPool.threadCount", "1");//必填
            props.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
            stdSchedulerFactory.initialize(props);
            Scheduler scheduler = stdSchedulerFactory.getScheduler();
            scheduler.start();
            scheduler.scheduleJob(jobDetail, trigger);
        }catch (SchedulerException e) {
            e.printStackTrace();
        }  catch (IOException e) {
            e.printStackTrace();
        }

    }
}
