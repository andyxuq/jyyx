# JYYX API设计

## 1 数据结构

### 1.1 图片(JyPic)
| 属性名      | 属性值 | 示例             | 备注               |
|:------------|:-------|:-----------------|:-------------------|
| id          | int    | 2                | 图片ID             |
| createTime  | long   | 1692039498       | 图片创建时间       |
| updatedTime | long   | 169290230        | 图片更新时间       |
| picLength   | int    | 2342             | 图片大小           |
| picCode     | String | 'home_header'    | 图片类型           |
| referId     | int    | 2                | 图片所关联的资源ID |
| orderCode   | int    | 2                | 排序号             |
| picPath     | String | http:/xxxx/1.png | 图片路径           |

### 1.2 产品(JyProduct)
| 属性名 | 属性值 | 示例 | 备注   |
|:------------|:--------------|:----------------|:-------------|
| id          | int           | 2               | 产品ID       |
| createTime  | long          | 1692023424      | 创建时间     |
| updatedTime | long          | 1692023424      | 更新时间     |
| productName | String        | "欧博特"        | 产品名称     |
| productDesc | String        | "高大上"        | 产品描述     |
| productLink | String        | "http:/xxx/xxx" | 产品链接     |
| pics        | `List<JyPic>` |                 | 产品图片列表 |
| productCategorys| `List<JyProductCategory>`|| 产品分类|

### 1.3 产品分类（JyProductCategory)
| 属性名 | 属性值 | 示例 | 备注   |
|:-------------|:-------|:-----------|:-----------|
| id           | int    | 2          | 分类ID     |
| createTime   | long   | 1692023424 | 创建时间   |
| updatedTime  | long   | 1692023424 | 更新时间   |
| categoryName | String | "灯饰照明" | 分类名字   |
| orderCode    | int    | 3          | 分类排序号 |
| parentId     | int    | 3          | 父分类ID   |
| childList | `List<JyProductCategory>` | | 子分类列表|

### 1.4 案例分类（JyCaseCategory)
| 属性名 | 属性值 | 示例 | 备注   |
|:-------------|:-------|:-----------|:-----------|
| id           | int    | 2          | 分类ID     |
| createTime   | long   | 1692023424 | 创建时间   |
| updatedTime  | long   | 1692023424 | 更新时间   |
| categoryName | String | "装修风格" | 分类名字   |
| orderCode    | int    | 3          | 分类排序号 |
| parentId     | int    | 3          | 父分类ID   |
| childList | `List<JyProductCategory>` | | 子分类列表|

### 1.5 装修案例(JyCase)
| 属性名 | 属性值 | 示例 | 备注   |
|:------------|:--------------|:-----------|:---------|
| id          | int           | 2          | 分类ID   |
| createTime  | long          | 1692023424 | 创建时间 |
| updatedTime | long          | 1692023424 | 更新时间 |
| caseName    | String        | "时尚个性" | 案例名字 |
| caseDesc    | String        | "案例描述" | 案例描述 |
| case_price  | Double        | 2342.3     | 案例金额 |
| caseTips    | String        | "案例主题" | 案例主题 |
| pics        | `List<JyPic>` |            | 案例图片 |
| caseCategorys| `List<JyCateCategory>`| | 案例分类列表|

### 1.6 资讯分类（JyMsgCategory)
| 属性名 | 属性值 | 示例 | 备注   |
|:-------------|:-------|:-----------|:---------|
| id           | int    | 2          | 分类ID   |
| createTime   | long   | 1692023424 | 创建时间 |
| updatedTime  | long   | 1692023424 | 更新时间 |
| categoryName | String | "家装常识" | 分类名   |
| orderCode | int | 3 | 分类排序号 |

### 1.7 资讯（JyMsg)
| 属性名 | 属性值 | 示例 | 备注   |
|:------------|:-------|:-----------|:-----------|
| id          | int    | 2          | 分类ID     |
| createTime  | long   | 1692023424 | 创建时间   |
| updatedTime | long   | 1692023424 | 更新时间   |
| msgTitle    | String | "资讯标题" | 资讯标题   |
| msgContent  | String | "资讯内容" | 资讯内容   |
| orderCode   | int    | 3          | 资讯分类   |
| categroyId  | int    | 2          | 资讯分类ID |
| visitCount  | int    | 3          | 访问次数   |
| msgDesc     | String | "资讯简介" | 资讯简介   |

### 1.8 后台管理用户信息(JyUser)
| 属性名 | 属性值 | 示例 | 备注   |
|:------------|:-------|:-----------|:---------|
| id          | int    | 2          | 分类ID   |
| createTime  | long   | 1692023424 | 创建时间 |
| updatedTime | long   | 1692023424 | 更新时间 |
| userName    | String | "张三"     | 用户名   |
| loginName   | String | aaa        | 登录账号 |
| loginPwd    | String | xbbb       | 登录密码 |
| userPhone   | String | 1920202001 | 用户手机 |
| userEmail| String | sss@xxx.com| 用户邮箱|

## 2 API接口

### 2.1 接口返回规范

api接口全部返回json格式数据，非分页数据结构如下：

```json
  {
    "code":0, //code为0时表示请求成功，非0则表示请求失败
    "msg":"", //code非0时展示错误信息，否则固定为"SUCCESS"
    "data":{}, //各接口返回的自定义数据结构
    "user":{} //当前操作的用户信息
  }
```

分页数据结构如下：
```json
  {
    "code":0, //code为0时表示请求成功，非0则表示请求失败
    "msg":"", //code非0时展示错误信息，否则固定为"SUCCESS"
    "data":{ //各接口返回的自定义数据结构
        "total":2, //总页数
        "pageRow":50, //每页显示的条数
        "pageData":{} //各接口返回的自定义数据结构
    },
    "user":{} //当前操作的用户信息
  }
```

### 2.2 用户相关接口

#### 2.2.1 添加用户接口
* URL: `/api/user/add`
* METHOD: `POST`
* 请求参数: 字段意思参照`1.8 用户信息(JyUser)`
```json
{
  "userName":"aaa",
  "loginName":"xxxx",
  "loginPwd":"bbbb",
  "userPhone":"19222222",
  "userEmail":"aaa@xxx.com"
}
```
* 返回数据: `data`字段 无

#### 2.2.2 修改用户
* URL: `/api/user/modify/{userId}`
* METHOD: `POST`
* 请求参数: 同2.2 添加用户参数一致
* 返回数据: `data`字段 无

#### 2.2.3 查询用户
* URL: `/api/user/get?page=1&pageRow=10`
* METHOD: `POST`
* 请求参数: 字段意思参照`1.8 用户信息(JyUser)`
```json
{
  "id":3, //用户ID，根据ID精确匹配
  "userName":"aaa", //用户名，根据名字模糊匹配
  "loginName":"" //登录名，根据登录名模糊匹配 
}
```
* 返回数据: `data`字段 当前查询到的`List<JyUser>`对象
* PS: 查询的URL参数`page`和`pageRow`可选，不传则表示不查询分页数据


#### 2.2.4 删除用户
* URL: `/api/user/delete/{userId}`
* METHOD: `POST`
* 请求参数: `url`中的`userId`
* 返回数据: `data`字段 无

### 2.3 产品相关接口

#### 2.3.1 增加产品分类
* URL: `/api/product/category/add`
* METHOD: `POST`
* 请求参数: 字段意思参照`1.3 产品分类（JyProductCategory)`
```json
{
  "categoryName":"好分类",
  "parentId":"父分类ID",  //父分类ID，顶层分类则parentId为0
  "orderCode":"排序号"   
}
```
* 返回数据: `data`字段 无

#### 2.3.2 修改产品分类
* URL: `/api/product/category/modify/{categoryId}`
* METHOD: `POST`
* 请求参数: 字段意思参照`1.3 产品分类（JyProductCategory)`
```json
{
  "categoryName":"好分类",
  "parentId":"父分类ID",  //父分类ID，顶层分类则parentId为0
  "orderCode":"排序号"   
}
```

#### 2.3.3 批量更改分类排序号接口
* URL: `/api/product/category/modifyOrder`
* METHOD: `POST`
* 请求参数：
```json
{
  "1":33, //key为资源ID， value为新的排序号
  "2":44
}
```
* 返回数据: `data`字段 无

#### 2.3.4 查询产品分类
* URL: `/api/product/category/get`
* METHOD: `POST`
* 请求参数:
```json
{
  "id":1, //产品分类ID，精确匹配
  "categoryName":"aaa", //分类名，模糊匹配
  "parentId":33 //父分类ID
}
```
* 返回数据: `data`字段 `List<JyProductCategory>`

#### 2.3.5 查询产品分类详情
* URL: `/api/product/category/get/{categoryId}`
* METHOD: `GET`
* 请求参数: `url`中的`categoryId`
* 返回数据: `data`字段 `JyProductCategory`

#### 2.3.5 删除分类
* URL: `/api/product/category/delete/{categoryId}`
* METHOD: `POST`
* 请求参数: `url`中的`categoryId`
* 返回数据: `data`字段 无

#### 2.3.6 添加产品
* URL: `/api/product/add`
* METHOD: `POST`
* 请求参数: 字段意思参照 `1.2 产品(JyProduct)`
```json
{
  "productName":"产品名",
  "productDesc":"产品描述",
  "productLink":"http://xxxxxx", //产品链接
  "productCategorys":[
    {
      "categoryId":2, //产品分类ID
      "orderCode":3 //排序号
    },
    {
      "categoryId":3, //产品分类ID
      "orderCode":4 //排序号
    }
  ]
}
```
* 返回数据: `data`字段 无

#### 2.3.7 修改产品
* URL: `/api/product/modify/{productId}`
* METHOD: `POST`
* 请求参数:
```json
{
  "productName":"产品名",
  "productDesc":"产品描述",
  "productLink":"http://xxxxxx", //产品链接
  "productCategorys":[
    {
      "categoryId":2, //产品分类ID
      "orderCode":3 //排序号
    },
    {
      "categoryId":3, //产品分类ID
      "orderCode":4 //排序号
    }
  ]
}
```

#### 2.3.8 查询产品
* URL: `/api/product/get&page=1&pageRow=10`
* METHOD: `POST`
* 请求参数:
```json
{
  "id":2, //产品分类，精确匹配
  "productName":"aa", //产品名，模糊匹配
  "productCategorys":[
    {
      "categoryId":2 //产品分类ID
    },
    {
      "categoryId":3 //产品分类ID
    }
  ]
}
```
* 返回结果: `data`字段 `List<Product>`对象
* PS: 查询的URL参数`page`和`pageRow`可选，不传则表示不查询分页数据

#### 2.3.9 查询产品详情
* URL: `/api/product/get/{productId}`
* METHOD: `GET`
* 请求参数: `url`中的`productId`
* 返回结果: `data`字段 `Product`对象

#### 2.3.9 删除产品
* URL: `/api/product/delete/{productId}`
* METHOD: `POST`
* 请求参数： `url`中的`productId`
* 返回数据: `data`字段 无

### 2.4 装修案例相关接口

#### 2.4.1 增加案例分类
* URL: `/api/case/category/add`
* METHOD: `POST`
* 请求参数: 字段意思参照`案例分类（JyCaseCategory)`
```json
{
  "categoryName":"好分类",
  "parentId":"父分类ID",  //父分类ID，顶层分类则parentId为0
  "orderCode":"排序号"   
}
```
* 返回数据: `data`字段 无

#### 2.4.2 修改案例分类
* URL: `/api/case/category/modify/{categoryId}`
* METHOD: `POST`
* 请求参数: 字段意思参照`案例分类（JyCaseCategory)`
```json
{
  "categoryName":"好分类",
  "parentId":"父分类ID",  //父分类ID，顶层分类则parentId为0
  "orderCode":"排序号"   
}
```

#### 2.4.3 批量更改分类排序号接口
* URL: `/api/case/category/modifyOrder`
* METHOD: `POST`
* 请求参数：
```json
{
  "1":33, //key为资源ID， value为新的排序号
  "2":44
}
```
* 返回数据: `data`字段 无

#### 2.4.4 查询案例分类
* URL: `/api/case/category/get`
* METHOD: `POST`
* 请求参数:
```json
{
  "id":1, //产品分类ID，精确匹配
  "categoryName":"aaa", //分类名，模糊匹配
  "parentId":33 //父分类ID
}
```
* 返回数据: `data`字段 `List<JyCaseCategory>`

#### 2.4.5 查询案例分类详情
* URL: `/api/case/category/get/{categoryId}`
* METHOD: `GET`
* 请求参数: `url`中的`categoryId`
* 返回数据: `data`字段 `JyCaseCategory`


#### 2.4.5 删除分类
* URL: `/api/case/category/delete/{categoryId}`
* METHOD: `POST`
* 请求参数: `url`中的`categoryId`
* 返回数据: `data`字段 无

#### 2.4.6 添加案例
* URL: `/api/case/add`
* METHOD: `POST`
* 请求参数: 字段意思参照 `装修案例(JyCase)`
```json
{
  "caseName":"案例名",
  "caseDesc":"案例描述",
  "casePrice": 23.4, //案例价格
  "caseTips": "好主题", //案例主题
  "caseCategorys":[
    {
      "categoryId":2, //案例分类ID
      "orderCode":3 //排序号
    },
    {
      "categoryId":3, //案例分类ID
      "orderCode":4 //排序号
    }
  ]
}
```
* 返回数据: `data`字段 无

#### 2.4.7 修改案例
* URL: `/api/case/modify/{caseId}`
* METHOD: `POST`
* 请求参数:
```json
{
  "caseName":"案例名",
  "caseDesc":"案例描述",
  "casePrice": 23.4, //案例价格
  "caseTips": "好主题", //案例主题
  "caseCategorys":[
    {
      "categoryId":2, //案例分类ID
      "orderCode":3 //排序号
    },
    {
      "categoryId":3, //案例分类ID
      "orderCode":4 //排序号
    }
  ]
}
```

#### 2.4.8 查询案例
* URL: `/api/case/get?page=1&pageRow=10`
* METHOD: `POST`
* 请求参数:
```json
{
  "id":2, //案例分类，精确匹配
  "caseName":"aa", //案例名，模糊匹配
  "caseCategorys":[
    {
      "categoryId":2 //案例分类ID
    },
    {
      "categoryId":3 //案例分类ID
    }
  ]
}
```
* 返回结果: `data`字段 `List<JyCase>`对象
* PS: 查询的URL参数`page`和`pageRow`可选，不传则表示不查询分页数据

#### 2.4.9 查询案例详情
* URL: `/api/case/get/{caseId}`
* METHOD: `GET`
* 请求参数:
* 返回结果: `data`字段 `JyCase`对象

#### 2.4.9 删除案例
* URL: `/api/case/delete/{caseId}`
* METHOD: `POST`
* 请求参数： `url`中的`caseId`
* 返回数据: `data`字段 无

### 2.5 资讯相关接口

#### 2.5.1 增加资讯分类
* URL: `/api/msg/category/add`
* METHOD: `POST`
* 请求参数: 字段意思参照`资讯分类（JyMsgCategory)`
```json
{
  "categoryName":"好分类",
  "orderCode":"排序号"   
}
```
* 返回数据: `data`字段 无

#### 2.5.2 修改资讯分类
* URL: `/api/msg/category/modify/{categoryId}`
* METHOD: `POST`
* 请求参数: 字段意思参照`资讯分类（JyMsgCategory)`
```json
{
  "categoryName":"好分类",
  "orderCode":"排序号"   
}
```

#### 2.5.3 批量更改分类排序号接口
* URL: `/api/msg/category/modifyOrder`
* METHOD: `POST`
* 请求参数：
```json
{
  "1":33, //key为资源ID， value为新的排序号
  "2":44
}
```
* 返回数据: `data`字段 无

#### 2.5.4 查询资讯分类
* URL: `/api/msg/category/get`
* METHOD: `POST`
* 请求参数:
```json
{
  "id":1, //分类ID，精确匹配
  "categoryName":"aaa" //分类名，模糊匹配  
}
```
* 返回数据: `data`字段 `List<JyMsgCategory>`

#### 2.5.5 查询资讯分类详情
* URL: `/api/msg/category/get/{categoryId}`
* METHOD: `GET`
* 请求参数: `url`中的`categoryId`
* 返回数据: `data`字段 `JyMsgCategory`

#### 2.5.5 删除分类
* URL: `/api/msg/category/delete/{categoryId}`
* METHOD: `POST`
* 请求参数: `url`中的`categoryId`
* 返回数据: `data`字段 无

#### 2.5.6 增加资讯
* URL: `/api/msg/add`
* METHOD: `POST`
* 请求参数:
```json
{
  "msgTitle":"资讯1", //资讯标题
  "msgContent":"资讯内容", //咨询内容
  "orderCode":3, //资讯排序号
  "categoryId":3, //资讯分类ID
}
```
* 返回数据: `data`字段 无

#### 2.5.7 修改资讯
* URL: `/api/msg/modify/{msgId}`
* METHOD: `POST`,
* 请求参数:
```json
{
  "msgTitle":"资讯1", //资讯标题
  "msgContent":"资讯内容", //咨询内容
  "orderCode":3, //资讯排序号
  "categoryId":3, //资讯分类ID
}
```
* 返回数据: `data`字段 无

#### 2.5.8 批量修改资讯排序
* URL: `/api/msg/modifyOrder`
* METHOD: `POST`
* 请求参数:
```json
{
  "1":33, //key为资源ID， value为新的排序号
  "2":44
}
```
* 返回数据: `data`字段 无

#### 2.5.9 查询资讯
* URL: `/api/msg/get?page=1&pageRow=10`
* METHOD: `POST`
* 请求参数:
```json
{
  "id":3, //资讯ID,精确匹配
  "msgTitle":"xxx", //资讯标题，模糊匹配
  "categoryId":3 //分类ID
}
```
* 返回数据: `data`字段 `List<JyMsg>`对象
* PS: 查询的URL参数`page`和`pageRow`可选，不传则表示不查询分页数据

#### 2.5.10 查询资讯详情
* URL: `/api/msg/get/{msgId}`
* METHOD: `GET`
* 请求参数： `url`中的`msgId`
* 返回数据: `data`字段 `JyMsg`对象

#### 2.5.11 删除资讯
* URL: `/api/msg/delete/{msgId}`
* METHOD: `POST`
* 请求参数： `url`中的`msgId`
* 返回数据: `data`字段 无

### 2.6 图片相关接口

#### 2.6.1 添加图片
* URL: `/api/pic/add`
* METHOD: `POST`
* 请求参数: 字段意思参照 `图片(JyPic)`
```json
{
  "picCode":"aaa", //图片类型code
  "referId":0, //关联资源ID，为0则表示不关联资源
  "orderCode":3, //排序号
  "picPath":"xxxx" //图片路径
}
```
* 返回数据: `data`字段 无

#### 2.6.2 修改图片
* URL: `/api/pic/modify/{picId}`
* METHOD: `POST`
* 请求参数:
```json
{
  "picCode":"aaa", //图片类型code
  "referId":0, //关联资源ID，为0则表示不关联资源
  "orderCode":3, //排序号
  "picPath":"xxxx" //图片路径
}
```
* 返回数据: `data`字段 无

#### 2.6.3 批量修改该图片排序号
* URL: `/api/pic/modifyOrder`
* METHOD: `POST`
* 请求参数：
```json
{
  "1":33, //key为资源ID， value为新的排序号
  "2":44
}
```

#### 2.6.4 查询图片
* URL: `/api/pic/get?page=1&pageRow=50`
* METHOD: `POST`
* 请求参数:
```json
{
  "picCode":"xxx", //图片类型
  "referId":3 //对应资源ID  
}
```
* 返回数据: `data`字段 `List<JyPic>`对象
* PS: 查询的URL参数`page`和`pageRow`可选，不传则表示不查询分页数据

#### 2.6.5 删除图片
* URL: `/api/pic/delete/{picId}`
* METHOD: `POST`
* 请求参数： `url`中的`picId`
* 返回数据: `data`字段 无

#### 2.6.6 查询图片详情
* URL: `/api/pic/get/{picId}`
* METHOD: `GET`
* 请求参数： `url`中的`picId`
* 返回数据: `data`字段 `JyPic`对象
