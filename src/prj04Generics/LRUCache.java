package prj04Generics;

import java.util.ArrayList;
import java.util.List;

public class LRUCache<T> {

    ArrayList<T> elements = new ArrayList<>();
    int size;

    public LRUCache(int size){
        elements = new ArrayList<>();
        this.size = size;
    }

    public void addElement(T element){
        int currentSize = elements.size();
        if(currentSize == size){
            elements.remove(0);
        }
        elements.add(element);
    }

    public T getElement(int i){
        if(elements.size() <= i || i < 0){
            return null;
        }
        return elements.get(i);
    }

    public List<T> getAllElements(){
        return elements;
    }
}
