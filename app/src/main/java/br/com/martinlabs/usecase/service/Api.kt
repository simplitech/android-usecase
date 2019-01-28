package br.com.martinlabs.usecase.service

import br.com.martinlabs.usecase.BuildConfig
import br.com.martinlabs.usecase.R
import br.com.martinlabs.usecase.context.BaseAct
import br.com.martinlabs.usecase.model.*
import br.com.martinlabs.usecase.response.*
import br.com.simpli.model.PagedResp
import br.com.simpli.model.RespException
import io.paperdb.Paper
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


/**
 * Created by gil on 15/11/17.
 */

class Api {

    companion object {

        val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ" // set the DATE_FORMAT to whatever you need

        val interceptor = Interceptor {
            chain ->

            val request = chain
                    .request()
                    .newBuilder()
                    .addHeader("Accept-Language", BaseAct.i.getString(R.string.language))
                    .addHeader("X-Client-Version", BaseAct.i.getString(R.string.version_name))
                    .addHeader("Authorization", "Bearer " + Paper.book().read("token"))
                    .build()

            var resp: Response

            try {

                resp = chain.proceed(request)

            } catch (e: Exception) {
                e.printStackTrace()
                val errorTxt = BaseAct.i.getString(R.string.nao_foi_possivel_se_conectar_com_o_servidor)

                var re = RespException(errorTxt)
                re.initCause(e)
                throw re
            }

            if (!resp.isSuccessful) {
                val respStr = resp.body()?.string()

                if (respStr.isNullOrEmpty()) {
                    val errorTxt = BaseAct.i.getString(R.string.falha_ao_se_conectar_ao_servidor_verifique_se_o_app_esta_atualizado)

                    throw RespException(errorTxt)
                } else {
                    try {
                        val respJson = JSONObject(respStr)
                        val errorTxt = respJson.getString("message")

                        if (respJson.has("code") && (respJson.getInt("code") == 33 || respJson.getInt("code") == 77)) {
                            Paper.book().delete("token")
                        }

                        if(respJson.has("code")) {
                            throw RespException(respJson.getInt("code"),errorTxt)
                        }
                        else
                        {
                            throw RespException(errorTxt)
                        }
                    } catch (e: JSONException) {
                        throw RespException(BaseAct.i.getString(R.string.erro_tentar_conectar_ao_servidor_verifique_se_o_app_esta_atualizado))
                    }
                }
            }

            resp
        }

        val resources: AppResources = Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .client(OkHttpClient.Builder()
                        .addInterceptor(interceptor)
                        .readTimeout(60, TimeUnit.SECONDS)
                        .connectTimeout(60, TimeUnit.SECONDS)
                        .build())
                .addConverterFactory(GsonConverterFactory.create(buildGson()))
                .build()
                .create(AppResources::class.java)
    }
}

class DateParam : Date {

    constructor(date: Date) : super(date.time)


    override fun toString(): String {
        return SimpleDateFormat(Api.DATE_FORMAT).format(this)
    }
}

interface AppResources {

    @POST("Crud/SignIn")
    fun login(@Body body: LoginHolder): Call<LoginResp>

    @GET("Crud/Principal/{id}")
    fun getPrincipal(@Path("id") id: Long?): Call<PrincipalResp>

    @GET("Crud/Principal")
    fun listPrincipal(
            @Query("query")
            query: String?,
            @Query("page")
            page: Int?,
            @Query("limit")
            limit: Int?,
            @Query("orderBy")
            orderRequest: String?,
            @Query("ascending")
            asc: Boolean?): Call<PagedResp<Principal>>

    @POST("Crud/Principal")
    fun persistPrincipal(@Body principal: Principal): Call<Long>

    @DELETE("Crud/Principal/{id}")
    fun removePrincipal(@Path("id") id: Long): Call<Any?>

}

