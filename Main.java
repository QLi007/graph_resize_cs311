
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        PriorityQ Q=new PriorityQ();
        Vertex a=new Vertex(1,2);
        a.setDis(10);
        Vertex b=new Vertex(2,3);
        b.setDis(20);
        Vertex c=new Vertex(3,4);
        c.setDis(5);
        Vertex d=new Vertex(4,5);
        d.setDis(7);
        Vertex e=new Vertex(5,6);
        e.setDis(2);
        Vertex f=new Vertex(6,7);
        f.setDis(17);
        Vertex g=new Vertex(7,8);
        g.setDis(26);
        Vertex h=new Vertex(8,9);
        h.setDis(8);
        Q.add(a,a.getDis());
        Q.add(b,b.getDis());
        Q.add(c,c.getDis());
        Q.add(d,d.getDis());
        Q.add(e,e.getDis());
        Q.add(f,f.getDis());
        Q.add(g,g.getDis());
        Q.add(h,h.getDis());
        Q.setPriority(5, 4);
        while (Q.isEmpty()!=true){
            String x=""+Q.extractMin().getDis();
            System.out.println(x);
        }
        WGraph graph =new WGraph("Graph.txt");
        ArrayList<Integer> shortest =graph.V2V(-1,-1,3,4);
        for(int i=0;i<shortest.size();i++) {
            System.out.println(shortest.get(i));
        }
        ArrayList<Integer> S1=new ArrayList<Integer>();
        S1.add(2);
        S1.add(0);
        S1.add(2);
        S1.add(1);
        S1.add(2);
        S1.add(2);
        S1.add(2);
        S1.add(3);
        ArrayList<Integer> shortest1 =graph.V2S(-1,-1,S1);
        for(int i=0;i<shortest1.size();i++) {
            System.out.println(shortest1.get(i));
        }
        ArrayList<Integer> S3=new ArrayList<Integer>();
        S3.add(-1);
        S3.add(0);
        S3.add(-1);
        S3.add(1);
        S3.add(-1);
        S3.add(2);
        S3.add(-1);
        S3.add(3);
        ArrayList<Integer> shortest2 =graph.S2S(S3,S1);
        for(int i=0;i<shortest2.size();i++) {
            System.out.println(shortest2.get(i));
        }
	// write your code here
    }
}
