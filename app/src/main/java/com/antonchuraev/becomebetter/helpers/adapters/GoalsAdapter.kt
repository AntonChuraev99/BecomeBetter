package com.antonchuraev.becomebetter.helpers.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.antonchuraev.becomebetter.dataClasses.Goal
import com.antonchuraev.becomebetter.views.goals.MyGoalListItemView

class GoalsAdapter : RecyclerView.Adapter<GoalsAdapter.GoalViewHolder>() {

    var isActiveMode = true

    var isSelectionEnabled = false
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onItemClickListener: ((Goal) -> Unit)? = null

    var onItemSelectionChange:((Goal, Boolean) -> Unit)? = null

    var onItemUpdateListener: ((Goal) -> Unit)? = null

    var items = mutableListOf<Goal>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder {
        return GoalViewHolder(
            MyGoalListItemView(parent.context)
        )
    }

    override fun onBindViewHolder(holder: GoalViewHolder, position: Int) {
        items[position]?.let { item->
            holder.bind(item)
            holder.goalView.apply {
                setOnClickListener {
                    if (isSelectionEnabled) {
                        item.isActive = !item.isActive
                        onItemSelectionChange?.invoke(item , item.isActive )
                    }
                    else{
                        onItemClickListener?.invoke(item)
                    }

                }
                updateGoalListener = onItemUpdateListener
                setSelectionMode(isSelectionEnabled)
                setChecked(item.isActive)
                isInActiveMode(isActiveMode)
            }
        }

    }

    override fun getItemCount() = items.size

    class GoalViewHolder(val goalView: MyGoalListItemView) : RecyclerView.ViewHolder(goalView) {
        fun bind(template: Goal) {
            goalView.setData(template)
        }

    }
}