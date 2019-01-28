package br.com.martinlabs.usecase.service

import android.databinding.*
import br.com.martinlabs.usecase.viewtools.ObservableDate
import com.google.gson.*
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * Created by gil on 28/03/18.
 */
fun buildGson() : Gson {
    return GsonBuilder().setDateFormat(Api.DATE_FORMAT)
            .registerTypeAdapter(ObservableField::class.java, ObservableFieldAdapter())
            .registerTypeAdapter(ObservableArrayList::class.java, ObservableArrayListAdapter())
            .registerTypeAdapter(ObservableLong::class.java, ObservableLongAdapter())
            .registerTypeAdapter(ObservableBoolean::class.java, ObservableBooleanAdapter())
            .registerTypeAdapter(ObservableDouble::class.java, ObservableDoubleAdapter())
            .registerTypeAdapter(ObservableFloat::class.java, ObservableFloatAdapter())
            .registerTypeAdapter(ObservableInt::class.java, ObservableIntAdapter())
            .create()
}

class ObservableFieldAdapter : JsonSerializer<ObservableField<Any>>, JsonDeserializer<ObservableField<Any>> {

    override fun serialize(src: ObservableField<Any>?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement? {
        return context?.serialize(src?.get())
    }

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): ObservableField<Any>? {
        val type = (typeOfT as ParameterizedType).actualTypeArguments[0]
        return ObservableField(context.deserialize<Any>(json, type))
    }
}

class ObservableArrayListAdapter : JsonSerializer<ObservableArrayList<Any>>, JsonDeserializer<ObservableArrayList<Any>> {

    override fun serialize(src: ObservableArrayList<Any>?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement? {
        return context?.serialize(src?.toArray())
    }

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): ObservableArrayList<Any>? {
        val type = (typeOfT as ParameterizedType).actualTypeArguments[0]
        val olist = ObservableArrayList<Any>()
        olist.addAll(context.deserialize<ArrayList<Any>>(json, ListParameterizedType(type)))
        return olist
    }
}

class ObservableLongAdapter : JsonSerializer<ObservableLong>, JsonDeserializer<ObservableLong> {

    override fun serialize(src: ObservableLong?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement? {
        return context?.serialize(src?.get())
    }

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): ObservableLong? {
        return ObservableLong(json.asLong)
    }
}

class ObservableBooleanAdapter : JsonSerializer<ObservableBoolean>, JsonDeserializer<ObservableBoolean> {

    override fun serialize(src: ObservableBoolean?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement? {
        return context?.serialize(src?.get())
    }

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): ObservableBoolean? {
        return ObservableBoolean(json.asBoolean)
    }
}

class ObservableDoubleAdapter : JsonSerializer<ObservableDouble>, JsonDeserializer<ObservableDouble> {

    override fun serialize(src: ObservableDouble?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement? {
        return context?.serialize(src?.get())
    }

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): ObservableDouble? {
        return ObservableDouble(json.asDouble)
    }
}

class ObservableFloatAdapter : JsonSerializer<ObservableFloat>, JsonDeserializer<ObservableFloat> {

    override fun serialize(src: ObservableFloat?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement? {
        return context?.serialize(src?.get())
    }

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): ObservableFloat? {
        return ObservableFloat(json.asFloat)
    }
}

class ObservableIntAdapter : JsonSerializer<ObservableInt>, JsonDeserializer<ObservableInt> {

    override fun serialize(src: ObservableInt?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement? {
        return context?.serialize(src?.get())
    }

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): ObservableInt? {
        return ObservableInt(json.asInt)
    }
}

class ListParameterizedType constructor(private val type: Type) : ParameterizedType {

    override fun getActualTypeArguments(): Array<Type> {
        return arrayOf(type)
    }

    override fun getRawType(): Type {
        return ArrayList::class.java
    }

    override fun getOwnerType(): Type? {
        return null
    }
}
