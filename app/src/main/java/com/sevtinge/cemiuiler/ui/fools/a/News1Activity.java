package com.sevtinge.cemiuiler.ui.fools.a;

import androidx.fragment.app.Fragment;
import com.sevtinge.cemiuiler.R;
import com.sevtinge.cemiuiler.ui.base.BaseAppCompatActivity;
import com.sevtinge.cemiuiler.ui.base.SubFragment;

public class News1Activity extends BaseAppCompatActivity {

    @Override
    public Fragment initFragment() {
        return new com.sevtinge.cemiuiler.ui.fools.a.News1Activity.News1Fragment();
    }

    public static class News1Fragment extends SubFragment {

        @Override
        public int getContentResId() {
            return R.xml.fools_a_news_1;
        }
    }


}
