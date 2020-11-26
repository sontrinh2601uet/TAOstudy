package self.sontq.taostudy.cardview;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import self.sontq.taostudy.R;

public class CustomCardView extends CardView {
    private final int CARD_VIEW = R.layout.card_view;
    protected TextView content;
    protected Button button;

    public CustomCardView(Context context, String content, String buttonName) {
        super(context);

        initView(content, buttonName);
    }

    private void initView(String content, String buttonName) {

    }
}
