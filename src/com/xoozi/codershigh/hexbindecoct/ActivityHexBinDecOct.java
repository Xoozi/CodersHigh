package com.xoozi.codershigh.hexbindecoct;

import com.xoozi.codershigh.ActivityBase;
import com.xoozi.codershigh.R;
import com.xoozi.codershigh.hexbindecoct.fragment.FragmentDisplay;
import com.xoozi.codershigh.hexbindecoct.fragment.FragmentInputDec;
import com.xoozi.codershigh.hexbindecoct.fragment.FragmentInputHex;

import android.os.Bundle;

public class ActivityHexBinDecOct extends ActivityBase{

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

        _fgDisplay = new FragmentDisplay();
        _fgDisplay.replaceFg(this, R.id.field_display);

        _fgInputDec = new FragmentInputDec(_fgDisplay);
        _fgInputDec.addFg(this, R.id.field_input);
        _fgInputDec.hideFg();

        _fgInputHex = new FragmentInputHex(_fgDisplay);
        _fgInputHex.addFg(this, R.id.field_input);
    }
}
