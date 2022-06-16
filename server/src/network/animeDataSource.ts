import { AnimeFlvNetworkDataSource } from "./animeFlvNetworkDataSoucre";

class AnimeDataSource extends AnimeFlvNetworkDataSource {

    async getAnimeResponse(type: string = "tv"): Promise<string | undefined> {
        const ctx = await this.init();
        const response = await ctx.page.goto(`${this.BASE_URL}/browse?type%5B%5D=${type}&order=default`);
        const text = await response?.text();
        await ctx.context.close()
        return text;
    }

    async getAnimeInfo(animeId: string): Promise<string | undefined> {
        const ctx = await this.init();
        await ctx.page.goto(`${this.BASE_URL}/anime/${animeId}`);
        await ctx.page.waitForSelector(".lazy")
        await ctx.page.evaluate(() => {
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
        await ctx.page.waitForSelector(".lazy")
        const content = await ctx.page?.content();
        await ctx.context.close();
        return content

    }

    async getSearchResults(query: string) {
        const ctx = await this.init();
        const page = ctx.page
        const response = await page.goto(`${this.BASE_URL}/browse?q=${query}`);
        const content = await response?.text();
        await ctx.context.close();
        return content
    }

    async delay(ms: number) {
        return new Promise((res, rej) => {
            setTimeout(res, ms)
        })
    }

}

export const animeDataSource = new AnimeDataSource();