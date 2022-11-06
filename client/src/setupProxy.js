const { createProxyMiddleware } = require('http-proxy-middleware');

const REACT_APP_API_URL = process.env.REACT_APP_API_URL;

module.exports = function(app) {
  app.use(
    '/api', //proxy가 필요한 path prameter를 입력합니다.
    createProxyMiddleware({
      // ec2-13-125-176-138.ap-northeast-2.compute.amazonaws.com:8080
      target: 'https://api.taekgil.xyz/', //타겟이 되는 api url를 입력합니다.
      changeOrigin: true, //대상 서버 구성에 따라 호스트 헤더가 변경되도록 설정하는 부분입니다.
      pathRewrite: {
        '^/api': '' // URL ^/api -> 공백 변경
      }
    })
  );
};