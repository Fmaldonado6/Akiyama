const express = require('express');
const router = express.Router();
const api = require('../api');

const { BASE_KUDASAI, BASE_PALOMITRON, BASE_RAMENPARADOS, BASE_CRUNCHYROLL } = require('../urls');

router.get('/schedule/:day' , (req, res) =>{

    let day = {current: req.params.day}

    api.schedule(day)
        .then(day =>{
            if (day.length > 0) {
                res.status(200).json({
                    day
                });
            } else (
                res.status(500).json({ message: 'Aruppi lost in the shell'})
            )
        }).catch((err) =>{
        console.error(err);
    });

});

router.get('/top/:type/:subtype/:page' , (req, res) =>{

    let type = req.params.type;
    let subtype = req.params.subtype;
    let page = req.params.page;

    api.top(type, subtype, page)
        .then(top =>{
            if (top.length > 0) {
                res.status(200).json({
                    top
                });
            } else (
                res.status(500).json({ message: 'Aruppi lost in the shell'})
            )
        }).catch((err) =>{
        console.error(err);
    });

});

router.get('/allAnimes' , (req, res) =>{

    api.getAllAnimes()
        .then(animes =>{
            if (animes.length > 0) {
                res.status(200).json({
                    animes
                });
            } else (
                res.status(500).json({ message: 'Aruppi lost in the shell'})
            )
        }).catch((err) =>{
        console.error(err);
    });

});

router.get('/allDirectory' , (req, res) =>{

    api.getAllDirectory()
        .then(directory =>{
            if (directory.length > 0) {
                res.status(200).json({
                    directory
                });
            } else (
                res.status(500).json({ message: 'Aruppi lost in the shell'})
            )
        }).catch((err) =>{
        console.error(err);
    });

});

router.get('/anitakume' , (req, res) =>{

    api.getAnitakume()
        .then(podcast =>{
            if (podcast.length > 0) {
                res.status(200).json({
                    podcast
                });
            } else (
                res.status(500).json({ message: 'Aruppi lost in the shell'})
            )
        }).catch((err) =>{
        console.error(err);
    });

});

router.get('/news' , (req, res) =>{

    let pagesRss = [
        { url: BASE_KUDASAI,        author: 'Kudasai',          content: 'content_encoded'  },
        { url: BASE_PALOMITRON,     author: 'Palomitron',       content: 'description'      },
        { url: BASE_RAMENPARADOS,   author: 'Ramen para dos',   content: 'content'          },
        { url: BASE_CRUNCHYROLL,    author: 'Crunchyroll',      content: 'content_encoded'  }
    ];

    api.getNews(pagesRss)
        .then(news =>{
            if (news.length > 0) {
                res.status(200).json({
                    news
                });
            } else (
                res.status(500).json({ message: 'Aruppi lost in the shell'})
            )
        }).catch((err) =>{
        console.error(err);
    });

});

router.get('/season/:year/:type' , (req, res) =>{

    let year = req.params.year;
    let type = req.params.type;

    api.season(year, type)
        .then(season =>{
            if (season.length > 0) {
                res.status(200).json({
                    season
                });
            } else (
                res.status(500).json({ message: 'Aruppi lost in the shell'})
            )
        }).catch((err) =>{
        console.error(err);
    });

});

router.get('/allSeasons' , (req, res) =>{

    api.allSeasons()
        .then(archive =>{
            if (archive.length > 0) {
                res.status(200).json({
                    archive
                });
            } else (
                res.status(500).json({ message: 'Aruppi lost in the shell'})
            )
        }).catch((err) =>{
        console.error(err);
    });

});

router.get('/laterSeasons' , (req, res) =>{

    api.laterSeasons()
        .then(future =>{
            if (future.length > 0) {
                res.status(200).json({
                    future
                });
            } else (
                res.status(500).json({ message: 'Aruppi lost in the shell'})
            )
        }).catch((err) =>{
        console.error(err);
    });

});

router.get('/lastEpisodes' , (req, res) =>{

    api.getLastEpisodes()
        .then(episodes =>{
            if (episodes.length > 0) {
                res.status(200).json({
                    episodes
                });
            } else (
                res.status(500).json({ message: 'Aruppi lost in the shell'})
            )
        }).catch((err) =>{
        console.error(err);
    });

});

router.get('/movies/:type/:page' , (req, res) =>{

    let type = {url: 'Movies', prop: 'movies'}
    let subType = req.params.type;
    let page = req.params.page;

    api.getSpecials(type, subType, page)
        .then(movies =>{
            if (movies.length > 0) {
                res.status(200).json({
                    movies
                });
            } else (
                res.status(500).json({ message: 'Aruppi lost in the shell'})
            )
        }).catch((err) =>{
        console.error(err);
    });

});

router.get('/ovas/:type/:page' , (req, res) =>{

    let type = {url: 'Ova', prop: 'ova'}
    let subType = req.params.type;
    let page = req.params.page;

    api.getSpecials(type, subType, page)
        .then(ovas =>{
            if (ovas.length > 0) {
                res.status(200).json({
                    ovas
                });
            } else (
                res.status(500).json({ message: 'Aruppi lost in the shell'})
            )
        }).catch((err) =>{
        console.error(err);
    });

});

router.get('/specials/:type/:page' , (req, res) =>{

    let type = {url: 'Special', prop: 'special'}
    let subType = req.params.type;
    let page = req.params.page;

    api.getSpecials(type, subType, page)
        .then(specials =>{
            if (specials.length > 0) {
                res.status(200).json({
                    specials
                });
            } else (
                res.status(500).json({ message: 'Aruppi lost in the shell'})
            )
        }).catch((err) =>{
        console.error(err);
    });

});

router.get('/tv/:type/:page' , (req, res) =>{

    let type = {url: 'Tv', prop: 'tv'}
    let subType = req.params.type;
    let page = req.params.page;

    api.getSpecials(type, subType, page)
        .then(tv =>{
            if (tv.length > 0) {
                res.status(200).json({
                    tv
                });
            } else (
                res.status(500).json({ message: 'Aruppi lost in the shell'})
            )
        }).catch((err) =>{
        console.error(err);
    });

});

router.get('/moreInfo/:title' , (req, res) =>{

    let title = req.params.title;

    api.getMoreInfo(title)
        .then(info =>{
            if (info.length > 0) {
                res.status(200).json({
                    info
                });
            } else (
                res.status(500).json({ message: 'Aruppi lost in the shell'})
            )
        }).catch((err) =>{
        console.error(err);
    });

});

router.get('/getAnimeServers/:id([^/]+/[^/]+)' , (req, res) =>{

    let id = req.params.id;

    api.getAnimeServers(id)
        .then(servers =>{
            if (servers.length > 0) {
                res.status(200).json({
                    servers
                });
            } else (
                res.status(500).json({ message: 'Aruppi lost in the shell'})
            )
        }).catch((err) =>{
        console.error(err);
    });

});

router.get('/search/:title' , (req, res) =>{

    let title = req.params.title;

    api.search(title)
        .then(search =>{
            if (search.length > 0) {
                res.status(200).json({
                    search
                });
            } else (
                res.status(500).json({ message: 'Aruppi lost in the shell'})
            )
        }).catch((err) =>{
        console.error(err);
    });

});

router.get('/images/:query' , (req, res) =>{

    let query = { title: req.params.query, count: '51', type: 'images', safesearch: '1', country: 'es_ES', uiv: '4'  };

    api.getImages(query)
        .then(images =>{
            if (images.length > 0) {
                res.status(200).json({
                    images
                });
            } else (
                res.status(500).json({ message: 'Aruppi lost in the shell'})
            )
        }).catch((err) =>{
        console.error(err);
    });

});

router.get('/videos/:channelId' , (req, res) =>{

    let channelId = { id: req.params.channelId, part: 'snippet,id', order: 'date', maxResults: '50', prop: 'items'  };

    api.getYoutubeVideos(channelId)
        .then(videos =>{
            if (videos.length > 0) {
                res.status(200).json({
                    videos
                });
            } else (
                res.status(500).json({ message: 'Aruppi lost in the shell'})
            )
        }).catch((err) =>{
        console.error(err);
    });

});

router.get('/radio' , (req, res) =>{

    api.getRadioStations()
        .then(stations =>{
            if (stations.length > 0) {
                res.status(200).json({
                    stations
                });
            } else (
                res.status(500).json({ message: 'Aruppi lost in the shell'})
            )
        }).catch((err) =>{
        console.error(err);
    });

});

router.get('/allThemes', (req, res) =>{

    api.getAllThemes()
        .then(themes =>{
            if (themes.length > 0) {
                res.status(200).json({
                    themes
                });
            } else (
                res.status(500).json({ message: 'Aruppi lost in the shell'})
            )
        }).catch((err) =>{
        console.error(err);
    });

});


router.get('/themes/:title' , (req, res) =>{

    let title = req.params.title;

    api.getOpAndEd(title)
        .then(themes =>{
            if (themes.length > 0) {
                res.status(200).json({
                    themes
                });
            } else (
                res.status(500).json({ message: 'Aruppi lost in the shell'})
            )
        }).catch((err) =>{
        console.error(err);
    });

});

router.get('/themesYear/:year?', (req, res) =>{

    let year = req.params.year;
    let season = req.params.season

    api.getThemesYear(year, season)
        .then(themes =>{
            if (themes.length > 0) {
                res.status(200).json({
                    themes
                });
            } else (
                res.status(500).json({ message: 'Aruppi lost in the shell'})
            )
        }).catch((err) =>{
        console.error(err);
    });

});

router.get('/randomTheme', (req, res) =>{

    api.getRandomTheme()
        .then(random =>{
            if (random.length > 0) {
                res.set('Cache-Control', 'no-store');
                res.status(200).json({
                    random
                });
            } else (
                res.status(500).json({ message: 'Aruppi lost in the shell'})
            )
        }).catch((err) =>{
        console.error(err);
    });

});

router.get('/artists/:id?', (req, res) =>{

    let id = req.params.id;

    api.getArtist(id)
        .then(artists =>{
            if (artists.length > 0) {
                res.status(200).json({
                    artists
                });
            } else (
                res.status(500).json({ message: 'Aruppi lost in the shell'})
            )
        }).catch((err) =>{
        console.error(err);
    });

});

router.get('/getByGenres/:genre/:order/:page?' , (req , res) =>{
    let genre = req.params.genre;
    let order = req.params.order;
    let page = req.params.page;
    api.getAnimeGenres(genre, order , page)
        .then(animes =>{
            if (animes.length > 0) {
                res.status(200).json({
                    animes
                });
            } else (
                res.status(500).json({ message: 'Aruppi lost in the shell'})
            )
        }).catch((err) =>{
        console.error(err);
    });
});

module.exports = router;
