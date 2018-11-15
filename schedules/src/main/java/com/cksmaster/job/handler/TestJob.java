package com.cksmaster.job.handler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.springframework.stereotype.Component;

/**
 * @Author: cks
 * @Date: Created by 2018/11/15 15:08
 * @Package: com.cksmaster.job.handler
 * @Description: job任务执行器
 */
@JobHandler(value = "testJob")
@Component
public class TestJob extends IJobHandler {

    @Override
    public ReturnT<String> execute(String s) throws Exception {
        System.out.println("XXL-JOB，参数:" + s);
        return SUCCESS;
    }
}
