package com.itshizhan.beatbox;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BeatBoxActivity extends SingleFragmentActivity {

    /********
     * 实现基类的createFragment方法
     */
    @Override
    protected Fragment createFragment() {
        return BeatBoxFragment.newInstance();
    }

}
