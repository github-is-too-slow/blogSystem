import axiox from 'axios'
export function request(config){
    const instance = axiox.create({
        baseURL: 'http://localhost:80',
        timeout: 5000
    })

    //对请求进行拦截
    instance.interceptors.request.use(config => {
        // 处理请求对象
        return config
    }, err => {
        // 处理请求错误
    })

    // 对响应进行拦截
    instance.interceptors.response.use(res => {
        // 处理响应结果
        return res.data
    }, err => {
        // 处理响应错误
    })
    // 进行网络请求，并且返回的是Promise对象
    return instance(config)
}