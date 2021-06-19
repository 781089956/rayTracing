package top.yzzblog.rayTracing.shape;

import top.yzzblog.rayTracing.HitRecord;
import top.yzzblog.rayTracing.Ray;

public interface Hittable {
    HitRecord hit(Ray ray, float minT, float maxT);
}
