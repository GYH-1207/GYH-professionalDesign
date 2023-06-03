import axios from 'axios'
// Vue.prototype.$http=axios;

export function request(config) {
  // 1. 创建axios实例
  const instance = axios.create({
    baseURL: 'http://127.0.0.1:8081/', // 通过/api别名指定后端路由
    withCredentials: false, // 跨域请求时是否需要访问凭证
    // timeout: 10 * 1000, // 请求超时时间
    headers: { // 请求头
		  
	  }
  })

  // 2 使用axios前置拦截器，让所有的请求都携带uToken
  instance.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';
   //2.1 获取到浏览器里面一直存储的token，并将它放到
   let uToken =  localStorage.getItem("uToken");
   if(uToken){
   //2.2 注意：给请求头里面添加u-token（后台判断就是取的这个请求头）请求头，并把随机数的token值也设置进去
       config.headers['u-token']=uToken;
       console.log("加入成功")
   }
   //2.3 必须要返回这个，才能执行后面代码
  //  console.log("config",config)
   return config;
  }, error => {
    Promise.reject(error);
  })

  //3 使用axios设置后置拦截器，处理后台被拦截，没有登录的请求
  instance.interceptors.response.use(result=>{
    console.log("result=",result.data)
    let data = result.data;
    //只要前台被拦截的请求里面含这两个参数，那么就跳转到登录界面
    if(data.code!=1 && data.msg==="NOTLOGIN")
        location.href = "/accountCenter/login";
    return result; //返回值必须为result原样返回，否则出错
  },error => {
    Promise.reject(error);
  })

  // 3. 发送真正的网络请求
  return instance(config)

}
