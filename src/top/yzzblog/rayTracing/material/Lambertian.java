package top.yzzblog.rayTracing.material;

import top.yzzblog.rayTracing.HitRecord;
import top.yzzblog.rayTracing.Ray;
import top.yzzblog.rayTracing.Vec3;
import top.yzzblog.rayTracing.Wrapper;

import static top.yzzblog.rayTracing.Vec3Util.randomInUnitSphere;

public class Lambertian extends AbstractMaterial {

    public Lambertian(Vec3 albedo) {
        super(albedo);
    }


    public boolean scatter(Ray r, HitRecord rec, Wrapper wrapper) {
        Vec3 target = rec.getP().add(rec.getNormal()).add(randomInUnitSphere());  //相对位置->绝对位置 （p + N） + S
        wrapper.scattered = new Ray(rec.getP(), target.subtract(rec.getP()));             //源点p 方向->ps
        wrapper.attenuation = getAlbedo();
        return true;
    }



}
