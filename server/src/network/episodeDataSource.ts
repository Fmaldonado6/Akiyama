import { AnimeFlvNetworkDataSource } from "./animeFlvNetworkDataSoucre";

class EpisodeDataSource extends AnimeFlvNetworkDataSource {

    async getLatestEpisodes(): Promise<string | undefined> {
        const page = await this.init();
        const response = await page.goto(`${this.BASE_URL}`);
        const content = await response?.text();
        await page.close();
        return content
    }

    async getEpisodeServers(episodeId: string): Promise<string | undefined> {
        const page = await this.init();
        await page.goto(`${this.BASE_URL}/ver/${episodeId}`);
        await page.waitForSelector("li[title]")

        const content = await page?.content();
        await page.close();
        return content
    }

}

export const episodeDataSource = new EpisodeDataSource();
