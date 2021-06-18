package top.yzzblog.rayTracing.material;

import top.yzzblog.rayTracing.HitRecord;
import top.yzzblog.rayTracing.Ray;
import top.yzzblog.rayTracing.Vec3;
import top.yzzblog.rayTracing.Wrapper;

public class DiffuseLight extends AbstractMaterial{
    private Vec3 color;

    public DiffuseLight(Vec3 albedo, Vec3 color) {
        super(albedo);
        this.color = color;
    }

    @Override
    public Vec3 emitted() {
        return color;
    }

    @Override
    public boolean scatter(Ray ray, HitRecord record, Wrapper wrapper) {
        return false;
    }
}
