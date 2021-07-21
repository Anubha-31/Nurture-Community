module.exports = {
  purge: {
    content: ['./public/**/*.html', './src/**/*.vue']
  },
  darkMode: false, // or 'media' or 'class'
  theme: {
    extend: {
      colors: {
        'aqua-1': '#067599',
        'aqua-2': '#005f7b',
        'aqua-3': '#003a4a',
      }
    },
  },
  variants: {
    extend: {},
  },
  plugins: [],
}
