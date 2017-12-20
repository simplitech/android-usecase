package br.com.martinlabs.usecase.viewtools

import android.databinding.InverseMethod
import android.databinding.ObservableList

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

import br.com.martinlabs.usecase.model.GrupoDoPrincipal
import br.com.martinlabs.usecase.model.WithIdAndTitle

/**
 * Created by gil on 17/12/17.
 */

object Converter {

// region DOUBLE

    @JvmStatic
    fun fromDouble(value: Double?): String? {
        return value?.toString()
    }

    @JvmStatic
    @InverseMethod("fromDouble")
    fun toDouble(value: String?): Double? {
        return if (value == null || value.isEmpty()) null else java.lang.Double.parseDouble(value)
    }

// endregion

// region DOUBLE NOT NULL

    @JvmStatic
    fun fromDoubleNotNull(value: Double): String {
        return value.toString() + ""
    }

    @JvmStatic
    @InverseMethod("fromDoubleNotNull")
    fun toDoubleNotNull(value: String?): Double {
        return if (value == null || value.isEmpty()) 0.0 else java.lang.Double.parseDouble(value)
    }

// endregion

// region INT

    @JvmStatic
    fun fromInt(value: Int?): String? {
        return value?.toString()
    }

    @JvmStatic
    @InverseMethod("fromInt")
    fun toInt(value: String?): Int? {
        return if (value == null || value.isEmpty()) null else Integer.parseInt(value)
    }

// endregion

// region INT NOT NULL

    @JvmStatic
    fun fromIntNotNull(value: Int): String {
        return value.toString() + ""
    }

    @JvmStatic
    @InverseMethod("fromIntNotNull")
    fun toIntNotNull(value: String?): Int {
        return if (value == null || value.isEmpty()) 0 else Integer.parseInt(value)
    }

// region DATE

    private val INVALID_DATE = Date(5108400000L) // sentinel value: 1970-03-01T00:00:00-03:00

    @JvmStatic
    fun fromDate(format: String, value: Date?): String? {
        if (value == null) return null

        val formatter = SimpleDateFormat(format)
        return formatter.format(value)
    }

    @JvmStatic
    @InverseMethod("fromDate")
    fun toDate(format: String, value: String?): Date? {
        if (value == null || value.length == 0) return null

        if (value.length != format.length) return INVALID_DATE

        val formatter = SimpleDateFormat(format)
        formatter.isLenient = false
        try {
            return formatter.parse(value)
        } catch (e: ParseException) {
            return INVALID_DATE
        }

    }

    @JvmStatic
    fun isValidDateChange(oldValue: Date?, newValue: Date?): Boolean {
        return newValue == null || newValue != INVALID_DATE && newValue != oldValue
    }

// endregion

// region CPF

    @JvmStatic
    fun fromCPF(value: String?): String? {
        if (value == null) return null

        var v = value.replace("\\D".toRegex(), "")
        v = v.replace("(\\d{3})(\\d{3})(\\d{3})(\\d{2})$".toRegex(), "$1.$2.$3-$4")
        return v
    }

    @JvmStatic
    @InverseMethod("fromCPF")
    fun toCPF(value: String?): String? {
        if (value == null || value.length == 0) return null

        var v = value.replace("[. ,:\\-/]+".toRegex(), "")
        return v
    }

// endregion

// region CNPJ

    @JvmStatic
    fun fromCNPJ(value: String?): String? {
        if (value == null) return null

        var v = value.replace("\\D".toRegex(), "")
        v = v.replace("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})$".toRegex(), "$1.$2.$3/$4-$5")
        return v
    }

    @JvmStatic
    @InverseMethod("fromCNPJ")
    fun toCNPJ(value: String?): String? {
        if (value == null || value.length == 0) return null

        var v = value.replace("[. ,:\\-/]+".toRegex(), "")
        return v
    }

// endregion

// region RG

    @JvmStatic
    fun fromRG(value: String?): String? {
        if (value == null) return null

        var v = value.replace("\\D".toRegex(), "")
        v = v.replace("(\\d{2})(\\d{3})(\\d{3})(\\d{1})$".toRegex(), "$1.$2.$3-$4")
        return v
    }

    @JvmStatic
    @InverseMethod("fromRG")
    fun toRG(value: String?): String? {
        if (value == null || value.length == 0) return null

        var v = value.replace("[. ,:\\-/]+".toRegex(), "")
        return v
    }

// endregion

// region PHONE

    @JvmStatic
    fun fromPhone(value: String?): String? {
        if (value == null) return null

        var v = value.replace("\\D".toRegex(), "")
        v = v.replace("(\\d{2})(\\d{5})(\\d{4})$".toRegex(), "($1) $2-$3")
        return v
    }

    @JvmStatic
    @InverseMethod("fromPhone")
    fun toPhone(value: String?): String? {
        if (value == null || value.length == 0) return null

        var v = value.replace("[. ,:\\-/]+".toRegex(), "")
        return v
    }

// endregion

// region CEP

    @JvmStatic
    fun fromCEP(value: String?): String? {
        if (value == null) return null

        var v = value.replace("\\D".toRegex(), "")
        v = v.replace("(\\d{5})(\\d{3})$".toRegex(), "$1-$2")
        return v
    }

    @JvmStatic
    @InverseMethod("fromCEP")
    fun toCEP(value: String?): String? {
        if (value == null || value.length == 0) return null

        var v = value.replace("[. ,:\\-/]+".toRegex(), "")
        return v
    }

// endregion

// region INDEX FROM MODEL

    @JvmStatic
    fun <T : WithIdAndTitle> indexFromModel(list: ObservableList<T>?, model: T?): Int? {
        if (list != null && model != null) {
            for (i in list.indices) {
                val it = list[i]
                if (it != null && it.id === model.id) {
                    return i
                }
            }
        }

        return -1
    }

    @JvmStatic
    @InverseMethod("indexFromModel")
    fun <T : WithIdAndTitle> indexToModel(list: ObservableList<T>?, index: Int?): T? {
        return if (list != null && index != null) list[index] else null
    }

// endregion

}
