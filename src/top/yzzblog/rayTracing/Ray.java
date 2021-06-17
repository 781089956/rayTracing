package top.yzzblog.rayTracing;

public class Ray {
    private Vec3 o;  //源点
    private Vec3 d;  //方向

    public Ray() {
    }

    public Ray(Vec3 origin, Vec3 dir) {
        o = origin;
        d = dir;
    }

    public Vec3 origin() {
        return o;
    }

    public Vec3 direction() {
        return d;
    }


    /**
     * p(t)=A+t*B 即返回t时刻光线的位置
     *
     * @param t 时间
     * @return 返回t时刻光线的坐标
     */
    public Vec3 pointAtT(float t) {
        return o.add(d.scale(t));
    }
}
