package top.yzzblog.rayTracing;

public class Camera {
    private Vec3 lowerLeft;
    private Vec3 horizontal;
    private Vec3 vertical;
    private Vec3 origin;

    public Camera() {
        lowerLeft = new Vec3(-2.0f, -1.0f, -1.0f);
        horizontal = new Vec3(4.0f, 0.0f, 0.0f);
        vertical = new Vec3(0.0f, 2.0f, 0.0f);
        origin = new Vec3(0.0f, 0.0f, 0.0f);
    }

    public Camera(Vec3 lookfrom, Vec3 lookat, Vec3 vup, float vfov, float aspect) {

        Vec3 u, v, w;
        float theta = (float) (vfov * Math.PI / 180);
        float half_height = (float) (Math.tan(theta / 2));
        float half_width = aspect * half_height;
        origin = lookfrom;
        w = lookfrom.subtract(lookat).normalize();      //相当于新的z
        u = vup.cross(w).normalize();                   //相当于新的x
        v = w.cross(u).normalize();                     //相当于新的y
        lowerLeft = origin.subtract(u.scale(half_width)).subtract(v.scale(half_height)).subtract(w);
        horizontal = u.scale(2 * half_width);
        vertical = v.scale(2 * half_height);
    }

    public Ray getRay(float u, float v) {
        return new Ray(origin, lowerLeft.add(horizontal.scale(u)).add(vertical.scale(v)).subtract(origin));
    }
}
