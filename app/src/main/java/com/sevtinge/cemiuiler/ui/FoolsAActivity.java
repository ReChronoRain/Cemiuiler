package com.sevtinge.cemiuiler.ui;

import androidx.fragment.app.Fragment;
import com.sevtinge.cemiuiler.R;
import com.sevtinge.cemiuiler.ui.base.BaseAppCompatActivity;
import com.sevtinge.cemiuiler.ui.base.SubFragment;

public class FoolsAActivity extends BaseAppCompatActivity {

    @Override
    public Fragment initFragment() {
        return new FoolsAActivity.FoolsFragment();
    }

    public static class FoolsFragment extends SubFragment {

        @Override
        public int getContentResId() {
            return R.xml.fools_main_a;
        }
    }


}
