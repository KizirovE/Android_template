package kz.kizirov.core.navigation_compose

import trikita.log.Log

object ResultNavigation {

    private var result: Any? = null

    fun getValue(): Any?{
        if(result != null) {
            val returnResult = result
            result = null
            return returnResult
        }
        else{
            return null
        }
    }

    fun setValue(result: Any){
        this.result = result
    }
}