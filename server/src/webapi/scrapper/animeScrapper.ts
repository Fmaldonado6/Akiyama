import { Anime, Episode } from "../../core/domain/models";
import { WebScrapper } from "./webScrapper";
import axios from "axios";
import { StringUtils } from "../utils/utils";
export class AnimeScrapper extends WebScrapper {


    getAnimesByResponse(): Anime[] {
        const animeList = this.$(".ListAnimes").first();

        const animeElements = this.$(animeList).find("li");
        const animes: Anime[] = [];

        for (let animeElement of animeElements) {
            const animeData = this.$(animeElement)
            const anime = new Anime();

            anime.title = animeData.find(".Title").first().text();
            const imageUrl = animeData.find("img").attr("src") ?? null;
            anime.image = imageUrl ?? "";
            anime.id = animeData.find("a").first().attr("href")?.split("/").pop() ?? "";
            anime.rating = animeData.find(".fa-star").text();
            anime.synopsis = StringUtils.removeLineBreaks(animeData.find(".Description").find("p").last().text());
            anime.type = animeData.find(".Type").first().text();
            animes.push(anime)

        }

        return animes;
    }

    getAnimeInfo(): Anime {
        const anime = new Anime();
        const genresContainer = this.$(".Nvgnrs").first().find("a");

        for (let genreAnchor of genresContainer) {
            const genre = this.$(genreAnchor).text()
            anime.genres.push(genre);
        }

        const imageUrl = this.$(".AnimeCover").find("img").first().attr("src") ?? null;


        anime.title = this.$("h1.Title").first().text();
        anime.rating = this.$(".vtprmd").first().text();
        anime.image = imageUrl != null ? this.IMAGE_BASE_URL + imageUrl : "";
        anime.status = this.$(".AnmStts").first().text();
        anime.synopsis = StringUtils.removeLineBreaks(this.$(".Description").first().text());
        anime.type = this.$(".Type").text() ?? ""

        const episodesContainer = this.$(".ListCaps").first().find("li");

        const nextEpisodeDate = episodesContainer.first().find(".Date").text()

        anime.nextEpisodeDate = nextEpisodeDate != "" ? nextEpisodeDate : undefined

        for (let episodeContainer of episodesContainer) {
            const episodeData = this.$(episodeContainer);
            const episode = new Episode();
            const id = episodeData.find("a").first().attr("href")?.split("/").pop() ?? ""

            if (id == "#") continue;

            episode.id = id;
            episode.title = episodeData.find("a").first().find("p").text().trim();
            episode.image = episodeData.find("img").first().attr("data-src") ?? "";
            episode.episode = Number.parseInt(episodeData.find("input").first().attr("data-number") ?? "0")
            anime.episodes.push(episode)
        }

        return anime

    }

}

