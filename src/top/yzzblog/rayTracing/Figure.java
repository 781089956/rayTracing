package top.yzzblog.rayTracing;

import top.yzzblog.rayTracing.material.DiffuseLight;
import top.yzzblog.rayTracing.material.Lambertian;
import top.yzzblog.rayTracing.material.Metal;
import top.yzzblog.rayTracing.material.Dielectric;
import top.yzzblog.rayTracing.shape.Box;
import top.yzzblog.rayTracing.shape.FlipNormal;
import top.yzzblog.rayTracing.shape.Sphere;
import top.yzzblog.rayTracing.shape.XZRect;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Figure {
    private static final String DIR = "./";

    private int width, height;

    private Camera camera;

    private static HittableList world = new HittableList();

    static {
//        world.add(new FlipNormal(new XZRect(0.5f, 1.5f, -1.5f, 0.5f, 1f,
//                new DiffuseLight(null, new Vec3(1, 1, 1)))));
        world.add(new Sphere(new Vec3(0, 1.2f, -0.7f), 0.3f, new DiffuseLight(null, new Vec3(1, 1, 1))));
        world.add(new Sphere(new Vec3(-0.8f, 0.0f, -1.0f), 0.5f,
                new Metal(new Vec3(0.8f, 0.6f, 0.2f), 0.1f)));
        world.add(new Sphere(new Vec3(1f, 0f, -1.0f), 0.5f,
                new Dielectric(1.5f)));
        world.add(new Box(new Vec3(-0.2f, -0.5f, -1.2f), new Vec3(0.4f, 0.1f, -0.6f),
                new Metal(new Vec3(1f, 0.8f, 0.7f), 0.3f)));
        world.add(new Sphere(new Vec3(0.0f, -100.5f, -1.0f), 100f,
                new Metal(new Vec3(0.5f, 0.8f, 0.9f), 0.3f)));

    }

    public Figure(int width, int height) {
        this.width = width;

        this.height = height;
        float aspect = (float) width / (float) height;  //宽高比
        camera = new Camera(new Vec3(-1.2f,0f,1.3f), new Vec3(0,0,-1),new Vec3(0,1,0), 40, aspect);
    }

    public void render() {
        int ns = 100;
        int all = width * height;
        SimpleDateFormat df = new SimpleDateFormat("HH_mm_ss");
        try (BufferedWriter fw = new BufferedWriter(new FileWriter(DIR + "result_" + ns + df.format(new Date()) + ".ppm"))) {
            //PPM格式头部分
            fw.write("P3\n" + width + " " + height + "\n255\n");
            int index = 0;

            //PPM格式图像数据部分
            for (int j = height - 1; j >= 0; j--) {
                for (int i = 0; i < width; i++) {
                    Vec3 color = new Vec3(0, 0, 0);

                    for (int s = 0; s < ns; s++) {
                        float u = (float) (i + Math.random()) / (float) width;
                        float v = (float) (j + Math.random()) / (float) height;
                        Ray ray = camera.getRay(u, v);
                        color = color.add(color(ray, world, 0));
                    }

                    color = color.scale(1.0f / (float) ns);

                    index += 1;

                    int ir = (int) (255.59f * Math.sqrt(color.x()));
                    int ig = (int) (255.59f * Math.sqrt(color.y()));
                    int ib = (int) (255.59f * Math.sqrt(color.z()));
                    fw.write(ir + " " + ig + " " + ib + "\n");

                    System.out.printf("\r%.1f%%", (float) index * 100 / all);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Vec3 color(Ray ray, HittableList world, int depth) {
        if (depth > 50) return new Vec3(0, 0, 0);

        HitRecord record;
        Wrapper wrapper = new Wrapper();
        if (null != (record = world.hit(ray, 0, Float.MAX_VALUE))) {
            Vec3 emitted = record.getMaterial().emitted();
            if (record.getMaterial().scatter(ray, record, wrapper)) {
                return emitted.add(color(wrapper.scattered, world, depth + 1).multiply(wrapper.attenuation));
            }else{
                return emitted;
            }
        }
        // 天空
//        Vec3 dir = ray.direction().normalize();
//        float y = 0.5f * (dir.y() + 1.0f);
//
//        return new Vec3(1.0f, 1.0f, 1.0f).scale(1.0f - y).add(new Vec3(0.5f, 0.7f, 1.0f).scale(y));
        return new Vec3(0f, 0f, 0f);
    }


}
