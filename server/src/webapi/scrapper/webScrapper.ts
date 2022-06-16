import cheerio from 'cheerio'

export class WebScrapper {

    private _$?: cheerio.Root
    protected IMAGE_BASE_URL = "https://www3.animeflv.net"

    protected get $() {

        if (!this._$)
            throw new Error("HTML not loaded, please load an HTML with loadResponse()")

        return this._$;

    }

    constructor(html?: string) {
        if (html)
            this.loadResponse(html)
    }

    loadResponse(html: string) {
        this._$ = cheerio.load(html)
    }

}