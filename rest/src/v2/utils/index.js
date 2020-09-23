const {
    BASE_ANIMEFLV, BASE_JIKAN, BASE_EPISODE_IMG_URL, SEARCH_URL, SEARCH_DIRECTORY, BASE_ARUPPI, BASE_JKANIME, JKANIME_URL
} = require('../api/urls.js');

const {
    homgot
} = require('../api/apiCall.js');

function btoa(str) {
    let buffer;
    if (str instanceof Buffer) {
        buffer = str;
    }
    else {
        buffer = Buffer.from(str.toString(), 'binary');
    }
    return buffer.toString('base64');
}

global.btoa = btoa;

async function videoServersJK(id) {

    let options = { scrapy: true }
    const $ =  await homgot(`${BASE_JKANIME}${id}`, options);

    const scripts = $('script');
    const episodes = $('div#reproductor-box li');
    const serverNames = [];
    let servers = [];

    episodes.each((index , element) =>{
        const $element = $(element);
        const serverName = $element.find('a').text();
        serverNames.push(serverName);
    })

    for(let i = 0; i < scripts.length; i++){
        const $script = $(scripts[i]);
        const contents = $script.html();
        try{
            if ((contents || '').includes('var video = [];')) {
                Array.from({length: episodes.length} , (v , k) =>{
                    let index = Number(k + 1);
                    let videoPageURL = contents.split(`video[${index}] = \'<iframe class="player_conte" src="`)[1].split('"')[0];
                    servers.push({iframe: videoPageURL});
                });
            }
        }catch(err) {
            return null;
        }
    }

    let serverList = [];
    for(let server in servers) {
        serverList.push({
            id: serverNames[server].toLowerCase(),
            url: await getVideoURL(servers[server].iframe),
            direct: true
        });
    }

    serverList = serverList.filter(x => x.id !== 'xtreme s' && x.id !== 'desuka' );

    return await Promise.all(serverList);
}

async function getVideoURL(url) {

    let options = { scrapy: true }
    const $ =  await homgot(url, options);

    const video = $('video');
    if(video.length){
        const src = $(video).find('source').attr('src');
        return src || null;
    }
    else{

        const scripts = $('script');
        const l = global;
        const ll = String;
        const $script2 = $(scripts[1]).html();
        eval($script2);
        return l.ss || null;
    }
}

const jkanimeInfo = async (id) => {

    let poster = ""
    let banner = ""
    let synopsis = ""
    let rating = ""
    let debut = ""
    let type = ""
    let $

    try {

        let options = { scrapy: true }
        $ = await homgot(`${BASE_JKANIME}${id}`, options);

        const animeExtraInfo = [];
        const genres = [];
        let listByEps;

        poster = $('div[id="container"] div.serie-info div.cap-portada')[0].children[1].attribs.src;
        banner = $('div[id="container"] div.serie-info div.cap-portada')[0].children[1].attribs.src;
        synopsis = $('div[id="container"] div.serie-info div.sinopsis-box p')[0].children[1].data;
        rating = "Sin calificación"
        debut = $('div[id="container"] div.serie-info div.info-content div')[6].children[3].children[0].children[0].data;
        type = $('div[id="container"] div.serie-info div.info-content div')[0].children[3].children[0].data

        animeExtraInfo.push({
            poster: poster,
            banner: banner,
            synopsis: synopsis,
            rating: rating,
            debut: debut,
            type: type,
        })

        let rawGenres = $('div[id="container"] div.serie-info div.info-content div')[1].children[3].children
        for (let i = 0; i <= rawGenres.length -1; i++) {
            if (rawGenres[i].name === 'a') {
                const genre = rawGenres[i].children[0].data
                genres.push(genre)
            }
        }

        let nextEpisodeDate
        let rawNextEpisode = $('div[id="container"] div.left-container div[id="proxep"] p')[0]
        if (rawNextEpisode === undefined) {
            nextEpisodeDate = null
        } else {
            if (rawNextEpisode.children[1].data === '  ') {
                nextEpisodeDate = null
            } else {
                nextEpisodeDate = rawNextEpisode
            }
        }

        const eps_temp_list = [];
        let episodes_aired = '';
        $('div#container div.left-container div.navigation a').each(async(index , element) => {
            const $element = $(element);
            const total_eps = $element.text();
            eps_temp_list.push(total_eps);
        })
        try{episodes_aired = eps_temp_list[0].split('-')[1].trim();}catch(err){}

        const animeListEps = [{nextEpisodeDate: nextEpisodeDate}];
        for (let i = 1; i <= episodes_aired; i++) {
                let episode = i;
                let animeId = $('div[id="container"] div.content-box div[id="episodes-content"]')[0].children[1].children[3].attribs.src.split('/')[7].split('.jpg')[0];
                let imagePreview = $('div[id="container"] div.content-box div[id="episodes-content"]')[0].children[1].children[3].attribs.src
                let link = `${animeId}/${episode}`

                animeListEps.push({
                    episode: episode,
                    id: link,
                    imagePreview: imagePreview
                })
        }

        listByEps = animeListEps;

        return {listByEps, genres, animeExtraInfo};

    } catch (err) {
        console.error(err)
    }

};

const animeflvGenres = async (id) => {

    const promises = [];

    let options = { scrapy: true }
    let $ = await homgot(`${BASE_ANIMEFLV}${id}`, options);

    $('main.Main section.WdgtCn nav.Nvgnrs a').each((index, element) => {
        const $element = $(element);
        const genre = $element.attr('href').split('=')[1] || null;
        promises.push(genre);
    });

    return promises;

}

const animeflvInfo = async (id, index) => {

    let poster = ""
    let banner = ""
    let synopsis = ""
    let rating = ""
    let debut = ""
    let type = ""
    let $

    try {

        let options = { scrapy: true }
        $ = await homgot(`${BASE_ANIMEFLV}anime/${id}`, options);

        const scripts = $('script');
        const anime_info_ids = [];
        const anime_eps_data = [];
        const animeExtraInfo = [];
        const genres = [];
        let listByEps;

        poster = `${BASE_ANIMEFLV}` + $('body div div div div div aside div.AnimeCover div.Image figure img').attr('src')
        banner = poster.replace('covers', 'banners').trim();
        synopsis = $('body div div div div div main section div.Description p').text().trim();
        rating = $('body div div div.Ficha.fchlt div.Container div.vtshr div.Votes span#votes_prmd').text();
        debut = $('body div.Wrapper div.Body div div.Container div.BX.Row.BFluid.Sp20 aside.SidebarA.BFixed p.AnmStts').text();
        type = $('body div.Wrapper div.Body div div.Ficha.fchlt div.Container span.Type').text()

        animeExtraInfo.push({
            poster: poster,
            banner: banner,
            synopsis: synopsis,
            rating: rating,
            debut: debut,
            type: type,
        })

        $('main.Main section.WdgtCn nav.Nvgnrs a').each((index, element) => {
            const $element = $(element);
            const genre = $element.attr('href').split('=')[1] || null;
            genres.push(genre);
        });


        Array.from({length: scripts.length}, (v, k) => {
            const $script = $(scripts[k]);
            const contents = $script.html();
            if ((contents || '').includes('var anime_info = [')) {
                let anime_info = contents.split('var anime_info = ')[1].split(';\n')[0];
                let dat_anime_info = JSON.parse(anime_info);
                anime_info_ids.push(dat_anime_info);
            }
            if ((contents || '').includes('var episodes = [')) {
                let episodes = contents.split('var episodes = ')[1].split(';')[0];
                let eps_data = JSON.parse(episodes)
                anime_eps_data.push(eps_data);
            }
        });
        const AnimeThumbnailsId = index;
        const animeId = id;
        let nextEpisodeDate

        if (anime_info_ids.length > 0) {
            if (anime_info_ids[0].length === 4) {
                nextEpisodeDate = anime_info_ids[0][3]
            } else {
                nextEpisodeDate = null
            }
        }

        const amimeTempList = [];
        for (const [key, value] of Object.entries(anime_eps_data)) {
            let episode = anime_eps_data[key].map(x => x[0]);
            let episodeId = anime_eps_data[key].map(x => x[1]);
            amimeTempList.push(episode, episodeId);
        }
        const animeListEps = [{nextEpisodeDate: nextEpisodeDate}];
        Array.from({length: amimeTempList[1].length}, (v, k) => {
            let data = amimeTempList.map(x => x[k]);
            let episode = data[0];
            let id = data[1];
            let imagePreview = `${BASE_EPISODE_IMG_URL}${AnimeThumbnailsId}/${episode}/th_3.jpg`
            let link = `${id}/${animeId}-${episode}`

            animeListEps.push({
                episode: episode,
                id: link,
                imagePreview: imagePreview
            })
        })

        listByEps = animeListEps;

        return {listByEps, genres, animeExtraInfo};

    } catch (err) {
        console.error(err)
    }

};

const getAnimeCharacters = async (title) => {

    try {
        let options = { parse: true }

        const res = await homgot(`${BASE_JIKAN}search/anime?q=${title}`, options);
        const matchAnime = res.results.filter(x => x.title === title);
        const malId = matchAnime[0].mal_id;

        if (typeof matchAnime[0].mal_id === 'undefined') return null;

        const data = await homgot(`${BASE_JIKAN}anime/${malId}/characters_staff`, options);
        let body = data.characters;

        if (typeof body === 'undefined') return null;

        const charactersId = body.map(doc => {
            return doc.mal_id
        })
        const charactersNames = body.map(doc => {
            return doc.name;
        });
        const charactersImages = body.map(doc => {
            return doc.image_url
        });

        let characters = [];
        Array.from({length: charactersNames.length}, (v, k) => {
            const id = charactersId[k];
            let name = charactersNames[k];
            let characterImg = charactersImages[k];
            characters.push({
                id: id,
                name: name,
                image: characterImg
            });
        });

        return Promise.all(characters);
    } catch (e) {
        console.log(e.message)
    }

};

const getAnimeVideoPromo = async (title) => {

    try {
        let options = { parse: true }
        const res = await homgot(`${BASE_JIKAN}search/anime?q=${title}`, options);
        const matchAnime = res.results.filter(x => x.title === title);
        const malId = matchAnime[0].mal_id;

        if (typeof matchAnime[0].mal_id === 'undefined') return null;

        const data = await homgot(`${BASE_JIKAN}anime/${malId}/videos`, options);
        const body = data.promo;
        const promises = [];

        body.map(doc => {
            promises.push({
                title: doc.title,
                previewImage: doc.image_url,
                videoURL: doc.video_url
            });
        });

        return Promise.all(promises);
    } catch (e) {
        console.log(e.message)
    }

};

const animeExtraInfo = async (title) => {

    try {

        let options = { parse: true }
        const res = await homgot(`${BASE_JIKAN}search/anime?q=${title}`, options);
        const matchAnime = res.results.filter(x => x.title === title);
        const malId = matchAnime[0].mal_id;

        if (typeof matchAnime[0].mal_id === 'undefined') return null;

        const data = await homgot(`${BASE_JIKAN}anime/${malId}`, options);
        const body = Array(data);
        const promises = [];

        body.map(doc => {

            let airDay = {
                'mondays': 'Lunes',
                'monday': 'Lunes',
                'tuesdays': 'Martes',
                'tuesday': 'Martes',
                'wednesdays': 'Miércoles',
                'wednesday': 'Miércoles',
                'thursdays': 'Jueves',
                'thursday': 'Jueves',
                'fridays': 'Viernes',
                'friday': 'Viernes',
                'saturdays': 'Sábados',
                'saturday': 'Sábados',
                'sundays': 'Domingos',
                'sunday': 'Domingos',
                'default': 'Sin emisión'
            };

            let broadcast
            if (doc.broadcast === null) {
                broadcast = null
            } else {
                broadcast = airDay[doc.broadcast.split('at')[0].replace(" ", "").toLowerCase()]
            }

            promises.push({
                titleJapanese: doc.title_japanese,
                source: doc.source,
                totalEpisodes: doc.episodes,
                aired: {
                    from: doc.aired.from,
                    to: doc.aired.to
                },
                duration: doc.duration.split('per')[0],
                rank: doc.rank,
                broadcast: broadcast,
                producers: doc.producers.map(x => x.name) || null,
                licensors: doc.licensors.map(x => x.name) || null,
                studios: doc.studios.map(x => x.name) || null,
                openingThemes: doc.opening_themes || null,
                endingThemes: doc.ending_themes || null
            });
        });
        return Promise.all(promises);

    } catch (e) {
        console.log(e.message)
    }


};

const imageUrlToBase64 = async (url) => {
    let img = await homgot(url)
    return img.rawBody.toString('base64');
};

const helper = async () => {}

const searchAnime = async (query) => {

    let $
    let promises = []

    const jkAnimeTitles = [
        { title: 'BNA', search: 'BNA'},
        { title: 'The God of High School', search: 'The god' },
        { title: 'Ansatsu Kyoshitsu', search: 'Assassination Classroom' },
    ];

    let jkanime = false
    let jkanimeName
    for (let name in jkAnimeTitles) {
        if (query === jkAnimeTitles[name].title) {
            jkanime = true
            jkanimeName = jkAnimeTitles[name].search
        }
    }

    if (jkanime === false) {
        let options = { scrapy: true }
        $ = await homgot(`${SEARCH_URL}${query}`, options);
        $('div.Container ul.ListAnimes li article').each((index, element) => {
            const $element = $(element);
            const id = $element.find('div.Description a.Button').attr('href').slice(1);
            const title = $element.find('a h3').text();
            let poster = $element.find('a div.Image figure img').attr('src') || $element.find('a div.Image figure img').attr('data-cfsrc');
            const type = $element.find('div.Description p span.Type').text();

            promises.push(helper().then(async () => ({
                id: id || null,
                title: title || null,
                type: type || null,
                image: await imageUrlToBase64(poster) || null
            })));

        })
    } else {

        let options = { scrapy: true }
        $ = await homgot(`${JKANIME_URL}${jkanimeName}`, options);

        $('.portada-box').each(function (index, element) {
            const $element = $(element);
            const title = $element.find('h2.portada-title a').attr('title');
            const id = $element.find('a.let-link').attr('href').split('/')[3];
            const poster = $element.find('a').children('img').attr('src');
            promises.push(helper().then(async () => ({
                id: id || null,
                title: title || null,
                type: 'Anime',
                image: await imageUrlToBase64(poster) || null
            })))
        });
    }

    return Promise.all(promises);

};

const transformUrlServer = async (urlReal) => {

    let res
    const promises = []

    for (const index in urlReal) {
        if (urlReal[index].server === 'amus' || urlReal[index].server === 'natsuki') {

            let options = { parse: true }
            res = await homgot(urlReal[index].code.replace("embed", "check"), options);

            urlReal[index].code = res.file || null
            urlReal[index].direct = true
        } else if (urlReal[index].server === 'gocdn' ) {
            urlReal[index].code = `https://s1.streamium.xyz/gocdn.php?v=${urlReal[index].code.split('/player_gocdn.html#')[1]}`
            urlReal[index].direct = true
        }
    }

    urlReal.map(doc => {
        promises.push({
            id: doc.title.toLowerCase(),
            url: doc.code,
            direct: doc.direct || false
        });
    });

    return promises;
}

const obtainPreviewNews = (encoded) => {

    let image;

    if (encoded.includes('src="https://img1.ak.crunchyroll.com/')) {
        image = `https://img1.ak.crunchyroll.com/${encoded.split('https://img1.ak.crunchyroll.com/')[1].split('.jpg')[0]}.jpg`
    } else if (encoded.includes('<img title=')) {
        image = encoded.substring(encoded.indexOf("<img title=\""), encoded.indexOf("\" alt")).split('src=\"')[1]
    } else if (encoded.includes('<img src=')) {
        image = encoded
            .substring(encoded.indexOf("<img src=\""), encoded.indexOf("\" alt"))
            .substring(10).replace("http", "https")
            .replace("httpss", "https")
    } else if (encoded.includes('<img')) {
        image = encoded.split("src=")[1].split(" class=")[0].replace("\"", '').replace('\"', '')
    } else if (encoded.includes('https://www.youtube.com/embed/')) {
        let getSecondThumb = encoded.split('https://www.youtube.com/embed/')[1].split('?feature')[0]
        image = `https://img.youtube.com/vi/${getSecondThumb}/0.jpg`
    } else if (encoded.includes('https://www.dailymotion.com/')) {
        let getDailymotionThumb = encoded
            .substring(encoded.indexOf("\" src=\""), encoded.indexOf("\" a"))
            .substring(47)
        image = `https://www.dailymotion.com/thumbnail/video/${getDailymotionThumb}`
    } else {
        let number = Math.floor(Math.random() * 30);
        image = `${BASE_ARUPPI}news/${number}.png`
    }

    return image;
}

const structureThemes = async (body, indv) => {

    const promises = []
    let themes

    if (indv === true) {
        themes = await getThemesData(body.themes)

        promises.push({
            title: body.title,
            year: body.year,
            themes: themes,
        });

    } else {

        for (let i = 0; i <= body.length - 1; i++) {

            themes = await getThemesData(body[i].themes)

            promises.push({
                title: body[i].title,
                year: body[i].year,
                themes: themes,
            });

        }

    }

    return promises;

};


const getThemesData = async (themes) => {

    let promises = []

    for (let i = 0; i <= themes.length - 1; i++) {

        promises.push({
            title: themes[i].name.split('"')[1] || 'Remasterización',
            type: themes[i].name.split('"')[0] || 'OP/ED',
            episodes: themes[i].episodes || null,
            video: themes[i].link
        });

    }

    return promises;

};

const getThemes = async (themes) => {

    let promises = []

    themes.map(doc => {

        promises.push({
            name: doc.themeName,
            type: doc.themeType,
            video: doc.mirror.mirrorURL
        });

    });

    return promises;

};

const getAnimes = async () => {
    let options = { parse: true }
    return await homgot(`${BASE_ANIMEFLV}api/animes/list`, options);
};


const getDirectory = async () => {

    let $
    let promises = []

    let options = { scrapy: true }
    $ = await homgot(`${SEARCH_URL}`, options);
    const lastPage = $('body div.Wrapper div.Container main div.NvCnAnm ul li a')[11].children[0].data

    for (let i = 1; i <= lastPage; i++) {

        let options = { scrapy: true }
        $ = await homgot(`${SEARCH_DIRECTORY}${i}`, options);

        $('div.Container ul.ListAnimes li article').each((index, element) => {
            const $element = $(element);
            const id = $element.find('div.Description a.Button').attr('href').slice(1);
            const title = $element.find('a h3').text();
            let poster = $element.find('a div.Image figure img').attr('src') || $element.find('a div.Image figure img').attr('data-cfsrc');
            const type = $element.find('div.Description p span.Type').text();

            promises.push(helper().then(async () => ({
                id: id || null,
                title: title || null,
                type: type || null,
                image: await imageUrlToBase64(poster) || null
            })));

        })

    }

    return Promise.all(promises);
};

module.exports = {
    jkanimeInfo,
    animeflvGenres,
    animeflvInfo,
    getAnimeCharacters,
    getAnimeVideoPromo,
    animeExtraInfo,
    imageUrlToBase64,
    searchAnime,
    transformUrlServer,
    obtainPreviewNews,
    structureThemes,
    getThemes,
    getAnimes,
    getDirectory,
    helper,
    videoServersJK
}
