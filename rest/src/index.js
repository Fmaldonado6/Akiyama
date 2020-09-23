const app = require('./app');
const port = process.env.PORT || 5000;
const addr = isNaN(port) ? '' : (process.env.ADDR || '0.0.0.0');

server = app.listen(port, addr, () => {
  /* eslint-disable no-console */
  console.log(`\n🚀 ... Listening: ${addr}${addr ? '\:' : 'unix://'}${port}`);
  /* eslint-enable no-console */
});

function shutdown() {
  server.close();
  process.exit();
}

process.on('SIGINT', shutdown);
process.on('SIGQUIT', shutdown);
process.on('SIGTERM', shutdown);
