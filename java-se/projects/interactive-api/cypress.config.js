const { defineConfig } = require('cypress')

module.exports = defineConfig({
  env: {
    apiUrl: 'http://localhost:9090',
  },
  e2e: {
    setupNodeEvents(on, config) {},
    supportFile: false,
  },
})