package com.antonchuraev.becomebetter.fragments.bottomNavigation.allGoals.myGoals

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.base.BaseFragment
import com.antonchuraev.becomebetter.base.Screens
import com.antonchuraev.becomebetter.dataClasses.Goal
import com.antonchuraev.becomebetter.databinding.FragmentAllGoalsBinding
import com.antonchuraev.becomebetter.databinding.FragmentMyGoalsBinding
import com.antonchuraev.becomebetter.fragments.bottomNavigation.NavigationTab
import com.antonchuraev.becomebetter.fragments.bottomNavigation.addGoal.createGoalFromTemplate.CreateGoalFromTemplatesFragment
import com.antonchuraev.becomebetter.fragments.bottomNavigation.allGoals.AllGoalsPresenter
import com.antonchuraev.becomebetter.fragments.bottomNavigation.allGoals.AllGoalsView
import com.antonchuraev.becomebetter.views.goals.MyGoalListItemView
import com.antonchuraev.becomebetter.views.goals.TemplateListItemView
import moxy.presenter.InjectPresenter


class MyGoalsFragment : BaseFragment<FragmentMyGoalsBinding>(), MyGoalsView {

    @InjectPresenter
    lateinit var presenter: MyGoalsGoalsPresenter

    override val layoutView: Int = R.layout.fragment_my_goals

    val goalsAdapter = GoalsAdapter().apply {
        onItemClickListener = { item ->
            appRouter.navigateTo(Screens.Screen.AddGoals.CreateGoal(item))
        }
    }

    override fun onCreateView(rootView: View) {
        enableToolbar()
        setListeners()
        setRv()
        presenter.loadAllTestGoals()
    }

    private fun setRv() {
        binding.rvGoals.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = goalsAdapter
        }
    }

    private fun enableToolbar() {
        setDefaultToolbar(R.string.all_my_goals)
        toolbar?.setRightButton(R.drawable.ic_edit) {
            goalsAdapter.isSelectionEnabled = !goalsAdapter.isSelectionEnabled
        }
    }

    private fun setListeners() {

    }

    override fun showAllTemplates(templates: MutableList<Goal>) {
        goalsAdapter.items = templates
    }

    class GoalsAdapter : RecyclerView.Adapter<GoalsAdapter.GoalViewHolder>() {

        var isSelectionEnabled = false
            set(value) {
                field = value
                notifyDataSetChanged()
            }

        var onItemClickListener: ((Goal) -> Unit)? = null

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
                        notifyItemChanged(position)
                    }
                    else{
                        onItemClickListener?.invoke(items[position])
                    }
                }
                setSelectionMode(isSelectionEnabled)
                setChecked(items[position].isSelected)
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