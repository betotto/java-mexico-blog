module.exports = require('babel-jest').createTransformer({
  presets: [[
    require('@babel/preset-env'), {
      targets: {
        chrome: 58,
        ie: 11
      }
    }]
  ],
  plugins: [[
    require('@babel/plugin-transform-react-jsx'), {
      pragma: 'h',
      pragmaFrag: 'Fragment'
    }]
  ]
});
