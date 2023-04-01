package com.sevtinge.cemiuiler.ui.fools.b;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.sevtinge.cemiuiler.R;
import com.sevtinge.cemiuiler.ui.base.BaseAppCompatActivity;
import com.sevtinge.cemiuiler.ui.base.SubFragment;
import com.sevtinge.cemiuiler.utils.SdkHelper;
import moralnorm.preference.SwitchPreference;

public class Tip1Activity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Fragment initFragment() {
        return new Tip1Activity.Tip1Fragment();
    }

    public static class Tip1Fragment extends SubFragment {


        @Override
        public int getContentResId() {
            return R.xml.fools_b_tip_1;
        }

        @Override
        public void initPrefs() {
        }
    }
}
