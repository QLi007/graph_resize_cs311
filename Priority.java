package io.github.jiangdequan;

public class Priority {
    Vertex name;
    int key;
    
    public Priority(Vertex v, int b){
        name = v;
        key = b;
    }

    public Vertex getName(){
        return name;
    }

    public int getKey(){
        return key;
    }

}