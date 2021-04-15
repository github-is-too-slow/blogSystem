import {request} from 'network/request'
export function saveUser(formData){
    return request({
        url: '/api/user/save',
        method: 'post',
        data: formData
    })
}

export function login(user) {
    return request({
        url: '/api/user/login',
        method: 'post',
        data: user
    })
}

export function checkUsername(username) {
    return request({
        url: '/api/user/checkUsername',
        method: 'get',
        params: {
            username
        }
    })
}