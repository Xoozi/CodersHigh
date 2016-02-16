package com.xoozi.codershigh.linearalgebra.fragment;


import com.xoozi.codershigh.R;
import com.xoozi.codershigh.math.linearalgebra.MatrixF;

import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;

public class FragmentMatrixEdit extends DialogFragment implements
        OnClickListener {

    private ConfirmImp _ci;
    private MatrixF    _matrix;
    private EditText   _e00;
    private EditText   _e01;
    private EditText   _e10;
    private EditText   _e11;


    public FragmentMatrixEdit(MatrixF matrix, ConfirmImp ci){
        _ci = ci;
        _matrix = matrix;
    }

    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        
        View v = inflater.inflate(R.layout.fragment_matrix_edit, container, false);
        v.findViewById(R.id.btn_ok).setOnClickListener(this);
        v.findViewById(R.id.btn_cancle).setOnClickListener(this);

        _e00 = (EditText) v.findViewById(R.id.edit_e00);
        _e01 = (EditText) v.findViewById(R.id.edit_e01);
        _e10 = (EditText) v.findViewById(R.id.edit_e10);
        _e11 = (EditText) v.findViewById(R.id.edit_e11);

        _e00.setText(String.format("%.02f", _matrix.getItem(0, 0)));
        _e01.setText(String.format("%.02f", _matrix.getItem(0, 1)));
        _e10.setText(String.format("%.02f", _matrix.getItem(1, 0)));
        _e11.setText(String.format("%.02f", _matrix.getItem(1, 1)));

        return v;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_ok:
                float e00, e01, e10, e11;
                e00 = Float.parseFloat(_e00.getText().toString());
                e01 = Float.parseFloat(_e01.getText().toString());
                e10 = Float.parseFloat(_e10.getText().toString());
                e11 = Float.parseFloat(_e11.getText().toString());
                _ci.onOk(e00, e10, e01, e11);
                break;

            case R.id.btn_cancle:
                break;
        }
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.remove(this).commit();
    }


    public interface ConfirmImp{
        public void onOk(float e00, float e10, float e01, float e11);
    }
}
