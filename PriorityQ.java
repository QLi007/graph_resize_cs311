package io.github.jiangdequan;
import java.util.ArrayList;
import java.util.List;

public class PriorityQ {
    public List<PriorityQ> list;
    private int index = 0;
    public List<Integer> indexList = new ArrayList<Integer>();
    
    //min heap
    PriorityQ(){
        list = new ArrayList<Priority>();
    }
    public Boolean isEmpty(){
        return list.isEmpty();
    }
    public int getkey(int a){
        int size = list.size();
        if (a < size){
            return list.get(a).getKey();
        }
        return -1;
    }
    public Vertex getValue(int a){
        int size = list.size();
        if (a < size){
            return list.get(a).getName();
        }
        return null;
    }
    private void sortDown(int i){
        while(i < list.size() -1 && i > -1){
            if (left(i) == null && right(i) == null) break;
            Priority bigger;
            int big;
            if (left(i) == null || left(i).getKey()< right.getKey()) {
                bigger = right(i);
                big = i*2 +2;
            }
            else {
                bigger = left(i);
                big = i*2 + 1;
            }
            if (list.get(i).getKey() > list.get(big).getKey()) {
                swap(i, big);
                i = big;
            }
            else break;
        }
    }
    private void sortup(int i){
        while(i > -1 && i < list.size()){
            if (parent(i) == null) break;
            else if (list.get(i).getKey() < parent(i).get(i).getKey()){
                int b = (i - 1)/2;
                swap(i, b);
                i = b;
            }
        }
    }
    // add to list, then sort
    private void sortadd(){
        int a = list.size() -1;
        while (a > 0){
            if (list.get(a).getKey() < parent(a)){
                swap(a, (a - 1)/2);
                a = (a - 1)/2;
            }
            else break;
        }
    }
    private void swap(int i , int j){
        Priority i1 = list.get(i);
        Priority j1 = list.get(j);
        //swap priority
        list.set(i,j1);
        list.set(j,i1);
        //swap the index
        int indexi = i1.getName().getIndex();
        int indexj = j1.getName().getIndex();
        int ii = indexList.get(indexi);
        int jj = indexList.get(indexj);
        indexList.set(indexi, jj);
        indexList.set(indexj, ii);
    }

    private Priority parent(int i){
        if (i == 0) return list.get(i);
        return list.get((i-1/2));
    }
    private Priority left(int i){
        int a = i*2 + 1;
        if (a > list.size()) return null;
        return list.get(a);
    }
    private Priority right(int i){
        int a = i*2 + 2;
        if (a > list.size()) return null;
        return list.get(a);
    }
    public viod add(Vertex node, int key){
        Priority newNode = new Priority(node, key);
        newNode.getName().setIndex(index);
        list.add(newNode);
        indexList.add(index);
        sordadd();
        index++;
    }
    public Vertex returnMin(){
        if (list.isEmpty()) return null;
        return list.get(0).getName();
    }
    public Vertex extractMin(){
        if (list.isEmpty()) return null;
        Vertex res = list.get(0).getName();
        swap(0, list.size() -1);
        list.remove(list.size() -1);
        sortdown(0);
        return res;
    }
    public viod remove(int i){
        if (i > list.size() - 1) throw new NullPointerException();
        else {
            Vertex v = list.get(i).getName();
            swap(i, list.size() - 1);
            list.remove(list.size() - 1);
            sortdown(i);
        }
    }
    public void decrementPriority(int i, int k){
        if (i > list.size() -1 || i<0) throw new NullPointerException();
        else {
            Vertex v = list.get(i).getName();
            int key = list.get(i).getKey();
            key -= k;
            Priority res = new Priority(v, key);
            list.set(i, res);
            sortup(i);
        }
    }
    public void increasePriority(int i, int k){
        if (i > list.size() - 1 || i < 0) throw new NullPointerException();
        else {
            Vertex v = list.get(i).getName();
            int key = list.get(i).getKey();
            key += k;
            Priority res = new Priority(v, key);
            list.set(i, res);
            sortdown(i);
        }
    }
    public void setPriority(int i, int k){
        if (i > list.size() - 1 || i < 0) throw new NullPointerException();
        else{
            Vertex v = list.get(i).getName();
            //int key = list.get(i).getKey();
            v.setDis(k);
            Priority res = new Priority(v,k);
            list.set(i,res);
            if (parent(i).getKey() > k) sortup(i); //key smaller than parent, sortup
            //greater than either child, sortdown
            else if (left(i).getKey() < k || right(i).getKey()<k) sortdown(i); //
        }
    }
    public List<Priority> getList(){
        return list;
    }
    public int[] priorityArray(){
        if (list.isEmpty()) return null;
        int [] res = new int[list.size()];
        for(int i = 0;i< list.size();i++){
            res[i] = list.get(i).getKey();
        }
        return res;
    }
}