package com.okugata.moviecatalogue.utils

import com.okugata.moviecatalogue.R
import com.okugata.moviecatalogue.data.Movie
import com.okugata.moviecatalogue.data.TvShow

object DataDummy {
    fun generateDummyMovies(): List<Movie> {
        val movies = ArrayList<Movie>()

        movies.add(
            Movie(
                "A Star Is Born",
                "October 3, 2018",
                R.drawable.poster_a_start_is_born,
                "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                "Drama, Romance, Music",
                "2h 16m"
            )
        )
        movies.add(
            Movie(
                "Alita: Battle Angel ",
                "January 31, 2019",
                R.drawable.poster_alita,
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "Action, Science Fiction, Adventure",
                "2h 2m"
            )
        )
        movies.add(
            Movie(
                "Aquaman",
                "December 7, 2018",
                R.drawable.poster_aquaman,
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "Action, Adventure, Fantasy",
                "2h 23m"
            )
        )
        movies.add(
            Movie(
                "Bohemian Rhapsody",
                "October 24, 2018",
                R.drawable.poster_bohemian,
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "Music, Drama, History",
                "2h 15m"
            )
        )
        movies.add(
            Movie(
                "Cold Pursuit",
                "February 7, 2019",
                R.drawable.poster_cold_pursuit,
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                "Action, Crime, Thriller",
                "1h 59m"
            )
        )
        movies.add(
            Movie(
                "Creed II",
                "November 21, 2018",
                R.drawable.poster_creed,
                "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                "Drama",
                "2h 10m"
            )
        )
        movies.add(
            Movie(
                "Fantastic Beasts: The Crimes of Grindelwald",
                "November 14, 2018",
                R.drawable.poster_crimes,
                "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
                "Adventure, Fantasy, Drama",
                "2h 14m"
            )
        )
        movies.add(
            Movie(
                "Glass",
                "January 16, 2019",
                R.drawable.poster_glass,
                "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                "Thriller, Drama, Science Fiction",
                "2h 9m"
            )
        )
        movies.add(
            Movie(
                "How to Train Your Dragon: The Hidden World",
                "January 3, 2019",
                R.drawable.poster_how_to_train,
                "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
                "Animation, Family, Adventure",
                "1h 44m"
            )
        )
        movies.add(
            Movie(
                "Avengers: Infinity War",
                "April 25, 2018",
                R.drawable.poster_infinity_war,
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                "Adventure, Action, Science Fiction",
                "2h 29m"
            )
        )
        return movies
    }

    fun generateDummyTvShows(): List<TvShow> {
        val tvShows = ArrayList<TvShow>()
        tvShows.add(
            TvShow(
                "Arrow",
                "October 10, 2012",
                R.drawable.poster_arrow,
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "Crime, Drama, Mystery, Action & Adventure",
                "42m",
                6
            )
        )

        return tvShows
    }
}