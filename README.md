### **리스트 화면 (RecyclerView) 만들기 (`also` 사용)**  

---

- `RecyclerView` 를 사용하여 가상의 사용자 리스트를 화면에 출력하세요.
- 각 항목에는 사용자 이름과 이메일이 표시되도록 하세요.
- `also`를 사용하여 `RecyclerView`의 어댑터를 설정하세요.


아래는 리사이클러뷰의 재활용 메커니즘에 따른 과정을 하나의 순서도로 정리한 예시입니다.

```
         [리사이클러뷰 어댑터가 리사이클러뷰에 연결]
                          │
                          ▼
             onAttachedToRecyclerView 호출
                          │
                          ▼
                  데이터 항목 처리 시작
                          │
                          ▼
             ┌─────────────────────────┐
             │   각 항목(ViewHolder) 처리  │
             └─────────────────────────┘
                          │
                          ▼
             ┌─────────────────────────┐
             │  재활용 가능한 ViewHolder가  │
             │      존재하는가?         │
             └─────────────────────────┘
                          │
              ┌───────────┴────────────┐
           Yes│                        │No
              ▼                        ▼
    [재활용 풀에서 기존의              [새로운 ViewHolder 생성]
       ViewHolder 사용]                ──────────────────────► onCreateViewHolder 호출
              │                                  │
              └─────────────┬────────────────────┘
                            │
                            ▼
                onBindViewHolder 호출
             (새 데이터 바인딩 수행)
                            │
                            ▼
              onViewAttachedToWindow 호출
          (ViewHolder가 화면에 완전히 보임)
                            │
                            ▼
              ── 사용자가 스크롤 등으로 ──
                 View가 화면에서 벗어나면
                            │
                            ▼
             onViewDetachedFromWindow 호출
          (ViewHolder가 화면에서 완전히 보이지 않음)
                            │
                            ▼
              [ViewHolder 재활용 대상이 됨]
                            │
                            ▼
                   onViewRecycled 호출
          (해당 ViewHolder가 재활용 풀로 이동)
                            │
                            ▼
          ── 이후, 재활용 풀에서 해당 ViewHolder를 ──
          ── 재사용할 경우 onCreateViewHolder는 생략되고 ──
          ── onBindViewHolder만 호출하여 데이터 갱신 ──
                            │
                            ▼
          [필요 시 재활용된 ViewHolder로 onBindViewHolder 호출]
                            │
                            ▼
          [리사이클러뷰 어댑터가 리사이클러뷰에서 분리될 때]
                            │
                            ▼
                  onDetachedFromRecyclerView 호출
```

### 요약
1. **초기 연결**:
    - `onAttachedToRecyclerView`로 어댑터가 연결되고, 각 항목에 대해 데이터 바인딩을 시작합니다.
2. **항목 생성 및 바인딩**:
    - **재활용 가능 여부 확인**: 재활용 풀에 ViewHolder가 있으면 바로 `onBindViewHolder`로 데이터만 갱신하고, 없으면 `onCreateViewHolder`로 새 ViewHolder 생성 후 `onBindViewHolder` 호출합니다.
3. **화면 표시**:
    - `onViewAttachedToWindow`로 ViewHolder가 완전히 보이게 됩니다.
4. **화면에서 사라짐 및 재활용**:
    - 스크롤 등으로 화면에서 사라지면 `onViewDetachedFromWindow`가 호출되고, 이후 `onViewRecycled`를 통해 재활용 풀에 들어갑니다.
5. **재사용 시**:
    - 재활용된 ViewHolder는 다시 사용될 때 **onCreateViewHolder 호출 없이** 바로 `onBindViewHolder`만 호출되어 데이터가 갱신됩니다.
6. **종료**:
    - 어댑터가 리사이클러뷰에서 분리될 때 `onDetachedFromRecyclerView`가 호출됩니다.

이와 같이, onViewRecycled로 인해 재활용된 ViewHolder는 재사용 시 onCreateViewHolder 없이 onBindViewHolder만 호출되어 데이터를 갱신하게 됩니다.
