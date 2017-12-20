package br.com.martinlabs.usecase.viewtools

import android.databinding.BaseObservable
import android.databinding.Observable
import android.databinding.ObservableList
import android.util.Pair

import java.util.ArrayList

/**
 * Created by gil on 30/11/17.
 */

open class Watchable : BaseObservable() {

//    private transient List<Pair<BaseObservable, OnPropertyChangedCallback>> watchPairs = new ArrayList<>();

    // WATCH BASE OBSERVABLE
    fun watch(observable: BaseObservable, cb: (sender: Observable, propertyId: Int) -> Unit) {
        val watcher = PropertyWatch(cb)

        observable.addOnPropertyChangedCallback(watcher)
//        watchPairs.add(new Pair(observable, watcher));
    }

    // COMPUTE BASE OBSERVABLE
    fun compute(observable: BaseObservable, observerHolder: BaseObservable, observer: Int) {
        watch(observable, {
            sender, propertyId ->
            observerHolder.notifyPropertyChanged(observer)
        })
    }

    // COMPUTE BASE OBSERVABLE
    fun compute(observable: BaseObservable, observer: Int) {
        compute(observable, this, observer)
    }

    // WATCH INT BINDABLE
    fun watch(observable: Int, cb: (sender: Observable, propertyId: Int) -> Unit) {
        watch(this, {
            sender, propertyId ->
            if (propertyId == observable) {
                cb(sender, propertyId)
            }
        })
    }

    // COMPUTE INT BINDABLE
    fun compute(observable: Int, observerHolder: BaseObservable, observer: Int) {
        watch(observable, {
            sender, propertyId ->
            observerHolder.notifyPropertyChanged(observer)
        })
    }

    // COMPUTE INT BINDABLE
    fun compute(observable: Int, observer: Int) {
        compute(observable, this, observer)
    }

    // WATCH OBSERVABLE LIST
    fun <T> watch(observable: ObservableList<T>, cb: (sender: ObservableList<T>) -> Unit) {
        val watcher: ObservableList.OnListChangedCallback<ObservableList<T>> = ListWatch(cb)

        observable.addOnListChangedCallback(watcher)
//        watchPairs.add(new Pair(observable, watcher));
    }

    // COMPUTE OBSERVABLE LIST
    fun compute(observable: ObservableList<*>, observerHolder: BaseObservable, observer: Int) {
        watch(observable, {
            sender ->
            observerHolder.notifyPropertyChanged(observer)
        })
    }

    // COMPUTE OBSERVABLE LIST
    fun compute(observable: ObservableList<*>, observer: Int) {
        compute(observable, this, observer)
    }

    // WATCH OBSERVABLE ITEMS IN LIST
    fun <T : Watchable> watch(list: ObservableList<T>, observable: Int, cb: (sender: Observable, propertyId: Int) -> Unit) {
        val watcher = PropertyWatch(cb)

        for (item in list) {
            item.watch(observable, cb)
        }

        list.addOnListChangedCallback(ListWatchInserted({
            sender, positionStart, itemCount ->
            for (i in positionStart until positionStart + itemCount) {
                val item = sender[i]
                item.watch(observable, cb)
            }
        }))
//        watchPairs.add(new Pair(observable, watcher));
    }

    // COMPUTE OBSERVABLE ITEMS IN LIST
    fun <T : Watchable> compute(list: ObservableList<T>, observable: Int, observerHolder: BaseObservable, observer: Int) {
        watch(list, observable, {
            sender, propertyId ->
            observerHolder.notifyPropertyChanged(observer)
        })
    }

    // COMPUTE OBSERVABLE ITEMS IN LIST
    fun <T : Watchable> compute(list: ObservableList<T>, observable: Int, observer: Int) {
        compute(list, observable, this, observer)
    }

//    @Override
//    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
//        super.removeOnPropertyChangedCallback(callback);
//
//        for (Pair<BaseObservable, OnPropertyChangedCallback> pair : watchPairs) {
//            pair.first.removeOnPropertyChangedCallback(pair.second);
//        }
//    }

    class PropertyWatch(private val cb: (sender: Observable, propertyId: Int) -> Unit) : Observable.OnPropertyChangedCallback() {

        override fun onPropertyChanged(sender: Observable, propertyId: Int) {
            cb(sender, propertyId)
        }
    }

    class ListWatch<T>(cb: (sender: ObservableList<T>) -> Unit) : ObservableList.OnListChangedCallback<ObservableList<T>>() {

        private val cb: (sender: ObservableList<T>) -> Unit

        init {
            this.cb = cb
        }

        override fun onChanged(sender: ObservableList<T>) {
            cb(sender)
        }

        override fun onItemRangeChanged(sender: ObservableList<T>, positionStart: Int, itemCount: Int) {
            cb(sender)
        }

        override fun onItemRangeInserted(sender: ObservableList<T>, positionStart: Int, itemCount: Int) {
            cb(sender)
        }

        override fun onItemRangeMoved(sender: ObservableList<T>, fromPosition: Int, toPosition: Int, itemCount: Int) {
            cb(sender)
        }

        override fun onItemRangeRemoved(sender: ObservableList<T>, positionStart: Int, itemCount: Int) {
            cb(sender)
        }
    }

    class ListWatchInserted<T>(cb: (sender: ObservableList<T>, positionStart: Int, itemCount: Int) -> Unit) : ObservableList.OnListChangedCallback<ObservableList<T>>() {

        private val cb: (sender: ObservableList<T>, positionStart: Int, itemCount: Int) -> Unit

        init {
            this.cb = cb
        }

        override fun onChanged(sender: ObservableList<T>) {}

        override fun onItemRangeChanged(sender: ObservableList<T>, positionStart: Int, itemCount: Int) {}

        override fun onItemRangeInserted(sender: ObservableList<T>, positionStart: Int, itemCount: Int) {
            cb(sender, positionStart, itemCount)
        }

        override fun onItemRangeMoved(sender: ObservableList<T>, fromPosition: Int, toPosition: Int, itemCount: Int) {}

        override fun onItemRangeRemoved(sender: ObservableList<T>, positionStart: Int, itemCount: Int) {}
    }
}

