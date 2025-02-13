package com.example.userListScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userListScreen.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 가상의 사용자 리스트 생성
        val users = listOf(
            User("Alice", "alice@example.com"),
            User("Bob", "bob@example.com"),
            User("Charlie", "charlie@example.com"),
            User("Diana", "diana@example.com"),
            User("Alice", "alice@example.com"),
            User("Bob", "bob@example.com"),
            User("Charlie", "charlie@example.com"),
            User("Diana", "diana@example.com"),
            User("Alice", "alice@example.com"),
            User("Bob", "bob@example.com"),
            User("Charlie", "charlie@example.com"),
            User("Diana", "diana@example.com"),
            User("Alice", "alice@example.com"),
            User("Bob", "bob@example.com"),
            User("Charlie", "charlie@example.com"),
            User("Diana", "diana@example.com")
        )

        // 어댑터 생성
        val adapter = UserAdapter(users)

        // RecyclerView의 레이아웃 매니저 설정
        binding.rvUsers.layoutManager = LinearLayoutManager(this)

        // also를 사용하여 어댑터 설정
        binding.rvUsers.also {
            it.adapter = adapter
        }
    }
}