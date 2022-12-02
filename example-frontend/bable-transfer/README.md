#### babel: es6->es5
procedures:
> - pnpm init
> - pnpm install babel-cli babel-preset-latest
> - 建立 .babelrc :
```json
{
  "presets": [
    "latest"
  ]
}
```

> - babel es6.js --out-file es5.js
> - babel src --out-dir dist