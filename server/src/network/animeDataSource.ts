import { AnimeFlvNetworkDataSource } from "./animeFlvNetworkDataSoucre";

class AnimeDataSource extends AnimeFlvNetworkDataSource {

    async getAnimeResponse(type: string = "tv"): Promise<string | undefined> {
        const page = await this.init();
        const response = await page.goto(`${this.BASE_URL}/browse?type%5B%5D=${type}&order=default`);
        return response?.text();
    }

    async getAnimeInfo(animeId: string): Promise<string | undefined> {
        const page = await this.init();
        await page.goto(`${this.BASE_URL}/anime/${animeId}`);
        await page.waitForSelector(".lazy")
        return page?.content();

    }

    async getSearchResults(query: string) {
        const page = await this.init();
        const response = await page.goto(`${this.BASE_URL}/browse?q=${query}`);
        return response?.text();
    }

}

export const animeDataSource = new AnimeDataSource();