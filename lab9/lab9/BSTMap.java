package lab9;

import java.util.*;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;
        private int size;

        private Node(K k, V v) {
            key = k;
            value = v;
            size = 1;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.size = 0;
        this.root = null;
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }



    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if(p == null){
            return null;
        }
        int com = key.compareTo(p.key);

        if(com < 0) {
            return getHelper(key, p.left);
        }
        else if(com > 0){
            return getHelper(key, p.right);
        }
        return p.value;
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }


    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if(p == null){
            return new Node(key, value);
        }

        int com = key.compareTo(p.key);

        if(com > 0){
            p.right = putHelper(key, value, p.right);
        }
        else if(com < 0){
            p.left = putHelper(key, value, p.left);
        }
        else{
            p.value = value;
        }
        p.size = 1 + sizeHelper(p.left) + sizeHelper(p.right);
        return p;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        root = putHelper(key, value, root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return sizeHelper(root);
    }

    private int sizeHelper(Node node){
        if(node == null){
            return 0;
        }
        return node.size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        for(K key : this){
            keySet.add(key);
        }
        return keySet;
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        V value = get(key);
        delete(key, root);
        return value;
    }

    private Node delete(K key, Node node){
        if(node == null){
            return null;
        }
        int com = key.compareTo(node.key);

        if(com > 0){
            node.right = delete(key, node.right);
        }
        else if(com < 0){
            node.left = delete(key, node.left);
        }
        else{
            if(node.right == null){
                return node.left;
            }
            else if(node.left == null){
                return node.right;
            }
            else{
                Node successor = getSuccessor(node);
                K sucKey = successor.key;
                V sucValue = successor.value;
                delete(sucKey, node);
                node.key = sucKey;
                node.value = sucValue;

            }
        }

        node.size = 1 + sizeHelper(node.left) + sizeHelper(node.right);

        return node;

    }
    private Node getSuccessor(Node node){
        Node right = node.right;
        while(right.left != null){
            right = right.left;
        }
        return right;
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        V valueRemoved = get(key);
        delete(key, root);
        return valueRemoved;

    }

    @Override
    public Iterator<K> iterator() {
        return new keyIterator();
    }



    private class keyIterator implements Iterator<K>{
        Queue<K> keysQueue;
        public keyIterator(){
            keysQueue = new LinkedList<>();
            addHelper(keysQueue, root);
        }

        public boolean hasNext(){
            return !keysQueue.isEmpty();
        }

        public K next(){
            return keysQueue.poll();
        }

        private void addHelper(Queue<K> queue,Node currNode){
            if(currNode == null){
                return;
            }
            addHelper(queue, currNode.left);
            queue.offer(currNode.key);
            addHelper(queue, currNode.right);
        }


    }

}
