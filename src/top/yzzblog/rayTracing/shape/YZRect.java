package top.yzzblog.rayTracing.shape;

import top.yzzblog.rayTracing.HitRecord;
import top.yzzblog.rayTracing.Ray;
import top.yzzblog.rayTracing.Vec3;
import top.yzzblog.rayTracing.material.Material;

public class YZRect implements Hittable {
    private float y0, y1, z0, z1, k;
    private Material material;


    public YZRect(float y0, float y1, float z0, float z1, float k,Material material) {
        this.y0 = y0;
        this.y1 = y1;
        this.z0 = z0;
        this.z1 = z1;
        this.k = k;

        this.material = material;
    }

    @Override
    public HitRecord hit(Ray r, float minT, float maxT) {
        HitRecord record = new HitRecord();
        float t = (k-r.origin().x())/r.direction().x();
        if(t < minT || t > maxT){
            return null;
        }
        float z = r.origin().z() + t*r.direction().z();
        float y = r.origin().y() + t*r.direction().y();
        if(y < y0 || y > y1 || z < z0 || z > z1){
            return null;
        }
//        record.u = (x-x0)/(x1-x0);
//        record.v = (y-y0)/(y1-y0);
        record.setT(t);
        record.setMaterial(material);
        record.setP(r.pointAtT(t));
        record.setNormal(new Vec3(1,0,0));

        return record;
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

    public float getZ0() {
        return z0;
    }

    public void setZ0(float z0) {
        this.z0 = z0;
    }

    public float getZ1() {
        return z1;
    }

    public void setZ1(float z1) {
        this.z1 = z1;
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
