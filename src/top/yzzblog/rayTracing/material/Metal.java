package top.yzzblog.rayTracing.material;

import top.yzzblog.rayTracing.*;

public class Metal extends AbstractMaterial{
    private float fuzz = 1; // 镜面模糊

    public Metal(Vec3 albedo, float fuzz) {
        super(albedo);
        if(fuzz < 1){
            this.fuzz = fuzz;
        }
    }

    @Override
    public boolean scatter(Ray ray, HitRecord record, Wrapper wrapper) {
        Vec3 ref = reflect(ray.direction(), record.getNormal().normalize());
        wrapper.attenuation = getAlbedo();
        wrapper.scattered = new Ray(record.getP(), ref.add(Vec3Util.randomInUnitSphere().scale(fuzz)));

        return (ref.dot(record.getNormal()) > 0);
    }

    private Vec3 reflect(Vec3 v, Vec3 n) {
        return v.subtract(n.scale(v.dot(n) * 2));
    }

}
