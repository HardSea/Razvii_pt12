package com.pmacademy.razvii_pt12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mePerson = preparePersonObject()

        val rv = findViewById<RecyclerView>(R.id.recyclerView)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = CustomRecyclerViewAdapter(mePerson)
    }


    private fun preparePersonObject(): Person {
        val me = Person("Artem", 20)

        me.mother = Person("Maria", 41)
        me.father = Person("Oleksandr", 42)

        me.mother?.mother = Person("Lyba", 61)
        me.mother?.father = Person("Vitaliy", 63)

        me.father?.mother = Person("Anna", 73)
        me.father?.father = Person("Anatoliy", 75)

        me.mother?.mother?.mother = Person("Viktoria", 85)
        me.mother?.mother?.father = Person("Viktor", 87)
        me.mother?.father?.mother = Person("Vasilina", 88)
        me.mother?.father?.father = Person("Petro", 86)

        me.father?.father?.mother = Person("Galya", 94)
        me.father?.father?.father = Person("Stepan", 95)
        me.father?.mother?.mother = Person("Tetyana", 93)
        me.father?.mother?.father = Person("Oleksandr", 96)


        return me
    }
}