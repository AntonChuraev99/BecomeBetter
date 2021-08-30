package com.antonchuraev.becomebetter.fragments.bottomNavigation.addGoal.createGoalFromTemplate

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.base.BaseFragment
import com.antonchuraev.becomebetter.base.cicerone.Screens
import com.antonchuraev.becomebetter.dataClasses.Goal
import com.antonchuraev.becomebetter.databinding.FragmentCreateGoalFromTemplatesBinding
import com.antonchuraev.becomebetter.helpers.extensions.add10DpDecorator
import com.antonchuraev.becomebetter.views.goals.TemplateListItemView
import moxy.presenter.InjectPresenter


class CreateGoalFromTemplatesFragment : BaseFragment<FragmentCreateGoalFromTemplatesBinding>() ,
        CreateGoalFromTemplatesView {

    @InjectPresenter
    lateinit var presenter: CreateGoalFromTemplatesPresenter

    override val layoutView: Int = R.layout.fragment_create_goal_from_templates

    val templatesAdapter = TemplatesAdapter().apply {
        onItemClickListener = { item->
            onTemplateClick(item)
        }
    }

    private fun onTemplateClick(item: Goal) {
        getRouter().navigateTo(Screens.Screen.AddGoals.CreateGoal(item))
    }

    override fun onCreateView(rootView: View) {
        enableToolbar()
        bindRv()
        presenter.loadTemplates(requireContext())
    }

    private fun enableToolbar() {
        toolbar?.let { toolbar->
            toolbar.show()
            toolbar.setTittle(context?.getString(R.string.choose_template))
            toolbar.defaultBackButton(getRouter())
        }
    }


    private fun bindRv() {
        binding.rvTemplates.apply {
            layoutManager = GridLayoutManager(context , 2) //3
            add10DpDecorator(context , DividerItemDecoration.VERTICAL )
            adapter = templatesAdapter
        }
    }

    override fun showTemplates(templates: MutableList<Goal>) {
        templatesAdapter.items = templates
    }

    class TemplatesAdapter:RecyclerView.Adapter<TemplatesAdapter.TemplateViewHolder>(){

        var onItemClickListener: ((Goal) -> Unit)? = null

        var items = mutableListOf<Goal>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TemplateViewHolder {
            return TemplateViewHolder(TemplateListItemView(parent.context))
        }

        override fun onBindViewHolder(holder: TemplateViewHolder, position: Int) {
            holder.bind(items[position])
            holder.templateView.setOnClickListener {
                onItemClickListener?.invoke(items[position])
            }
        }

        override fun getItemCount() = items.size

        class TemplateViewHolder(val templateView:TemplateListItemView):RecyclerView.ViewHolder(templateView){
            fun bind(template:Goal){
                templateView.setData(template)
            }

        }
    }


    companion object {

        fun newInstance() = CreateGoalFromTemplatesFragment().apply {

        }

    }

}