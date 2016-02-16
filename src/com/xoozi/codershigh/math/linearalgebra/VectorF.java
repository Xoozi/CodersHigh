package com.xoozi.codershigh.math.linearalgebra;

import com.xoozi.codershigh.utils.Utils;


/**
 * 向量(float) 数据封装
 */
public class VectorF{

    /**
     * 向量维度
     */
    public final int        dimension;

    /**
     * 向量数据
     */
    private float[]         _buf;


    /**
     * 只指定维度初始化
     */
    public VectorF(int dimension) {
        if(dimension <= 0){
            throw new RuntimeException("VectorF with zero dimension");
        }
        this.dimension  = dimension;
        _buf        = new float[dimension];
    }

    /**
     * 指定维度和数据初始化
     * 如果提供的数据不足维度
     * 则填充0
     */
    public VectorF(int dimension, float ...args){
        this(dimension);
        Utils.amLog("-----vector dimension:"+dimension);
        int argLen = args.length;
        if(argLen > dimension){
            throw new RuntimeException("VectorF component more than it's dimension");
        }
        for(int i = 0; i < dimension; i++){
            if(i >= argLen){
                _buf[i] = 0;
            }else{
                _buf[i] = args[i];
            }
        }
    }


    /**
     * 初始化二维向量
     */
    public VectorF(float x, float y) {
        this(2, x, y);
    }

    /**
     * 初始化三维向量
     */
    public VectorF(float x, float y, float z) {
        this(3, x, y, z);
    }

    /**
     * 获取特定分量
     * 为了效率不做
     * 让数组去检查访问越界的情况
     */
    public float get(int index){
        return _buf[index];
    }

    /**
     * 设置特定分量
     * 为了效率不做
     * 让数组去检查访问越界的情况
     */
    public void set(int index, float value){
        _buf[index] = value;
    }

    /**
     * 设置一系列分量
     * 如果小于维度则后面填充0
     */
    public void set(float ...args) {
        int argLen = args.length;
        if(argLen > dimension){
            throw new RuntimeException("Set component more than vector's dimension");
        }
        for(int i = 0; i < dimension; i++){
            if(i >= argLen){
                _buf[i] = 0;
            }else{
                _buf[i] = args[i];
            }
        }
    }

    /**
     * 拷贝构造
     */
    public VectorF copy() {
        return new VectorF(dimension, _buf);
    }


    /**
     * 范数
     */
    public float norm() {
        float sum = 0;
        for(float f:_buf){
            sum += f*f;
        }
        return (float) Math.sqrt(sum);
    }


    /**
     * 范数平方, 用于比较而不求值的场合
     */
    public float normSq() {
        float sum = 0;
        for(float f:_buf){
            sum += f*f;
        }
        return sum;
    }


    /**
     * 向量加法
     * 加到本向量的分量上
     */
    public void add(VectorF v) {
        if(v.dimension != dimension){
            throw new RuntimeException("Add two vectors with diffrent dimension");
        }
        for(int i = 0; i < dimension; i++){
            _buf[i] += v.get(i);
        }
    }

    /**
     * 向量加法
     * 加到本向量的分量上
     */
    public void add(float ...args) {
        int argLen = args.length;
        if(argLen > dimension){
            throw new RuntimeException("Add component more than vector's dimension");
        }
        for(int i = 0; i < dimension; i++){
            if(i >= argLen){
            }else{
                _buf[i] += args[i];
            }
        }
    }

    /**
     * 向量加法
     * 新建向量保存结果
     */
    public static VectorF add(VectorF v1, VectorF v2) {
        return add(v1, v2, null);
    }

    /**
     * 向量加法
     * 新建向量保存结果 null == target
     * 或保存到target中 null != target
     */
    public static VectorF add(VectorF v1, VectorF v2, VectorF target) {
        if(v1.dimension != v2.dimension){
            throw new RuntimeException("Add two vectors with diffrent dimension");
        }

        float[] buf = new float[v1.dimension];
        for(int i = 0; i < v1.dimension; i++){
            buf[i] = v1.get(i) + v2.get(i);
        }

        if (target == null) {
            target = new VectorF(v1.dimension, buf);
        } else {
            if(v1.dimension != target.dimension){
                throw new RuntimeException("Add to target vector with diffrent dimension");
            }
            target.set(buf);
        }
        return target;
    }


    /**
     * 向量减法
     * 减到本向量的分量上
     */
    public void sub(VectorF v) {
        if(v.dimension != dimension){
            throw new RuntimeException("Sub two vectors with diffrent dimension");
        }
        for(int i = 0; i < dimension; i++){
            _buf[i] -= v.get(i);
        }
    }

    /**
     * 向量减法
     * 减到本向量的分量上
     */
    public void sub(float ...args) {
        int argLen = args.length;
        if(argLen > dimension){
            throw new RuntimeException("Sub component more than vector's dimension");
        }
        for(int i = 0; i < dimension; i++){
            if(i >= argLen){
            }else{
                _buf[i] -= args[i];
            }
        }
    }

    /**
     * 向量减法
     * 新建向量保存结果
     */
    public static VectorF sub(VectorF v1, VectorF v2) {
        return sub(v1, v2, null);
    }

    /**
     * 向量减法
     * 新建向量保存结果 null == target
     * 或保存到target中 null != target
     */
    public static VectorF sub(VectorF v1, VectorF v2, VectorF target) {
        if(v1.dimension != v2.dimension){
            throw new RuntimeException("Sub two vectors with diffrent dimension");
        }

        float[] buf = new float[v1.dimension];
        for(int i = 0; i < v1.dimension; i++){
            buf[i] = v1.get(i) - v2.get(i);
        }

        if (target == null) {
            target = new VectorF(v1.dimension, buf);
        } else {
            if(v1.dimension != target.dimension){
                throw new RuntimeException("Add to target vector with diffrent dimension");
            }
            target.set(buf);
        }
        return target;
    }



    /**
     * 标量乘法
     * 新建向量保存结果
     */
    public void mult(float n) {
        for(int i = 0; i < dimension; i++){
            _buf[i] *= n;
        }
    }


    /**
     * 标量乘法
     * 新建向量保存结果
     */
    public static VectorF mult(VectorF v, float n) {
        return mult(v, n, null);
    }


    /**
     * 标量乘法
     * 新建向量保存结果 null == target
     * 或保存到target中 null != target
     */
    public static VectorF mult(VectorF v, float n, VectorF target) {
        float[] buf = new float[v.dimension];
        for(int i = 0; i < v.dimension; i++){
            buf[i] = v.get(i) * n;
        }

        if (target == null) {
            target = new VectorF(v.dimension, buf);
        } else {
            if(v.dimension != target.dimension){
                throw new RuntimeException("Mult to target vector with diffrent dimension");
            }
            target.set(buf);
        }
        return target;
    }


    /**
     * 标量除法
     */
    public void div(float n) {
        for(int i = 0; i < dimension; i++){
            _buf[i] /= n;
        }
    }


    /**
     * 标量除法
     * 新建向量保存结果
     */
    public static VectorF div(VectorF v, float n) {
        return div(v, n, null);
    }


    /**
     * 标量除法
     * 新建向量保存结果 null == target
     * 或保存到target中 null != target
     */
    public static VectorF div(VectorF v, float n, VectorF target) {
        float[] buf = new float[v.dimension];
        for(int i = 0; i < v.dimension; i++){
            buf[i] = v.get(i) / n;
        }

        if (target == null) {
            target = new VectorF(v.dimension, buf);
        } else {
            if(v.dimension != target.dimension){
                throw new RuntimeException("Div to target vector with diffrent dimension");
            }
            target.set(buf);
        }
        return target;
    }


    /**
     * 当向量代表空间中的点时
     * 计算距离
     */
    public float dist(VectorF v) {
        if(dimension != v.dimension){
            throw new RuntimeException("Dist to vector with diffrent dimension");
        }
        float sum = 0;
        for(int i = 0; i < dimension; i++){
            sum += get(i) - v.get(i);
        }
        return (float) Math.sqrt(sum);
    }


    /**
     * 当向量代表空间中的点时
     * 计算距离
     */
    public static float dist(VectorF v1, VectorF v2) {
        if(v1.dimension != v2.dimension){
            throw new RuntimeException("Dist of two vectors with diffrent dimension");
        }
        float sum = 0;
        for(int i = 0; i < v1.dimension; i++){
            sum += v1.get(i) - v2.get(i);
        }
        return (float) Math.sqrt(sum);
    }



    /**
     * 计算內积
     */
    public float dot(VectorF v) {
        if(dimension != v.dimension){
            throw new RuntimeException("Dot product to vector with diffrent dimension");
        }
        float sum = 0;
        for(int i = 0; i < dimension; i++){
            sum += get(i) * v.get(i);
        }
        return sum;
    }


    /**
     * 计算內积
     */
    public static float dot(VectorF v1, VectorF v2) {
        return v1.dot(v2);
    }


    /**
     * 计算叉积 非三维向量的情况暂不研究
     * 后续研究将叉积推广到高维可以看:
     * http://spaces.ac.cn/index.php/archives/2219/
     */
    public VectorF cross(VectorF v) {
        return cross(v, null);
    }


    /**
     * 计算叉积 非三维向量的情况暂不研究
     * 后续研究将叉积推广到高维可以看:
     * http://spaces.ac.cn/index.php/archives/2219/
     */
    public VectorF cross(VectorF v, VectorF target) {
        if(3 != v.dimension ||
                (null != target &&3 != target.dimension)){
            throw new RuntimeException("Vector's dimension not equal 3, cross product is invalidate");
        }
        float crossX = get(1) * v.get(2) - v.get(1) * get(2);
        float crossY = get(2) * v.get(0) - v.get(2) * get(0);
        float crossZ = get(0) * v.get(1) - v.get(0) * get(1);

        if (target == null) {
            target = new VectorF(crossX, crossY, crossZ);
        } else {
            target.set(crossX, crossY, crossZ);
        }
        return target;
    }


    /**
     * 计算叉积 非三维向量的情况暂不研究
     * 后续研究将叉积推广到高维可以看:
     * http://spaces.ac.cn/index.php/archives/2219/
     */
    public static VectorF cross(VectorF v1, VectorF v2, VectorF target) {
        if(3 != v1.dimension ||
           3 != v2.dimension ||
                (null != target &&3 != target.dimension)){
            throw new RuntimeException("Vector's dimension not equal 3, cross product is invalidate");
        }
        float crossX = v1.get(1) * v2.get(2) - v2.get(1) * v1.get(2);
        float crossY = v1.get(2) * v2.get(0) - v2.get(2) * v1.get(0);
        float crossZ = v1.get(0) * v2.get(1) - v2.get(0) * v1.get(1);

        if (target == null) {
            target = new VectorF(crossX, crossY, crossZ);
        } else {
            target.set(crossX, crossY, crossZ);
        }
        return target;
    }

    /**
     * 归一化
     */
    public void normalize() {
        float m = norm();
        if (m != 0 && m != 1) {
          div(m);
        }
    }


    /**
     * 归一化
     */
    public VectorF normalize(VectorF target) {
        float m = norm();
        return mult(this, 1/m, target);
    }


    /**
     * 如果向量的范数大于最大值
     * 则将其归一化再标乘以该值
     */
    public void limit(float max) {
        if (normSq() > max*max) {
            normalize();
            mult(max);
        }
    }


    /**
     * 保持原方向设置向量的范数
     */
    public void setNorm(float len) {
        normalize();
        mult(len);
    }


    /**
     * 保持原方向, 设置target向量的范数
     */
    public VectorF setNorm(VectorF target, float len) {
        target = normalize(target);
        target.mult(len);
        return target;
    }



    /**
     * 在本向量和其他向量v之间, 按系数amt进行线性插值
     * 结果保存在本向量
     */
    public void lerp(VectorF v, float amt) {
        if(dimension != v.dimension){
            throw new RuntimeException("lerp with different dimension");
        }
        for(int i = 0; i < dimension; i++){
            float f = get(i);
            set(i, lerp(f, v.get(i), amt));
        }
    }

    /**
     * 在start 和 stop 间 按系数amt进行线性插值
     */
    public static final float lerp(float start, float stop, float amt) {
        return start + (stop-start) * amt;
    }


    /**
     * 在向量v1和向量v2之间, 按系数amt进行线性插值
     * 新建一个维度相同的向量来存放结果
     */
    public static VectorF lerp(VectorF v1, VectorF v2, float amt) {
        VectorF v = v1.copy();
        v.lerp(v2, amt);
        return v;
    }

    /**
     * 在本向量和某标量序列之间, 按系数amt进行线性插值
     * 标量序列长度必须要等于本向量的维度
     * 结果保存在本向量
     */
    public void lerp(float amt, float ...args){
        if(args.length != dimension){
            throw new RuntimeException("lerp with different dimension");
        }
        for(int i = 0; i < dimension; i++){
            float f = get(i);
            set(i, lerp(f, args[i], amt));
        }
    }


    /**
     * 利用余弦定理来计算两个向量间的夹角
     */
    public static float angleBetween(VectorF v1, VectorF v2) {

        double dot = dot(v1, v2);
        double v1norm = v1.norm();
        double v2norm = v2.norm();

        double amt = dot / (v1norm * v2norm);

        if (amt <= -1) {
          return (float)Math.PI;
        } else if (amt >= 1) {
          return 0;
        }
        return (float) Math.acos(amt);
    }


    /**
     * 输出字符串
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = 0; i < dimension; i++){
            float f = get(i);
            sb.append(f);
            if(i < (dimension - 1))
                sb.append(", ");
        }
        sb.append("]'");
        return sb.toString();
    }



    /**
     * 判断向量相等
     * 规则比较严格
     * 非VecotrF对象视为不相等
     * 维度不同视为不相等
     * 只要任一分量不同视为不相等
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof VectorF)) {
            return false;
        }
        final VectorF p = (VectorF) obj;
        if(p.dimension != dimension){
            return false;
        }
        for(int i = 0; i < p.dimension; i++){
            if(p.get(i) != get(i))
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
        for(float f:_buf){
            result = 31 * result + Float.floatToIntBits(f);
        }
        return result;
    }
}
