package top.yzzblog.rayTracing.material;

import top.yzzblog.rayTracing.HitRecord;
import top.yzzblog.rayTracing.Ray;
import top.yzzblog.rayTracing.Vec3;
import top.yzzblog.rayTracing.Wrapper;

public class Dielectric extends AbstractMaterial {
    private float ref_idx;

    private Vec3 refracted;

    public Dielectric(float ref_idx) {
        super(new Vec3(1, 1, 1));
        this.ref_idx = ref_idx;
    }

    @Override
    public boolean scatter(Ray ray, HitRecord record, Wrapper wrapper) {
        Vec3 n = record.getNormal();
        Vec3 direction = ray.direction();
        wrapper.attenuation = getAlbedo();

        Vec3 outward_normal;    //入射时的法向量
        Vec3 reflected = reflect(ray.direction(), n);
        float ni_over_nt;
        float reflect_prob;
        float cosine;

        if (ray.direction().dot(record.getNormal()) > 0) {
            // 空气->球 光疏->光密
            outward_normal = n.scale(-1);
            ni_over_nt = ref_idx;
            cosine = direction.dot(n) / direction.length();      //入射角余弦
        } else {
            // 球->空气
            outward_normal = n;
            ni_over_nt = 1.0f / ref_idx;
            cosine = -direction.dot(n) / direction.length();      //入射角余弦
        }
        if (refract(direction, outward_normal, ni_over_nt)) {
            //发生了折射 计算反射系数
            reflect_prob = schlick(cosine, ref_idx);
        } else {
            //计算折射光线方向向量的函数返回false，即出现全反射。
            reflect_prob = 1.0f;
        }
        /* 反射光线和折射光线的叠加 */
        if (Math.random() < reflect_prob) {
            wrapper.scattered = new Ray(record.getP(), reflected);
        } else {
            wrapper.scattered = new Ray(record.getP(), refracted);
        }
        return true;
    }

    public boolean refract(Vec3 v, Vec3 n, float nt) {
        Vec3 uv = v.normalize();
        float cos_a1 = -1.0f * uv.dot(n);
        float temp = 1.0f - nt * nt * (1.0f - cos_a1 * cos_a1);
        if (temp > 0.0f) {
            refracted = uv.scale(nt).add(n.scale((float) (nt * cos_a1 - Math.sqrt(temp))));
            return true;
        } else {
            return false;
        }
    }

    Vec3 reflect(Vec3 v, Vec3 n) {
        return v.subtract(n.scale(v.dot(n) * 2));
        //return v - 2 * dot(v, n)*n;
    }

    float schlick(float cosine, float ref_idx) {
        float r0 = (1 - ref_idx) / (1 + ref_idx);
        r0 = r0 * r0;
        return (float) (r0 + (1 - r0) * Math.pow((1 - cosine), 5));
    }

}
