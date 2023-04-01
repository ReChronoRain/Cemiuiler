package com.sevtinge.cemiuiler.ui.fools.a;

import androidx.fragment.app.Fragment;
import com.sevtinge.cemiuiler.R;
import com.sevtinge.cemiuiler.ui.base.BaseAppCompatActivity;
import com.sevtinge.cemiuiler.ui.base.SubFragment;

public class Tip1Activity extends BaseAppCompatActivity {

    @Override
    public Fragment initFragment() {
        return new com.sevtinge.cemiuiler.ui.fools.a.Tip1Activity.Tip1Fragment();
    }

    public static class Tip1Fragment extends SubFragment {

        @Override
        public int getContentResId() {
            return R.xml.fools_a_tip_1;
        }
    }


}
