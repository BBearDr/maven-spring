package com.bbear.example.lintcode;

import java.util.HashMap;

/**
 * 使用HashMap<K,链表>的形式，实现LRU算法
 *
 * @author junxiongchen
 * @date 2018/11/15
 */
public class LRUCache<K, V> {
    private int currentCacheSize;
    private int CacheCapcity;
    private HashMap<K, CacheNode> cachesHash;
    private CacheNode firstCache;
    private CacheNode lastCache;

    public LRUCache(int size) {
        currentCacheSize = 0;
        this.CacheCapcity = size;
        cachesHash = new HashMap<K, CacheNode>(size);
    }

    public void put(K k, V v) {
        CacheNode node = cachesHash.get(k);
        if (node == null) {
            if (cachesHash.size() >= CacheCapcity) {
                cachesHash.remove(lastCache.key);
                removeLast();
            }
            node = new CacheNode();
            node.key = k;
        }
        node.value = v;
        moveToFirst(node);
        cachesHash.put(k, node);
    }

    public Object get(K k) {
        CacheNode node = cachesHash.get(k);
        if (node == null) {
            return null;
        }
        moveToFirst(node);
        return node.value;
    }

    public Object remove(K k) {
        CacheNode node = cachesHash.get(k);
        if (node != null) {
            if (node.pre != null) {
                node.pre.next = node.next;
            }
            if (node.next != null) {
                node.next.pre = node.pre;
            }
            if (node == firstCache) {
                firstCache = node.next;
            }
            if (node == lastCache) {
                lastCache = node.pre;
            }
        }

        return cachesHash.remove(k);
    }

    public void clear() {
        firstCache = null;
        lastCache = null;
        cachesHash.clear();
    }


    private void moveToFirst(CacheNode node) {
        if (firstCache == node) {
            return;
        }
        if (node.next != null) {
            node.next.pre = node.pre;
        }
        if (node.pre != null) {
            node.pre.next = node.next;
        }
        if (node == lastCache) {
            lastCache = lastCache.pre;
        }
        if (firstCache == null || lastCache == null) {
            firstCache = lastCache = node;
            return;
        }

        node.next = firstCache;
        firstCache.pre = node;
        firstCache = node;
        firstCache.pre = null;

    }

    private void removeLast() {
        if (lastCache != null) {
            lastCache = lastCache.pre;
            if (lastCache == null) {
                firstCache = null;
            } else {
                lastCache.next = null;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        CacheNode node = firstCache;
        while (node != null) {
            sb.append(String.format("%s:%s ", node.key, node.value));
            node = node.next;
        }

        return sb.toString();
    }

    class CacheNode {
        CacheNode pre;
        CacheNode next;
        Object key;
        Object value;

        public CacheNode() {

        }
    }

    public static void main(String[] args) {

        LRUCache<Integer, String> lru = new LRUCache<Integer, String>(3);

        lru.put(1, "a");    // 1:a
        System.out.println(lru.toString());
        lru.put(2, "b");    // 2:b 1:a
        System.out.println(lru.toString());
        lru.put(3, "c");    // 3:c 2:b 1:a
        System.out.println(lru.toString());
        lru.put(4, "d");    // 4:d 3:c 2:b
        System.out.println(lru.toString());
        lru.put(1, "aa");   // 1:aa 4:d 3:c
        System.out.println(lru.toString());
        lru.put(2, "bb");   // 2:bb 1:aa 4:d
        System.out.println(lru.toString());
        lru.put(5, "e");    // 5:e 2:bb 1:aa
        System.out.println(lru.toString());
        lru.get(1);         // 1:aa 5:e 2:bb
        System.out.println(lru.toString());
        lru.remove(11);     // 1:aa 5:e 2:bb
        System.out.println(lru.toString());
        lru.remove(1);      //5:e 2:bb
        System.out.println(lru.toString());
        lru.put(1, "aaa");  //1:aaa 5:e 2:bb
        System.out.println(lru.toString());
    }
}
