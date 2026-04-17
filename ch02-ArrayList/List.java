/** java.util.List 인터페이스를 단순화한 버전(Simplified version)입니다. */
public interface List<E> {

    /** 리스트에 저장된 요소(Elements)의 총 개수를 반환합니다. */
    int size();

    /** 리스트가 비어있는지(Empty) 여부를 확인하여 반환합니다. */
    boolean isEmpty();

    /** * 인덱스(index) i에 위치한 요소를 반환합니다.
     * 요소를 제거(Remove)하지 않고 조회만 수행합니다.
     */
    E get(int i) throws IndexOutOfBoundsException;

    /** * 인덱스 i의 요소를 새로운 요소 e로 교체(Replace)합니다.
     * 교체되기 전의 이전 요소를 반환합니다.
     */
    E set(int i, E e) throws IndexOutOfBoundsException;

    /** * 인덱스 i에 새로운 요소 e를 삽입(Insert)합니다.
     * 해당 위치 뒤에 있는 요소들은 한 칸씩 뒤로 밀려납니다(Shifting).
     */
    void add(int i, E e) throws IndexOutOfBoundsException;

    /** * 인덱스 i에 있는 요소를 삭제(Remove)하고 반환합니다.
     * 삭제된 위치 뒤의 요소들은 앞으로 한 칸씩 당겨집니다(Shifting).
     */
    E remove(int i) throws IndexOutOfBoundsException;
}