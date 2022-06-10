import { AnimeFlvNetworkDataSource } from "./animeFlvNetworkDataSoucre";

class AnimeDataSource extends AnimeFlvNetworkDataSource {

    async getLatestAnimeResponse(): Promise<string | undefined> {
        const page = await this.init();
        const response = await page.goto(this.BASE_URL);
        return response?.text();
    }

}

export const animeDataSource = new AnimeDataSource();