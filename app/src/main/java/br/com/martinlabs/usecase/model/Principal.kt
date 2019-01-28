package br.com.martinlabs.usecase.model

import android.databinding.*
import java.io.Serializable
import java.util.*


class Principal : Serializable {

    val grupoDoPrincipal = ObservableField<GrupoDoPrincipal>()
    val grupoDoPrincipalFacultativo = ObservableField<GrupoDoPrincipal>()
    val tagPrincipal = ObservableArrayList<Tag>()
    val idPrincipalPk = ObservableLong()
    val textoObrigatorio = ObservableField<String>()
    val textoFacultativo = ObservableField<String>()
    val decimalObrigatorio = ObservableDouble()
    val decimalFacultativo = ObservableDouble()
    val inteiroObrigatorio = ObservableLong()
    val inteiroFacultativo = ObservableLong()
    val booleanoObrigatorio = ObservableBoolean()
    val booleanoFacultativo = ObservableBoolean()
    val dataObrigatoria = ObservableField<Date>()
    val dataFacultativa = ObservableField<Date>()
    val datahoraObrigatoria = ObservableField<Date>()
    val datahoraFacultativa = ObservableField<Date>()
    val ativo = ObservableBoolean()
    val email = ObservableField<String>()
    val senha = ObservableField<String>()
    val urlImagem = ObservableField<String>()
    val url = ObservableField<String>()
    val unico = ObservableField<String>()
    val dataCriacao = ObservableField<Date>()
    val dataAlteracao = ObservableField<Date>()
    val nome = ObservableField<String>()
    val titulo = ObservableField<String>()
    val cpf = ObservableField<String>()
    val cnpj = ObservableField<String>()
    val rg = ObservableField<String>()
    val celular = ObservableField<String>()
    val textoGrande = ObservableField<String>()

    var idGrupoDoPrincipalFk: Long
        get() = grupoDoPrincipal.get()?.idGrupoDoPrincipalPk?.get() ?: 0
        set(idGrupoDoPrincipalFk) {
            if (grupoDoPrincipal.get() == null) {
                grupoDoPrincipal.set(GrupoDoPrincipal())
            }
            grupoDoPrincipal.get()?.idGrupoDoPrincipalPk?.set(idGrupoDoPrincipalFk)
        }

    var idGrupoDoPrincipalFacultativoFk: Long?
        get() = if (grupoDoPrincipalFacultativo.get() == null || grupoDoPrincipalFacultativo.get()?.idGrupoDoPrincipalPk?.get() == 0L) null else grupoDoPrincipalFacultativo.get()?.idGrupoDoPrincipalPk?.get()
        set(idGrupoDoPrincipalFacultativoFk) {
            if (idGrupoDoPrincipalFacultativoFk == null) {
                grupoDoPrincipalFacultativo.set(null)
                return
            }
            if (grupoDoPrincipalFacultativo.get() == null) {
                grupoDoPrincipalFacultativo.set(GrupoDoPrincipal())
            }
            grupoDoPrincipalFacultativo.get()?.idGrupoDoPrincipalPk?.set(idGrupoDoPrincipalFacultativoFk)
        }
}
