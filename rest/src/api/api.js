const rss = require('rss-to-json');

const {
  homgot
} = require('../api/apiCall');

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
  helper,
  videoServersJK,
  getThemes
} = require('../utils/index');

const ThemeParser = require('../utils/animetheme');
const parserThemes = new ThemeParser()

const {
  BASE_ANIMEFLV_JELU, BASE_JIKAN, BASE_IVOOX, BASE_QWANT, BASE_YOUTUBE, GENRES_URL, BASE_THEMEMOE, BASE_ANIMEFLV, BASE_ARUPPI
} = require('./urls');

const schedule = async (day) =>{

  const data = await homgot(`${BASE_JIKAN}schedule/${day.current}`, { parse: true });

  return data[day.current].map(doc =>({
      title: doc.title,
      malid: doc.mal_id,
      image: doc.image_url
  }));

};

const top = async (top) =>{

  let data

  if (top.subtype !== undefined) {
    data = await homgot(`${BASE_JIKAN}top/${top.type}/${top.page}/${top.subtype}`, { parse: true });
  } else {
    data = await homgot(`${BASE_JIKAN}top/${top.type}/${top.page}`, { parse: true });
  }

  return data.top

};

const getAllAnimes = async () =>{

  let data = await homgot(`${BASE_ANIMEFLV}api/animes/list`, { parse: true })

  return data.map(item => ({
    index: item[0],
    animeId: item[3],
    title: item[1],
    id: item[2],
    type: item[4]
  }));

};

const getAllDirectory = async () =>{

  let data = JSON.parse(JSON.stringify(require('../assets/directory.json')));

  return data.map(doc => ({
    id: doc.id,
    title: doc.title,
    mal_title: doc.mal_title,
    poster: doc.poster,
    type: doc.type,
    genres: doc.genres,
    state: doc.state,
    score: doc.score,
    jkanime: false,
    description: doc.description
  }));

};

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
        mp3: doc.enclosures[0].url
      });
    });

  });

  return promises;

};

const getNews = async (pageRss) =>{

  let promises = []

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

const season = async (season) =>{

  const data = await homgot(`${BASE_JIKAN}season/${season.year}/${season.type}`, { parse: true });

  return data.anime.map(doc =>({
    title: doc.title,
    image: doc.image_url,
    genres: doc.genres.map(x => x.name)
  }));

};

const allSeasons = async () =>{

  const data = await homgot(`${BASE_JIKAN}season/archive`, { parse: true });

  return data.archive.map(doc =>({
      year: doc.year,
      seasons: doc.seasons,
  }));

};

const laterSeasons = async () =>{

  const data = await homgot(`${BASE_JIKAN}season/later`, { parse: true });

  return data.anime.map(doc =>({
      title: doc.title,
      image: doc.image_url,
      malink: doc.url
  }));

};

const getLastEpisodes = async () =>{

  const data = await homgot(`${BASE_ANIMEFLV_JELU}LatestEpisodesAdded`, { parse: true });
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

const getSpecials = async (data) =>{

  const res = await homgot(`${BASE_ANIMEFLV_JELU}${data.url}/${data.type}/${data.page}`, { parse: true });

  return res[data.prop].map(doc =>({
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
  }));

};

const getMoreInfo = async (title) =>{

  const promises = []

  let data = JSON.parse(JSON.stringify(require('../assets/directory.json')));
  const res = data.filter(x => x.title === title || x.mal_title === title)[0];

  if (!res.jkanime) {
    if (res.type === 'Anime') {

      promises.push({
        title: res.title || null,
        poster: res.poster || null,
        synopsis: res.description || null,
        status: res.state || null,
        type: res.type || null,
        rating: res.score || null,
        genres: res.genres || null,
        episodes: await animeflvInfo(res.id).then(episodes => episodes || null),
        moreInfo: await animeExtraInfo(res.mal_title).then(info => info || null),
        promo: await getAnimeVideoPromo(res.mal_title).then(promo => promo || null),
        characters: await getAnimeCharacters(res.mal_title).then(characters => characters || null)
      });

    }
    else {
      promises.push({
        title: res.title || null,
        poster: res.poster || null,
        synopsis: res.description || null,
        status: res.state || null,
        type: res.type || null,
        rating: res.score || null,
        genres: res.genres || null,
        episodes: await animeflvInfo(res.id).then(episodes => episodes || null)
      });
    }
  } else {
    if (res.type === 'Anime') {
      promises.push({
        title: res.title || null,
        poster: res.poster || null,
        synopsis: res.description || null,
        status: res.state || null,
        type: res.type || null,
        rating: res.score || null,
        genres: res.genres || null,
        episodes: await jkanimeInfo(res.id).then(episodes => episodes || null),
        moreInfo: await animeExtraInfo(res.mal_title).then(info => info || null),
        promo: await getAnimeVideoPromo(res.mal_title).then(promo => promo || null),
        characters: await getAnimeCharacters(res.mal_title).then(characters => characters || null)
      });
    }
    else {
      promises.push({
        title: res.title || null,
        poster: res.poster || null,
        synopsis: res.description || null,
        status: res.state || null,
        type: res.type || null,
        rating: res.score || null,
        genres: res.genres || null,
        episodes: await jkanimeInfo(res.id).then(episodes => episodes || null)
      });
    }
  }

  return promises;

};

const getAnimeServers = async (id) => {

  if (isNaN(id.split('/')[0])) {
    return await videoServersJK(id)
  } else {
    const data = await homgot(`${BASE_ANIMEFLV_JELU}GetAnimeServers/${id}`, { parse: true });
    return await transformUrlServer(data.servers);
  }

};

const search = async (title) =>{ return await searchAnime(title); };

const getImages = async (query) => {

  const data = await homgot(`${BASE_QWANT}count=${query.count}&q=${query.title}&t=${query.type}&safesearch=${query.safesearch}&locale=${query.country}&uiv=4`, { parse: true });

  return data.data.result.items.map(doc =>({
      type: doc.thumb_type,
      thumbnail: `https:${doc.thumbnail}`,
      fullsize: `https:${doc.media_fullsize}`
  }));

};

const getYoutubeVideos = async (channelId) => {

  const data = await homgot(`${BASE_YOUTUBE}${channelId.id}&part=${channelId.part}&order=${channelId.order}&maxResults=${channelId.maxResults}`, { parse: true });

  return data[channelId.prop].map(doc =>({
     title: doc.snippet.title,
     videoId: doc.id.videoId,
     thumbDefault: doc.snippet.thumbnails.default.url,
     thumbMedium: doc.snippet.thumbnails.medium.url,
     thumbHigh: doc.snippet.thumbnails.high.url
  }));

};

const getRadioStations = async () => require('../assets/radiostations.json');

const getOpAndEd = async (title) => await structureThemes(await parserThemes.serie(title), true);

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

  let data = await homgot(`${BASE_THEMEMOE}roulette`, { parse: true });
  let themes = await getThemes(data.themes)

  return themes.map(doc =>({
    name: data.name,
    title: doc.name,
    link: doc.video
  }));

};

const getArtist = async (id) => {

  if (id === undefined) {
    return await parserThemes.artists();
  } else {
    return await structureThemes(await parserThemes.artist(id), false)
  }

};

const getAnimeGenres = async(genres) => {

  let $
  let promises = []

  if (genres.genre === undefined && genres.page === undefined && genres.order === undefined)  {
    return require('../assets/genres.json');
  } else {

    if (genres.page !== undefined) {
      $ = await homgot(`${GENRES_URL}genre%5B%5D=${genres.genre}&order=${genres.order}&page=${genres.page}`,{ scrapy: true })
    } else {
      $ = await homgot(`${GENRES_URL}genre%5B%5D=${genres.genre}&order=${genres.order}`,{ scrapy: true })
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

  }

};

const getAllThemes = async () => await structureThemes(await parserThemes.all(), false);

const getDestAnimePlatforms = async () => {

  let data = await homgot(`${BASE_ARUPPI}res/documents/animelegal/top.json`, { parse: true });

  return data.map(doc =>({
    id: doc.id,
    name: doc.name,
    logo: doc.logo
  }));

};

const getPlatforms = async (id) => {

  let data

  if (id === undefined) {

    data = await homgot(`${BASE_ARUPPI}res/documents/animelegal/typeplatforms.json`, { parse: true });

    return data.map(doc =>({
      id: doc.id,
      name: doc.name,
      comming: doc.comming || false,
      cover: doc.cover
    }));

  } else {

    data = await homgot(`${BASE_ARUPPI}res/documents/animelegal/type/${id}.json`, { parse: true });

    return data.map(doc =>({
      id: doc.id,
      name: doc.name,
      type: doc.type,
      logo: doc.logo,
      cover: doc.cover,
      link: doc.link
    }));
  }

};

const getProfilePlatform = async (id) => {

  let data = await homgot(`${BASE_ARUPPI}res/documents/animelegal/platforms/${id}.json`, { parse: true })

  let channelId = { id: data[0].youtubeId, part: 'snippet,id', order: 'date', maxResults: '50', prop: 'items'  };
  let videos = await getYoutubeVideos(channelId)

  return data.map(doc =>({
    id: doc.id,
    name: doc.name,
    logo: doc.logo,
    cover: doc.cover,
    category: doc.category,
    description: doc.description,
    facebook: doc.facebook,
    twitter: doc.twitter,
    instagram: doc.instagram,
    webpage: doc.webpage,
    simulcast: doc.simulcast,
    paid: doc.paid,
    shop: doc.shop,
    faq: doc.faq,
    videos: videos
  }));

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
  getAllThemes,
  getDestAnimePlatforms,
  getPlatforms,
  getProfilePlatform
};
