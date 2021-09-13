package com.antonchuraev.becomebetter.dataClasses

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.antonchuraev.becomebetter.fragments.bottomNavigation.bottomBar.NavigationTab
import java.io.Serializable

@Entity
data class Goal(
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    var name: String,

    var progress: Int = 0,//прогресс от  0%=0 до 100%=100
    var progressMax:Int = 100,//максимум прогресса

    var progressType: ProgressType = ProgressType.PERCENTS_AND_DAYS, //тип как ведется проогресс
) : Serializable {

    /**
     * тип как ведется прогресс
     * мотивация - дни
     * копилка - свой макисмум
     * проекты - дни + 100%
     *
     */
    enum class ProgressType() {
        DAYS(),
        CUSTOM_MAX(),
        PERCENTS_AND_DAYS(),
        ;

        companion object{
            fun findByPosition(pos:Int) = values()[pos]
        }

    }

}