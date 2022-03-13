
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.retrofitandroid.JokesDao
import com.example.retrofitandroid.MyJokesData

class JokesDataRepo (private val jokesDao: JokesDao){

    fun getJokes() : LiveData<List<MyJokesData>>{
        Log.d("jokesDataRepo","Showing this")
        return jokesDao.getJoke()
    }
    suspend fun insertJokes(myJokesData: MyJokesData){
        Log.d("jokesDataRepo","Showing this insertJokes")
        jokesDao.insertJoke(myJokesData)
    }



}