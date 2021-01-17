package com.jongman22.basicstudy

class SampleData {
    private var items = ArrayList<DramaList>()
    fun getItems(): ArrayList<DramaList> {
        val mistersun = DramaList(
            "https://w.namu.la/s/fa59c01d74015f6174fec40bbd7d4dd8d5b6be825c941fd19986596087dcc850d14e9a03d7b63a2599ded8686b652723e52f1eb0162f776edaa36e5ddd93368a46387893235cda97301eb90bbb3ef6008f5f82cb214801eab6e3091f0bf92a79a718fa55befd343390b9228b7a6e5ddb",
            "미스터 션샤인", "작가 김은숙 / 연출 이응복", "tvn",
            "이병헌 멋있다."
        )
        items.add(mistersun)
        return items
    }
}