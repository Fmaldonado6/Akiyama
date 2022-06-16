import { AnimeFlvNetworkDataSource } from "./animeFlvNetworkDataSoucre";

class EpisodeDataSource extends AnimeFlvNetworkDataSource {

    async getLatestEpisodes(): Promise<string | undefined> {
        const ctx = await this.init();
        const response = await ctx.page.goto(`${this.BASE_URL}`);
        const content = await response?.text();
        await ctx.context.close();

        return content
    }

    async getEpisodeServers(episodeId: string): Promise<string | undefined> {
        const ctx = await this.init();
        await ctx.page.goto(`${this.BASE_URL}/ver/${episodeId}`);
        await ctx.page.waitForSelector("li[title]")

        const content = await ctx.page?.content();
        await ctx.context.close();
        return content
    }

}

export const episodeDataSource = new EpisodeDataSource();
