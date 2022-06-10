import { Anime } from "../../core/domain/models";
import { WebScrapper } from "./webScrapper";
import axios from "axios";
export class AnimeScrapper extends WebScrapper {

    private IMAGE_BASE_URL = "https://www3.animeflv.net"

    async getLatestAnimesByResponse(): Promise<Anime[]> {
        const animeList = this.$(".ListAnimes").first();

        const animeElements = this.$(animeList).find("li");
        const animes: Anime[] = [];

        for (let animeElement of animeElements) {
            const animeData = this.$(animeElement)
            const anime = new Anime();

            anime.title = animeData.find(".Title").text();
            const imageUrl = animeData.find("img").attr("src") ?? null;
            // const imageData = await axios.get(imageUrl ?? "", {
            //     responseType: 'arraybuffer'
            // })
            anime.poster = imageUrl ?? "";
            anime.banner = imageUrl ?? "";
            anime.id = animeData.find("a").first().attr("href") ?? "";
            anime.debut = "En Emisión"
            anime.rating = animeData.find(".fa-star").text();
            anime.synopsis = animeData.find(".Description").find("p").last().text();
            anime.type = animeData.find(".Type").first().text();
            animes.push(anime)

        }



        return animes;
    }

}

