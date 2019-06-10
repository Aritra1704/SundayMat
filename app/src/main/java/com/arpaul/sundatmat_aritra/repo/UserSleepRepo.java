package com.arpaul.sundatmat_aritra.repo;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.arpaul.sundatmat_aritra.daos.SleepDao;
import com.arpaul.sundatmat_aritra.daos.UserSleepDao;
import com.arpaul.sundatmat_aritra.dataaccess.SleepRoomDB;
import com.arpaul.sundatmat_aritra.models.Sleep;
import com.arpaul.sundatmat_aritra.models.UserSleep;

import java.util.List;

public class UserSleepRepo {
    private UserSleepDao sleepDAO;
    private LiveData<List<UserSleep>> mUsers;

    public UserSleepRepo(Application application) {
        SleepRoomDB db = SleepRoomDB.getDatabase(application);
        sleepDAO = db.userSleepDao();
        mUsers = sleepDAO.getAllUserSleepRecs();
    }

    public LiveData<List<UserSleep>> getUsers() {
        return mUsers;
    }

    /**
     * Perfom in a non-UI thread
     */
    public void insert(UserSleep user) {
        new insertAsyncTask(sleepDAO).execute(user);
    }

    public void deleteAll() {
        new deleteAsyncTask(sleepDAO).execute();
    }

    private static class insertAsyncTask extends AsyncTask<UserSleep, Void, Void> {

        private UserSleepDao mAsyncTaskDao;

        insertAsyncTask(UserSleepDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final UserSleep... params) {

            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Void, Void, Void> {

        private UserSleepDao mAsyncTaskDao;

        deleteAsyncTask(UserSleepDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }
}
