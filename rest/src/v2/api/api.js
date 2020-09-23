const rss = require('rss-to-json');

const {
  homgot
} = require('./apiCall');

const {
  jkanimeInfo,
  animeflvGenres,
  animeflvInfo,
  imageUrlToBase64,
  getAnimeCharacters,
  getAnimeVideoPromo,
  animeExtraInfo,
  searchAnime,
  transformUrlServer,
  obtainPreviewNews,
  structureThemes,
  getAnimes,
  getDirectory,
  helper,
  videoServersJK,
  getThemes
} = require('../utils');

const ThemeParser = require('../utils/animetheme');
const parserThemes = new ThemeParser()

const {
  BASE_ANIMEFLV_JELU, BASE_JIKAN, BASE_IVOOX, BASE_QWANT, BASE_YOUTUBE, GENRES_URL, BASE_THEMEMOE
} = require('./urls');

const schedule = async (day) =>{

  let options = { parse: true }
  const data = await homgot(`${BASE_JIKAN}schedule/${day.current}`, options);
  const body = data[day.current];
  const promises = []

  body.map(doc =>{

    promises.push({
      title: doc.title,
      malid: doc.mal_id,
      image: doc.image_url
    });

  });

  return promises;

};

const top = async (type, subtype, page) =>{
  let options = { parse: true }
  const data = await homgot(`${BASE_JIKAN}top/${type}/${page}/${subtype}`, options);
  return data.top;
};

const getAllAnimes = async () =>{

  let data = await getAnimes()

  return data.map(item => ({
    index: item[0],
    animeId: item[3],
    title: item[1],
    id: item[2],
    type: item[4]
  }));

};

const getAllDirectory = async () =>{ return await getDirectory(); };

const getAnitakume = async () =>{

  const promises = []

  await rss.load(BASE_IVOOX).then(rss => {

    const body = JSON.parse(JSON.stringify(rss, null, 3)).items
    body.map(doc =>{

      let time = new Date(doc.created)
      const monthNames = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"];

      let day = time.getDate()
      let month = monthNames[time.getMonth()]
      let year = time.getFullYear()
      let date

      if(month < 10){
        date = `${day} de 0${month} de ${year}`
      }else{
        date = `${day} de ${month} de ${year}`
      }

      promises.push({
        title: doc.title,
        duration: doc.itunes_duration,
        created: date,
        mp3: doc.enclosures.map(x => x.url)
      });
    });

  });

  return promises;

};

const getNews = async (pageRss) =>{

  const promises = []

  for(let i = 0; i <= pageRss.length -1; i++) {

    await rss.load(pageRss[i].url).then(rss => {

      const body = JSON.parse(JSON.stringify(rss, null, 3)).items
      body.map(doc => {

        promises.push({
          title: doc.title,
          url: doc.link,
          author: pageRss[i].author,
          thumbnail: obtainPreviewNews(doc[pageRss[i].content]),
          content: doc[pageRss[i].content]
        });

      });

    });

  }

  return promises;

};

const season = async (year, type) =>{

  let options = { parse: true }
  const data = await homgot(`${BASE_JIKAN}season/${year}/${type}`, options);
  let body = data.anime;
  const promises = []

  body.map(doc =>{

    promises.push({
      title: doc.title,
      malid: doc.mal_id,
      image: doc.image_url,
      genres: doc.genres.map(x => x.name)
    });
  });

  return promises;

};

const allSeasons = async () =>{

  let options = { parse: true }
  const data = await homgot(`${BASE_JIKAN}season/archive`, options);
  let body = data.archive;
  const promises = []

  body.map(doc =>{

    promises.push({
      year: doc.year,
      seasons: doc.seasons,
    });
  });

  return promises;

};

const laterSeasons = async () =>{

  let options = { parse: true }
  const data = await homgot(`${BASE_JIKAN}season/later`, options);
  let body = data.anime;
  const promises = []

  body.map(doc =>{

    promises.push({
      malid: doc.mal_id,
      title: doc.title,
      image: doc.image_url
    });
  });

  return promises;

};

const getLastEpisodes = async () =>{

  let options = { parse: true }
  const data = await homgot(`${BASE_ANIMEFLV_JELU}LatestEpisodesAdded`, options);
  let body = data.episodes;
  const promises = []

  body.map(doc => {

    promises.push(helper().then(async () => ({
       id: doc.id,
       title: doc.title,
       image: doc.poster,
       episode: doc.episode,
       servers: await transformUrlServer(JSON.parse(JSON.stringify(doc.servers)))
     })));

  });

  return Promise.all(promises);

};

const getSpecials = async (type, subType, page) =>{

  let options = { parse: true }
  const data = await homgot(`${BASE_ANIMEFLV_JELU}${type.url}/${subType}/${page}`, options);
  let body = data[type.prop];
  const promises = []

  body.map(doc =>{

    promises.push({
      id: doc.id,
      title: doc.title,
      type: doc.type,
      banner: doc.banner,
      image: doc.poster,
      synopsis: doc.synopsis,
      status: doc.debut,
      rate: doc.rating,
      genres: doc.genres.map(x => x),
      episodes: doc.episodes.map(x => x)
    });
  });

  return promises;

};

const getMoreInfo = async (title) =>{

  const promises = []
  let animeTitle = ''
  let animeId = ''
  let animeType = ''
  let animeIndex = ''

  let seriesTitle
  let position

  const jkAnimeTitles = [
    { title: 'The God of High School', id: 'the-god-of-high-school' },
    { title: 'Kami no Tou', id: 'kami-no-tou' },
    { title: 'BNA', id: 'bna' },
    { title: 'Ansatsu Kyoushitsu (TV)', id: 'ansatsu-kyoushitsu-tv' },
    { title: 'Ansatsu Kyoushitsu (TV) 2nd Season', id: 'ansatsu-kyoushitsu-tv-2nd-season' }
  ];

  const jkMyAnimetitles = [
    { jkanime: 'Ansatsu Kyoushitsu (TV)', myanimelist: 'Ansatsu Kyoushitsu'},
    { jkanime: 'Ansatsu Kyoushitsu (TV) 2nd Season', myanimelist: 'Ansatsu Kyoushitsu 2nd Season' }
  ];

  let jkanime = false
  let jkanimeID
  let jkanimeName
  for (let name in jkAnimeTitles) {
    if (title === jkAnimeTitles[name].title) {
      jkanime = true
      jkanimeID = jkAnimeTitles[name].id

      for (let name in jkMyAnimetitles) {
        if (title === jkMyAnimetitles[name].jkanime || title === jkMyAnimetitles[name].myanimelist) {
          jkanimeName = jkMyAnimetitles[name].myanimelist
          position = name
        }
      }

      if (jkanimeName === undefined) {
        jkanimeName = jkAnimeTitles[name].title
      }

    }
  }

  if (jkanime === false) {
    const titles = [
      { animeflv: 'Kaguya-sama wa Kokurasetai: Tensai-tachi no Renai Zunousen 2nd Season', myanimelist: 'Kaguya-sama wa Kokurasetai?: Tensai-tachi no Renai Zunousen', alternative: 'Kaguya-sama wa Kokurasetai'},
      { animeflv: 'Naruto Shippuden', myanimelist: 'Naruto: Shippuuden' },
      { animeflv: 'Rock Lee no Seishun Full-Power Ninden', myanimelist: 'Naruto SD: Rock Lee no Seishun Full-Power Ninden' },
      { animeflv: 'BAKI: dai reitaisai-hen', myanimelist: 'Baki 2nd Season' },
      { animeflv: 'Hitoribocchi no ○○ Seikatsu', myanimelist: 'Hitoribocchi no Marumaru Seikatsu' },
      { animeflv: 'Nekopara (TV)', myanimelist: 'Nekopara' },
      { animeflv: 'Black Clover (TV)', myanimelist: 'Black Clover' }
    ];

    for (let name in titles) {
      if (title === titles[name].animeflv || title === titles[name].myanimelist || title === titles[name].alternative) {
        seriesTitle = titles[name].animeflv
        position = name
      }
    }


    if (seriesTitle === undefined) {
      seriesTitle = title
    }

    await getAllAnimes().then(animes => {

      for (const i in animes) {
        if (animes[i].title.split('\t')[0] === seriesTitle.split('\t')[0] ||
            animes[i].title === `${seriesTitle} (TV)` ||
            animes[i].title.includes(seriesTitle.split('○')[0])
        ) {
          if (animes[i].title.includes('(TV)', 0)) { animeTitle = animes[i].title.split('\t')[0].replace(' (TV)', '') }
          else { animeTitle = animes[i].title.split('\t')[0] }
          animeId = animes[i].id
          animeIndex = animes[i].index
          animeType = animes[i].type.toLowerCase()

          if (position !== undefined) {
            seriesTitle = titles[position].myanimelist
          }

          break;

        }
      }
    });

    try{

      if (animeType === 'tv') {
        promises.push(await animeflvInfo(animeId, animeIndex).then(async extra => ({
          title: animeTitle || null,
          poster: await imageUrlToBase64(extra.animeExtraInfo[0].poster) || null,
          synopsis: extra.animeExtraInfo[0].synopsis || null,
          status: extra.animeExtraInfo[0].debut || null,
          type: extra.animeExtraInfo[0].type || null,
          rating: extra.animeExtraInfo[0].rating || null,
          genres: extra.genres || null,
          episodes: extra.listByEps || null,
          moreInfo: await animeExtraInfo(seriesTitle).then(info =>{
            return info || null
          }),
          promo: await getAnimeVideoPromo(seriesTitle).then(promo =>{
            return promo || null
          }),
          characters: await getAnimeCharacters(seriesTitle).then(characters =>{
            return characters || null
          })
        })));
      } else {
        promises.push(await animeflvInfo(animeId).then(async extra => ({
          title: animeTitle || null,
          poster: await imageUrlToBase64(extra.animeExtraInfo[0].poster) || null,
          synopsis: extra.animeExtraInfo[0].synopsis || null,
          status: extra.animeExtraInfo[0].debut || null,
          type: extra.animeExtraInfo[0].type || null,
          rating: extra.animeExtraInfo[0].rating || null,
          genres: extra.genres || null,
          episodes: extra.listByEps || null,
        })));
      }

    }catch(err){
      console.log(err)
    }
  } else {
    promises.push(await jkanimeInfo(jkanimeID).then(async extra => ({
      title: jkanimeName || null,
      poster: await imageUrlToBase64(extra.animeExtraInfo[0].poster) || null,
      synopsis: extra.animeExtraInfo[0].synopsis || null,
      status: extra.animeExtraInfo[0].debut || null,
      type: extra.animeExtraInfo[0].type || null,
      rating: extra.animeExtraInfo[0].rating || null,
      genres: extra.genres || null,
      episodes: extra.listByEps || null,
      moreInfo: await animeExtraInfo(jkanimeName).then(info =>{
        return info || null
      }),
      promo: await getAnimeVideoPromo(jkanimeName).then(promo =>{
        return promo || null
      }),
      characters: await getAnimeCharacters(jkanimeName).then(characters =>{
        return characters || null
      })
    })));
  }

  return promises;

};

const getAnimeServers = async (id) => {

  const jkAnimeIDs = [
    { id: 'the-god-of-high-school' },
    { id: 'kami-no-tou' },
    { id: 'bna' },
    { id: 'ansatsu-kyoushitsu-tv' },
    { id: 'ansatsu-kyoushitsu-tv-2nd-season' }
  ];

  let jkanime = false
  let jkanimeID
  for (let name in jkAnimeIDs) {
    if (id.includes(jkAnimeIDs[name].id)) {
      jkanime = true
      jkanimeID = id
    }
  }

  if (jkanime === false) {

    let options = { parse: true }
    const data = await homgot(`${BASE_ANIMEFLV_JELU}GetAnimeServers/${id}`, options);
    let body = data.servers;

    return await transformUrlServer(body);

  } else {
    return await videoServersJK(jkanimeID)
  }

};

const search = async (title) =>{ return await searchAnime(title); };

const getImages = async (query) => {

  let options = { parse: true }
  const data = await homgot(`${BASE_QWANT}count=${query.count}&q=${query.title}&t=${query.type}&safesearch=${query.safesearch}&locale=${query.country}&uiv=4`, options);
  const body = data.data.result.items;
  const promises = []

  body.map(doc =>{

    promises.push({
      type: doc.thumb_type,
      thumbnail: `https:${doc.thumbnail}`,
      fullsize: `https:${doc.media_fullsize}`
    });

  });

  return promises;

};

const getYoutubeVideos = async (channelId) => {

  let options = { parse: true }
  const data = await homgot(`${BASE_YOUTUBE}${channelId.id}&part=${channelId.part}&order=${channelId.order}&maxResults=${channelId.maxResults}`, options);
  const body = data[channelId.prop];
  const promises = []

  body.map(doc =>{

    promises.push({
      title: doc.snippet.title,
      videoId: doc.id.videoId,
      thumbDefault: doc.snippet.thumbnails.default.url,
      thumbMedium: doc.snippet.thumbnails.medium.url,
      thumbHigh: doc.snippet.thumbnails.high.url
    });

  });

  return promises;

};

const getRadioStations = async () => {
  return require('../assets/radiostations.json');
}

const getOpAndEd = async (title) => {
  let data = await parserThemes.serie(title)
  return await structureThemes(data, true)
};

const getThemesYear = async (year) => {
  let data = []

  if (year === undefined) {
    return await parserThemes.allYears();
  } else {
    data = await parserThemes.year(year)
    return await structureThemes(data, false)
  }

};

const getRandomTheme = async () => {

  let promise = []
  let options = { parse: true }
  const data = await homgot(`${BASE_THEMEMOE}roulette`, options);
  let themes = await getThemes(data.themes)

  promise.push({
    name: data.name,
    title: themes[0].name,
    link: themes[0].video
  })

  return promise;
};

const getArtist = async (id) => {

  let data

  if (id === undefined) {
    return await parserThemes.artists();
  } else {
    data = await parserThemes.artist(id)
    return await structureThemes(data, false)
  }

};

const getAnimeGenres = async(genre, order, page) => {

  let $
  let promises = []
  let options = { scrapy: true }

  if (page !== undefined) {
    $ = await homgot(`${GENRES_URL}genre%5B%5D=${genre}&order=${order}&page=${page}`,options)
  } else {
    $ = await homgot(`${GENRES_URL}genre%5B%5D=${genre}&order=${order}`,options)
  }


  $('div.Container ul.ListAnimes li article').each((index , element) =>{
    const $element = $(element);
    const id = $element.find('div.Description a.Button').attr('href').slice(1);
    const title = $element.find('a h3').text();
    const poster = $element.find('a div.Image figure img').attr('src');
    const banner = poster.replace('covers' , 'banners').trim();
    const type = $element.find('div.Description p span.Type').text();
    const synopsis = $element.find('div.Description p').eq(1).text().trim();
    const rating = $element.find('div.Description p span.Vts').text();

    promises.push(animeflvGenres(id).then(async genres => ({
      id: id || null,
      title: title || null,
      poster: await imageUrlToBase64(poster) || null,
      banner: banner || null,
      synopsis: synopsis || null,
      type: type || null,
      rating: rating || null,
      genres: genres || null
    })))

  })

  return Promise.all(promises);

};

const getAllThemes = async () => {
  let data = await parserThemes.all()
  return await structureThemes(data, false)
};

module.exports = {
  schedule,
  top,
  getAllAnimes,
  getAllDirectory,
  getAnitakume,
  getNews,
  season,
  allSeasons,
  laterSeasons,
  getLastEpisodes,
  getSpecials,
  getMoreInfo,
  getAnimeServers,
  search,
  getImages,
  getYoutubeVideos,
  getRadioStations,
  getOpAndEd,
  getThemesYear,
  getRandomTheme,
  getArtist,
  getAnimeGenres,
  getAllThemes
};
