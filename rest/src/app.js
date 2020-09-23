const express = require('express');
const helmet = require('helmet');
const cors = require('cors');
const bodyParser = require('body-parser');
const version = require('./../package.json').version;

const middlewares = require('./middlewares/index').middleware;
const api = require('./api');

const api_legacy = require('./v2/api');

const app = express();

app.use(helmet());
app.use(cors());
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));

app.get('/', (req, res) => {
  res.set('Cache-Control', 'no-store');
  res.redirect('/api/')
});

app.get('/api/', (req, res) => {
  res.set('Cache-Control', 'no-store');
  res.json({
    title: 'Aruppi API',
    version: version,
    source: 'https://github.com/aruppi/aruppi-api',
    description: 'This API has everything about Japan, from anime, music, radio, images, videos... to japanese culture (Spanish Only)',
    powers: 'https://play.google.com/store/apps/details?id=com.jeluchu.aruppi&hl=es_419'
  });
});

app.get('/api/v1', (req, res) => {
  res.set('Cache-Control', 'no-store');
  res.json({
    message: 'Sorry, version v1 is deprecated, if you want to see content go to v2'
  });
});

app.use('/api/v2', api_legacy);
app.use('/api/v3', api);

app.use(middlewares);

module.exports = app;
