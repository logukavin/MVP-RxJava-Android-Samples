package com.example.rxjava.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rxjava.utils.LoggerUtils;

import java.util.List;

public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = LoggerUtils.makeLogTag(BaseActivity.class);

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public View getView() {
        return findViewById(android.R.id.content);
    }

    public void setHeaderTitle(String title) {
        if (getSupportActionBar() != null) getSupportActionBar().setTitle(title);
    }

    public void hideSoftInput() {
        try {
            InputMethodManager manager =
                    (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
        } catch (NullPointerException e) {
            LoggerUtils.e(TAG, "Exception in hideSoftInput", e);
        }
    }

    public Fragment getVisibleFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null) {
            for (android.support.v4.app.Fragment fragment : fragments) {
                if (fragment != null && fragment.isVisible()) return fragment;
            }
        }
        return null;
    }


    public void showToast(String msg) {
        try {
            Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } catch (Exception e) {
            Log.e("Something went wrong : ", e.getMessage());
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        boolean userEvent = false;
        try {
            View v = getCurrentFocus();
            userEvent = super.dispatchTouchEvent(event);
            if (v instanceof EditText) {
                View w = getCurrentFocus();
                int scr[] = new int[2];
                if (w != null) {
                    w.getLocationOnScreen(scr);
                }
                assert w != null;
                float x = event.getRawX() + w.getLeft() - scr[0];
                float y = event.getRawY() + w.getTop() - scr[1];
                if (event.getAction() == MotionEvent.ACTION_UP && (x < w.getLeft()
                        || x >= w.getRight()
                        || y < w.getTop()
                        || y > w.getBottom())) {
                    hideSoftInput();
                }
            }
        } catch (Exception e) {
            LoggerUtils.e(TAG, "Exception in dispatchTouchEvent", e);
        }
        return userEvent;
    }

    public abstract Context context();
}
