package com.example.astonhw4_2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class UsersFragment : Fragment() {

    lateinit var adapter: UsersAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userList = mutableListOf(
            User("0","Иван", "Иванов", "+1234567890", "https://loremflickr.com/cache/resized/65535_52262425980_36be11baf3_n_200_200_nofilter.jpg"),
            User("1","Елена", "Петрова", "+0987654321", "https://loremflickr.com/cache/resized/65535_52688291677_2d1961dfaa_n_200_200_nofilter.jpg"),
            User("2","Максим", "Сидоров", "+1112233445", "https://loremflickr.com/cache/resized/4838_44768963335_a7413783cc_n_200_200_nofilter.jpg"),
            User("3","Анна", "Кузнецова", "+6677889900", "https://loremflickr.com/cache/resized/65535_52485773254_572ae23e7a_200_200_nofilter.jpg")
        )

        recyclerView = view.findViewById(R.id.rv_users)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = UsersAdapter(userList){ user ->
            (requireActivity() as EditButtonClickListener).onEditButtonClickListener(user)
        }
        recyclerView.adapter = adapter
    }

    interface EditButtonClickListener{
        fun onEditButtonClickListener(user: User)
    }

    companion object {
        const val USER_FRAGMENT_TAG = "USER_FRAGMENT_TAG"

        fun newInstance() = UsersFragment()
    }
}