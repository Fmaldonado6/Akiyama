import { AnimeFlvNetworkDataSource } from "./animeFlvNetworkDataSoucre";

class EpisodeDataSource extends AnimeFlvNetworkDataSource {

    async getEpisodeServers(episodeId: string): Promise<string | undefined> {
        const page = await this.init();
        await page.goto(`${this.BASE_URL}/ver/${episodeId}`);
        await page.waitForSelector("li[title]")
        return page?.content();
    }

}

export const episodeDataSource = new EpisodeDataSource();
