import { AnimeFlvNetworkDataSource } from "./animeFlvNetworkDataSoucre";

class AnimeDataSource extends AnimeFlvNetworkDataSource {

    async getLatestAnimeResponse(type:string = "tv"): Promise<string | undefined> {
        const page = await this.init();
        const response = await page.goto(`${this.BASE_URL}/browse?type%5B%5D=${type}&order=default`);
        return response?.text();
    }
    async getAnimeInfo(animeId: string): Promise<string | undefined> {
        const page = await this.init();
        const response = await page.goto(this.BASE_URL + animeId);
        return response?.text();

    }

}

export const animeDataSource = new AnimeDataSource();