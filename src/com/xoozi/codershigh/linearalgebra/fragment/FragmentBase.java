package com.xoozi.codershigh.linearalgebra.fragment;



import com.xoozi.codershigh.linearalgebra.ActivityVector;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;

public abstract class FragmentBase extends Fragment{

    protected ActivityVector     _parent;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        _parent = (ActivityVector)activity;
    }


    public void    showFg(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.show(this);
        ft.commit();
    }

    public void    hideFg(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.hide(this);
        ft.commit();
    }

    public void   replaceFg(Activity parent, int rid){
        FragmentTransaction ft = parent.getFragmentManager().beginTransaction();
        ft.replace(rid, this);
        ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
        ft.commit();
    }

    public void   addFg(Activity parent, int rid){
        FragmentTransaction ft = parent.getFragmentManager().beginTransaction();
        ft.add(rid, this);
        ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
        ft.commit();
    }
}
