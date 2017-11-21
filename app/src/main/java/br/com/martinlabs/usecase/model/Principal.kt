package br.com.martinlabs.usecase.model

import android.databinding.BaseObservable
import android.databinding.Bindable
import br.com.martinlabs.usecase.BR
import java.io.Serializable
import java.util.*


class Principal : BaseObservable(), Serializable {

    @Bindable
    var grupoDoPrincipal: GrupoDoPrincipal? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.grupoDoPrincipal)
        }

    @Bindable
    var grupoDoPrincipalFacultativo: GrupoDoPrincipal? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.grupoDoPrincipalFacultativo)
        }

    @Bindable
    var idPrincipalPk: Long = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.idPrincipalPk)
        }

    @Bindable
    var textoObrigatorio: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.textoObrigatorio)
        }

    @Bindable
    var textoFacultativo: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.textoFacultativo)
        }

    @Bindable
    var decimalObrigatorio: Double = 0.0
        set(value) {
            field = value
            notifyPropertyChanged(BR.decimalObrigatorio)
        }

    @Bindable
    var decimalFacultativo: Double? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.decimalFacultativo)
        }

    @Bindable
    var inteiroObrigatorio: Long = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.inteiroObrigatorio)
        }

    @Bindable
    var inteiroFacultativo: Long? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.inteiroFacultativo)
        }

    @Bindable
    var booleanoObrigatorio: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.booleanoObrigatorio)
        }

    @Bindable
    var booleanoFacultativo: Boolean? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.booleanoFacultativo)
        }

    @Bindable
    var dataObrigatoria: Date? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.dataObrigatoria)
        }

    @Bindable
    var dataFacultativa: Date? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.dataFacultativa)
        }

    @Bindable
    var datahoraObrigatoria: Date? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.datahoraObrigatoria)
        }

    @Bindable
    var datahoraFacultativa: Date? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.datahoraFacultativa)
        }

    @Bindable
    var ativo: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.ativo)
        }

    @Bindable
    var email: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.email)
        }

    @Bindable
    var senha: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.senha)
        }

    @Bindable
    var urlImagem: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.urlImagem)
        }

    @Bindable
    var url: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.url)
        }

    @Bindable
    var unico: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.unico)
        }

    @Bindable
    var dataCriacao: Date? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.dataCriacao)
        }

    @Bindable
    var dataAlteracao: Date? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.dataAlteracao)
        }

    @Bindable
    var nome: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.nome)
        }

    @Bindable
    var titulo: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.titulo)
        }

    @Bindable
    var cpf: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.cpf)
        }

    @Bindable
    var cnpj: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.cnpj)
        }

    @Bindable
    var rg: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.rg)
        }

    @Bindable
    var celular: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.celular)
        }

    @Bindable
    var textoGrande: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.textoGrande)
        }

    @Bindable
    var tagPrincipal: MutableList<Tag>? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.tagPrincipal)
        }
}
