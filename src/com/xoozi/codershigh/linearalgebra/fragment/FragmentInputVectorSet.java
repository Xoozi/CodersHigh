package com.xoozi.codershigh.linearalgebra.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xoozi.codershigh.R;

public class FragmentInputVectorSet extends FragmentBase implements
        View.OnClickListener {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_input_vector_set, container, false);

        v.findViewById(R.id.btn_0).setOnClickListener(this);
        v.findViewById(R.id.btn_1).setOnClickListener(this);
        v.findViewById(R.id.btn_2).setOnClickListener(this);
        v.findViewById(R.id.btn_3).setOnClickListener(this);
        v.findViewById(R.id.btn_4).setOnClickListener(this);
        v.findViewById(R.id.btn_5).setOnClickListener(this);
        v.findViewById(R.id.btn_6).setOnClickListener(this);
        v.findViewById(R.id.btn_7).setOnClickListener(this);
        v.findViewById(R.id.btn_8).setOnClickListener(this);
        v.findViewById(R.id.btn_9).setOnClickListener(this);


        v.findViewById(R.id.btn_dot).setOnClickListener(this);
        v.findViewById(R.id.btn_comma).setOnClickListener(this);

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
                //_fgDisplay.clean();
                break;

            case R.id.btn_back:
                //_fgDisplay.goBack();
                break;

            default:
                //_fgDisplay.append(((Button)view).getText);
                break;
        }
    }
}

