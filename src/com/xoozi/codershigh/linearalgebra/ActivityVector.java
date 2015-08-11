package com.xoozi.codershigh.linearalgebra;

import com.xoozi.codershigh.ActivityBase;
import com.xoozi.codershigh.R;
import com.xoozi.codershigh.linearalgebra.fragment.FragmentInputVectorSet;
import com.xoozi.codershigh.math.linearalgebra.MatrixF;
import com.xoozi.codershigh.utils.Utils;

import android.os.Bundle;

public class ActivityVector extends ActivityBase{

    private FragmentInputVectorSet      _fgInputSet;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vector);
        _initWork();

    }


    private void _initWork(){
        
        _fgInputSet = new FragmentInputVectorSet();
        _fgInputSet.addFg(this, R.id.field_input);

        MatrixF m = new MatrixF(4, 4);
        m.setRow(0, 1, 3, 5, 7);
        m.setRow(1, 11, 13, 17 ,19);
        m.setRow(2, 23, 29, 31, 37);
        m.setRow(3, 41, 43, 47, 51);
        Utils.amLog(m.toString());

        m.subMultRow(0, 1, 11);
        Utils.amLog(m.toString());
    }
}
