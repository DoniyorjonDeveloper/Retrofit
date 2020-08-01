package intellect.dev.retrofit

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            val api = ApiClient.retrofit.create(UserApi::class.java)
            api.getContacts().enqueue(object : Callback<List<ContactData>> {
                override fun onFailure(call: Call<List<ContactData>>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<List<ContactData>>,
                    response: Response<List<ContactData>>
                ) {
                    val ls = response.body()
                    if (response.isSuccessful && ls != null)
                        Toast.makeText(this@MainActivity, "${ls.size}", Toast.LENGTH_LONG).show()
                }

            })
        }
    }
}