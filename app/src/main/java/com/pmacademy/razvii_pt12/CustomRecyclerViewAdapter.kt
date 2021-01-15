package com.pmacademy.razvii_pt12

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomRecyclerViewAdapter(person: Person) :
    RecyclerView.Adapter<CustomRecyclerViewAdapter.CustomViewHolder>() {

    private val personMap: MutableMap<Person, Int> = mutableMapOf()
    private lateinit var map: Map<Person, Int>
    private var arrayOfPerson: ArrayList<Person>

    //convert dp in px
    private val Int.px: Int
        get() = (this * Resources.getSystem().displayMetrics.density).toInt()

    private fun createSortedMap() {
        map = personMap.toList().sortedBy { (_, value) -> value }.toMap()
    }

    private fun createMapFromPerson(person: Person, depth: Int = 1) {
        personMap[person] = depth

        if (person.mother != null)
            createMapFromPerson(person.mother!!, depth + 1)

        if (person.father != null)
            createMapFromPerson(person.father!!, depth + 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val itemVIew =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return CustomViewHolder(itemVIew)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.tvName?.text = arrayOfPerson[position].name
        holder.tvAge?.text = arrayOfPerson[position].age.toString()

        val paddingRate: Int? = (map[arrayOfPerson[position]])
        if (paddingRate != null) {
            holder.tvName?.setPadding(0, 0, 0, (8.px * paddingRate))
            //setPadding get value in px, before we convert 8 to px and multiply to depth level
        }
    }

    override fun getItemCount(): Int {
        return map.size
    }

    class CustomViewHolder(itemVIew: View) : RecyclerView.ViewHolder(itemVIew) {
        var tvName: TextView? = null
        var tvAge: TextView? = null

        init {
            tvName = itemVIew.findViewById(R.id.tvName)
            tvAge = itemVIew.findViewById(R.id.tvAge)
        }

    }

    init {
        createMapFromPerson(person)
        createSortedMap()
        arrayOfPerson = ArrayList(map.keys)
    }
}
