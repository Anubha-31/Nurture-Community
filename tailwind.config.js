module.exports = {
  purge: {
    content: ['./public/**/*.html', './src/**/*.vue']
  },
  darkMode: false, // or 'media' or 'class'
  theme: {
    extend: {
      colors: {
        'aqua-2': '#005f7b',
      }
    },
  },
  variants: {
    extend: {},
  },
  plugins: [],
}
