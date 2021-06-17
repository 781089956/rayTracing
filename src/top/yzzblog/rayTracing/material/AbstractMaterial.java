package top.yzzblog.rayTracing.material;

import top.yzzblog.rayTracing.HitRecord;
import top.yzzblog.rayTracing.Ray;
import top.yzzblog.rayTracing.Vec3;

public abstract class AbstractMaterial implements Material{
    private Vec3 albedo;    // 反射率
//    private float fuzz; // 模糊

    public AbstractMaterial(Vec3 albedo) {
        this.albedo = albedo;
    }

    public Vec3 getAlbedo() {
        return albedo;
    }

}
