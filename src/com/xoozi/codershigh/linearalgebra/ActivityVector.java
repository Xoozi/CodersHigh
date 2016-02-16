package com.xoozi.codershigh.linearalgebra;

import com.xoozi.codershigh.ActivityBase;
import com.xoozi.codershigh.R;
import com.xoozi.codershigh.linearalgebra.fragment.FragmentInputVectorSet;
import com.xoozi.codershigh.math.linearalgebra.MatrixF;
import com.xoozi.codershigh.math.linearalgebra.VectorF;
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

        VectorF v   = new VectorF(2, 3, -5);
        VectorF vp  = new VectorF(-5, 2, 3);

        v.normalize();
        vp.normalize();

        float dp = v.dot(vp);
        double theta = Math.acos(dp);
        double deg = Math.toDegrees(theta);

        Utils.amLog("v normalize:"+v.toString());
        Utils.amLog("vp normalize:"+vp.toString());
        Utils.amLog("dp:"+dp+", theta:"+theta+", degress:"+deg);

        /*
        MatrixF A = new MatrixF(2, 5);
        A.setCol(0, 0, 0);
        A.setCol(1, 2, 0);
        A.setCol(2, 2, 1);
        A.setCol(3, 0, 1);
        A.setCol(4, 1, 0.5f);
        Utils.amLog("Matrix A:\n"+A.toString());

        VectorF x = new VectorF(5, 0.8f, 1.06f, 1.2f, 1, 0.934f);
        Utils.amLog("\nVector x:\n"+x.toString());

        VectorF b = A.multVector(x);
        Utils.amLog("\nVector b:\n"+b.toString());
        b.div(5);
        Utils.amLog("\nVector b/5:\n"+b.toString());*/
    }
}
