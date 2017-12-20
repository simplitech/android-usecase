package br.com.martinlabs.usecase.service

import android.util.Log
import br.com.martinlabs.usecase.BuildConfig
import br.com.martinlabs.usecase.R
import br.com.martinlabs.usecase.context.BaseAct
import br.com.martinlabs.usecase.context.LoginAct
import br.com.martinlabs.usecase.model.*
import br.com.martinlabs.usecase.response.*
import com.google.gson.GsonBuilder
import com.simpli.model.PagedResp
import com.simpli.model.RespException
import io.paperdb.Paper
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import com.google.gson.Gson



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
                    .addHeader("Accept-Language", BaseAct.instance?.getString(R.string.language))
                    .addHeader("X-Client-Version", BaseAct.instance?.getString(R.string.version))
                    .addHeader("Authorization", "Bearer " + Paper.book().read("token"))
                    .build()

            var resp: Response

            try {

                resp = chain.proceed(request)

            } catch (e: Exception) {
                val errorTxt = BaseAct.instance!!.getString(R.string.unexpected_error)

                BaseAct.instance?.errorToast(errorTxt)
                var re = RespException(errorTxt)
                re.initCause(e)
                throw re
            }

            if (!resp.isSuccessful) {
                val respStr = resp.body()?.string()

                if (respStr.isNullOrEmpty()) {
                    val errorTxt = BaseAct.instance!!.getString(R.string.unexpected_error)

                    BaseAct.instance?.errorToast(errorTxt)
                    throw RespException(errorTxt)
                } else {
                    val respJson = JSONObject(respStr)
                    val errorTxt = respJson.getString("message")

                    BaseAct.instance?.errorToast(errorTxt)

                    if (respJson.has("code") && respJson.getInt("code") == 33) {
                        Paper.book().delete("token")

                        BaseAct.instance?.startActivityClearTask(LoginAct::class)
                    }

                    throw RespException(errorTxt)
                }
            }

            resp
        }

        val resources: AppResources = Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .client(OkHttpClient.Builder().addInterceptor(interceptor).build())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setDateFormat(DATE_FORMAT).create()))
                .build()
                .create(AppResources::class.java)
    }
}

interface AppResources {

    @POST("Crud/Login")
    fun login(@Body body: LoginHolder): Call<LoginResp>

    @GET("Crud/Conectado/{id}")
    fun getConectado(@Path("id") id: Long): Call<ConectadoResp>

    @GET("Crud/Conectado")
    fun listConectado(
            @Query("query")
            query: String?,
            @Query("page")
            page: Int?,
            @Query("limit")
            limit: Int?,
            @Query("orderBy")
            orderRequest: String?,
            @Query("ascending")
            asc: Boolean?): Call<PagedResp<Conectado>>

    @POST("Crud/Conectado")
    fun persistConectado(@Body conectado: Conectado): Call<Long>

    @GET("Crud/ConectorPrincipal/{idPrincipalFk}/{idConectadoFk}")
    fun getConectorPrincipal(
            @Path("idPrincipalFk")
            idPrincipalFk: Long,
            @Path("idConectadoFk")
            idConectadoFk: Long): Call<ConectorPrincipalResp>

    @GET("Crud/ConectorPrincipal")
    fun listConectorPrincipal(
            @Query("query")
            query: String?,
            @Query("page")
            page: Int?,
            @Query("limit")
            limit: Int?,
            @Query("orderBy")
            orderRequest: String?,
            @Query("ascending")
            asc: Boolean?): Call<PagedResp<ConectorPrincipal>>

    @POST("Crud/ConectorPrincipal")
    fun persistConectorPrincipal(@Body conectorPrincipal: ConectorPrincipal): Call<Long>

    @GET("Crud/Endereco/{id}")
    fun getEndereco(@Path("id") id: Long): Call<EnderecoResp>

    @GET("Crud/Endereco")
    fun listEndereco(
            @Query("query")
            query: String?,
            @Query("page")
            page: Int?,
            @Query("limit")
            limit: Int?,
            @Query("orderBy")
            orderRequest: String?,
            @Query("ascending")
            asc: Boolean?): Call<PagedResp<Endereco>>

    @POST("Crud/Endereco")
    fun persistEndereco(@Body endereco: Endereco): Call<Long>

    @GET("Crud/ExtensaoDoPrincipal/{id}")
    fun getExtensaoDoPrincipal(@Path("id") id: Long): Call<ExtensaoDoPrincipalResp>

    @GET("Crud/ExtensaoDoPrincipal")
    fun listExtensaoDoPrincipal(
            @Query("query")
            query: String?,
            @Query("page")
            page: Int?,
            @Query("limit")
            limit: Int?,
            @Query("orderBy")
            orderRequest: String?,
            @Query("ascending")
            asc: Boolean?): Call<PagedResp<ExtensaoDoPrincipal>>

    @POST("Crud/ExtensaoDoPrincipal")
    fun persistExtensaoDoPrincipal(@Body extensaoDoPrincipal: ExtensaoDoPrincipal): Call<Long>

    @GET("Crud/GrupoDoPrincipal/{id}")
    fun getGrupoDoPrincipal(@Path("id") id: Long): Call<GrupoDoPrincipalResp>

    @GET("Crud/GrupoDoPrincipal")
    fun listGrupoDoPrincipal(
            @Query("query")
            query: String?,
            @Query("page")
            page: Int?,
            @Query("limit")
            limit: Int?,
            @Query("orderBy")
            orderRequest: String?,
            @Query("ascending")
            asc: Boolean?): Call<PagedResp<GrupoDoPrincipal>>

    @POST("Crud/GrupoDoPrincipal")
    fun persistGrupoDoPrincipal(@Body grupoDoPrincipal: GrupoDoPrincipal): Call<Long>

    @GET("Crud/ItemDoPrincipal/{id}")
    fun getItemDoPrincipal(@Path("id") id: Long): Call<ItemDoPrincipalResp>

    @GET("Crud/ItemDoPrincipal")
    fun listItemDoPrincipal(
            @Query("query")
            query: String?,
            @Query("page")
            page: Int?,
            @Query("limit")
            limit: Int?,
            @Query("orderBy")
            orderRequest: String?,
            @Query("ascending")
            asc: Boolean?): Call<PagedResp<ItemDoPrincipal>>

    @POST("Crud/ItemDoPrincipal")
    fun persistItemDoPrincipal(@Body itemDoPrincipal: ItemDoPrincipal): Call<Long>

    @GET("Crud/Principal/{id}")
    fun getPrincipal(@Path("id") id: Long): Call<PrincipalResp>

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

    @GET("Crud/Tag/{id}")
    fun getTag(@Path("id") id: Long): Call<TagResp>

    @GET("Crud/Tag")
    fun listTag(
            @Query("query")
            query: String?,
            @Query("page")
            page: Int?,
            @Query("limit")
            limit: Int?,
            @Query("orderBy")
            orderRequest: String?,
            @Query("ascending")
            asc: Boolean?): Call<PagedResp<Tag>>

    @POST("Crud/Tag")
    fun persistTag(@Body tag: Tag): Call<Long>

    @GET("Crud/User/{id}")
    fun getUser(@Path("id") id: Long): Call<UserResp>

    @GET("Crud/User")
    fun listUser(
            @Query("query")
            query: String?,
            @Query("page")
            page: Int?,
            @Query("limit")
            limit: Int?,
            @Query("orderBy")
            orderRequest: String?,
            @Query("ascending")
            asc: Boolean?): Call<PagedResp<User>>

    @POST("Crud/User")
    fun persistUser(@Body user: User): Call<Long>

}

