package com.arpaul.sundatmat_aritra.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.arpaul.sundatmat_aritra.models.Sleep;
import com.arpaul.sundatmat_aritra.repo.SleepRepo;

import java.util.List;

public class SleepVM extends AndroidViewModel {
    private SleepRepo mRepository;
    private LiveData<List<Sleep>> mAllUsers;

    /**
     * Never pass context to the instances.
     * @param application
     */
    public SleepVM(Application application) {
        super(application);
        mRepository = new SleepRepo(application);
        mAllUsers = mRepository.getUsers();
    }

    public LiveData<List<Sleep>> getSleep() { return mAllUsers; }

    public void insert(Sleep sleep) {
        mRepository.insert(sleep);
    }

    public void deleteAll() {
        mRepository.deleteAll();
    }
}
