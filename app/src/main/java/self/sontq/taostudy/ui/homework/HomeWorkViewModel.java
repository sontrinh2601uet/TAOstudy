package self.sontq.taostudy.ui.homework;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class HomeWorkViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeWorkViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}