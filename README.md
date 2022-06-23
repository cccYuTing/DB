<h1 align="center">Package Delivery Company </h1>
<img src="https://img.shields.io/badge/made%20by-Ching Yi-blue.svg" >
<img src="https://img.shields.io/badge/made%20by-Yu Ting-blue.svg" >

## describe
- Keep track of packages shipped and their customers.


## usage
#### 選擇登入身份
- client / Manager

![選擇登入身份](https://img.onl/p2QyRc)

### Client
---
(1) client -> 選擇功能
-  Order summary / Meter pricing

![選擇功能](https://img.onl/h9tHM8)

(2) client / Order summary -> 包裹進度查詢
- 輸入包裹ID即可查詢包裹進度

![包裹進度查詢](https://img.onl/WtlWPi)

(3) client / Meter pricing -> 包裹運費試算
- 輸入包裹資訊後可試算運費

![運費試算](https://img.onl/ufFou3)

### Manager 
---
- 輸入SQL語法可查詢（以下會有幾個範例）

![選擇功能](https://img.onl/FlcVmM)

---
<h3 align="center">Example</h3>

---
 - 假設卡車 8879 在一次碰撞中被毀。查找事故發生時在那輛卡車上有包裹的所有客戶。
```sql
SELECT p.client_id,p.name 
FROM delivery_log as dinner join package_overall as p on p.package_id=d.package_id
where d.trans_s_id=(SELECT s.schedule_id 
                    FROM schedule as s
                    where s.site_id='8779' and s.start_time='2022-01-01 17:00:00')
```


 - 查找事故發生前該卡車最後一次成功交付。
```sql
SELECT * FROM delivery_log as d
where d.trans_s_id=(SELECT s.schedule_id 
 FROM schedule as s
 where s.site_id='8779' and s.start_time='2022-01-01 17:00:00')
order by update_time desc
limit 1;
```


 - 查找過去一年運送包裹最多的客戶。
```sql
SELECT client_id as c_id,name as name, count(*) as count FROM package_overall
group by client_id
order by count desc
limit 1
```


 - 找出過去一年在運費上花費最多的客戶。
```sql
SELECT client_id as c_id,name as name, sum(price) as sum FROM package_overall
group by client_id
order by sum desc
limit 1
```


 - 找出未在承諾時間內送達的包裹。
```sql
SELECT * FROM package_overall
where ontime_ornot='false'
```

## use
- Java
- MySQLWorkBench


## java程式碼

|.java                 | 內容           |
|:-------------        |:-------------  |
| GuiAddPackage.java   | 加入包裹       |
| GuiClient.java       | Client功能選擇 |  
| GuiFrontPage.java    | 首頁           |
| GuiManager.java      |管理員查詢      |  
| GuiMeterPricing.java |價格試算        |
| GuiOrderSummary.java |客戶查詢包裹進度|
| NewRunDelivery.java  |安排運送        |
| NewShiftSchedule.java|交通工具排班表  |
| TestMain.java        |主程式          |
| UpdatePackage.java   |更新包裹狀態    |



---
