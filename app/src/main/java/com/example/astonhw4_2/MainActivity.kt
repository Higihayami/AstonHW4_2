package com.example.astonhw4_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), UsersFragment.EditButtonClickListener, EditUserFragment.SaveButtonListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(supportFragmentManager.beginTransaction()) {
            replace(R.id.container, UsersFragment())
            commit()
        }
    }

    override fun onEditButtonClickListener(user: User) {
        with(supportFragmentManager.beginTransaction()) {
            replace(
                R.id.container,
                EditUserFragment.newInstance(user),
                EditUserFragment.EDIT_USER_FRAGMENT
            )
            addToBackStack(EditUserFragment.EDIT_USER_FRAGMENT)
            commit()
        }
    }

    override fun onSaveButtonListener(user: User) {
        supportFragmentManager.addOnBackStackChangedListener {
            val fragment = supportFragmentManager.findFragmentById(R.id.container)
            if (fragment is UsersFragment) {
                fragment.adapter.newUser(user)
            }
        }
        with(supportFragmentManager.beginTransaction()) {
            replace(
                R.id.container,
                UsersFragment.newInstance(),
                UsersFragment.USER_FRAGMENT_TAG
            )
            addToBackStack(UsersFragment.USER_FRAGMENT_TAG)
            commit()
        }
    }
}