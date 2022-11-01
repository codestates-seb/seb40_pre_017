// 스토리지에 값 저장할 때 사용. 토큰 포함.
export const setItemWithExpireTime = (keyName, keyValue, tts) => {
  // localStorage에 저장할 객체  
  const obj = {
    value : keyValue,
    expire : Date.now() + tts  
  }   
  // 객체를 JSON 문자열로 변환  
  const objString = JSON.stringify(obj);   
  // setItem  
  window.localStorage.setItem(keyName, objString);
}

// 스토리지 값 가져올 때 사용.
export const getItemWithExpireTime = (keyName) => {
  // localStorage 값 읽기 (문자열)  
  const objString = window.localStorage.getItem(keyName);

  // null 체크  
  if(!objString) {
    return null;  
  }

  const obj = JSON.parse(objString);    
  // 현재 시간과 localStorage의 expire 시간 비교
  if(Date.now() > obj.expire) {
    // 만료시간이 지난 item 삭제    
    window.localStorage.removeItem(keyName);        
    // null 리턴    
    return null;  
  }    
  
  // 만료기간이 남아있는 경우, value 값 리턴  
  return obj.value;
}



// 쿠키에서 리프레시 토큰이 있는지 확인
// 있으면 컨트롤러 토큰 요청.
// 없으면 로그인 페이지로 이동.
export const AccessTokenControl = () => {

  let token = getItemWithExpireTime("authorization");

  if(token !== null) {
    // 엑세스 토큰이 스토리지에 저장되어 있고 유효기간이 남아있으면 엑세스 토큰 반환. 
    return token
  }else{
    // 엑세스 토큰이 스토리지에 저장되어 있지 않거나 유효기간이 끝났으면 실행.
    
    //fetch로 토큰 재발급 요청.
    fetch('http://localhost:3001/users/reissue')
    .then((res) => {
      // access 토큰 재발급 후 로컬 스토리지에 저장.
      let jwtToken = res.headers.authorization;
      setItemWithExpireTime("authorization", jwtToken, 1000 * 60 * 30);

      let newToken = getItemWithExpireTime("authorization");

      if(newToken !== null) {
        // 엑세스 토큰이 스토리지에 저장되어 있고 유효기간이 남아있으면 엑세스 토큰 반환. 
        return newToken
      }

    })
    .catch((err) => {
      // 리프레시 토큰이 유효하지 않을 경우.
      // 로그아웃 호출
      fetch('http://localhost:3001/users/logout')
      .then((res) => {
        localStorage.removeItem("member");
        localStorage.removeItem("authorization");
        localStorage.removeItem("isLogin");
        alert("Logout")
        window.location.href = "/"
      })
    });
  }

}