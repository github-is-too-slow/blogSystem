export default function scrollBehavior(to, from, savedPosition) {//异步滚动
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            if (savedPosition) {
                resolve(savedPosition);
            } else {
                resolve({ x: 0, y: 0 });
            }
        }, 0)
    })

}