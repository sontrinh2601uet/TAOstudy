package self.sontq.taostudy.ui.study;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;

import self.sontq.taostudy.R;

public class StudyFragment extends Fragment {

    private StudyViewModel studyViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        studyViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(StudyViewModel.class);
        View root = inflater.inflate(R.layout.fragment_study, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        studyViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}