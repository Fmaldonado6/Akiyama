import axios from "axios";
import puppeteer from "puppeteer";
export class AnimeFlvNetworkDataSource {


    protected BASE_URL = "https://www3.animeflv.net/";



    protected async init(): Promise<puppeteer.Page> {
        const browser = await puppeteer.launch()
        const page = await browser.newPage();
        await page.setUserAgent('Mozilla/5.0 (Windows NT 5.1; rv:5.0) Gecko/20100101 Firefox/5.0')
        return page;
    }


}
