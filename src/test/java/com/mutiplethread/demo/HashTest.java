package com.mutiplethread.demo;

import com.mutiplethread.demo.bean.Key;
import com.mutiplethread.demo.bean.Value;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName HashTest
 * @Description: TODO
 * @Author lxc
 * @Date 2020/7/15 17:14
 * @Version V1.0
 **/
public class HashTest {

    @Test
    public void testHashCode(){
        int hash=0;
        String s="ok";

        StringBuilder sb = new StringBuilder(s);
        System.out.println(s.hashCode()+" "+sb.hashCode());

        String t = new String("ok");
        StringBuilder tb = new StringBuilder(t);
        System.out.println(t.hashCode()+" "+tb.hashCode());
    }

    @Test
    public void testHashMap(){
        Map<String, Value> map1 = new HashMap<String,Value>();

        String s1= new String("key");
        String s2= new String("key");
        Value value = new Value(2);
        map1.put(s1,value);
        System.out.println("s1.equals(s2):"+s1.equals(s2));
        System.out.println("map1.get(s1):"+map1.get(s1).getHashCode());
        System.out.println("map1.get(s2):"+map1.get(s2).getHashCode());

       Map<Key,Value> map2 = new HashMap<Key, Value>();
        Key key1= new Key("A");
        Key key2= new Key("A");
        map2.put(key1,value);
        System.out.println("key1.equals(key2):"+key1.equals(key2));
        System.out.println("map2.get(key1):"+map2.get(key1).getHashCode());

        System.out.println("map2.get(key2):"+map2.get(key2)==null?"Null":map2.get(key2).getHashCode());
    }
}
