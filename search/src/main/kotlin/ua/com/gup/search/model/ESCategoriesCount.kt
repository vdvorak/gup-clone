package ua.com.gup.search.model

data class ESCategoriesCount constructor(var code: Long?, var count: Long?, var title: String?) {
    constructor(code: Long?, count: Long?) : this(code, count, null)
}