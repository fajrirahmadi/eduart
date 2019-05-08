package arloji.life.smarthumanity.utils.string

import android.app.Activity
import android.content.res.Configuration
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class StringHelper {
    companion object {
        fun getStringBuilderToString(vararg items: String): String {
            val stringBuilder = StringBuilder()
            for (s in items) {
                stringBuilder.append(s)
            }
            return stringBuilder.toString()
        }

        private fun getStringBuilderToStringFromList(list: List<String>, divider: String): String {
            val stringBuilder = StringBuilder()
            val i = list.size - 1
            for (x in list.indices) {
                if (list[x].isNotBlank()) {
                    stringBuilder.append(list[x])
                    if (x != i) {
                        stringBuilder.append(divider)
                    }
                }
            }
            return stringBuilder.toString()
        }

        private fun getStringByLocale(context: Activity, id: Int, locale: String): String {
            val configuration = Configuration(context.resources.configuration)
            configuration.setLocale(Locale(locale))
            return context.createConfigurationContext(configuration).resources.getString(id)
        }

        fun getStringByLocaleEN(context: Activity, id: Int): String {
            return getStringByLocale(context, id, "en")
        }

        fun splitUpperCaseCharacter(value: String): String {
            val values = value.split("(?=\\p{Upper})".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            return getStringBuilderToStringFromList(Arrays.asList(*values), " ")
        }

        fun getLastIndexWordFromString(value: String): String {
            val values = value.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            return values[values.size - 1]
        }

        fun getPriceInRp(price: BigDecimal?): String {
            return if (price != null)
                getStringBuilderToString("Rp ", getSimplePriceFormatter(price.toDouble()))
            else
                ""
        }

        fun getPriceInRp(price: Long?): String {
            return if (price != null)
                getStringBuilderToString("Rp ", getSimplePriceFormatter(price.toDouble()))
            else
                ""
        }

        fun getSimplePriceFormatter(format: String, price: Double?): String {
            return String.format(format, price).replace(",", ".")
        }

        fun getSimplePriceFormatter(price: Double?): String {
            return String.format("%,.0f", price).replace(",", ".")
        }

        fun getDecimalFormatter(price: String): String {
            val formatter = DecimalFormat("#,###,###,###")
            return formatter.format(BigDecimal(price))
                .replace(",", ".")
        }

        fun getFormatterToLongDigitValue(price: BigDecimal?): String? {
            val formatter = DecimalFormat("#,###,###,###")
            return if (price != null)
                formatter.format(price).replace(",", "")
                    .replace(".", "")
            else
                null
        }

        fun removeDotFromFormatedValue(price: String?): String {
            return price?.replace(".", "") ?: ""
        }

        fun convertCommaToDot(value: String): String {
            return value.replace(",", ".")
        }

        fun getDecimalWithCommasFormatter(price: String): String {
            val otherSymbols = DecimalFormatSymbols()
            otherSymbols.decimalSeparator = ','
            otherSymbols.groupingSeparator = '.'
            val formatter = DecimalFormat("#.00")
            return formatter.format(BigDecimal(price))
        }
    }
}