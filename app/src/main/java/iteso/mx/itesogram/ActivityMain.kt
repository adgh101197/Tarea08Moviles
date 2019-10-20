package iteso.mx.itesogram

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parse.*
import iteso.mx.itesogram.Adapters.AdapterPhoto
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync

class ActivityMain: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actvity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyler_view_users_rv)

        doAsync {
            val query = ParseQuery.getQuery<ParseObject>("Feed")
            query.findInBackground(object : FindCallback<ParseObject> {
                var users: List<ParseObject> = arrayListOf()

                override fun done(userList: List<ParseObject>, pe: ParseException?) {
                    if (pe == null) {
                        users = userList
                        activityUiThread {
                            recyclerView.adapter = AdapterPhoto(users)
                            recyclerView.adapter?.notifyDataSetChanged()
                            recyclerView.layoutManager = LinearLayoutManager(parent)
                        }

                    }
                }
            })
        }
    }
}