package com.bbear.example.thread;

import org.apache.commons.lang3.time.DateUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * @author junxiongchen
 * @date 2018/10/19
 */
public class QuartzBean implements Job{
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(Thread.currentThread().getName() + " Quartz is begin " + new Date(System.currentTimeMillis()));
    }
}
