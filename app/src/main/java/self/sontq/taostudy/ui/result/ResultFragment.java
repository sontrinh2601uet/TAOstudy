package self.sontq.taostudy.ui.result;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import self.sontq.taostudy.R;
import self.sontq.taostudy.api.ServiceBuilder;
import self.sontq.taostudy.ui.homework.HomeWorkFragment;

import static self.sontq.taostudy.MainActivity.getCookie;

public class ResultFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_homework, container, false);

        CookieManager.getInstance().setCookie("http://aigle.blife.ai/tao/Main/index", getCookie());
        WebView webView = root.findViewById(R.id.webView);
        webView.setWebViewClient(new ResultFragment.MyBrowser());
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        getListData();
        return root;
    }

    private void getListData() {
        ServiceBuilder.getApiServiceAuthen().getTest("results", "taoOutcomeUi").enqueue(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println(call);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println(call);
            }
        });
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}