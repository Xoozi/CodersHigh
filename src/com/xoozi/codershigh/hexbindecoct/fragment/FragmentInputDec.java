package com.xoozi.codershigh.hexbindecoct.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xoozi.codershigh.R;

public class FragmentInputDec extends FragmentBase
        implements View.OnClickListener {

    private FragmentDisplay     _fgDisplay;

    public FragmentInputDec(FragmentDisplay fgDisplay){
        _fgDisplay = fgDisplay;
    }

    @Override
    public void showFg() {
        super.showFg();
        _fgDisplay.setBase(10);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_input_dec, container, false);

        View btn = v.findViewById(R.id.btn_0);
        btn.setTag("0");
        btn.setOnClickListener(this);

        btn = v.findViewById(R.id.btn_1);
        btn.setTag("1");
        btn.setOnClickListener(this);

        btn = v.findViewById(R.id.btn_2);
        btn.setTag("2");
        btn.setOnClickListener(this);

        btn = v.findViewById(R.id.btn_3);
        btn.setTag("3");
        btn.setOnClickListener(this);

        btn = v.findViewById(R.id.btn_4);
        btn.setTag("4");
        btn.setOnClickListener(this);

        btn = v.findViewById(R.id.btn_5);
        btn.setTag("5");
        btn.setOnClickListener(this);

        btn = v.findViewById(R.id.btn_6);
        btn.setTag("6");
        btn.setOnClickListener(this);

        btn = v.findViewById(R.id.btn_7);
        btn.setTag("7");
        btn.setOnClickListener(this);

        btn = v.findViewById(R.id.btn_8);
        btn.setTag("8");
        btn.setOnClickListener(this);

        btn = v.findViewById(R.id.btn_9);
        btn.setTag("9");
        btn.setOnClickListener(this);


        v.findViewById(R.id.btn_clear).setOnClickListener(this);
        v.findViewById(R.id.btn_back).setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {

        if(!isVisible())
            return;

        switch(view.getId()){
            case R.id.btn_clear:
                _fgDisplay.clean();
                break;

            case R.id.btn_back:
                _fgDisplay.goBack();
                break;

            default:
                _fgDisplay.append(view.getTag().toString());
                break;
        }
    }
}

