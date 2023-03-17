package at.htl.leonding.bhitm5.teacherapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import at.htl.leonding.bhitm5.teacherapp.databinding.TeacherCardBinding
import at.htl.leonding.bhitm5.teacherapp.module.Teacher

class TeachersAdapter(private val teachers: List<Teacher>): RecyclerView.Adapter<TeachersAdapter.TeacherViewHolder>() {
    val LOG_TAG = TeachersAdapter::class.java.simpleName
    class TeacherViewHolder(val binding: TeacherCardBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(teacher: Teacher){
            binding.teacher = teacher
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val binding = TeacherCardBinding.inflate(inflator, parent, false)
        val viewHolder = TeacherViewHolder(binding)
        return viewHolder
    }
    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        holder.bind(teachers[position])
        holder.binding.ivTeacher.setImageResource(teachers[position].imageUri)
    }
    override fun getItemCount() = teachers.size
}