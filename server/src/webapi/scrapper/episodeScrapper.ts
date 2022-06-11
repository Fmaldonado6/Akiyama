import { Server } from "../../core/domain/models";
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