var userId;
async function main() {
    layui.use(['form', 'layer', 'table'], function () {
        const form = layui.form, $ = layui.jquery, layer = layui.layer, table = layui.table;
        const dataMap = {//映射关系
            'name': '姓名',
            'phone': '手机号码',
            'studentId': '学号',
            'idCard':'身份证号',
            'wayToTianjin':'自驾/公共交通',
            'route':'返津路线（非自驾填“无”）',
            'carId':'返津自驾车牌号（非自驾填“无”）',
            'wayToSchool':'返津主要交通方式（火车/飞机/大巴车/天津市内公共交通）（非公共交通填“无”）',
            'seat':'具体车次/航班号、车厢号、座位号（非公共交通填“无”）',
            'station':'抵津场站（东站/西站/南站/机场/市内交通）（非公共交通填“无”）',
            'wayToStation':'从居住地到机场、车站交通方式（具体到车牌号）（非公共交通填“无”）',
            'transit':'交通中转地（自驾返回，可填写“无”）',
            'departureDate':'出发时间',
            'arriveTime':'抵津日期（具体到某日某时，填写格式24小时制）',
            'updateTime':'最后修改时间',
            'times':'修改次数'
        };
        const data = [];
        const cols = [[
            {field: 'key', title: '项目', minWidth: 80}
            , {field: 'value', title: '待确认内容', minWidth: 150}
        ]];
        table.render({
            elem: '#test'
            , title: '个人信息确认'
            , cols
            , data
            , limit:17
        });
        form.on('submit(formDemo)', function (res) {
            Http.request({
                url: `${config.baseUrl}${config.getInfo.url}`,
                data: res.field
            }).then(value => {
                for (const key in value) {
                    console.log(key);
                    if(!dataMap[key]){
                        continue;
                    }
                    const baseData = {
                        "key": dataMap[key],
                        "value": value[key]
                    }
                    data.push(baseData);
                }
                userId = value.id
                // console.log(data)
                table.render({
                    elem: '#test'
                    , title: '个人信息确认'
                    , cols
                    , data
                    , limit:17
                });
            }, error => {
                layer.msg(error.msg)
            })
            return false; //阻止表单跳转
        });
        form.on('submit(confirm)',function confirm() {
            Http.request({
                url: `${config.baseUrl}${config.confim.url}`
                ,data:{
                    id : userId
                }
            }).then(value => {
                layer.msg("信息核验成功")
                // window.location.reload();
            }, error => {
                layer.msg(error.msg)
            })
        })

        form.on('submit(hasProblem)',function hasProblem() {
            Http.request({
                url: `${config.baseUrl}${config.problem.url}`
                ,data:{
                    id : userId
                }
            }).then(value => {
                layer.msg("错误上报成功")
                // window.location.reload();
            }, error => {
                layer.msg(error.msg)
            })
        })
    })
}

main();