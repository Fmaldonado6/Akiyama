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
        await page.evaluate(() => {
            return new Promise((res, rej) => {
                const episodeList = document.getElementById("episodeList")
                if (episodeList == null) return
                setInterval(() => {

                    let last = episodeList.children[episodeList.children.length - 1]
                    let lastLabel = last.children[1].attributes.getNamedItem("for")?.value
                    if (lastLabel == "epi1" || lastLabel == null) return res(true)
                    episodeList.scrollTop = episodeList.scrollHeight

                }, 200)

            })


        })
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

    async delay(ms: number) {
        return new Promise((res, rej) => {
            setTimeout(res, ms)
        })
    }

}

export const animeDataSource = new AnimeDataSource();