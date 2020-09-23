const hooman = require('hooman');
const { CookieJar } = require('tough-cookie');
const cookieJar = new CookieJar();
const cheerio = require('cheerio');

let response
let data

const homgot = async (url, options) => {

    response = await hooman.get(url, cookieJar);

    if (options !== undefined) {
        if (options.scrapy) {
            data = await cheerio.load(response.body)
        }
        if (options.parse) {
            data = JSON.parse(response.body)
        }
    } else {
        data = response
    }

    return data

}

module.exports = {
    homgot
}
