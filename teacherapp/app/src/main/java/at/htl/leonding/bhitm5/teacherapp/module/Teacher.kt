package at.htl.leonding.bhitm5.teacherapp.module

import at.htl.leonding.bhitm5.teacherapp.R

data class Teacher(val name: String, val day: String, val time: String, val room: String, val detailsId: Int?, val imageUri: Int){
    companion object{
        public fun getDummyTeachers(): List<Teacher>{
            val teachers = listOf<Teacher>(
                Teacher("Holzer Wolfgang", room = "E38", day = "Monday", time = "10:55 - 11:45", detailsId = 2314, imageUri = R.drawable.holzer),
                Teacher("Fabian Ettinger", room = "234", day = "Sunday", time = "23:00-0:05", detailsId = 12341234, imageUri = R.drawable.ettinger),
                Teacher("Marcel Pougeht", "Neversday", "12:00-12:50", "E34", 13243, imageUri = R.drawable.pouget)
            )
            return teachers
        }
    }
}

