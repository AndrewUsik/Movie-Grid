package no.multimedianordic.roomr.platform.list

interface ItemModel<in T> {
    fun setData(data: T)
}