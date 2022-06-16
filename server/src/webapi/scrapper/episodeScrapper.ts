import { Episode, Server } from "../../core/domain/models";
import { WebScrapper } from "./webScrapper";

interface RawServer {
    server: string
    title: string
    ads: number
    url: string
    allow_mobile: boolean;
    code: string
}

export class EpisodeScrapper extends WebScrapper {

    getLatestEpisodesFromResponse(): Episode[] {
        const episodesContainer = this.$(".ListEpisodios").find("li");
        const episodes: Episode[] = []

        for (let episodeContainer of episodesContainer) {
            const episodeData = this.$(episodeContainer);
            const episode = new Episode();

            episode.title = episodeData.find(".Capi").first().text();
            episode.id = episodeData.find("a").first().attr("href")?.split("/").pop() ?? "";

            const imageUrl = episodeData.find("img").first().attr("src")
            episode.image = imageUrl != null ? this.IMAGE_BASE_URL + imageUrl : "s"

            episode.episode = Number.parseInt(episode.title.split(" ").pop() ?? "0")

            episodes.push(episode)

        }

        return episodes;
    }

    getEpisodeServersFromResponse(): Server[] | null {
        const servers: Server[] = []
        const scripts = this.$("script")

        let parsedJson: RawServer[] | null = null;

        for (let script of scripts) {

            const text = this.$(script).html() ?? "";

            const hasVar = text.includes("var videos");


            if (!hasVar) continue
            const regex = RegExp(/{(.*)}/gm)
            const jsonString = "{" + regex.exec(text)?.pop() + "}";
            parsedJson = JSON.parse(jsonString).SUB as RawServer[]

        }

        if (parsedJson == null) return null;

        for (let rawServer of parsedJson) {
            const server = new Server();

            server.title = rawServer.title;
            server.serverCode = rawServer.server
            server.allowMobile = rawServer.allow_mobile
            server.url = rawServer.code

            servers.push(server)
        }

        return servers
    }

}