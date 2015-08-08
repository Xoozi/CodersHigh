package com.xoozi.codershigh;

import com.xoozi.codershigh.fragment.FragmentDisplay;
import com.xoozi.codershigh.fragment.FragmentInputDec;
import com.xoozi.codershigh.fragment.FragmentInputHex;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;

public class ActivityDecHex extends Activity{

    private FragmentDisplay     _fgDisplay;
    private FragmentInputDec    _fgInputDec;
    private FragmentInputHex    _fgInputHex;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dec_hex);
        _initWork();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case android.R.id.home:
                finish();
            break;
        }
        return true;
    }


    /**
     * 交换输入区
     */
    public void exchange(){
        
        if(_fgInputHex.isVisible()){
            _fgInputHex.hideFg(); 
            _fgInputDec.showFg();
        }else if(_fgInputDec.isVisible()){
            _fgInputDec.hideFg();
            _fgInputHex.showFg();
        }
    }




    private void _initWork(){
        
        ActionBar actionBar = getActionBar();
        int flag = ActionBar.DISPLAY_HOME_AS_UP;
        int change = actionBar.getDisplayOptions() ^ flag;
        actionBar.setDisplayOptions(change, flag);
        actionBar.setBackgroundDrawable(new ColorDrawable(0x60009010));

        _fgDisplay = new FragmentDisplay();
        _fgDisplay.replaceFg(this, R.id.field_display);

        _fgInputDec = new FragmentInputDec(_fgDisplay);
        _fgInputDec.addFg(this, R.id.field_input);
        _fgInputDec.hideFg();

        _fgInputHex = new FragmentInputHex(_fgDisplay);
        _fgInputHex.addFg(this, R.id.field_input);
    }
}
