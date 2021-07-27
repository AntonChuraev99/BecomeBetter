package com.antonchuraev.becomebetter.dataClasses

import java.io.Serializable
import java.time.Duration

data class Goal(
        val name:String,
        /**
         * 0F to R.string.forever , 1F to R.string.one_day , 2F to R.string.three_days ,
           3F to R.string.one_week , 4F to R.string.one_month , 5F to R.string.one_year
         */
        val duration:Float = 0F,
        val description:String? = null,
        /**
         * 0F to R.string.small_prority , 1F to R.string.standart_prority ,
                2F to R.string.higherst_prority
         */
        val priority:Float = 1F

):Serializable {



}