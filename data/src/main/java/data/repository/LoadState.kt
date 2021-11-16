package data.repository

//TODO: move to ui module
enum class LoadState {
    SUCCESS,
    LOADING,
    INTERNET_ERROR,
    UNKNOWN_ERROR,
    EMPTY_ITEMS_LIST_ERROR,
    EMPTY_ITEMS_DETAILS_LIST_ERROR,
    INCONSISTENCY_ITEM_ID_ERROR,
    IDLE
}