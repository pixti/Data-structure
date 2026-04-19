// 각 원소의 부모(parent)와 트리 내 높이(rank) 정보를 저장하는 기본 단위
public class Node {
    int parent;
    int rank;

    public Node(int newParent, int newRank) {
        parent = newParent;
        rank = newRank;
    }

    public int getParent() { return parent; }
    public int getRank() { return rank; }
    public void setParent(int newParent) { parent = newParent; }
    public void setRank(int newRank) { rank = newRank; }
}
