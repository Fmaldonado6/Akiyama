const express = require('express');
const routes = require('./routes/index');

const router = express.Router();

router.get('/', (req, res) => {
  res.set('Cache-Control', 'no-store');
  res.json({
    message: 'Aruppi API - 🎏',
    author: 'Jéluchu',
    version: '2.6.8',
    credits: 'The bitch loves APIs that offers data to Aruppi App',
    deprecated: 'This version will be available until users migrate to Aruppi App v1.5.0',
    entries: [
      {
        'Schedule': '/api/v2/schedule/:day',
        'Top': '/api/v2/top/:type/:subtype/:page',
        'AllAnimes': '/api/v2/allAnimes',
        'Anitakume': '/api/v2/anitakume',
        'News': '/api/v2/news',
        'Season': '/api/v2/season/:year/:type',
        'All Seasons': '/api/v2/allSeasons',
        'All Directory': '/api/v2/allDirectory',
        'Genres': '/api/v2/getByGenres/:genre/:order/:page?',
        'Futures Seasons': '/api/v2/laterSeasons',
        'LastEpisodes': '/api/v2/lastEpisodes',
        'Movies': '/api/v2/movies/:type/:page',
        'Ovas': '/api/v2/ovas/:type/:page',
        'Specials': '/api/v2/specials/:type/:page',
        'Tv': '/api/v2/tv/:type/:page',
        'MoreInfo': '/api/v2/moreInfo/:title',
        'GetAnimeServers': '/api/v2/getAnimeServers/:id',
        'Search': '/api/v2/search/:title',
        'Images': '/api/v2/images/:query',
        'Videos': '/api/v2/videos/:channelId',
        'Radios': '/api/v2/radio',
        'All Themes': '/api/v2/allThemes',
        'Themes': '/api/v2/themes/:title',
        'Year Themes': '/api/v2/themesYear/:year?',
        'Random Theme': '/api/v2/randomTheme',
        'Artists Theme': '/api/v2/artists/:id?'
      }
    ]
  });
});

router.use('/', routes);

module.exports = router;
