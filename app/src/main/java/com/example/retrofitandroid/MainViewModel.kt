import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitandroid.MyJokesData
import androidx.lifecycle.viewModelScope


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val jokesDataRepo: JokesDataRepo): ViewModel()
{
    fun getJokes() : LiveData<List<MyJokesData>>
    {

        return jokesDataRepo.getJokes()


    }
    fun insertJokes(myJokesData: MyJokesData)
    {
        viewModelScope.launch(Dispatchers.IO) {
            jokesDataRepo.insertJokes(myJokesData)
        }
    }

}