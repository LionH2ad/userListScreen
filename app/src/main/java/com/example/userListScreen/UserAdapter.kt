package com.example.userListScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.userListScreen.databinding.ItemUserBinding

/**
 * 데이터 제공: getItemCount()를 통해 데이터 개수를 제공합니다.
 * 뷰 생성: onCreateViewHolder()를 통해 항목 뷰(레이아웃)를 생성합니다.
 * 데이터 바인딩: onBindViewHolder()에서 각 항목의 데이터를 해당 뷰에 바인딩합니다.
 * 순서: 1. UserAdapter 클래스가 처음 호출될 때, 인스턴스가 생성되고 RecyclerView에 연결됩니다.
 *      2. getItemCount() 함수로 전체 데이터 개수를 확인한 후,
 *      3. onCreateViewHolder() 함수가 호출되어 새 아이템 뷰가 필요할 때마다 XML 레이아웃을 뷰로 변환하고,
 *         이 과정에서 UserViewHolder의 생성자가 호출되어 뷰 홀더 객체가 생성됩니다.
 *      4. 이후 **onBindViewHolder()**가 호출되어 해당 위치의 데이터를 뷰에 바인딩하게 됩니다.
 *      5. 스크롤 등의 동작에 따라 뷰 홀더는 재활용되며, 필요한 경우 다시 데이터가 업데이트됩니다.
 * */
class UserAdapter(private val userList: List<User>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    /**
     * - 역할 : 개별 항목 뷰를 보관하는 역할을 하며, 뷰 객체들을 재활용(recycle)할 수 있도록 관리합니다.
     * inner 키워드를 사용하여 외부 클래스(UserAdapter)의 멤버에 접근할 수 있습니다.
     * 파라미터 binding: ItemUserBinding
     * - ItemUserBinding: 뷰 바인딩 클래스로, item_user.xml 레이아웃 파일의 각 뷰들을 자동 으로 참조할 수 있게 해줍니다.
     * binding 변수는 해당 항목의 루트 뷰와 하위 뷰들에 접근할 수 있도록 해주며, 데이터 바인딩을 보다 간결하게 만들어 줍니다.
     * - RecyclerView.ViewHolder(binding.root)
     * 뷰 홀더는 반드시 itemView를 가져야 하는데, 여기서는 binding.root를 전달하여 item_user.xml의 최상위 뷰를 사용합니다.
     */
    inner class UserViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    /**
     * - 역할: 개별 아이템을 표현할 뷰 홀더(ViewHolder)를 생성합니다.
     * - 언제 호출되는가?: RecyclerView가 새로운 뷰 홀더가 필요할 때 호출됩니다.
     *
     * - 파라미터
     * (parent: ViewGroup) : 새 뷰가 속하게 될 부모 뷰 그룹(보통 RecyclerView)입니다.
     * (viewType: Int) : 항목의 뷰 타입을 나타내며, 여러 뷰 타입을 사용할 때 구분하는 용도로 사용합니다. (여기서는 단일 뷰 타입만 사용)
     * - 작업 과정:
     * 1. ItemUserBinding.inflate(...)를 통해 XML 레이아웃 파일(item_user.xml 등)을 메모리에 로드하고, 이를 기반으로 뷰 객체를 생성합니다.
     * 2. 생성된 binding 객체를 이용해 새로운 UserViewHolder 인스턴스를 생성하고 반환합니다.
     * */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    /**
     * - 역할: RecyclerView의 특정 위치에 있는 데이터를 해당 뷰 홀더의 뷰에 바인딩합니다.
     * - 작업 과정:
     * 1. userList[position]를 통해 해당 항목의 User 객체를 가져 옵니다.
     * 2. holder.binding.apply { ... } 블록을 사용 하여 뷰 바인딩 객체에 쉽게 접근 하여 각각 매칭 해줍니다.
     * */
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.binding.apply {
            tvName.text = user.name
            tvEmail.text = user.email
        }
    }

    // 역할: 어댑터가 관리하는 전체 항목의 개수를 반환합니다.
    override fun getItemCount(): Int = userList.size
}