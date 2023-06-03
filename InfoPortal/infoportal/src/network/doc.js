import { http } from '@/utils/http'

export function add(params){
    return http.post("/doc/add",params)
}
//查询全部/条件查询
export function list(params){
    if(params==null){
        return http.get("/doc/list")
    }
    return http.get("/doc/list",params)
}