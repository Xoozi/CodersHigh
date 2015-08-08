package com.xoozi.codershigh.fragment;

import com.xoozi.codershigh.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentDisplay extends FragmentBase
    implements OnClickListener{


    private int             _base;
    private TextView        _lableDisplay;
    private StringBuilder   _content = new StringBuilder();
    private long            _num;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_display, container, false);

        _lableDisplay = (TextView)v.findViewById(R.id.lable_display);

        _lableDisplay.setOnClickListener(this);
        return v;
    }

    public void clean(){
        _content.setLength(0);
        _updateDisplay();
    }

    public void append(String dig){
        _content.append(dig);
        _updateDisplay();
    }

    public void goBack(){
        int len = _content.length();
        if(len > 0){
            _content.setLength(len-1);
            _updateDisplay();
        }
    }

    public void setBase(int base){
        _base = base;
        _content.setLength(0);
        if(_base == 10){
            _content.append(Long.toString(_num)); 
        }else{
            _content.append(Long.toHexString(_num).toUpperCase());
        }

        _updateDisplay();
    }


    private void _updateDisplay(){
        int len = _content.length();
        if(len > 0){
            try{
                if(_base == 10){
                    _num = Long.decode(_content.toString());
                }else{
                    _num = Long.decode("#"+_content.toString());
                }
            }catch(NumberFormatException e){
                if(len > 0){
                    _content.setLength(len-1);
                }
            }
        }
        _lableDisplay.setText(_content.toString());
    }

    @Override
    public void onClick(View arg0) {
        _parent.exchange();
    }
}
