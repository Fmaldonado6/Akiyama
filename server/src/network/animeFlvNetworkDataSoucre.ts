import axios from "axios";
import puppeteer, { Puppeteer } from "puppeteer";

class BroswerInstance {
    private browser?: puppeteer.Browser


    async getBrowser() {
        if (!this.browser)
            this.browser = await puppeteer.launch({
                args: [
                    '--no-sandbox',
                    '--disable-setuid-sandbox',
                    '--disable-dev-shm-usage',
                ], headless: true
            })

        return this.browser
    }

}

const browserInstance = new BroswerInstance()

export interface PageContext {
    page: puppeteer.Page
    context: puppeteer.BrowserContext
}

export class AnimeFlvNetworkDataSource {


    protected BASE_URL = "https://www3.animeflv.net";


    protected async init(): Promise<PageContext> {
        const browser = await browserInstance.getBrowser()
        const context = await browser.createIncognitoBrowserContext()
        const page = await context.newPage();
        await page.setRequestInterception(true);
        page.on('request', (req) => {
            if (req.resourceType() == 'stylesheet' || req.resourceType() == 'font' || req.resourceType() == 'image')
                req.abort();
            else
                req.continue();

        });
        page.setDefaultNavigationTimeout(30000);
        await page.setUserAgent('Mozilla/5.0 (Windows NT 5.1; rv:5.0) Gecko/20100101 Firefox/5.0')

        return { page, context };
    }


}
