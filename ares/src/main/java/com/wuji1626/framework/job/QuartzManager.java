package com.wuji1626.framework.job;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.spi.JobFactory;
import org.springframework.context.ApplicationContext;

import com.wuji1626.framework.utils.ApplicationUtil;

/** 
 * @Description: 定时任务管理�?
 *  
 * @ClassName: QuartzManager 
 * @Copyright: Copyright (c) 2014 
 *  
     * @author zhangwh
     * @date 2015-11-26 下午10:49:21 
     * @version V1.0 
 */  
public class QuartzManager {

	private static SchedulerFactory gSchedulerFactory = (SchedulerFactory)ApplicationUtil.getBean("scheduler");
    private static String JOB_GROUP_NAME = "EXTJWEB_JOBGROUP_NAME";  
    private static String TRIGGER_GROUP_NAME = "EXTJWEB_TRIGGERGROUP_NAME"; 
    
    /** 
     * @Description: 添加�?��定时任务，使用默认的任务组名，触发器名，触发器组�?
     *  
     * @param jobName 
     *            任务�?
     * @param cls 
     *            任务 
     * @param time 
     *            时间设置，参考quartz说明文档 
     *  
     * @Title: QuartzManager.java 
     * @Copyright: Copyright (c) 2014 
     *  
     * @author zhangwh
     * @date 2015-11-26 下午10:49:21 
     * @version V1.0 
     */  
    @SuppressWarnings("unchecked")  
    public static void addJob(String jobName, Class cls, String time) {  
        try {  
            Scheduler sched = gSchedulerFactory.getScheduler();  
            sched.setJobFactory((JobFactory)ApplicationUtil.getBean("jobFactory"));
            JobDetail jobDetail = new JobDetail(jobName, JOB_GROUP_NAME, cls);// 任务名，任务组，任务执行�? 
            
            // 触发�? 
            CronTrigger trigger = new CronTrigger(jobName, TRIGGER_GROUP_NAME);// 触发器名,触发器组  
            trigger.setCronExpression(time);// 触发器时间设�? 
            sched.scheduleJob(jobDetail, trigger);  
            // 启动  
            if (!sched.isShutdown()) {  
                sched.start();  
            }  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
  
    /** 
     * @Description: 添加�?��定时任务 
     *  
     * @param jobName 
     *            任务�?
     * @param jobGroupName 
     *            任务组名 
     * @param triggerName 
     *            触发器名 
     * @param triggerGroupName 
     *            触发器组�?
     * @param jobClass 
     *            任务 
     * @param time 
     *            时间设置，参考quartz说明文档 
     *  
     * @Title: QuartzManager.java 
     * @Copyright: Copyright (c) 2014 
     *  
     * @author zhangwh
     * @date 2015-11-26 下午10:49:21 
     * @version V1.0 
     */  
    @SuppressWarnings("unchecked")  
    public static void addJob(String jobName, String jobGroupName,  
            String triggerName, String triggerGroupName, Class jobClass,  
            String time) {  
        try {  
            Scheduler sched = gSchedulerFactory.getScheduler();  
            JobDetail jobDetail = new JobDetail(jobName, jobGroupName, jobClass);// 任务名，任务组，任务执行�? 
            // 触发�? 
            CronTrigger trigger = new CronTrigger(triggerName, triggerGroupName);// 触发器名,触发器组  
            trigger.setCronExpression(time);// 触发器时间设�? 
            sched.scheduleJob(jobDetail, trigger);  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
  
    /** 
     * @Description: 修改�?��任务的触发时�?使用默认的任务组名，触发器名，触发器组名) 
     *  
     * @param jobName 
     * @param time 
     *  
     * @Title: QuartzManager.java 
     * @Copyright: Copyright (c) 2014 
     *  
     * @author zhangwh
     * @date 2015-11-26 下午10:49:21 
     * @version V1.0 
     */  
    @SuppressWarnings("unchecked")  
    public static void modifyJobTime(String jobName, String time, ApplicationContext context) {  
        try {  
            Scheduler sched = gSchedulerFactory.getScheduler();  
            CronTrigger trigger = (CronTrigger) sched.getTrigger(jobName,TRIGGER_GROUP_NAME);  
            if (trigger == null) {  
                return;  
            }  
            String oldTime = trigger.getCronExpression();  
            if (!oldTime.equalsIgnoreCase(time)) {  
                JobDetail jobDetail = sched.getJobDetail(jobName,JOB_GROUP_NAME);  
                Class objJobClass = jobDetail.getJobClass();  
                removeJob(jobName);  
                addJob(jobName, objJobClass, time);  
            }  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
  
    /** 
     * @Description: 修改�?��任务的触发时�?
     *  
     * @param triggerName 
     * @param triggerGroupName 
     * @param time 
     *  
     * @Title: QuartzManager.java 
     * @Copyright: Copyright (c) 2014 
     *  
     * @author zhangwh
     * @date 2015-11-26 下午10:49:21 
     * @version V1.0 
     */  
    public static void modifyJobTime(String triggerName,  
            String triggerGroupName, String time) {  
        try {  
            Scheduler sched = gSchedulerFactory.getScheduler();  
            CronTrigger trigger = (CronTrigger) sched.getTrigger(triggerName,triggerGroupName);  
            if (trigger == null) {  
                return;  
            }  
            String oldTime = trigger.getCronExpression();  
            if (!oldTime.equalsIgnoreCase(time)) {  
                CronTrigger ct = (CronTrigger) trigger;  
                // 修改时间  
                ct.setCronExpression(time);  
                // 重启触发�? 
                sched.resumeTrigger(triggerName, triggerGroupName);  
            }  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
  
    /** 
     * @Description: 移除�?��任务(使用默认的任务组名，触发器名，触发器组名) 
     *  
     * @param jobName 
     *  
     * @Title: QuartzManager.java 
     * @Copyright: Copyright (c) 2014 
     *  
     * @author zhangwh
     * @date 2015-11-26 下午10:49:21 
     * @version V1.0 
     */  
    public static void removeJob(String jobName) {  
        try {  
            Scheduler sched = gSchedulerFactory.getScheduler();  
            sched.pauseTrigger(jobName, TRIGGER_GROUP_NAME);// 停止触发�? 
            sched.unscheduleJob(jobName, TRIGGER_GROUP_NAME);// 移除触发�? 
            sched.deleteJob(jobName, JOB_GROUP_NAME);// 删除任务  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
  
    /** 
     * @Description: 移除�?��任务 
     *  
     * @param jobName 
     * @param jobGroupName 
     * @param triggerName 
     * @param triggerGroupName 
     *  
     * @Title: QuartzManager.java 
     * @Copyright: Copyright (c) 2014 
     *  
     * @author zhangwh
     * @date 2015-11-26 下午10:49:21 
     * @version V1.0 
     */  
    public static void removeJob(String jobName, String jobGroupName,  
            String triggerName, String triggerGroupName) {  
        try {  
            Scheduler sched = gSchedulerFactory.getScheduler();  
            sched.pauseTrigger(triggerName, triggerGroupName);// 停止触发�? 
            sched.unscheduleJob(triggerName, triggerGroupName);// 移除触发�? 
            sched.deleteJob(jobName, jobGroupName);// 删除任务  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
  
    /** 
     * @Description:启动�?��定时任务 
     *  
     *  
     * @Title: QuartzManager.java 
     * @Copyright: Copyright (c) 2014 
     *  
     * @author zhangwh
     * @date 2015-11-26 下午10:49:21 
     * @version V1.0 
     */  
    public static void startJobs() {  
        try {  
            Scheduler sched = gSchedulerFactory.getScheduler();  
            sched.start();  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
  
    /** 
     * @Description:关闭�?��定时任务 
     *  
     *  
     * @Title: QuartzManager.java 
     * @Copyright: Copyright (c) 2014 
     *  
     * @author zhangwh
     * @date 2015-11-26 下午10:49:21 
     * @version V1.0 
     */  
    public static void shutdownJobs() {  
        try {  
            Scheduler sched = gSchedulerFactory.getScheduler();  
            if (!sched.isShutdown()) {  
                sched.shutdown();  
            }  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
}
