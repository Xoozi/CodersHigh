package com.xoozi.codershigh.math.linearalgebra;

/**
 * 主列矩阵
 */
public class MatrixF{

    public final int            row;
    public final int            col;

    private final VectorF[]     _colVectors;

    /**
     *  指定行列数来初始化矩阵
     */
    public MatrixF(int row, int col){
        this.row = row;
        this.col = col;

        _colVectors = new VectorF[col];
        for(int i = 0; i < col; i++){
            _colVectors[i] = new VectorF(row);
        }
    }

    /**
     * 拷贝构造
     */
    public MatrixF(MatrixF mat){
        this.row = mat.row;
        this.col = mat.col;
        _colVectors = new VectorF[col];
        for(int i = 0; i < col; i++){
            _colVectors[i] = mat._colVectors[i].copy();
        }
    }

    /**
     * 由于是主列矩阵, 设置列数据是主要的初始化方式
     */
    public void setCol(int col, float ...args){
        _colVectors[col].set(args);
    }

    /**
     * 设置行数据, 用于行变换, 
     */
    public void setRow(int row, float ...args){
        int argLen = args.length;
        if(argLen != col){
            throw new RuntimeException("Set row's args length must equal row count");
        }
        for(int i = 0; i < argLen; i++){
            _colVectors[i].set(row, args[i]);
        }
    }

    /**
     * 设置某项的值
     */
    public void setItem(int row, int col, float value){
        _colVectors[col].set(row, value);
    }

    /**
     * 获取某项的值
     */
    public float getItem(int row, int col){
        return _colVectors[col].get(row);
    }

    /**
     * 交换行
     */
    public void exchangeRow(int row1, int row2){
        if(row1 == row2){
            return;
        }
        float tmp;
        for(int i = 0; i < col; i++){
            tmp = _colVectors[i].get(row1);
            _colVectors[i].set(row1, _colVectors[i].get(row2));
            _colVectors[i].set(row2, tmp);
        }
    }

    /**
     * 对行做数乘
     */
    public void multRow(int row, float factor){
        for(int i = 0; i < col; i++){
            _colVectors[i].set(row, factor * _colVectors[i].get(row));
        }
    }

    /**
     * 对行做除法
     */
    public void divRow(int row, float divisor){
        for(int i = 0; i < col; i++){
            _colVectors[i].set(row,  _colVectors[i].get(row) / divisor);
        }
    }

    /**
     * 将源行的数值 乘以因子 加到目标行上
     */
    public void addMultRow(int src, int dest, float factor){
        float tmp;
        for(int i = 0; i < col; i++){
            tmp = factor * _colVectors[i].get(src);
            _colVectors[i].set(dest, _colVectors[i].get(dest) + tmp);
        }
    }

    /**
     * 将源行的数值 乘以因子 减到目标行上
     */
    public void subMultRow(int src, int dest, float factor){
        float tmp;
        for(int i = 0; i < col; i++){
            tmp = factor * _colVectors[i].get(src);
            _colVectors[i].set(dest, _colVectors[i].get(dest) - tmp);
        }
    }

    /**
     * 将源行的数值 除以除数  加到目标行上
     */
    public void addDivRow(int src, int dest, float divsior){
        float tmp;
        for(int i = 0; i < col; i++){
            tmp =  _colVectors[i].get(src) / divsior;
            _colVectors[i].set(dest, _colVectors[i].get(dest) + tmp);
        }
    }

    /**
     * 将源行的数值 除以除数 减到目标行上
     */
    public void subDivRow(int src, int dest, float divisor){
        float tmp;
        for(int i = 0; i < col; i++){
            tmp = _colVectors[i].get(src) / divisor;
            _colVectors[i].set(dest, _colVectors[i].get(dest) - tmp);
        }
    }

    /**
     * 向量左乘矩阵
     */
    public VectorF  multVector(VectorF v){
        if(col != v.dimension){
            throw new RuntimeException("Can't mult a vector to matrix, with vector's dimension not equals to matrix's col ("
                    +col+"-"+v.dimension+")");
        }
        VectorF ret = new VectorF(row);
        for(int col_index= 0; col_index < col; col_index ++){
            VectorF colVec = _colVectors[col_index];
            float factor = v.get(col_index);
            for(int row_index = 0; row_index < row; row_index++){
                ret.set(row_index, ret.get(row_index) + factor * colVec.get(row_index));
            }
        }
        return ret;
    }

    /**
     * 输出字符串
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int index_row = 0; index_row < row; index_row++){
            for(int index_col = 0; index_col < col; index_col++){
                sb.append(getItem(index_row, index_col)+"\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * 判断矩阵相等
     * 非MatrixF对象视为不相等
     * 维度不同视为不相等
     * 只要任一列向量不同视为不相等
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MatrixF)) {
            return false;
        }
        final MatrixF m = (MatrixF) obj;
        if(m.row != row || m.col != col){
            return false;
        }
        for(int i = 0; i < m.col; i++){
            if(_colVectors[i].equals(m._colVectors[i]))
                return false;
        }
        return true;
    }


    /**
     * 哈希码简易算法
     */
    @Override
    public int hashCode() {
        int result = 1;
        for(int i = 0; i < col; i++){
            result = 33 * result + _colVectors[i].hashCode();
        }
        return result;
    }
}
