package utils;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import test.User;

public class CollectionUtils {

   /**
     * 将 List 集合转化为 Map, 可将 List 对象的任意字段当做 Map 的 key
     * @param list          对象集合
     * @param methodName    获取字段值的方法，比如 "getId"
     * @param <V>           key的类型，比如 Long
     * @param <T>           对象类型，比如 User
     * @return  Map
     */
    @SuppressWarnings("unchecked")
    public static <V, T> Map<V, List<T>> listToListMap(List<T> list, String methodName){
        Map<V, List<T>> map = new HashMap<>();
        if (list.size() == 0){
            return map;
        }
        try{
            for (T obj : list) {
                Method method = obj.getClass().getMethod(methodName);
                V key = (V) method.invoke(obj);
                if (map.get(key) == null){
                    List<T> objList = new ArrayList<T>();
                    objList.add(obj);
                    map.put(key, objList);
                }else {
                    map.get(key).add(obj);
                }
            }
        }catch (Exception exception){
            System.err.println("List 转化为 Map 失败！");
        }
        return map;
    }
    
    public static void main(String[] args) {
            List<User> userList = new ArrayList<User>();
            userList.add(new User(1L, "小明"));
            userList.add(new User(1L, "小红"));
            userList.add(new User(2L, "小明"));
            userList.add(new User(3L, "小小"));
            Map<Object, List<User>> idMap = CollectionUtils.listToListMap(userList, "getId");
            Map<Object, List<User>> usernameMap = CollectionUtils.listToListMap(userList, "getName");
            System.out.println(idMap);
            System.out.println(usernameMap);
     

    }

    
    
}
