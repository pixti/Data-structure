/**
 * 외부 라이브러리(ArrayList) 없이 구현한 가변 크기 배열 클래스
 */
public class MyArrayList {
    private String[] elements; // 데이터를 저장할 배열
    private int capacity;      // 현재 배열의 전체 용량
    private int size;          // 실제로 저장된 데이터의 개수

    // 초기 생성자 (기본 용량 10으로 설정)
    public MyArrayList() {
        this.capacity = 10;
        this.elements = new String[capacity];
        this.size = 0;
    }

    /**
     * 리스트의 맨 뒤에 데이터를 추가합니다.
     * 배열이 가득 찼을 경우 용량을 2배로 늘립니다.
     */
    public void addLast(String element) {
        if (size == capacity) {
            resize();
        }
        elements[size++] = element;
    }

    /**
     * 특정 인덱스의 데이터를 가져옵니다.
     */
    public String get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return elements[index];
    }

    /**
     * 현재 리스트에 저장된 요소의 개수를 반환합니다.
     */
    public int size() {
        return this.size;
    }

    /**
     * 리스트가 비어있는지 확인합니다.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 배열의 공간이 부족할 때 기존 용량의 2배로 확장합니다.
     */
    private void resize() {
        capacity *= 2;
        String[] newElements = new String[capacity];
        // 기존 데이터를 새 배열로 복사
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        this.elements = newElements;
    }
}
