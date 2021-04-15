import Vue from 'vue'
import Router from 'vue-router'
import scrollBehavior from 'common/utils/scrollBehavior'
import store from 'store'

Vue.use(Router)

const Home = () => import("views/home/Home")
const HomeIndex = () => import("views/home/children/index/HomeIndex")
const CategoryAndTag = () => import("views/home/children/category/CategoryAndTag")
const Category = () => import("views/home/children/category/Category")
const ArticleForSomeCategory = () => import("views/home/children/category/ArticleForSomeCategory")
const Tag = () => import("views/home/children/category/Tag")
const ArticleForSomeTag = () => import("views/home/children/category/ArticleForSomeTag")
const Register = () => import("views/user/Register")
const Login = () => import("views/user/Login")
const WriteBlog  = () => import("views/blog/WriteBlog")
const ViewBlog = () => import("views/blog/ViewBlog")

const routes = [
    {
        path: '',
        redirect: '/home'
    },
    {
      path: '/register',
      component: Register,
      meta: {
          title: '注册'
      }
    },
    {
        path: '/login',
        component: Login,
        meta: {
            title: '登录'
        }
    },
    {
        path: '/home',
        component: Home,
        children: [
            {
                path: '',
                component: HomeIndex,
                meta: {
                    title: '首页'
                }
            },
            {
                path: 'view',
                component: ViewBlog,
                meta: {
                }
            },
            {
                path: 'categorytag',
                component: CategoryAndTag,
                meta: {
                    title: '文章分类'
                },
                children: [
                    {
                        path: '',
                        redirect: 'category'
                    },
                    {
                        path: 'category',
                        component: Category,
                        meta: {
                            title: '分类'
                        }
                    },
                    {
                        path: 'somecategory',
                        component: ArticleForSomeCategory,
                        meta: {
                        }
                    },
                    {
                        path: 'tag',
                        component: Tag,
                        meta: {
                            title: '标签'
                        }
                    },
                    {
                        path: 'sometag',
                        component: ArticleForSomeTag,
                        meta: {
                        }
                    }
                ]
            },
        ]
    },
    {
        path: '/writeBlog',
        component: WriteBlog,
        meta: {
            title: '写博客',
            requireLogin: true
        }
    }
]

const router = new Router({
    routes,
    mode: 'history',
    scrollBehavior
})

router.beforeEach((to, from, next) => {
    window.document.title = to.matched[0].meta.title || to.meta.title || to.query.title
    if(to.matched.some(elem => elem.meta.requireLogin)){
        if(store.state.id < 0){//未登录
            //this.$router.push("/login")
            next('/login')
        }else {
            next()
        }
    }else{
        next()
    }
})

export default router