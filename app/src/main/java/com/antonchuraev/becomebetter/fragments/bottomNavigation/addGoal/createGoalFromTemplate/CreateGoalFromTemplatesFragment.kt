package com.antonchuraev.becomebetter.fragments.bottomNavigation.addGoal.createGoalFromTemplate

import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.base.BaseFragment
import com.antonchuraev.becomebetter.dataClasses.Template
import com.antonchuraev.becomebetter.databinding.FragmentCreateGoalBinding
import com.antonchuraev.becomebetter.databinding.FragmentCreateGoalFromTemplatesBinding
import com.antonchuraev.becomebetter.fragments.bottomNavigation.addGoal.createGoal.CreateGoalFragment
import com.antonchuraev.becomebetter.views.goals.TemplateListItemView
import moxy.presenter.InjectPresenter


class CreateGoalFromTemplatesFragment : BaseFragment<FragmentCreateGoalFromTemplatesBinding>() ,
        CreateGoalFromTemplatesView {

    @InjectPresenter
    lateinit var presenter: CreateGoalFromTemplatesPresenter

    override val layoutView: Int = R.layout.fragment_create_goal_from_templates

    val templatesAdapter = TemplatesAdapter().apply {
        onItemClickListener = {item->

        }
    }

    override fun onCreateView(rootView: View) {
        enableToolbar()
        bindRv()
        presenter.loadTemplates()
    }

    private fun enableToolbar() {
        toolbar?.let { toolbar->
            toolbar.show()
            toolbar.setTittle(context?.getString(R.string.choose_template))
            toolbar.defaultBackButton(appRouter)
        }
    }


    private fun bindRv() {
        binding.rvTemplates.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = templatesAdapter
        }
    }

    override fun showTemplates(templates: MutableList<Template>) {
        templatesAdapter.items = templates
    }

    class TemplatesAdapter:RecyclerView.Adapter<TemplatesAdapter.TemplateViewHolder>(){

        var onItemClickListener: ((Template) -> Unit)? = null

        var items = mutableListOf<Template>()
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
            fun bind(template:Template){
                templateView.setData(template)
            }

        }
    }


    companion object {

        fun newInstance() = CreateGoalFromTemplatesFragment().apply {

        }

    }

}