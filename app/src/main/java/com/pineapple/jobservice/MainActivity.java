package com.pineapple.jobservice;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pineapple.jobservice.Service.JobServiceTest;

public class MainActivity extends AppCompatActivity {

    JobServiceTest jobServiceTest;
    private static int jobId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startJobService();
    }

    public void startJobService() {
        ComponentName componentName = new ComponentName(this, JobServiceTest.class);
        JobInfo.Builder builder = new JobInfo.Builder(jobId++, componentName);

        builder.setMinimumLatency(5 * 1000);
        builder.setOverrideDeadline(50 * 1000);

        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED);

        builder.setRequiresDeviceIdle(true);

        builder.setRequiresCharging(false);

        JobScheduler jobScheduler = (JobScheduler) getApplication().getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(builder.build());
    }

    public void cancelAllJobs() {
        JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.cancelAll();
    }
}
