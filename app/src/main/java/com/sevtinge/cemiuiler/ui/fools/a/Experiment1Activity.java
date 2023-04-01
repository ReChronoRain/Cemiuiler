package com.sevtinge.cemiuiler.ui.fools.a;

import androidx.fragment.app.Fragment;
import com.sevtinge.cemiuiler.R;
import com.sevtinge.cemiuiler.ui.base.BaseAppCompatActivity;
import com.sevtinge.cemiuiler.ui.base.SubFragment;

public class Experiment1Activity extends BaseAppCompatActivity {

    @Override
    public Fragment initFragment() {
        return new com.sevtinge.cemiuiler.ui.fools.a.Experiment1Activity.Experiment1Fragment();
    }

    public static class Experiment1Fragment extends SubFragment {

        @Override
        public int getContentResId() {
            return R.xml.fools_a_experiment_1;
        }
    }


}
