import Vue from 'vue'
import Vuex from 'vuex'
import {login} from 'network/user'
import {SET_ID, SET_USERNAME,
    SET_NICKNAME, SET_ADAVAR} from 'store/mutations-types'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
      id: -1,
      username: '',
      nickname: '',
      adavar: ''
  },
  mutations: {//同步操作，更新state状态
      ["SET_ID"](state, id){
        state.id = id;
      },
      ["SET_USERNAME"](state, username){
        state.username = username;
      },
      ["SET_NICKNAME"](state, nickname){
        state.nickname = nickname;
      },
      ["SET_ADAVAR"](state, adavar){
        state.adavar = adavar;
      }
  },
  actions: {//异步操作，请求后端数据
      login(context, user){
          return new Promise((resolve, reject) => {
              login(user).then(res => {
                  //调用mutations方法修改state状态
                  //修改用户id
                  context.commit("SET_ID", res.data.user.id)
                  //修改用户名
                  context.commit("SET_USERNAME", res.data.user.username)
                  //修改用户昵称
                  context.commit("SET_NICKNAME", res.data.user.nickname)
                  //修改用户头像
                  context.commit("SET_ADAVAR", res.data.user.adavar)
                  resolve(res)
              }).catch(err => {
                  //错误处理
                  reject(err)
              })
          })
      }
  }
})
