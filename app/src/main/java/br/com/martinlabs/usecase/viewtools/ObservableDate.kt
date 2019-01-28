package br.com.martinlabs.usecase.viewtools

import android.databinding.BaseObservable
import android.databinding.ObservableField
import java.util.*

class ObservableDate : ObservableField<DateOrString> {
    constructor(date: Date?) : super(DateOrString(date, null))
    constructor(str: String?) : super(DateOrString(null, str))
    constructor() : super()
}

class DateOrString(val date: Date? = null, val str: String? = null)