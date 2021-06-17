package top.yzzblog.rayTracing;

public interface Hittable {
    HitRecord hit(Ray ray, float minT, float maxT);
}
