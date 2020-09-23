const express = require('express');
const routes = require('./routes/index');
const version = require('./../../package.json').version;

const router = express.Router();

router.get('/', (req, res) => {
  res.set('Cache-Control', 'no-store');
  res.json({
    message: 'Aruppi API - 🎏',
    author: 'Jéluchu',
    version: version,
    credits: 'The bitch loves APIs that offers data to Aruppi App',
    entries: [
      {
        'Schedule': '/api/v3/schedule/:day',
        'Top': '/api/v3/top/:type/:subtype/:page',
        'AllAnimes': '/api/v3/allAnimes',
        'Anitakume': '/api/v3/anitakume',
        'News': '/api/v3/news',
        'Season': '/api/v3/season/:year/:type',
        'All Seasons': '/api/v3/allSeasons',
        'All Directory': '/api/v3/allDirectory',
        'Genres': '/api/v3/getByGenres/:genre?/:order?/:page?',
        'Futures Seasons': '/api/v3/laterSeasons',
        'LastEpisodes': '/api/v3/lastEpisodes',
        'Movies': '/api/v3/movies/:type/:page',
        'Ovas': '/api/v3/ovas/:type/:page',
        'Specials': '/api/v3/specials/:type/:page',
        'Tv': '/api/v3/tv/:type/:page',
        'MoreInfo': '/api/v3/moreInfo/:title',
        'GetAnimeServers': '/api/v3/getAnimeServers/:id',
        'Search': '/api/v3/search/:title',
        'Images': '/api/v3/images/:query',
        'Videos': '/api/v3/videos/:channelId',
        'Radios': '/api/v3/radio',
        'All Themes': '/api/v3/allThemes',
        'Themes': '/api/v3/themes/:title',
        'Year Themes': '/api/v3/themesYear/:year?',
        'Random Theme': '/api/v3/randomTheme',
        'Artists Theme': '/api/v3/artists/:id?',
        'Famous Platforms': '/api/v3/destAnimePlatforms',
        'Legal Platforms': '/api/v3/platforms/:id?',
        'Platforms Details': '/api/v3/profilePlatform:id'
      }
    ]
  });
});

router.use('/', routes);

module.exports = router;
