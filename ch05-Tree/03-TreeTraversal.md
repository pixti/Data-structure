## 3. 이진 트리의 순회 (Binary Tree Traversal)
이진 트리에서 수행되는 대부분의 연산(출력, 복사, 삭제 등)은 트리를 순회하면서 진행됩니다.  
모든 순회 방식은 루트 노드에서 시작하며, 각 노드를 반드시 1번씩 방문해야 종료됩니다.  

### 3-1. Preorder Traversal (전위 순회, V → L → R ) 
전위 순회는 이름 그대로 현재 노드를 먼저 처리한 후, 자식 노드들을 탐색합니다.  
탐색의 흐름은 항상 왼쪽에서 오른쪽으로 진행됩니다.  
  
N (Node) / V (Visit): 현재 노드를 방문합니다.  
L (Left): 왼쪽 서브트리를 순회합니다.  
R (Right): 오른쪽 서브트리를 순회합니다.  
  
#### 재귀적 정의:   
각 서브트리의 방문 역시 동일한 NLR 방식으로 이루어집니다.     
즉, 트리 전체가 하나의 커다란 NLR 구조이면서, 그 안의 작은 가지들도 각각 NLR 구조를 가집니다.  

<img width="396" height="301" alt="image" src="https://github.com/user-attachments/assets/641c42d5-613d-41b8-8497-6c25510a14fd" />

```java
public void preorder(Node n) { // 전위 순회
    if (n != null) {
        // 1. N: 현재 노드 n 방문 (데이터 출력 등)
        System.out.print(n.getKey() + " "); 
        
        // 2. L: n의 왼쪽 서브트리를 순회하기 위해 재귀 호출
        preorder(n.getLeft()); 
        
        // 3. R: n의 오른쪽 서브트리를 순회하기 위해 재귀 호출
        preorder(n.getRight()); 
    }
}
```
#### 전위 순회(Preorder) 알고리즘 실행 과정       
**N(현재 노드 방문) → L(왼쪽 서브트리 이동) → R(오른쪽 서브트리 이동)** 순서에 따라 수행되며,   
R은 현재 단계의 L 과정이 완전히 종료될 때까지 시스템 스택(Stack)에서 대기합니다.  

Step 1. preorder(A) 시작   
N: 노드 A를 방문하여 출력한다. -> **A**  
L: preorder(B)를 호출하며 이동한다.  
R: preorder(C)는 스택에 저장되어 대기한다. (L이 끝나야 실행 가능)  
Stack : preorder(C)

Step 2. preorder(B) 시작  
N: 노드 B를 방문하여 출력한다. -> **B**  
L: preorder(D)를 호출하며 이동한다.  
R: preorder(E)는 스택에 저장되어 대기한다. (L이 끝나야 실행 가능)  
Stack : preorder(E) -> preorder(C)  

Step 3. preorder(D) 시작  
N: 노드 D를 방문하여 출력한다. -> **D**  
L: preorder(G)를 호출하며 이동한다.  
R: 오른쪽 자식이 없으므로(null) 대기 작업이 없다.  
Stack : preorder(E) -> preorder(C)  

Step 4. preorder(G) 시작  
N: 노드 G를 방문하여 출력한다. -> **G**  
L: 왼쪽 자식이 없으므로(null) 호출을 종료한다.   
R: 오른쪽 자식이 없으므로(null) 호출을 종료한다.   
Return: preorder(B)의 R로 **복귀** 한다.   
Stack : preorder(E) -> preorder(C)  

Step 5. preorder(E) 시작 (Step 2에서 대기하던 preorder(B)의 R 실행)   
N: 노드 E를 방문하여 출력한다. -> **E**  
L: preorder(H)를 호출하며 이동한다.   
R: 오른쪽 자식이 없으므로(null) 대기 작업이 없다.   
Stack : preorder(C)  
  
Step 6. preorder(H) 시작  
N: 노드 H를 방문하여 출력한다. -> **H**  
L: 왼쪽 자식이 없으므로(null) 호출을 종료한다.      
R: 오른쪽 자식이 없으므로(null) 호출을 종료한다.      
Return: preorder(A)의 R로 **복귀** 한다.   
Stack : preorder(C)  

Step 7. preorder(C) 시작 (Step 1에서 대기하던 preorder(A)의 R 실행)    
N: 노드 C를 방문하여 출력한다. -> **C**  
L: 왼쪽 자식이 없으므로(null) 호출을 종료한다.  
R: preorder(F)를 호출하며 이동한다.  
Stack : 비어있음.  

Step 8. preorder(F) 시작  
N: 노드 F를 방문하여 출력한다. -> **F**  
L: 자식 노드가 없으므로 호출을 종료한다.   
R: 자식 노드가 없으므로 호출을 종료한다.    
Return: preorder(F) → preorder(C) → preorder(A) 순서로 복귀하고 프로그램을 종료한다.  


#### 특징 및 시간 복잡도  

시간 복잡도: $O(N)$ - 모든 노드를 한 번씩 방문하므로 노드 수에 비례   
공간 복잡도: $O(H)$ - 트리의 높이( $H$ ) 만큼 재귀 스택이 쌓임    
주요 활용 : 트리 복사, 전위 표기법(Prefix) 계산, 구조 출력  - 트리의 상단부터 아래로 훑는 작업에 유리  

**장점** : 트리의 구조를 그대로 복사하거나 저장할 때 유용합니다. 뿌리 노드를 가장 먼저 알 수 있기 때문입니다. 
**주의** : 편향 트리(Skewed Tree)의 경우 트리의 높이가 $N$ 과 같아져 공간 복잡도가 $O(N)$ 까지 늘어날 수 있습니다.  



