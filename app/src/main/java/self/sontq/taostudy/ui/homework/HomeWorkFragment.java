package self.sontq.taostudy.ui.homework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import self.sontq.taostudy.R;

import static self.sontq.taostudy.MainActivity.cookie;

public class HomeWorkFragment extends Fragment {

    private static String testAddress = "http://aigle.blife.ai/taoDelivery/DeliveryServer";
    private HomeWorkViewModel homeWorkViewModel;
    private ListView listHomework;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeWorkViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(HomeWorkViewModel.class);
        View root = inflater.inflate(R.layout.fragment_homework, container, false);
//        listHomework = root.findViewById(R.id.list_homework);

        CookieManager.getInstance().setCookie("http://aigle.blife.ai/taoDelivery/DeliveryServer/index", cookie);
        WebView webView = root.findViewById(R.id.webView);
        webView.setWebViewClient(new HomeWorkFragment.MyBrowser());
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl("http://aigle.blife.ai/taoDelivery/DeliveryServer/index");

        homeWorkViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}