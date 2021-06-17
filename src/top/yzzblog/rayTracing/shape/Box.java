package top.yzzblog.rayTracing.shape;

import top.yzzblog.rayTracing.*;
import top.yzzblog.rayTracing.material.Material;

import java.util.ArrayList;
import java.util.List;

public class Box implements Hittable {
    private Vec3 pmin, pmax;
    HittableList list;


    /**
     * @param p0  Minimum corner of box
     * @param p1  Maximum corner of box
     * @param mat material
     */
    public Box(Vec3 p0, Vec3 p1, Material mat) {
        pmin = p0;
        pmax = p1;
        this.list = new HittableList();
        list.add(new XYRect(p0.x(), p1.x(), p0.y(), p1.y(), p1.z(), mat));
        list.add(new FlipNormal(new XYRect(p0.x(), p1.x(), p0.y(), p1.y(), p0.z(), mat)));
        list.add(new XZRect(p0.x(), p1.x(), p0.z(), p1.z(), p1.y(), mat));
        list.add(new FlipNormal(new XZRect(p0.x(), p1.x(), p0.z(), p1.z(), p0.y(), mat)));
        list.add(new YZRect(p0.y(), p1.y(), p0.z(), p1.z(), p1.x(), mat));
        list.add(new FlipNormal(new YZRect(p0.y(), p1.y(), p0.z(), p1.z(), p0.x(), mat)));
    }

    @Override
    public HitRecord hit(Ray r, float minT, float maxT) {
        return list.hit(r, minT, maxT);
    }

    public Vec3 getPmin() {
        return pmin;
    }

    public void setPmin(Vec3 pmin) {
        this.pmin = pmin;
    }

    public Vec3 getPmax() {
        return pmax;
    }

    public void setPmax(Vec3 pmax) {
        this.pmax = pmax;
    }

    public HittableList getHlist() {
        return list;
    }

    public void setHlist(HittableList hlist) {
        this.list = hlist;
    }
}
