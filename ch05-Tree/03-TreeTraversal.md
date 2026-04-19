## 이진 트리의 순회 (Binary Tree Traversal)
이진 트리에서 수행되는 대부분의 연산(출력, 복사, 삭제 등)은 트리를 순회하면서 진행됩니다.  
모든 순회 방식은 루트 노드에서 시작하며, 각 노드를 반드시 1번씩 방문해야 종료됩니다.  

### 3. Preorder Traversal (전위 순회, V→L→R) 
전위 순회는 이름 그대로 현재 노드를 먼저 처리한 후, 자식 노드들을 탐색합니다. 탐색의 흐름은 항상 왼쪽에서 오른쪽으로 진행됩니다.  
  
V (Visit): 현재 노드를 방문합니다.  
L (Left): 왼쪽 서브트리를 순회합니다.  
R (Right): 오른쪽 서브트리를 순회합니다.  
  
#### 3-1. 재귀적 정의:   
각 서브트리의 방문 역시 동일한 NLR 방식으로 이루어집니다.     
즉, 트리 전체가 하나의 커다란 NLR 구조이면서, 그 안의 작은 가지들도 **각각 NLR 구조** 를 가집니다.  

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
#### 3-2. Preorder Traversal(전위 순회) 단계별 상세 실행 과정 (Step 1 ~ 8)       
전위 순회는 **루트 노드를 먼저 방문**한 후, 왼쪽과 오른쪽 서브트리를 순차적으로 탐색하는 방식입니다.  

* **수행 순서:** **N(현재 노드 방문)** → **L(왼쪽 서브트리 이동)** → **R(오른쪽 서브트리 이동)**  
* **스택 동작:** 오른쪽 자식(R)은 현재 단계의 왼쪽(L) 과정이 완전히 종료될 때까지 시스템 스택(Stack)에서 대기합니다.  

| 단계 | 실행 내용 (N → L → R) | 시스템 스택 (대기 중인 R) | 출력 결과 |  
| :--- | :--- | :--- | :--- |
| **Step 1** | **preorder(A)** 시작<br>N: 노드 **A** 방문 및 출력<br>L: preorder(B) 호출<br>R: preorder(C) 대기 | `preorder(C)` | **A** |
| **Step 2** | **preorder(B)** 시작<br>N: 노드 **B** 방문 및 출력<br>L: preorder(D) 호출<br>R: preorder(E) 대기 | `preorder(E)` → `preorder(C)` | **A B** |
| **Step 3** | **preorder(D)** 시작<br>N: 노드 **D** 방문 및 출력<br>L: preorder(G) 호출<br>R: 없음(null) | `preorder(E)` → `preorder(C)` | **A B D** |
| **Step 4** | **preorder(G)** 시작<br>N: 노드 **G** 방문 및 출력<br>L/R: 자식 없음(null) 호출 종료<br>↩️ **preorder(B)의 R(Step 2 대기분)로 복귀** | `preorder(E)` → `preorder(C)` | **A B D G** |
| **Step 5** | **preorder(E)** 시작 (B의 오른쪽 자식)<br>N: 노드 **E** 방문 및 출력<br>L: preorder(H) 호출<br>R: 없음(null) | `preorder(C)` | **A B D G E** |
| **Step 6** | **preorder(H)** 시작<br>N: 노드 **H** 방문 및 출력<br>L/R: 자식 없음(null) 호출 종료<br>↩️ **preorder(A)의 R(Step 1 대기분)로 복귀** | `preorder(C)` | **A B D G E H** |
| **Step 7** | **preorder(C)** 시작 (A의 오른쪽 자식)<br>N: 노드 **C** 방문 및 출력<br>L: 없음(null) 호출 종료<br>R: preorder(F) 호출 | (Empty) | **A B D G E H C** |
| **Step 8** | **preorder(F)** 시작<br>N: 노드 **F** 방문 및 출력<br>L/R: 자식 없음 호출 종료<br>🔚 **F → C → A 순으로 복귀 후 프로그램 종료** | (Empty) | **A B D G E H C F** |


#### 3-3. 시간 및 공간 복잡도
* **시간 복잡도:** $O(N)$
    * 모든 노드를 정확히 한 번씩 방문하므로 노드 수($N$)에 비례합니다.
* **공간 복잡도:** $O(H)$
    * 트리의 높이($H$)만큼 재귀 스택이 쌓입니다.
    * **주의:** 편향 트리(Skewed Tree)의 경우 높이가 $N$과 같아져 $O(N)$까지 늘어날 수 있습니다.

#### 3-4. 주요 활용 및 장단점
* **주요 활용:** * **트리 복사:** 루트를 먼저 생성해야 하므로 구조를 그대로 복사할 때 유리합니다.  
    * **전위 표기법(Prefix):** 수식 계산 시 연산자를 피연산자 앞에 배치하는 계산법에 사용됩니다.  
    * **구조 출력:** 트리의 상단부터 계층적으로 출력할 때 적합합니다.  
* **장점:** 뿌리(Root) 노드를 가장 먼저 파악할 수 있어 트리의 전체적인 골격을 잡는 데 유용합니다.  


