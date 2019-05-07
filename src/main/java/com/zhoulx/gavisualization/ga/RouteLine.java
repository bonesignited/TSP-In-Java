package com.zhoulx.gavisualization.ga;


public class RouteLine {
    private double x_start;
    private double y_start;

    private double x_end;
    private double y_end;

    private int index;

    public RouteLine(double x_start, double y_start, double x_end, double y_end, int index) {
        this.x_start = x_start;
        this.y_start = y_start;
        this.x_end = x_end;
        this.y_end = y_end;
        this.index = index;
    }

    public double getX_start() {
        return x_start;
    }

    public void setX_start(double x_start) {
        this.x_start = x_start;
    }

    public double getY_start() {
        return y_start;
    }

    public void setY_start(double y_start) {
        this.y_start = y_start;
    }

    public double getX_end() {
        return x_end;
    }

    public void setX_end(double x_end) {
        this.x_end = x_end;
    }

    public double getY_end() {
        return y_end;
    }

    public void setY_end(double y_end) {
        this.y_end = y_end;
    }

    public double getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
