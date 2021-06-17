package top.yzzblog.rayTracing.material;

import top.yzzblog.rayTracing.HitRecord;
import top.yzzblog.rayTracing.Ray;
import top.yzzblog.rayTracing.Vec3;
import top.yzzblog.rayTracing.Wrapper;

public interface Material {
    boolean scatter(Ray ray, HitRecord record, Wrapper wrapper);
}
