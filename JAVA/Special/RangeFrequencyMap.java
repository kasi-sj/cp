import java.util.HashMap;
import java.util.TreeMap;

class RangeFrequencyMap {
    HashMap<Integer,TreeMap<Integer,Integer>> map;
    RangeFrequencyMap(int arr[]){
        this.map = new HashMap<>();
        for(int i = 0 ; i < arr.length ; i++){
            if(!map.containsKey(arr[i]))map.put(arr[i], new TreeMap<>());
            map.get(arr[i]).put(i,map.get(arr[i]).size());
        }
    }

    int query(int value , int left  , int right){
        if(!map.containsKey(value))return 0;
        TreeMap<Integer,Integer> fmap = map.get(value);
        Integer min = fmap.floorKey(left);
        Integer max = fmap.floorKey(right);
        if(max==null)return 0;
        if(max!=null&&min==null)return fmap.get(max)+1;
        if(min==left)return fmap.get(max) - fmap.get(min) + 1;
        return fmap.get(max) - fmap.get(min);
    }
}