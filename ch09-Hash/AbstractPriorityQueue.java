// =================================================================
// Code Fragment 9.1: Entry 인터페이스
// =================================================================
/** key-value 쌍을 위한 Interface. */
interface Entry<K,V> {
    K getKey();       // 이 entry에 저장된 key를 반환
    V getValue();     // 이 entry에 저장된 value를 반환
}


// =================================================================
// Code Fragment 9.2: PriorityQueue 인터페이스
// =================================================================
/** priority queue ADT를 위한 Interface. */
interface PriorityQueue<K,V> {
    int size();
    boolean isEmpty();
    Entry<K,V> insert(K key, V value) throws IllegalArgumentException;
    Entry<K,V> min();
    Entry<K,V> removeMin();
}


// =================================================================
// AbstractPriorityQueue 추상 클래스
// =================================================================
/** PriorityQueue 인터페이스 구현을 돕기 위한 abstract base class. */
public abstract class AbstractPriorityQueue<K,V> implements PriorityQueue<K,V> {
    
    //---------------- 중첩된 PQEntry 클래스 ----------------
    protected static class PQEntry<K,V> implements Entry<K,V> {
        private K k; // key
        private V v; // value
        
        public PQEntry(K key, V value) {
            k = key;
            v = value;
        }
        
        // Entry 인터페이스의 메소드들
        public K getKey() { return k; }
        public V getValue() { return v; }
        
        // Entry 인터페이스 외부로 노출되지 않는 유틸리티 메소드들
        protected void setKey(K key) { k = key; }
        protected void setValue(V value) { v = value; }
    } //---------------- 중첩된 PQEntry 클래스 끝 ----------------

    // AbstractPriorityQueue의 인스턴스 변수
    /** priority queue 안에서 key들의 정렬 순서를 정의하는 comparator. */
    private Comparator<K> comp;
    
    /** 주어진 comparator를 사용하여 key를 정렬하는 빈 priority queue를 생성. */
    protected AbstractPriorityQueue(Comparator<K> c) { comp = c; }
    
    /** key들의 자연스러운 순서(natural ordering)를 기반으로 빈 priority queue를 생성. */
    protected AbstractPriorityQueue() { this(new DefaultComparator<K>()); }
    
    /** key에 따라 두 entry를 비교하는 메소드 */
    protected int compare(Entry<K,V> a, Entry<K,V> b) {
        return comp.compare(a.getKey(), b.getKey());
    }
    
    /** key가 유효한지 판별하는 메소드 */
    protected boolean checkKey(K key) throws IllegalArgumentException {
        try {
            return (comp.compare(key,key) == 0); // key가 자신과 비교될 수 있는지 확인
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Incompatible key");
        }
    }
    
    /** priority queue가 비어있는지 테스트. */
    public boolean isEmpty() { return size() == 0; }
}
