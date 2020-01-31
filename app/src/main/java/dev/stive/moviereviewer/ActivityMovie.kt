package dev.stive.moviereviewer

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dev.stive.moviereviewer.MainActivity.Companion.MOVIE_DATA

class ActivityMovie : AppCompatActivity() {

    private val descriptionWhoAmIMovie =
        "Бенджамин — молодой компьютерный гений. С детства он мечтает стать супергероем из комиксов и покорить мир. Но в реальном мире он — никто. Его жизнь неожиданно меняется, когда он встречает свою полную противоположность — харизматичного Макса. Не желая жить в жёстких рамках системы, они совершают череду дерзких кибер-преступлений. Восстав против равнодушного общества, они становятся кумирами для целого поколения. Теперь Бенджамин — не просто супергерой, он — самый разыскиваемый хакер в мире. Но что его ждёт после погружения в альтернативную реальность? Кто же он на самом деле"
    private val descriptionAccountentMovie =
        "Лента расскажет историю математического гения Кристиана Вульфа, который подрабатывает аудитором для самых опасных преступных организаций. Когда ему «на хвост» садится отдел по борьбе с преступностью Министерства финансов во главе с Рэем Кингом Кристиан решает найти себе законопослушного клиента и проводит аудит в компании по производству новейшей робототехники, где сотрудница финансового отдела обнаружила «нестыковочку» в миллионы долларов. Но стоит Кристиану взяться за счета и выйти на след, как в деле начинают появляться жертвы."
    private val descriptionIronManMovie =
        "Роберт Дауни-младший и Гвинет Пэлтроу превращаются в культовых героев знаменитых комиксов от Marvel. Тони Старк – баловень судьбы, наследник миллиардного состояния, филантроп и плейбой. У него отлично получается прожигать жизнь, он знаток лучших вечеринок, изысканных ресторанов, и в роли его спутниц всегда выступают только самые красивые девушки. Однако есть и другая грань личности героя: он руководитель огромной компании и талантливый изобретатель. Однажды в Афганистане, во время презентации одного из своих «товаров», ракеты Иерихон, Тони попадает в плен к террористам. Он тяжело ранен, но с помощью своего дара инженера конструирует невероятные доспехи, которые спасают ему жизнь и помогают выбраться из укрытия преступников. После всех своих приключений Старк намерен свернуть производство оружия в своей компании, но новоиспеченного пацифиста поддерживают далеко не все. "

    private lateinit var imageMovie: ImageView
    private lateinit var txtMovieTitle: TextView
    private lateinit var txtMovieDescription: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movie)

        imageMovie = findViewById(R.id.imgMovie)
        txtMovieTitle = findViewById(R.id.txtTitleMovie)
        txtMovieDescription = findViewById(R.id.txtMovieDescription)

        val value = getIntent().getParcelableExtra<PassData>(MOVIE_DATA)
        val movieName = value?.movieName

        Toast.makeText(this, "Movie title:$movieName", Toast.LENGTH_LONG).show()

        txtMovieTitle.text = value?.movieName
        imageMovie.setImageResource(value.imgResId)

        when (movieName) {
            "Who am I" -> txtMovieDescription.text = descriptionWhoAmIMovie
            "Iron Man" -> txtMovieDescription.text = descriptionIronManMovie
            "Accountent" -> txtMovieDescription.text = descriptionAccountentMovie
            else -> txtMovieDescription.text = "Description is missing"
        }

//        imageMovie.setImageBitmap(value?.imgPoster)

//        val intent = Intent()
//        intent.putExtra(DATA_FROM_EXPLICIT_INTENT,"Movie ${txtMovie.text} was shown!")
//        setResult(Activity.RESULT_OK, intent)
    }
}
