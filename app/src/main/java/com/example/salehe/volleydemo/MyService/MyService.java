package com.example.salehe.volleydemo.MyService;

import android.widget.Toast;

import me.tatarka.support.job.JobParameters;
import me.tatarka.support.job.JobService;

/**
 * Created by Salehe on 8/22/2016.
 */
public class MyService extends JobService {
    @Override
    public boolean onStartJob(JobParameters params) {
        Toast.makeText(this,"onStartJob",Toast.LENGTH_SHORT).show();
        jobFinished(params,false);
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
