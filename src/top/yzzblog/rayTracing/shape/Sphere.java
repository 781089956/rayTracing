package top.yzzblog.rayTracing.shape;

import top.yzzblog.rayTracing.HitRecord;
import top.yzzblog.rayTracing.Hittable;
import top.yzzblog.rayTracing.Ray;
import top.yzzblog.rayTracing.Vec3;
import top.yzzblog.rayTracing.material.Material;

public class Sphere implements Hittable {
    private Vec3 center;
    private float radius;
    private Material material;

    public Sphere(Vec3 center, float radius, Material material) {
        this.center = center;
        this.radius = radius;
        this.material = material;
    }
//    public Sphere(Vec3 center, float radius) {
//        this.center = center;
//        this.radius = radius;
//    }

    public Vec3 getCenter() {
        return center;
    }

    public void setCenter(Vec3 center) {
        this.center = center;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    @Override
    public HitRecord hit(Ray ray, float minT, float maxT) {
        HitRecord record = null;

        Vec3 co = ray.origin().subtract(center); //A-C
        float b = 2 * ray.direction().dot(co);
        float a = ray.direction().dot(ray.direction());
        float c = co.dot(co) - radius * radius;
        float delta = b * b - 4 * (a * c);
        if (delta < 0) {
            return null;
        } else {
            float sqrtDelta = (float) Math.sqrt(delta);
            float t = (-b - sqrtDelta) / (2.0f * a);

            if (t < maxT && t > minT) {
                Vec3 p = ray.pointAtT(t);
                return new HitRecord(t, p, p.subtract(center).scale(1.0f / radius), material);
            }
            t = (-b + sqrtDelta) / (2.0f * a);

            if (t < maxT && t > minT) {
                Vec3 p = ray.pointAtT(t);
                return new HitRecord(t, p, p.subtract(center).scale(1.0f / radius), material);
            }

            return null;
        }

    }
}
