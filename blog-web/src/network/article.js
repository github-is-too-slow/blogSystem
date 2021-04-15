import {request} from "network/request"

export function getArticles(pageNumber, pageSize) {
    return request({
        url: '/api/article/list',
        method: 'get',
        params: {
            pageNumber,
            pageSize
        }
    })
}

export function getArticleById(id) {
    return request({
        url: '/api/article/getArticleById',
        method: 'get',
        params: {
            id
        }
    })
}

export function getArticleByCategory(pageNumber, pageSize, categoryName) {
    return request({
        url: "/api/article/getArticleByCategory",
        method: "get",
        params: {
            pageNumber,
            pageSize,
            categoryName
        }
    })
}

export function getArticleByTagId(pageNumber, pageSize, tagId) {
    return request({
        url: "/api/article/getArticleByTagId",
        method: "get",
        params: {
            pageNumber,
            pageSize,
            tagId
        }
    })
}

export function upload(formData) {
    return request({
        url: '/api/article/upload',
        method: 'post',
        data: formData
    })
}

export function getCategorysAndTags() {
    return request({
        url: '/api/article/getMetaInfo',
        method: 'get'
    })
}

export function getCategoryInfo() {
    return request({
        url: '/api/article/getCategoryInfo',
        method: 'get'
    })
}

export function getTagInfo() {
    return request({
        url: '/api/article/getTagInfo',
        method: 'get'
    })
}

export function publishArticle(article) {
    return request({
        url: '/api/article/publish',
        method: 'post',
        data: article
    })
}