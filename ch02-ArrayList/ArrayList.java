public class ArrayList<E> implements List<E> {
    // 인스턴스 변수 (instance variables)
    public static final int CAPACITY = 16;     // 기본 배열 용량 (default array capacity)
    private E[] data;                        // 데이터를 저장할 제네릭 배열 (generic array for storage)
    private int size = 0;                    // 현재 저장된 요소의 개수 (current number of elements)

    // 생성자 (constructors)
    public ArrayList() { this(CAPACITY); }    // 기본 용량으로 리스트 생성

    public ArrayList(int capacity) {          // 전달받은 용량으로 리스트 생성
        data = (E[]) new Object[capacity];    // 안전한 형변환(safe cast); 컴파일러 경고가 발생할 수 있음
    }

    // 공개 메서드 (public methods)
    /** 리스트에 저장된 요소의 개수를 반환합니다. */
    public int size() { return size; }

    /** 리스트가 비어있는지 여부를 반환합니다. */
    public boolean isEmpty() { return size == 0; }

    /** 인덱스 i에 있는 요소를 반환합니다. (제거하지 않음) */
    public E get(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        return data[i];
    }

    /** 인덱스 i의 요소를 e로 교체하고, 이전 요소를 반환합니다. */
    public E set(int i, E e) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        E temp = data[i];
        data[i] = e;
        return temp;
    }

    /** 인덱스 i에 요소 e를 삽입합니다. 이후의 요소들은 뒤로 밀려납니다(shifting). */
    public void add(int i, E e) throws IndexOutOfBoundsException {
        checkIndex(i, size + 1);
        if (size == data.length) {           // 용량이 부족한 경우 (not enough capacity)
            resize(2 * data.length);         // 현재 용량의 2배로 늘림 (double the capacity)
        }
        for (int k = size - 1; k >= i; k--) { // 가장 오른쪽 요소부터 뒤로 이동(shifting) 시작
            data[k + 1] = data[k];
        }
        data[i] = e;                         // 새로운 요소 삽입
        size++;
    }

    /** 인덱스 i의 요소를 삭제하고 반환합니다. 빈 자리를 채우기 위해 이후 요소들을 당깁니다(shifting). */
    public E remove(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        E temp = data[i];
        for (int k = i; k < size - 1; k++) {  // 빈 공간(hole)을 채우기 위해 요소들을 앞으로 이동
            data[k] = data[k + 1];
        }
        data[size - 1] = null;                // 가비지 컬렉션(GC)을 돕기 위해 빈 공간을 null로 처리
        size--;
        return temp;
    }

    // 유틸리티 메서드 (utility method)
    /** 인덱스가 [0, n-1] 범위 안에 있는지 확인합니다. */
    protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
        if (i < 0 || i >= n) {
            throw new IndexOutOfBoundsException("Illegal index: " + i);
        }
    }

    /** 내부 배열의 크기를 조정(Resizes)합니다. */
    protected void resize(int capacity) {
        E[] temp = (E[]) new Object[capacity]; // 새로운 크기의 배열 생성
        for (int k = 0; k < size; k++) {
            temp[k] = data[k];                // 기존 데이터 복사
        }
        data = temp;                          // 새로운 배열을 사용하기 시작
    }
}