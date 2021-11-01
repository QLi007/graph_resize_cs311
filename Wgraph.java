import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.*;

//import io.github.jiangdequan.Vertex;

//import io.github.jiangdequan.PriorityQ;
//import io.github.jiangdequan.Vertex;

//import io.github.jiangdequan.Vertex;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static java.lang.Integer.valueOf;

public class WGraph{
    private ArrayList<Vertex> Vertices = new ArrayList<Vertex>();
    private ArrayList<Edge> Edges = new ArrayList<Edge>();
    private ArrayList<Vertex> hashv = new ArrayList<Vertex>();
    private Set<Vertex> hash = new HashSet<Vertex>();
    private int min_x = MAX_VALUE;
    private int max_x = MIN_VALUE;
    private int min_y = MAX_VALUE;
    private int max_y = MIN_VALUE;

    //constructor, need to read the file
    public WGraph(String fileName){
        try{
            String pathName = "";
            pathName = pathName + fileName;
            File fn = new File(pathName);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(pathName));
            BufferedReader br = new BufferedReader(reader);
            String line = "";
            String line1 = "";
            String line2 = "";
            
            line = br.readLine();
            line1 = br.readLine();
            line2 = br.readLine();

            while(line2 != null){
                Scanner sc = new Scanner(line2);
                int sx = sc.nextInt();
                int sy = sc.nextInt();
                int ex = sc.nextInt();
                int ey = sc.nextInt();
                int wt = sc.nextInt();
                if (sx < min_x) min_x = sx;
                if (sx > max_x) max_x = sx;
                if (sy < min_y) min_y = sy;
                if (sy > max_y) max_y = sy;
                if (ex < min_x) min_x = ex;
                if (ex > max_x) max_x = ex;
                if (ey < min_y) min_y = ey;
                if (ey > max_y) max_y = ey;
                Edge e = new Edge(sx, sy, ex, ey, wt);
                Vertex start= e.getStart();
                Vertex end = e.getEnd();
                Edges.add(e);
                int st = 0;
                int ed = 0;
                boolean contains1 = false;
                boolean contains2 = false;
                for (int i =0; i< Vertices.size(); i++){
                    Vertex v = Vertices.get(i);
                    if (v.isequal(start.getX(), start.getY())){
                        contain1 = true;
                        st =i;
                    }
                    if (v.isequal(end.getX(), end.getY())){
                        contain2 = true;
                        ed = i;
                    }
                }

                if (!contain1 && !contain2){
                    start.addEdge(Vertices.size() + 1, wt);
                    Vertices.add(start);
                    Vertices.add(end);
                }
                else if (!contain1 && contain2){
                    start.addEdge(ed, wt);
                    Vertices.add(start);
                }
                else if (contain1 && !contain2){
                    //start.addEdge(Vertices.size(), wt);
                    Vertices.get(st).addEdge(Vertices.size(), wt);
                    Vertices.add(end);
                }
                else if (contain1 && contain2){
                    Vertices.get(st).addEdge(Vertices.get(ed), wt);
                }
                line2 = br.readLine();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private ArrayList<Integer> shortest(ArrayList<Integer> startList, ArrayList<Integer> endList){
        ArrayList<Integer> finalres = new ArrayList<>();
        int smallest = MAX_VALUE;
        Vertex small = null;
        for (int s = 0; s< startList.size(); s+=2){
            int sx = startList.get(s);
            int sy = startList.get(s+1);
            Boolean contain = false;

            for (int u = 0; u< Vertices.size();u++){
                Vertex a = Vertices.get(u);
                if (a.isequal(sx, sy)) {
                    contain = true;
                    break;
                }
            }
            if (contain){
                ArrayList<Integer> res = new ArrayList<Integer>();
                ArrayList<Vertex> resv = new ArrayList<Vertex>();
                //initialize 
                for (int i =0; i< Vertices.size();i++){
                    if (Vertices.get(i).isequal(sx, sy)){
                        Vertices.get(i).setDis(0);
                    }
                    else{
                        Vertices.get(i).unvisited();
                        Vertices.get(i).setDis(MAX_VALUE);
                    }
                    Vertices.get(i).setParent(null);
                }

                //add all vertices to Queue
                PriorityQ Q = new PriorityQ();
                for (int i =0;i<Vertices.size();i++){
                    Q.add(Vertices.get(i), Vertices.get(i).getDis());
                }
                
                while(!Q.isEmpty()){
                    Vertex u = Q.extractMin();
                    if (u.getDis() > smallest) break;
                    if (u.getX() == endList.get(0) && u.getY() == endList.get(1)){
                        smallest = u.getDis();
                        small = u;
                        break;
                    }
                    if (u.getDis() == MAX_VALUE){
                        break;
                    }

                    ArrayList<Integer> lst = u.getList();//get the edges
                    for (int k =0; k< lst.size();k +=2){
                        int e = lst.get(k); //index of neighbour node
                        int wt = lst.get(k+1);
                        Vertex v = Vertices.get(e);
                        if (v.getDis() > u.getDis() + wt){
                            List ve = Q.getList();
                            ArrayList<Integer> indexlist = Q.indexlist;
                            int index = indexlist.get(e);
                            if (Q.list.get(index).getName().getDis() > u.getDis() + wt){
                                Q.list.get(index).getName().setDis(u.getDis() + wt);
                                Q.setPriority(index, u.getDis() + wt);
                            }
                            if (!Vertices.get(e).isequal(sx,sy)){
                                Vertices.get(e).setParent(u);
                            }
                        }
                    }
                }
                ArrayList<Integer> finalres1 = new ArrayList<Integer>();
                ArrayList<Integer> finalres2 = new ArrayList<Integer>();
                finalres1.add(small.getX());
                finalres1.add(small.getY());
                while(small.getParent()!= null){
                    finalres1.add(small.getParent().getY()); // need to reverse
                    finalres1.add(small.getParent().getX());
                    small = small.getParent();
                }
                //reverse finalres1
                finalres2 = Collections.reverse(finalres1);
            }
        }
        return finalres2;
    }
    public ArrayList<Integer> V2V(int ux, int uy, int vx, int vy){
        ArrayList<Integer> res = new ArrayList<Integer>();
        ArrayList<Integer> start = new ArrayList<Integer>();
        ArrayList<Integer> end = new ArrayList<Integer>();
        start.add(ux);
        start.add(uy);
        end.add(vx);
        end.add(vy);
        res = shortest(start, end);
        return res;
    }

    public ArrayList<Integer> V2S(int ux, int uy, ArrayList<Integer> s){
        ArrayList<Integer> res = new ArrayList<Integer>();
        ArrayList<Integer> start = new ArrayList<Integer>();
        ArrayList<Integer> end = s;
        start.add(ux);
        start.add(uy);
        res = shortest(start,end);
        return res;
    }
    public ArrayList<Integer> S2S(ArrayList< Integer> start, ArrayList<Integer> end){
        ArrayList<Integer> res = new ArrayList<Integer>();
        res = shortest(start,end);
        return res;
    }

}