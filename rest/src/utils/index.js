const {
    BASE_ANIMEFLV, BASE_JIKAN, BASE_ARUPPI, BASE_JKANIME
} = require('../api/urls');

const {
    homgot
} = require('../api/apiCall');

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

    const $ =  await homgot(`${BASE_JKANIME}${id}`, { scrapy: true });

    const scripts = $('script');
    const episodes = $('div#reproductor-box li');
    const serverNames = [];
    let servers = [];

    episodes.each((index , element) => serverNames.push($(element).find('a').text()))

    for(let i = 0; i < scripts.length; i++){
        const contents = $(scripts[i]).html();
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

    const $ = await homgot(url, { scrapy: true });

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

    let $ = await homgot(`${BASE_JKANIME}${id}`, { scrapy: true });

    let nextEpisodeDate
    let rawNextEpisode = $('div[id="container"] div.left-container div[id="proxep"] p')[0]
    if (rawNextEpisode === undefined) {
        nextEpisodeDate = null
    } else {
        if (rawNextEpisode.children[1].data === '  ') {
            nextEpisodeDate = null
        } else {
            nextEpisodeDate = rawNextEpisode.children[1].data.trim()
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
        let link = `${animeId}/${episode}`

        animeListEps.push({
            episode: episode,
            id: link
        })
    }

    return animeListEps;

};

const animeflvGenres = async (id) => {

    let $ = await homgot(`${BASE_ANIMEFLV}${id}`, { scrapy: true });
    $('main.Main section.WdgtCn nav.Nvgnrs a').each((index, element) => {
        return $(element).attr('href').split('=')[1] || null;
    });

}

const animeflvInfo = async (id) => {

    let $ = await homgot(`${BASE_ANIMEFLV}anime/${id}`, { scrapy: true });
    let scripts = $('script').toArray();

    const anime_info_ids = [];
    const anime_eps_data = [];

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
        let link = `${id}/${animeId}-${episode}`

        animeListEps.push({
            episode: episode,
            id: link,
        })
    })

    return animeListEps

};

const getAnimeCharacters = async (title) => {

    try {

        const res = await homgot(`${BASE_JIKAN}search/anime?q=${title}`, { parse: true });
        const matchAnime = res.results.filter(x => x.title === title);
        const malId = matchAnime[0].mal_id;

        if (typeof malId === 'undefined') return null;

        const data = await homgot(`${BASE_JIKAN}anime/${malId}/characters_staff`, { parse: true });
        let body = data.characters;

        if (typeof body === 'undefined') return null;

        const charactersId = body.map(doc => doc.mal_id)
        const charactersNames = body.map(doc => doc.name);
        const charactersImages = body.map(doc => doc.image_url);

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

        const res = await homgot(`${BASE_JIKAN}search/anime?q=${title}`, { parse: true });
        const matchAnime = res.results.filter(x => x.title === title);
        const malId = matchAnime[0].mal_id;

        if (typeof malId === 'undefined') return null;

        const data = await homgot(`${BASE_JIKAN}anime/${malId}/videos`, { parse: true });
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

        const res = await homgot(`${BASE_JIKAN}search/anime?q=${title}`, { parse: true });
        const matchAnime = res.results.filter(x => x.title === title);
        const malId = matchAnime[0].mal_id;

        if (typeof malId === 'undefined') return null;

        const data = await homgot(`${BASE_JIKAN}anime/${malId}`, { parse: true });
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

    let data = JSON.parse(JSON.stringify(require('../assets/directory.json')));
    const res = data.filter(x => x.title.includes(query));

    return res.map(doc => ({
        id: doc.id || null,
        title: doc.title || null,
        type: doc.type || null,
        image: doc.poster || null
    }));

};

const transformUrlServer = async (urlReal) => {

    for (const data of urlReal) {
        if (data.server === 'amus' || data.server === 'natsuki') {
            let res = await homgot(data.code.replace("embed", "check"), { parse: true });
            data.code = res.file || null
            data.direct = true
        } else if (data.server === 'gocdn' ) {
            if (data.code.split('/player_gocdn.html#')[1] === undefined) {
                data.code = `https://s1.streamium.xyz/gocdn.php?v=${data.code.split('/gocdn.html#')[1]}`
            } else {
                data.code = `https://s1.streamium.xyz/gocdn.php?v=${data.code.split('/player_gocdn.html#')[1]}`
            }
            data.direct = true
        }
    }

    return urlReal.map(doc =>({
        id: doc.title.toLowerCase(),
        url: doc.code,
        direct: doc.direct || false
    }));

}

const obtainPreviewNews = (encoded) => {

    let image;

    if (encoded.includes('src="https://img1.ak.crunchyroll.com/')) {
        if (encoded.split('https://img1.ak.crunchyroll.com/')[1].includes('.jpg')) {
            image = `https://img1.ak.crunchyroll.com/${encoded.split('https://img1.ak.crunchyroll.com/')[1].split('.jpg')[0]}.jpg`
        } else {
            image = `https://img1.ak.crunchyroll.com/${encoded.split('https://img1.ak.crunchyroll.com/')[1].split('.png')[0]}.png`
        }
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

    return promises.filter(x => x.title !== 'Remasterización');

};

const getThemes = async (themes) => {

    return themes.map(doc =>({
        name: doc.themeName,
        type: doc.themeType,
        video: doc.mirror.mirrorURL
    }));

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
    helper,
    videoServersJK
}
