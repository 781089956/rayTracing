package top.yzzblog.rayTracing;

import top.yzzblog.rayTracing.shape.Hittable;

import java.util.ArrayList;
import java.util.List;

public class HittableList implements Hittable {
    private List<Hittable> list = new ArrayList<>();

    public HittableList() {

    }

    public HittableList(List<Hittable> list) {
        this.list = list;
    }

    public void add(Hittable sth) {
        list.add(sth);
    }

    public void remove(Hittable sth) {
        list.remove(sth);
    }

    @Override
    public HitRecord hit(Ray ray, float minT, float maxT) {
        HitRecord record = null;
        float closest = maxT;

        for (Hittable sth : list) {
            HitRecord tmp =  sth.hit(ray, minT, closest);
            if (null != tmp) {
                record = tmp;
                closest = record.getT();
            }
        }

        return record;
    }
}
