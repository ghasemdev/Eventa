package com.jakode.eventa.ui.featured

import com.jakode.eventa.R
import com.jakode.eventa.model.Event
import com.jakode.eventa.ui.adapter.ViewType

data class EventsViewType(private val model: Event) : ViewType<Event> {
    override fun layoutId(): Int = R.layout.item_events
    override fun data(): Event = model
}