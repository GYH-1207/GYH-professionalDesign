import { http } from '@/utils/http'

export function list(){
    return http.get('/user/list')
}

export function login(params){
    return http.post('/user/login',params)
}

export function regist(params){
    return http.post('/user/regist',params)
}