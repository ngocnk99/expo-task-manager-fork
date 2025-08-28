package expo.modules.taskManager;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;

public class TaskJobService extends JobService {
  @Override
  public boolean onStartJob(JobParameters params) {
    // Null check for params
    if (params == null) {
      return false;
    }
    try {
      // Get context with null check
      Context context = getApplicationContext();
      if (context == null) {
        return false;
      }
      // Create TaskService instance
      TaskService taskService = new TaskService(context);
      if (taskService == null) {
        return false;
      }
      return taskService.handleJob(this, params);
    } catch (Exception e) {
      // Ensure job is finished even if there's an exception
      try {
        jobFinished(params, false);
      } catch (Exception finishException) {
      }
      return false;
    }
  }

  @Override
  public boolean onStopJob(JobParameters params) {
    // Null check for params
    if (params == null) {
      return false;
    }
    try {
      // Get context with null check
      Context context = getApplicationContext();
      if (context == null) {
        return false;
      }
      // Use existing taskService instance or create new one
       TaskService  taskService = new TaskService(context);
      if (taskService == null) {
        return false;
      }
      return taskService.cancelJob(this, params);
    } catch (Exception e) {
      return false;
    }
  }

}