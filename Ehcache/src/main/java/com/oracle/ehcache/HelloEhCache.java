package com.oracle.ehcache;

import java.nio.ByteBuffer;
import java.util.Scanner;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.CacheManager;
//import org.ehcache.impl.serialization.JavaSerializer;
import org.ehcache.Cache;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.sizeof.SizeOf;
import org.ehcache.spi.serialization.Serializer;
import org.ehcache.spi.serialization.SerializerException;

public class HelloEhCache {

     public static void main(String[] args) {

          //1. Create a cache manager
          //CacheManager cm = CacheManager.newInstance();
          
          CacheManager cm = CacheManagerBuilder.newCacheManagerBuilder()
                  .withCache("preConfigured",
                          CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, String.class, ResourcePoolsBuilder.heap(10).offheap(200, MemoryUnit.MB)))
                  .build(true);
         // cm.init();
          //2. Get a cache called "cache1", declared in ehcache.xml
          Cache<String, String> preConfigured = cm.getCache("preConfigured", String.class, String.class);
          Cache<String, String> myCache = cm.createCache("myCache",
                  CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, String.class, ResourcePoolsBuilder.heap(10)));
          
          for (int i = 0; i < 300; i++) {
               preConfigured.put("ppp"+i,"pppValue"+i);
                       }

          SizeOf sizeOf = SizeOf.newInstance(); // (1)
          long shallowSize = sizeOf.sizeOf(preConfigured); // (2)
//       //   long deepSize  = sizeOf.deepSizeOf(preConfigured); // (2)
//          long size= sizeOf.deepSizeOf(new A(1));
          
          System.out.println("shallowSize : "+ shallowSize);
          System.out.println("shallow value : "+ preConfigured.get("ppp"+3));
          
          Scanner sc=new Scanner(System.in);
          sc.next();
         // System.out.println("deepSize : "+ deepSize);
     }
     
     
     //shallowSize : 80
     //deepSize : 351456
      //            352144
     //             352592
     //             336448
     


}
class A{
     Integer a;
     A(Integer a){
         this.a=a; 
     }

     @Override
     public String toString() {
          System.out.print(a); //To change body of generated methods, choose Tools | Templates.
          return a+"";
     }
     
}

class ASerializer implements Serializer<A> {

     public ByteBuffer serialize(A t) throws SerializerException {
         System.out.print("ByteBuffer serialize  :"+t);
           return null;
     }

     public A read(ByteBuffer bb) throws ClassNotFoundException, SerializerException {
         System.out.print("A read(ByteBuffer bb)  : "+bb);
          return null;
     }

     public boolean equals(A t, ByteBuffer bb) throws ClassNotFoundException, SerializerException {
          System.out.print("public boolean equals(A t, ByteBuffer bb)  : "+bb);
          System.out.print("public boolean equals(A t, ByteBuffer bb)   :  "+ t);
          return true;
     }

    
   
    
//     public ByteBuffer serialize(A t) throws SerializerException {
//            System.out.print(t);
//            return null;
//     }
//
//     public T read(ByteBuffer bb) throws ClassNotFoundException, SerializerException {
//           System.out.print(bb);
//           return null;
//     }
//
//     public boolean equals(T t, ByteBuffer bb) throws ClassNotFoundException, SerializerException {
//         System.out.print(bb);
//         System.out.print(t);
//         return true;
//     }
     
}

