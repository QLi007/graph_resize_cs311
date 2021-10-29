package io.github.jiangdequan;

public class Edge {
    private int startx;
    private int starty;
    private int endx;
    private int endy;
    private int weight;

    //constructor
    public Edge(int sx, int sy, int ex, int ey, int weight){
        startx = sx;
        starty = sy;
        endx = ex;
        endy = ey;
        this.weight = weight;
    }
    
    public Vertex getStart(){
        Vertex result = new Vertex(startx, starty);
        return result;
    }

    public Vertex getEnd(){
        return new Vertex(endx, endy);
    }

    public int getWeight(){
        return weight;
    }

}