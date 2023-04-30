package com.example.animaxx.data.data_source

import com.example.animaxx.R
import com.example.animaxx.domain.model.Anime

val attackOnTitan = Anime(
    name = "Attack On Titan",
    stars = 4.5,
    genre = "Action, Adventure",
    actors = "Josh Grelle, Yuki Kazi",
    image = R.drawable.attack_on_titan,
    description = "Several hundred years ago, humans were nearly exterminated by giants. Giants are typically several stories tall, seem to have no intelligence, devour human beings and, worst of all, seem to do it for the pleasure rather than as a food source. A small percentage of humanity survived by enclosing themselves in a city protected by extremely high walls, even taller than the biggest of giants. Flash forward to the present and the city has not seen a giant in over 100 years. Teenage boy Eren and his foster sister Mikasa witness something horrific as the city walls are destroyed by a super giant that appears out of thin air. As the smaller giants flood the city, the two kids watch in horror as their mother is eaten alive. Eren vows that he will murder every single giant and take revenge for all of mankind."
)

val juijitsuKaisen = Anime(
    name = "Juijitse Kaisen",
    stars = 4.2,
    genre = "Shonen manga",
    actors = "Junya Enoki, Yuji Itadori, Yûichi Nakamura",
    image = R.drawable.juijitsu_kaisen,
    description = "Idly indulging in baseless paranormal activities with the Occult Club, high schooler Yuuji Itadori spends his days at either the clubroom or the hospital, where he visits his bedridden grandfather. However, this leisurely lifestyle soon takes a turn for the strange when he unknowingly encounters a cursed item. Triggering a chain of supernatural occurrences, Yuuji finds himself suddenly thrust into the world of Curses—dreadful beings formed from human malice and negativity—after swallowing the said item, revealed to be a finger belonging to the demon Sukuna Ryoumen, the \"King of Curses.\""
)

val naruto = Anime(
    name = "Naruto",
    stars = 4.7,
    genre = "Action, Comedy, Drama",
    actors = "Jamie Simone, Mary Elizabeth McGlynn, Jeff Nimoy",
    image = R.drawable.naruto,
    description = " Naruto closely follows the life of a boy who is feared and detested by the villagers of the hidden leaf village of Konoha. The distrust of the boy has little to do with the boy himself, but it’s what’s inside him that causes anxiety. Long before Naruto came to be, a Kyuubi (demon fox) with great fury and power waged war taking many lives. The battle ensued for a long time until a man known as the Fourth Hokage, Yondaime, the strongest ninja in Konoha, fiercely fought the Kyuubi. The fight was soon won by Yondaime as he sealed the evil demon in a human body. Thus the boy, Naruto, was born. As Naruto grows he decides to become the strongest ninja in Konoha in an effort to show everyone that he is not as they perceive him to be, but is a human being worthy of love and admiration. But the road to becoming Hokage, the title for the strongest ninja in Konoha, is a long and arduous one. It is a path filled with betrayal, pain, and loss; but with hard work, Naruto may achieve Hokage."
)

val spyFamily = Anime(
    name = "Spy Family",
    stars = 3.9,
    genre = "Action, Comedy, Drama",
    actors = "Loid Forger, Anya Forger",
    image = R.drawable.spy_family,
    description = "Agent Twilight, the greatest spy for the nation of Westalis, has to infiltrate an elite private school. In order to do so he assumes the identity of psychiatrist Loid Forger, adopts an orphan girl, and marries a city hall employee. Unknown to him, his daughter Anya is a telepath and his wife Yor is an assassin. The three learn to become a family while working to complete Twilight's missions and maintain world peace."


)

val demonSlayer = Anime(
    name = "Demon Slayer",
    stars = 4.0,
    genre = "Action, Horror, Supernatural",
    actors = "Takahiro Sakurai, Natsuki Hanae, Akari Kitō",
    image = R.drawable.demon_slayer,
    description = "Since ancient times, rumors have abounded of man-eating demons lurking in the woods. Because of this, the local townsfolk never venture outside at night. Legend has it that a demon slayer also roams the night, hunting down these bloodthirsty demons. For young Tanjiro, these rumors will soon to become his harsh reality. Ever since the death of his father, Tanjiro has taken it upon himself to support his family. Although their lives may be hardened by tragedy, they've found happiness. But that ephemeral warmth is shattered one day when Tanjiro finds his family slaughtered and the lone survivor, his sister Nezuko, turned into a demon. To his surprise, however, Nezuko still shows signs of human emotion and thought. Thus begins Tanjiro's request to fight demons and turn his sister human again."
)

val recentlySearchedList = listOf(juijitsuKaisen, attackOnTitan, naruto)
val topRatedList = listOf(naruto, demonSlayer, attackOnTitan)
val exploreList = listOf(attackOnTitan, demonSlayer, spyFamily)
val list = listOf(attackOnTitan, juijitsuKaisen, naruto, spyFamily, demonSlayer)
val starredList = listOf(attackOnTitan)

fun searchAnimeByKeyword(animeList: List<Anime>, keyword: String): List<Anime> {
    return animeList.filter {
        listOf(it.name).any { it2 ->
            it2.contains(
                keyword,
                ignoreCase = true
            )
        }
    }

}