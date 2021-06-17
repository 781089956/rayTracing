package top.yzzblog.rayTracing;

import top.yzzblog.rayTracing.material.Material;

import java.util.*;

public class HitRecord {
    private float t;     //相撞的时间
    private Vec3 p;      //撞击点的坐标
    private Vec3 normal; //撞击点的法向量
    private Material material;

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public HitRecord() {
        t = 0;
        p = new Vec3(0, 0, 0);
        normal = new Vec3(0, 0, 0);
    }

    public HitRecord(float t, Vec3 p, Vec3 normal, Material material) {
        this.normal = normal;
        this.p = p;
        this.t = t;
        this.material = material;
    }



    public float getT() {
        return t;
    }

    public void setT(float t) {
        this.t = t;
    }

    public Vec3 getP() {
        return p;
    }

    public void setP(Vec3 p) {
        this.p = p;
    }

    public Vec3 getNormal() {
        return normal;
    }

    public void setNormal(Vec3 normal) {
        this.normal = normal;
    }
}
