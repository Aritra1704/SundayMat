package com.arpaul.sundatmat_aritra.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.arpaul.sundatmat_aritra.models.Sleep;
import com.arpaul.sundatmat_aritra.models.UserSleep;
import com.arpaul.sundatmat_aritra.repo.SleepRepo;
import com.arpaul.sundatmat_aritra.repo.UserSleepRepo;

import java.util.List;

public class UserSleepVM extends AndroidViewModel {
    private UserSleepRepo mRepository;
    private LiveData<List<UserSleep>> mAllUsers;

    /**
     * Never pass context to the instances.
     * @param application
     */
    public UserSleepVM(Application application) {
        super(application);
        mRepository = new UserSleepRepo(application);
        mAllUsers = mRepository.getUsers();
    }

    public LiveData<List<UserSleep>> getSleep() { return mAllUsers; }

    public void insert(UserSleep sleep) {
        mRepository.insert(sleep);
    }

    public void deleteAll() {
        mRepository.deleteAll();
    }
}
