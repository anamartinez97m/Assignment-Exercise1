package assignment.exercise1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AssignmentExercise1 {

    public static void main(String[] args) {
        
        ArrayList<List<Integer>> lists = new ArrayList();
        List<Integer> list1 = new ArrayList(Arrays.asList(4,10,15,24,26));
        List<Integer> list2 = new ArrayList(Arrays.asList(0,9,12,20));
        List<Integer> list3 = new ArrayList(Arrays.asList(5,18,22,30));
        lists.add(list1);
        lists.add(list2);
        lists.add(list3);
        
        ArrayList<Integer> list = new ArrayList();
        
        list = order(list, lists);
        
        int[] minRange = new int[2];
        minRange = seekMinimunRange(list, lists);
        System.out.println("Range: [" + minRange[0] + ", " + minRange[1] + "]");
    }
    
    public static int[] seekMinimunRange(ArrayList<Integer> list, ArrayList<List<Integer>> lists){
            int[] minRange = new int[2];
            int minDistance = list.get(list.size() - 1) - list.get(0);
            minRange[0] = list.get(0);
            minRange[1] = list.get(list.size() - 1);

            for(int i = 0; i < list.size(); i++){
                boolean viable = false;

                for(int j = 1; j < list.size() && !viable; j++){
                    if(isViable(lists, list.get(i), list.get(j))){
                        viable = true;
                        if( (list.get(j) - list.get(i)) < minDistance ){
                            minRange[0] = list.get(i);
                            minRange[1] = list.get(j);
                            minDistance = list.get(j) - list.get(i);
                        }
                    }
                }
            }
            return minRange;
    }
    
    public static boolean isViable(ArrayList<List<Integer>> lists, int i, int j){
        boolean viable = true;
        
        for(int a = 0; a < lists.size() && viable; a++){
            boolean found = false;
            
            for(int b = 0; b < lists.get(a).size() && !found; b++){
                if(lists.get(a).get(b) >= i && lists.get(a).get(b) <= j){
                    found = true;
                }
            }
            
            if(!found){
                viable = false;
            }
        }
        
        return viable;
    }
    
    public static ArrayList<Integer> order(ArrayList<Integer> list, ArrayList<List<Integer>> lists){
        ArrayList<Integer> mergedList = new ArrayList();
        
        for(List<Integer> l : lists){
            mergedList.addAll(l);
        }
        
        list = mergeSort(mergedList);
        return list;
    }
    
    public static ArrayList<Integer> mergeSort(ArrayList<Integer> mergedList){
        ArrayList<Integer> left = new ArrayList();
        ArrayList<Integer> right = new ArrayList();
        int middle;
        
        if(mergedList.size() == 1){
            return mergedList;
        } else{
            middle = mergedList.size() / 2;
            
            for(int i = 0; i < middle; i++){
                left.add(mergedList.get(i));
            }
            
            for(int i = middle; i < mergedList.size(); i++){
                right.add(mergedList.get(i));
            }
            
            left = mergeSort(left);
            right = mergeSort(right);
            
            merge(mergedList, left, right);
        }
        return mergedList;
    }
    
    public static void merge(ArrayList<Integer> mergedList, ArrayList<Integer> left, ArrayList<Integer> right){
        int leftPos = 0;
        int rightPos = 0;
        int mergedListPos = 0;
        
        while(leftPos < left.size() && rightPos < right.size()){
            if((left.get(leftPos) - right.get(rightPos)) < 0){
                mergedList.set(mergedListPos, left.get(leftPos++));
            } else{
                mergedList.set(mergedListPos, right.get(rightPos++));
            }
            mergedListPos++;
        }
        
        ArrayList<Integer> rest;
        int restPos;
        
        if(leftPos >= left.size()){
            rest = right;
            restPos = rightPos;
        } else{
            rest = left;
            restPos = leftPos;
        }
        
        for(int i = restPos; i < rest.size(); i++){
            mergedList.set(mergedListPos++, rest.get(i));
        }
    }
}



