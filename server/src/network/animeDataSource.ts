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
        const content = await page?.content();
        await page.close();
        return content

    }

    async getSearchResults(query: string) {
        const page = await this.init();
        const response = await page.goto(`${this.BASE_URL}/browse?q=${query}`);
        const content = await response?.text();
        await page.close();
        return content
    }

}

export const animeDataSource = new AnimeDataSource();