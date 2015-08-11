package com.xoozi.codershigh;


import com.xoozi.codershigh.dom.DOMItem;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

public class ActivityBase extends Activity{


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String name = intent.getStringExtra(DOMItem.KEY_NAME);
        setTitle(name);

        ActionBar actionBar = getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(0x60009010));
    }
}
