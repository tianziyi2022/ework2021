class Http {
    //在除了login以外的地方使用，如果没有获得token，则会直接跳转到login，成功时只返回data，code不为0则返回全部
    static request({
                       url,
                       data,
                       method = 'POST'
                   }) {
        if (!data) {//如果没有写数据，就给个空对象
            data = {}
        }
        console.log(url)
        return axios({
            method,
            url,
            dataType: 'json',
            headers: {
                "Content-Type": "application/json"
            },
            data
        }).then(value => {
            console.log("value: ", value)
            if (value.status !== 200) {
                return Promise.reject(value);
            }
            if (value.data.code !== 0) {
                return Promise.reject(value.data);
            }
            return value.data.data;
        }, error => {
            console.log("error", error);
            return Promise.reject(error);
        });
    }
}