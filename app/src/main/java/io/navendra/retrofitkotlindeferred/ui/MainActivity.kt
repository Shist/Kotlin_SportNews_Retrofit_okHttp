package io.navendra.retrofitkotlindeferred.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.navendra.retrofitkotlindeferred.R
import io.navendra.retrofitkotlindeferred.model.NewsItem

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, NewsListFragment())
            .commit()
    }

    fun onItemClick(news: NewsItem) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, NewsPageFragment(news))
            .addToBackStack("Вернуться")
            .commit()
    }

}

/*
TODO
### Смотри его код из видосика ###
1) В конструктор адаптера нужно передать какую-то функцию, которая будет вызываться по клику
1.1) Внутри onBindViewHolder взять ItemView (один из List<NewsItem>), на неё повесить onClickListener
1.2) Внутри onClickListener вызвать функцию, которую потом передать в SportAdapter (туда прокинуть NewsItem)
1.3) В Apadter будет проброшен правильный callback, и мы можем уже вызвать функцию, которая у Activity (у фрагмента взять Activity, скастить её к нужному Activity и вызвать метод)
### Смотри его код из видосика ###
##################################################
2) Внутри Activity нужно делать не ".add", а ".replace" и добавлять его в BackStack(), чтобы можно было по кнопке back вернуться назад на наш layout (Туда нужно передать новый фрагмент - SportPageFragment)
3) Вызывать адаптер не каждый раз при загрузке данных. Можно создать его выше (сразу в начале onViewCreated), там же поставить "его recycle view". Когда будет приходить observer (данные), можно сделатьк какой-нибудь метод SetItem(), который принимает новый список, а потом просто вызвать у адаптера "нотифай дата сэт чэйнджед", типо что данные поменялись (тогда recycle view перерисует их)
4) Мы используем просто Adapter, а можно ещё посмотреть "ListAdapter", его фишка в том, что он уже содержит в себе список, в нем есть submitItems, в нём есть "DifUtils" (смотрит старый и новый списки, находит разницу, сравнивает, если ничего не поменялось - он ничего не делает). Есть ListAdapter для ListView (старый, не смотрим его), а нам нужен второй - более новый - ListAdapter для recycle view.
5) Нам нужен отдельный котлин-класс SportPageFragment, туда засунуть переменную pageText (данные для разных активити отдельно пишутся соответственно в разных классах, каждому классу своя xml-ка)
6) Для xml-ки новой activity можно использовать ScrollView (для прокрутки), а не текущий CardView, который сейчас стоит (я его просто делал как в первом activity, но тут лучше другой)
6.1) В рутовой view-хе нужно поставить match_parent (мы можем использовать всё место)
6.2) Картинка сверху (на всю ширину), текст снизу... Headline либо на самой картинке, либо сверху от неё (как он нам показывал скрин с телефона)
6.3) Внутри SportAdapter.kt внутри класса MyViewHolder, "а функцию fun bind(...) вообще можете удалить, всю, она вам не нада здесь"
 */