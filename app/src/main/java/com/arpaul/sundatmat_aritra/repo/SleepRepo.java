package com.arpaul.sundatmat_aritra.repo;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.arpaul.sundatmat_aritra.daos.SleepDao;
import com.arpaul.sundatmat_aritra.dataaccess.SleepRoomDB;
import com.arpaul.sundatmat_aritra.models.Sleep;

import java.util.List;

public class SleepRepo {
    private SleepDao sleepDAO;
    private LiveData<List<Sleep>> mUsers;

    public SleepRepo(Application application) {
        SleepRoomDB db = SleepRoomDB.getDatabase(application);
        sleepDAO = db.sleepDao();
        mUsers = sleepDAO.getAllSleeps();
    }

    public LiveData<List<Sleep>> getUsers() {
        return mUsers;
    }

    /**
     * Perfom in a non-UI thread
     */
    public void insert(Sleep user) {
        new insertAsyncTask(sleepDAO).execute(user);
    }

    public void deleteAll() {
        new deleteAsyncTask(sleepDAO).execute();
    }

    private static class insertAsyncTask extends AsyncTask<Sleep, Void, Void> {

        private SleepDao mAsyncTaskDao;

        insertAsyncTask(SleepDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Sleep... params) {

            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Void, Void, Void> {

        private SleepDao mAsyncTaskDao;

        deleteAsyncTask(SleepDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }
}
