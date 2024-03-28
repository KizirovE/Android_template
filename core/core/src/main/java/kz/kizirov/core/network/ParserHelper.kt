package kz.kizirov.core.network

import android.os.Build
import trikita.log.Log
import java.text.SimpleDateFormat
import java.util.*

class ParserHelper {
    enum class DATE_TEMPLATE{
        DD_MM_YYYY,
        DD_MM_YYYY_HH_MM_SS,
        DD_MM_YYYY_HH_MM,
        HH_MM_SS,
    }
    companion object {

        fun getActualLocalText(kz: String?, ru: String?): String? {
            if(Locale.getDefault().getLanguage() == "kk" && kz != null){
                return kz
            }else if(ru != null){
                return ru
            }else{
                return null
            }
        }


        fun formatDate(dateRaw: String?, template: DATE_TEMPLATE):String?{
            if(dateRaw == null) return null
            val parse = parseDate(dateRaw)
            if(parse == null) return null
            try {
                when (template) {
                    DATE_TEMPLATE.DD_MM_YYYY -> return SimpleDateFormat("dd.MM.yyyy").format(parse)
                    DATE_TEMPLATE.DD_MM_YYYY_HH_MM_SS -> return SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(parse)
                    DATE_TEMPLATE.DD_MM_YYYY_HH_MM -> return SimpleDateFormat("dd.MM.yyyy HH:mm").format(parse)
                    DATE_TEMPLATE.HH_MM_SS -> return SimpleDateFormat("HH:mm:ss").format(parse)
                }
            }catch (e:Exception){
                return null
            }
        }

        private fun parseDate(dateRaw: String):Date?{
            val listTeamplate = listOf<String>(
                "yyyy-MM-dd'T'HH:mm:ss.SSSXXX",
                "yyyy-MM-dd'T'HH:mm:ss.Sz",
                "yyyy-MM-dd HH:mm:ss",
                "yyyy-MM-dd",
            )
            listTeamplate.forEach { tamplate ->
                val date = parseDateFromTamplate(dateRaw, tamplate)
                if(date != null) return date
            }
            return null
        }
        private fun parseDateFromTamplate(dateRaw: String, template: String): Date?{
            try {
                val sdfP = SimpleDateFormat(template)
                return sdfP.parse(dateRaw)
            }catch (e: Exception){
                return null
            }
        }

        fun digitalWithSpace(digital: Double?): String? {
            if(digital == null) return null
            val digitalString = digital.toBigDecimal().toPlainString()
            val afterDot = try {
                "." + digitalString.split(".")[1]
            }catch (e: Exception){
                ""
            }
            val d = digitalString.split(".")[0]
            var digitalWithSpace = ""
            d.reversed().forEachIndexed { index, c ->
                digitalWithSpace+= c
                if((index+1)%3 == 0){
                    digitalWithSpace+= " "
                }
            }
            return digitalWithSpace.reversed() + afterDot
        }
    }
}