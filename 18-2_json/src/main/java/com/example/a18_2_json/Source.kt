package com.example.a18_2_json

import android.accounts.AuthenticatorDescription

data class Source(var id:String, var name:String)

data class ItemModel(
    var source:Source,
    var author:String,
    var title: String,
    var description: String,
    var urlToImage:String,
    val publishedAt: String,
)

data class PageListModel(var articles: MutableList<ItemModel>)
