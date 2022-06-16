import axios from "axios";
import puppeteer, { Puppeteer } from "puppeteer";
export class AnimeFlvNetworkDataSource {


    protected BASE_URL = "https://www3.animeflv.net";

    protected browser?: puppeteer.Browser

    protected async init(): Promise<puppeteer.Page> {
        if (!this.browser)
            this.browser = await puppeteer.launch({ args: ['--no-sandbox'], headless: true })
        const page = await this.browser.newPage();
        await page.setUserAgent('Mozilla/5.0 (Windows NT 5.1; rv:5.0) Gecko/20100101 Firefox/5.0')

        return page;
    }


}
