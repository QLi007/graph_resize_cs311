package io.github.jiangdequan;

import java.util.ArrayList;
import static java.lang.Integer.MAX_VALUE;

public class Vertex {
    private boolean ifvisited = true;
    private int X;
    private int Y;
    private Vertex parent = null;
    private int dis = MAX_VALUE;
    private ArrayList<Integer> list = new ArrayList<Integer>();
    private  int index = -1;

    public Vertex(int x, int y){
        X = x;
        Y = y;
    }

    public int getIndex(){
        return index;
    }
    
    public void setIndex(int id){
        index = id;
    }

    public int getX(){
        return X;
    }
    public int getY(){
        return Y;
    }
    
    public boolean isequal(int a, int b){
        return (X ==a && Y ==b);
    }

    public ArrayList<Integer> getList(){
        return list;
    }

    public void addEdge(int index, int wt){
        list.add(index);
        list.add(wt);
    }

    public void unVisited(){
        ifvisited = false;
    }

    public void setParent(Vertex p){
        parent = p;
    }
    
    public Vertex getParent(){
        return parent;
    }
    
    public void setDis(int distance){
        dis = distance;
    }

    public int getDis(){
        return dis;
    }

}