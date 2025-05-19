package com.nikhil.Cache.EvictionAlgorithms.ConcreteEvictionAlgorithms;

import com.nikhil.Cache.EvictionAlgorithms.EvictionAlgorithm;
import com.nikhil.Cache.Utility.DoublyLinkedList;
import com.nikhil.Cache.Utility.DoublyLinkedListNode;

import java.util.HashMap;
import java.util.Map;

public class LRUEvictionAlgorithm<K> implements EvictionAlgorithm<K> {

    private final DoublyLinkedList<K> dll;
    private final Map<K, DoublyLinkedListNode<K>> keyVsNodeMap;

    public LRUEvictionAlgorithm() {
        this.dll = new DoublyLinkedList<>();
        this.keyVsNodeMap = new HashMap<>();
    }

    @Override
    public synchronized void keyAccessed(K key) throws Exception {
        if (keyVsNodeMap.containsKey(key)) {
            DoublyLinkedListNode<K> keyNode = keyVsNodeMap.get(key);
            dll.detachNode(keyNode);
            dll.addNodeAtTail(keyNode);
        } else {
            DoublyLinkedListNode<K> doublyLinkedListNode = new DoublyLinkedListNode<>(key);
            dll.addNodeAtTail(doublyLinkedListNode);
            keyVsNodeMap.put(key, doublyLinkedListNode);
        }
    }

    @Override
    public synchronized K evictKey() throws Exception {
        DoublyLinkedListNode<K> nodeToEvict = dll.getHead();
        if (nodeToEvict == null) {
            return null;
        }
        K evictKey = nodeToEvict.getValue();
        dll.removeHead();
        keyVsNodeMap.remove(evictKey);
        return evictKey;
    }
}
