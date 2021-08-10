package com.antonchuraev.becomebetter.fragments.bottomNavigation.allGoals.myGoals

import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.base.BaseFragment
import com.antonchuraev.becomebetter.base.Screens
import com.antonchuraev.becomebetter.dataClasses.Goal
import com.antonchuraev.becomebetter.databinding.FragmentMyGoalsBinding
import com.antonchuraev.becomebetter.fragments.bottomNavigation.addGoal.createGoal.CreateGoalFragment
import com.antonchuraev.becomebetter.views.goals.MyGoalListItemView
import moxy.presenter.InjectPresenter


class MyGoalsFragment : BaseFragment<FragmentMyGoalsBinding>(), MyGoalsView {

    @InjectPresenter
    lateinit var presenter: MyGoalsGoalsPresenter

    override val layoutView: Int = R.layout.fragment_my_goals

    val activeGoalsAdapter = GoalsAdapter().apply {
        onItemClickListener = { item ->
            appRouter.navigateTo(Screens.Screen.AddGoals.CreateGoal(item , mode = CreateGoalFragment.Companion.Mode.EDIT))
        }
    }

    val disabledGoalsAdapter = GoalsAdapter().apply {
        isActiveMode = false
        onItemClickListener = { item ->
            appRouter.navigateTo(Screens.Screen.AddGoals.CreateGoal(item ,mode = CreateGoalFragment.Companion.Mode.EDIT))
        }
    }

    override fun onCreateView(rootView: View) {
        enableToolbar()
        setListeners()
        setRv()
        presenter.loadActiveGoals()
        presenter.loadActiveGoals()
    }

    private fun setRv() {
        binding.rvActiveGoals.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = activeGoalsAdapter
        }
        binding.rvDisabledGoals.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = disabledGoalsAdapter
        }
    }

    private fun enableToolbar() {
        setDefaultToolbar(R.string.all_my_goals)
        toolbar?.setRightButton(R.drawable.ic_edit) {
            activeGoalsAdapter.isSelectionEnabled = !activeGoalsAdapter.isSelectionEnabled
            disabledGoalsAdapter.isSelectionEnabled = !disabledGoalsAdapter.isSelectionEnabled
        }
    }

    private fun setListeners() {
        activeGoalsAdapter.onItemSelectionChange = { item,state ->
            if (!state){
                activeGoalsAdapter.items.remove(item)
                disabledGoalsAdapter.items.add(item)
                chekAdaptersChangeVisibility()
            }
        }
        disabledGoalsAdapter.onItemSelectionChange = { item,state ->
            if (state){
                disabledGoalsAdapter.items.remove(item)
                activeGoalsAdapter.items.add(item)
                chekAdaptersChangeVisibility()
            }
        }
    }

    private fun chekAdaptersChangeVisibility() {
        binding.llActiveGoals.isVisible = activeGoalsAdapter.items.isNotEmpty()
        binding.llDisabledGoals.isVisible = disabledGoalsAdapter.items.isNotEmpty()
    }

    override fun showActiveGoals(templates: MutableList<Goal>) {
        activeGoalsAdapter.items = templates
    }

    override fun showDisabledGoals(goals: MutableList<Goal>) {
        disabledGoalsAdapter.items = goals
        binding.llDisabledGoals.isVisible = goals.isNotEmpty()
    }

    class GoalsAdapter : RecyclerView.Adapter<GoalsAdapter.GoalViewHolder>() {

        var isActiveMode = true

        var isSelectionEnabled = false
            set(value) {
                field = value
                notifyDataSetChanged()
            }

        var onItemClickListener: ((Goal) -> Unit)? = null

        var onItemSelectionChange:((Goal, Boolean) -> Unit)? = null

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
            holder.bind(items[position])
            holder.goalView.apply {
                setOnClickListener {
                    if (isSelectionEnabled) {
                        items[position].isSelected = !items[position].isSelected
                        onItemSelectionChange?.invoke(items[position] , items[position].isSelected )
                        notifyDataSetChanged() // TODO: 09.08.2021 optimize 
                    }
                    else{
                        onItemClickListener?.invoke(items[position])
                    }
                }
                setSelectionMode(isSelectionEnabled)
                setChecked(items[position].isSelected)
                isInActiveMode(isActiveMode)
            }

        }

        override fun getItemCount() = items.size

        class GoalViewHolder(val goalView: MyGoalListItemView) : RecyclerView.ViewHolder(goalView) {
            fun bind(template: Goal) {
                goalView.setData(template)
            }

        }
    }

    companion object {
        fun newInstance() = MyGoalsFragment().apply {

        }
    }


}