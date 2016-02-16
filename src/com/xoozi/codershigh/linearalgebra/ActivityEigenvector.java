package com.xoozi.codershigh.linearalgebra;

import java.util.ArrayList;
import java.util.List;

import com.xoozi.codershigh.ActivityBase;
import com.xoozi.codershigh.R;
import com.xoozi.codershigh.linearalgebra.fragment.FragmentMatrixEdit;
import com.xoozi.codershigh.linearalgebra.fragment.FragmentMatrixEdit.ConfirmImp;
import com.xoozi.codershigh.math.linearalgebra.MatrixF;
import com.xoozi.codershigh.math.linearalgebra.VectorF;
import com.xoozi.codershigh.utils.Utils;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ActivityEigenvector extends ActivityBase implements ConfirmImp{

    private MatrixF A;
    private List<PointF>    _trace = new ArrayList<PointF>();
    private float           _theta = 0;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(new SampleView(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.eigenvector_actions, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.action_matrix_edit:{
                _editMatrix();
            break;
            }

        }
        return true;
    }

    @Override
    public void onOk(float e00, float e10, float e01, float e11) {
        Utils.amLog(String.format("Edit Matrix ok: %.02f, %.02f, %.02f, %.02f", 
                                e00, e10, e01, e11));
        A.setItem(0, 0, e00);
        A.setItem(1, 0, e10);
        A.setItem(0, 1, e01);
        A.setItem(1, 1, e11);
        _theta = 0;
        _trace.clear();
    }

    private void    _editMatrix(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        //ft.addToBackStack(null);

        DialogFragment dlg = new FragmentMatrixEdit(A, this);
        dlg.show(ft, "dialog");
    }

    public class SampleView extends View {

        private float   _step = 0.01f;
        private Paint   _paintUniCircle;
        private Paint   _paintOrgVector;
        private Paint   _paintLambdaVector;
        private Paint   _paintTrace;
        private Paint   _paintPointOrg;
        private Paint   _paintPointLambda;
        private VectorF _vectorOrg;
        private VectorF _vectorLambda;

        public SampleView(Context context) {
            super(context);
            _vectorOrg = new VectorF(2);
            _vectorOrg.set((float)Math.cos(_theta), (float)Math.sin(_theta));
            A = new MatrixF(2, 2);
            A.setCol(0, 0.8f, 0.2f);
            A.setCol(1, 0.3f, 0.7f);
            _vectorLambda = A.multVector(_vectorOrg);

            _paintUniCircle = new Paint();
            _paintUniCircle.setColor(0xFF000000);
            _paintUniCircle.setStyle(Paint.Style.STROKE);
            _paintUniCircle.setAntiAlias(true);
            _paintUniCircle.setTextSize(30);

            _paintOrgVector = new Paint();
            _paintOrgVector.setStrokeWidth(2);
            _paintOrgVector.setColor(0xFF0000FF);
            _paintOrgVector.setStyle(Paint.Style.STROKE);
            _paintOrgVector.setAntiAlias(true);

            _paintLambdaVector = new Paint();
            _paintLambdaVector.setStrokeWidth(2);
            _paintLambdaVector.setColor(0xFFFF0000);
            _paintLambdaVector.setStyle(Paint.Style.STROKE);
            _paintLambdaVector.setAntiAlias(true);

            _paintTrace = new Paint();
            _paintTrace.setColor(0xFFFF0000);
            _paintTrace.setStyle(Paint.Style.STROKE);
            _paintTrace.setAntiAlias(true);

            _paintPointOrg = new Paint();
            _paintPointOrg.setColor(0xEE0000EE);
            _paintPointOrg.setAntiAlias(true);

            _paintPointLambda = new Paint();
            _paintPointLambda.setColor(0xEEEE0000);
            _paintPointLambda.setAntiAlias(true);
        }


        @Override
        protected void onDraw(Canvas canvas) {
            float centerX, centerY, unitRadius;

            canvas.drawColor(0xFFEEEEEE);

            centerX = getWidth()/2;
            centerY = getHeight()/2;
            unitRadius = centerX < centerY ? centerX : centerY;
            unitRadius *= 0.8f;

            canvas.drawCircle(centerX, centerY, unitRadius, _paintUniCircle);

            _drawVectors(canvas, centerX, centerY, unitRadius);
            _drawTrace(canvas);

            _drawMatrix(canvas, centerX);

            invalidate();
        }

        private void    _drawMatrix(Canvas canvas, float x){

            String rowStr;

            rowStr = String.format("%.2f %.2f", A.getItem(0, 0), A.getItem(0, 1));
            canvas.drawText(rowStr, x, 60, _paintUniCircle);
            rowStr = String.format("%.2f %.2f", A.getItem(1, 0), A.getItem(1, 1));
            canvas.drawText(rowStr, x, 100, _paintUniCircle);
        }

        private void    _drawVectors(Canvas canvas, float centerX, float centerY, float radius){
            _theta += _step;
            _vectorOrg.set((float)Math.cos(_theta), (float)Math.sin(_theta));
            _vectorOrg.setNorm(radius);
            canvas.drawLine(centerX, centerY, centerX+_vectorOrg.get(0), centerY+_vectorOrg.get(1), _paintOrgVector);
            canvas.drawCircle(centerX+_vectorOrg.get(0), centerY+_vectorOrg.get(1), 5, _paintPointOrg);

            _vectorLambda = A.multVector(_vectorOrg);
            canvas.drawLine(centerX, centerY, centerX+_vectorLambda.get(0), centerY+_vectorLambda.get(1), _paintLambdaVector);
            canvas.drawCircle(centerX+_vectorLambda.get(0), centerY+_vectorLambda.get(1), 5, _paintPointLambda);
            if(_theta < (2*Math.PI)){
                _trace.add(new PointF(centerX+_vectorLambda.get(0), centerY+_vectorLambda.get(1)));
            }
        }

        private void    _drawTrace(Canvas canvas){
            Path trace = new Path();
            if(_trace.size() > 2){
                PointF start = _trace.get(0);
                trace.moveTo(start.x, start.y);
                for(int i = 1; i < _trace.size(); i++){
                    PointF p = _trace.get(i);
                    trace.lineTo(p.x, p.y);
                }

                canvas.drawPath(trace, _paintTrace);
            }
        }

    }

}
