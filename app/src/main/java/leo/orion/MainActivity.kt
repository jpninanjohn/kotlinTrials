package leo.orion

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import leo.orion.adapters.ForecastListAdapter
import leo.orion.network.Request
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.longToast

class MainActivity : AppCompatActivity() {

    private val items = listOf("a", "b", "c")

    val url = "http://api.openweathermap.org/data/2.5/forecast/daily?" +
            "APPID=2afad0c08fba13710457596c4f75921f&q=94043&mode=json&units=metric&cnt=7"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val forecastList: RecyclerView = find(R.id.list)
        forecastList.layoutManager = LinearLayoutManager(this)
        forecastList.adapter = ForecastListAdapter(items)

        doAsync(){
            Request(url).run()
            runOnUiThread { longToast("Request Performed") }
        }
    }
}
