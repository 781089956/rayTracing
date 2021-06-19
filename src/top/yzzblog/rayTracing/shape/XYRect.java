package top.yzzblog.rayTracing.shape;

import top.yzzblog.rayTracing.HitRecord;
import top.yzzblog.rayTracing.Ray;
import top.yzzblog.rayTracing.Vec3;
import top.yzzblog.rayTracing.material.Material;

public class XYRect implements Hittable {
    private float x0, x1, y0, y1, k;
    private Material material;


    public XYRect(float x0, float x1, float y0, float y1, float k,Material material) {
        this.x0 = x0;
        this.x1 = x1;
        this.y0 = y0;
        this.y1 = y1;
        this.k = k;

        this.material = material;
    }

    @Override
    public HitRecord hit(Ray r, float minT, float maxT) {
        HitRecord record = new HitRecord();
        float t = (k-r.origin().z())/r.direction().z();
        if(t < minT || t > maxT){
            return null;
        }
        float x = r.origin().x() + t*r.direction().x();
        float y = r.origin().y() + t*r.direction().y();
        if(x < x0 || x > x1 || y < y0 || y > y1){
            return null;
        }
//        record.u = (x-x0)/(x1-x0);
//        record.v = (y-y0)/(y1-y0);
        record.setT(t);
        record.setMaterial(material);
        record.setP(r.pointAtT(t));
        record.setNormal(new Vec3(0,0,1));

        return record;
    }

    public float getX0() {
        return x0;
    }

    public void setX0(float x0) {
        this.x0 = x0;
    }

    public float getX1() {
        return x1;
    }

    public void setX1(float x1) {
        this.x1 = x1;
    }

    public float getY0() {
        return y0;
    }

    public void setY0(float y0) {
        this.y0 = y0;
    }

    public float getY1() {
        return y1;
    }

    public void setY1(float y1) {
        this.y1 = y1;
    }

    public float getK() {
        return k;
    }

    public void setK(float k) {
        this.k = k;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
