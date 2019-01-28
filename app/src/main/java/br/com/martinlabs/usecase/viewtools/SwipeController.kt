package br.com.martinlabs.usecase.viewtools

import android.graphics.Canvas
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.support.v7.widget.helper.ItemTouchHelper.Callback
import android.view.MotionEvent

import android.support.v7.widget.helper.ItemTouchHelper.*

internal enum class ButtonsState {
    GONE,
    LEFT_VISIBLE,
    RIGHT_VISIBLE
}

typealias Consumer<T> = (T) -> Unit

internal class SwipeController : Callback {

    private var swipeBack = false
    private var buttonShowedState = ButtonsState.GONE
    private var currentItemViewHolder: RecyclerView.ViewHolder? = null
    private val buttonWidthInPx: Float
    private val leftSwipe: Consumer<Int>?
    private val rightSwipe: Consumer<Int>?

    constructor(buttonWidthInDp: Float, leftSwipe: Consumer<Int>? = null, rightSwipe: Consumer<Int>? = null) : super() {
        this.buttonWidthInPx = Converter.fromDpToPixel(buttonWidthInDp)
        this.leftSwipe = leftSwipe
        this.rightSwipe = rightSwipe
    }


    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        return ItemTouchHelper.Callback.makeMovementFlags(0, LEFT or RIGHT)
    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

    }

    override fun convertToAbsoluteDirection(flags: Int, layoutDirection: Int): Int {
        if (swipeBack) {
            swipeBack = buttonShowedState != ButtonsState.GONE
            return 0
        }
        return super.convertToAbsoluteDirection(flags, layoutDirection)
    }

    override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        var mdX = dX
        if (actionState == ACTION_STATE_SWIPE) {
            if (buttonShowedState != ButtonsState.GONE) {
                if (buttonShowedState == ButtonsState.LEFT_VISIBLE) mdX = Math.max(mdX, buttonWidthInPx)
                if (buttonShowedState == ButtonsState.RIGHT_VISIBLE) mdX = Math.min(mdX, -buttonWidthInPx)
                super.onChildDraw(c, recyclerView, viewHolder, mdX, dY, actionState, isCurrentlyActive)
            } else {
                setTouchListener(c, recyclerView, viewHolder, mdX, dY, actionState, isCurrentlyActive)
            }
        }

        if (buttonShowedState == ButtonsState.GONE) {
            super.onChildDraw(c, recyclerView, viewHolder, mdX, dY, actionState, isCurrentlyActive)
        }
        currentItemViewHolder = viewHolder
    }

    private fun setTouchListener(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        recyclerView.setOnTouchListener { _, event ->
            swipeBack = event.action == MotionEvent.ACTION_CANCEL || event.action == MotionEvent.ACTION_UP
            if (swipeBack) {
                if (dX < -buttonWidthInPx) {
                    rightSwipe?.let { it(viewHolder.adapterPosition) }
                    buttonShowedState = ButtonsState.RIGHT_VISIBLE
                } else if (dX > buttonWidthInPx) {
                    leftSwipe?.let { it(viewHolder.adapterPosition) }
                    buttonShowedState = ButtonsState.LEFT_VISIBLE
                }

                if (buttonShowedState != ButtonsState.GONE) {
                    setTouchDownListener(c, recyclerView, viewHolder, dY, actionState, isCurrentlyActive)
                    setItemsClickable(recyclerView, false)
                }
            }
            false
        }
    }

    private fun setTouchDownListener(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        recyclerView.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                setTouchUpListener(c, recyclerView, viewHolder, dY, actionState, isCurrentlyActive)
            }
            false
        }
    }

    private fun setTouchUpListener(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        recyclerView.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                super@SwipeController.onChildDraw(c, recyclerView, viewHolder, 0f, dY, actionState, isCurrentlyActive)
                recyclerView.setOnTouchListener { _, _ -> false }
                setItemsClickable(recyclerView, true)
                swipeBack = false

                buttonShowedState = ButtonsState.GONE
                currentItemViewHolder = null
            }
            false
        }
    }

    private fun setItemsClickable(recyclerView: RecyclerView, isClickable: Boolean) {
        for (i in 0 until recyclerView.childCount) {
            recyclerView.getChildAt(i).isClickable = isClickable
        }
    }
}