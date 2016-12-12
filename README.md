#KuwoRadio
# 截图
![图片描述](http://img.junbaor.com/2016121228F9AA56FD3AD0C954B8A624144C8F91.png)

# 声明

以下所有 API 均由 __北京酷我科技有限公司__
提供，本人采取非正常手段获取。获取与共享之行为或有侵犯酷我科技权益的嫌疑。若被告知需停止共享与使用，本人会及时删除此页面与整个项目。  
请您了解相关情况，并遵守酷我科技协议。

# API 说明
*  数据以 JSON 格式(UTF-8)返回

* 以下所有 API 使用的 HTTP Method 均为 `GET`

# API 分析

### 1. 电台节目列表
* URL: `http://album.kuwo.cn/album/servlet/commkdtpage?flag=2`  
* 参数:
    * `flag` : 含义未知，不可省略,且只能等于2
    * `listid` : 电台栏目ID，不可省略,具体栏目对应的ID会在下面列出
    * `pn` : 页码,可为空,缺省值 `0`
    * `rn` : 分页条数,可为空,缺省值 `20`
* 栏目ID:

         1. 吐小槽扒新闻
         2. 莫萱日记
         3. 爆笑糗事段子
         4. 柜子开了
         5. 酷我音乐调频
         6. 一路向北
         7. 真心话大冒险
         8. 爱的速递站
         9. 阳光音乐铺
        10. 酷我漫音坊
        11. 听他们说
        12. 听郭德纲说相声
        13. 灵异事件簿
        14. 今日星座运势
        15. 请给我一首歌的时间
        16. 贵圈那些事儿
        17. 萱草私房歌
        18. 每日正能量
        19. 历史那点事
        20. 放肆音乐
        21. 微时光
        22. 小曹胡咧咧
        23. 情感热线
        24. 晚安蜜语
        25. 小明和小红的日常生活
        26. 爆笑录音室

* 响应实例:

        {
		    "ret":"ok",
		    "total":711,
		    "musiclist":[
		        {
		            "yr":"2015-09-20",
		            "musicrid":"6620582",
		            "name":"从没被人表白过是什么体验(莫萱日记9月20日)",
		            "artist":"莫大人&萱草",
		            "album":"莫萱日记 2015合辑",
		            "formats":"WMA96|WMA128|MP3128|MP3192|MP3H|AAC48"
		        },
		        {
		            "yr":"2015-09-19",
		            "musicrid":"6620585",
		            "name":"生活中你感觉最美好的瞬间(莫萱日记9月19日)",
		            "artist":"莫大人&萱草",
		            "album":"莫萱日记 2015合辑",
		            "formats":"WMA96|WMA128|MP3128|MP3192|MP3H|AAC48"
		        },
		        {
		            "yr":"2015-09-18",
		            "musicrid":"6618997",
		            "name":"女生自己一个人在家时的小癖好(莫萱日记9月18日)",
		            "artist":"莫大人&萱草",
		            "album":"莫萱日记 2015合辑",
		            "formats":"WMA96|WMA128|MP3128|MP3192|MP3H|AAC48"
		        }
		    ]
		}

* 分析:
    * `ret` : 状态码，成功时为`OK`失败时为`end gt total`
    * `total` : 栏目数据总数
    * `musiclist` : 音乐数组
        * `yr` : 节目的日期
        * `musicrid` : 音乐的ID，获取音频数据的时候要用
        * `name` : 节目名称
        * `artist` : 主持人
        * `album` :  所属专辑
        * `formats` : 音质种类

### 2. 获取音频地址
* URL: `http://antiserver.kuwo.cn/anti.s?type=convert_url&rid=MUSIC_6559980&format=mp3&response=url`
* 参数:
    * `rid` : 上一步中的`musicrid`,但要在前面加上`music_`,注意有下划线,对大小写不敏感
    * `format` : 音频音频格式,目前已知候选值：`mp3`,`wma`,`aac`
* 响应实例:

         http://rg01.sycdn.kuwo.cn/4c68c9faa19f7eabcda06871db47fca7/5602ba83/resource/m1/40/63/1915016500.wma



