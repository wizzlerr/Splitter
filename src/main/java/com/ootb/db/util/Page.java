package com.ootb.db.util;


import java.util.List;

/**
 * Created by Adam on 2017-03-28.
 */
public class Page {

    private List<?> objects;
    private int totalPages;

    public Page(List<?> objects, int startIndex) {
        int endIndex = startIndex + 5;
        this.totalPages = getNoPages(objects);

        if(endIndex > objects.size() - 1) {
            this.objects = objects.subList(startIndex, objects.size());
        } else {
            this.objects = objects.subList(startIndex, endIndex);
        }

    }

    public static int getNoPages(List<?> objects) {
        if(objects.size() % 5 == 0) {
            return objects.size() / 5;
        } else {
            return objects.size() / 5 + 1;
        }
    }

    public List<?> getObjects() {
        return objects;
    }

    public void setObjects(List<Object> objects) {
        this.objects = objects;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
